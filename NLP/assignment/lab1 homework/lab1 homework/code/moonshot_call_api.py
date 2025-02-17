# call the moonshot api,
# and chat with the model and return the response
from openai import OpenAI
from tqdm import tqdm
import asyncio
 

class MoonshotAPI:
    def __init__(self, api_key, base_url="https://api-sg.moonshot.ai/v1"):
        self.client = OpenAI(api_key=api_key, base_url=base_url)
 
    def one_chat(self, sys_msg, user_msg):
        completion = self.client.chat.completions.create(
            model="moonshot-v1-8k",
            messages= [
        {"role": "system", f"content": sys_msg},
        {"role": "user", f"content": user_msg},
        ],
            temperature=0.3,
        )
        # return completion.choices[0].message["content"]
        return completion.choices[0].message.content
    
    async def batch_chat(self, sys_msgs, user_msgs, max_concurrency=5):

        semaphore = asyncio.Semaphore(max_concurrency)

        async def call_api(sys_msg, user_msg):
            async with semaphore:
                completion = await asyncio.to_thread(
                    self.client.chat.completions.create,
                    model="moonshot-v1-32k",
                    messages=[
                        {"role": "system", "content": sys_msg},
                        {"role": "user", "content": user_msg},
                    ],
                    temperature=0.3,
                )
                return completion.choices[0].message.content

        tasks = [call_api(s, u) for s, u in zip(sys_msgs, user_msgs)]
        responses = await asyncio.gather(*tasks)
        return responses
 

if __name__ == "__main__":
    api_key = 'your_api_key'
    sys_msg = "answer the question in English."
    user_msg = "中国首都是哪里?"
    sys_msgs = ["answer the question in English."] * 10
    user_msgs = ["中国首都是哪里?"] * 10

    client = MoonshotAPI(api_key)
    responses = asyncio.run(client.batch_chat(sys_msgs, user_msgs))
    for res in responses:
        print(res)
