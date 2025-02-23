import transformers
import torch
import torch.nn as nn
import torch.nn.functional as F
import sys
import os
import numpy as np
import pandas as pd
import re
from gemini_call_api2 import GeminiAPI
import re
from save_labels import Save
from tqdm import tqdm
import asyncio


# get the directory of the current file
base_dir = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

# add code/ to sys.path
sys.path.append(os.path.join(base_dir, 'code'))

# load data from tsv files
data_dir = os.path.join(base_dir, 'data')
train_file = os.path.join(data_dir, 'train_cp.tsv')
test_file = os.path.join(data_dir, 'test.tsv')

train_data = pd.read_csv(train_file, sep='\t', keep_default_na=False)
test_data = pd.read_csv(test_file, sep='\t', keep_default_na=False)

# zero shot response
deepseek_key_file = os.path.join(base_dir, 'api_key', 'deepseek.api.key')
api_key = open(deepseek_key_file, 'r').read()
client = GeminiAPI(api_key)

# generate the query
def generate_msgs(test_data, modify=False, col='Label2'):
    sys_msg = r"You are a helpful sentiment analysis expert."
    phrases = test_data['Phrase'].tolist()
    sentences = test_data['Sentence'].tolist()
    # true_labels = train_data['Sentiment'].tolist()
    user_msgs = phrases.copy()
    # load first part of prompt
    prompt_file = os.path.join(data_dir, 'prompt.txt')
    with open(prompt_file, 'r') as f:
        prompt1 = f.read()
    for i in range(len(user_msgs)):
        user_msgs[i] = prompt1 + "\n" + \
            "Question: " + \
            f"\n Review: \"{sentences[i]}\"" + \
            f"\n Truncated phrase: \"{phrases[i]}\""
        
    sys_msgs = [sys_msg] * len(user_msgs)

    # only modify the label_col that are not -1
    if modify:
        label_col = test_data[col].tolist()
        for i in range(len(label_col)):
            if label_col[i] != -1:
                sys_msgs[i] = str(label_col[i])
                
    return user_msgs, sys_msgs

# hyperparameter
modify = True 
col = 'Label4'
user_msgs, sys_msgs = generate_msgs(test_data, modify=modify, col=col)

# print(user_msgs[1000])
# print(sys_msgs[1000])
# sys.exit()

def seperate(responses):
    # seperate the label from the response
    labels = []
    for response in responses:
        pattern = r'\\label\{(\d)\}'
        match = re.search(pattern, response)
        if match:
            matches = list(re.finditer(pattern, response))
            last_match = matches[-1]
            label = last_match.group(1)
        else:
            label = -1
        labels.append(int(label))
    return labels, responses

def save_labels(labels, col_name, path, df):
    save = Save(df=df)
    save.add_labels(col_name, labels)
    save.save_df(path)

async def get_responses():
    responses = []
    batch_size = 1000 # hyperparameter
    for i in tqdm(range(0, len(test_data), batch_size)):
    # for i in tqdm(range(200, 205, batch_size)):
        batch_responses = await client.batch_chat(
            sys_msgs=sys_msgs[i : i + batch_size],
            user_msgs=user_msgs[i : i + batch_size],
            max_concurrency=70,
            # model="deepseek-r1",
            model="deepseek-v3",
            # model="grok-3-reasoner",
            # model="grok-3",
        )
        responses += batch_responses
    return responses

responses = asyncio.run(get_responses())

print(responses.__len__())
# print(responses[1])
# labels, responses = seperate(responses)
# print(labels)
# sys.exit()

# save responses variable as a file
save_file = os.path.join(data_dir, 'responses.txt')
backup_file = os.path.join(data_dir, 'responses_backup.txt')
with open(save_file, 'w') as f:
    for response in responses:
        f.write(f"{response}\n\n<split>\n\n")
with open(backup_file, 'w') as f:
    for response in responses:
        f.write(f"{response}\n\n<split>\n\n")


labels, responses = seperate(responses)
# reload test_data
test_data = pd.read_csv(test_file, sep='\t', keep_default_na=False)
# save labels
save_labels(labels, 'Label4', test_file, test_data) # hyperparameter
