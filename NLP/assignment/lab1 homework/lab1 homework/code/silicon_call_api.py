from openai import OpenAI
import requests
from tqdm import tqdm
import asyncio
import aiohttp
from tqdm.asyncio import tqdm_asyncio


class AsyncOpenAI:
    def __init__(self, api_key, base_url="https://api.siliconflow.com/v1/chat/completions"):
        self.api_key = api_key
        self.base_url = base_url
        
    def one_chat(self, sys_msg, user_msg, model="deepseek-ai/DeepSeek-V3"):
        url = self.base_url
        payload = {
            "model": model,
            "messages": [
                {
                    "role": "system",
                    "content": sys_msg,
                    "role": "user",
                    "content": user_msg
                }
            ],
            "stream": False,
            "max_tokens": 4096,
            "stop": ["null"],
            "temperature": 0.3,
            "top_p": 0.7,
            "top_k": 50,
            "frequency_penalty": 0.5,
            "n": 1,
            "response_format": {"type": "text"},
            # "tools": [
            #     {
            #         "type": "function",
            #         "function": {
            #             "description": "<string>",
            #             "name": "<string>",
            #             "parameters": {},
            #             "strict": False
            #         }
            #     }
            # ]
        }
        headers = {
            "Authorization": f"Bearer {self.api_key}",
            "Content-Type": "application/json"
        }

        response = requests.request("POST", url, json=payload, headers=headers)
        return response.text
    
    def batch_chat(self, sys_msgs, user_msgs, model="deepseek-ai/DeepSeek-V3", max_concurrent=5):

        url = self.base_url

        async def call_api(session, sem, s_msg, u_msg):
            async with sem:
                payload = {
                    "model": model,
                    "messages": [
                        {
                            "role": "system",
                            "content": s_msg,
                            "role": "user",
                            "content": u_msg
                        }
                    ],
                    "stream": False,
                    "max_tokens": 4096,
                    "stop": ["null"],
                    "temperature": 0.3,
                    "top_p": 0.7,
                    "top_k": 50,
                    "frequency_penalty": 0.5,
                    "n": 1,
                    "response_format": {"type": "text"},
                }
                headers = {
                    "Authorization": f"Bearer {self.api_key}",
                    "Content-Type": "application/json"
                }
                async with session.post(url, json=payload, headers=headers) as resp:
                    return await resp.text()

        async def main():
            results = []
            sem = asyncio.Semaphore(max_concurrent)
            async with aiohttp.ClientSession() as session:
                tasks = [call_api(session, sem, s, u) for s, u in zip(sys_msgs, user_msgs)]
                for coro in tqdm_asyncio.as_completed(tasks, desc="Sending requests", total=len(tasks)):
                    results.append(await coro)
            return results

        return asyncio.run(main())
              
            

if __name__ == '__main__':
    api_key = "your_api_key"
    # base_url = "https://api.siliconflow.com/v1/chat/completions"
    client = AsyncOpenAI(api_key=api_key)

    sys_msg = "answer the question in English."
    user_msg = "中国首都是哪里?"

    response = client.one_chat(sys_msg, user_msg)
    print(response)    