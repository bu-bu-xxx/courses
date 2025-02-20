# apply
from google import genai
from google.genai import types
from tqdm import tqdm
import asyncio


class GenminiAPI:
    def __init__(self, api_key, base_url="https://api-sg.moonshot.ai/v1"):
        self.client = genai.Client(api_key=api_key, base_url=base_url)
 
    def one_chat(self, sys_msg, user_msg):
        response = self.client.models.generate_content(
            model="gemini-2.0-flash",
            config=types.GenerateContentConfig(
            # max_output_tokens=500,
            temperature=0.2,
            system_instruction=sys_msg,
            ),
            contents=[user_msg],
        )
        return response.text
    
    async def batch_chat(self, sys_msgs, user_msgs, max_concurrency=5):

        semaphore = asyncio.Semaphore(max_concurrency)

        async def call_api(sys_msg, user_msg):
            async with semaphore:
                response = self.client.models.generate_content(
                    model="gemini-2.0-flash",
                    config=types.GenerateContentConfig(
                    # max_output_tokens=500,
                    temperature=0.2,
                    system_instruction=sys_msg,
                    ),
                    contents=[user_msg],
                )
                return response.text

        tasks = [call_api(s, u) for s, u in zip(sys_msgs, user_msgs)]
        responses = await asyncio.gather(*tasks)
        return responses
    
if __name__ == "__main__":
    pass
