# call the gemini2 api,
# and chat with the model and return the response
from openai import OpenAI
from tqdm import tqdm
import asyncio
 

class GeminiAPI:
    def __init__(self, api_key, base_url="https://api.aigogo.top/v1"):
        self.client = OpenAI(api_key=api_key, base_url=base_url)
 
    def one_chat(self, sys_msg, user_msg):
        completion = self.client.chat.completions.create(
            model="deepseek-r1",
            # model="gemini-2.0-flash-exp",
            messages= [
        {"role": "system", f"content": sys_msg},
        {"role": "user", f"content": user_msg},
        ],
            temperature=0.2,
        )
        # return completion.choices[0].message["content"]
        return completion.choices[0].message.content
    
    async def batch_chat(self, sys_msgs, user_msgs, model='deepseek-v3', max_concurrency=5):
        semaphore = asyncio.Semaphore(max_concurrency)
        async def call_api(sys_msg, user_msg):
            async with semaphore:
                retries = 3
                # check if sys_msg is in [0, 1, 2, 3, 4]
                if sys_msg in ["0", "1", "2", "3", "4"]:
                    # print("111")
                    return '\\label{' + str(sys_msg) +'}'
                
                for attempt in range(retries):
                    try:
                        completion = await asyncio.to_thread(
                            self.client.chat.completions.create,
                            model=model,
                            messages=[
                                {"role": "system", "content": sys_msg},
                                {"role": "user", "content": user_msg},
                            ],
                            temperature=0.2,
                        )
                        return completion.choices[0].message.content
                    except Exception as e:
                        if attempt < retries - 1:
                            await asyncio.sleep(1)  # wait a bit before retrying
                        else:
                            return "error"

        tasks = [call_api(s, u) for s, u in zip(sys_msgs, user_msgs)]
        responses = await asyncio.gather(*tasks)
        return responses
 

if __name__ == "__main__":
    # api_key = 'sk-9p0D6IrT9aHjnDrMgEijENQSbYgtpoShPmhcFV4raQSfCMt7'
    # api_key = 'sk-coGcqvPdNaOCARzoyiyPDUhvFLoe1uiOePtTqtHiNFdNR1dj'
    api_key = 'sk-888vODGT01RPYcrL4MQZOgGXW4DuCqRIm5CwiL2ZiwdCp8yb'
    sys_msg = "answer the question in English."
    user_msg = "中国首都是哪里?"
    sys_msgs = [sys_msg] * 10
    user_msgs = ["中国首都是哪里?"] * 10

    client = GeminiAPI(api_key)
    responses = asyncio.run(client.batch_chat(sys_msgs, user_msgs))
    for res in responses:
        print(res)

    # client = GeminiAPI(api_key)
    # response = client.one_chat(sys_msg, user_msg)
    # print(response)