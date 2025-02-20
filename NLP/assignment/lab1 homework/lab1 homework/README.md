# Method 1 (finetuned)

[finetuned code](./assignment-lab1.ipynb)

1. we **finetune** based on bert model and add a classification layer after last_hidden_state.

the output layer is with 5 dim, which stands for 5 categories of sentiment.

2. we **preprocess** the data, and the tokenization rules is:

`<cls> "Phrase" <sep> "whole review" <sep>`

Examples:

`'[CLS]','budget','[SEP]', 'a', 'loud', ',', 'low', '-', 'budget', 'and', 'tired', 'formula', 'film', 'that', 'arrives', 'cloak', '##ed', 'in', 'the', 'eu', '##ph', '##emi', '##sm', '[SEP]'`

the tokenizer splits the word to subwords and add the special tokens `[CLS], [SEP], [PAD]`.

3. **training process:** we set a small learning rate for BERT blocks and a relatively large learning rate for classification layer. But the **performance** is not ideal. Hyper-parameter is shown below:

   ``` 
   batch_size=8
   learning rate:
       {'params': model.bert.parameters(), 'lr': 5e-6},
       {'params': model.classifier.parameters(), 'lr': 5e-5}
   seed=7777
   ```

   

# Method 2 (CoT)

[calling api code](./code/gemini_call_api2.py)

[CoT code](./assignment-lab1.ipynb)

1. we apply **reasoning LLm models** deepseek r1 and deepseek v3, and provide 2 kind of prompt: zero-shot and few-shot. We find that deepseek r1 perform better than deepseek v3 much more and few-shot perform better than zero-shot a bit.

2. our **prompt** is:

   **system prompt:**

   ```
   You are a helpful sentiment analysis expert.
   ```
   **zero-shot user prompt:**
   
   ```
   Your task is to analysis classify movie review phrase which is truncated from a whole movie review sentence. please classify the sentiment of phrase into one of five sentiment categories: negative (0), somewhat negative (1), neutral (2), somewhat positive (3), or positive (4). You should carefully consider the intensity and context of the phrases to determine the most appropriate label.Please show your chain of thinking step by step, and print the result at the end of output with format: Label: \\label{{number}}, Sentiment: \\sentiment{{text}}.
   ```
   **few-shot user prompt:**
   ```
   Task: Classify the sentiment of the truncated phrase (not the full review) into one of five categories:
   0: Negative
   1: Somewhat Negative
   2: Neutral
   3: Somewhat Positive
   4: Positive
   
   Format:
   \\label{{number}}, Sentiment: \\sentiment{{text}}
   
   Examples:
   
   Review: "The movie was a complete disaster. The plot was incoherent, and the acting was terrible."
   Truncated Phrase: "complete disaster"
   Output:
   \\label{0}, Sentiment: \\sentiment{negative}
   
   Review: "The film had a few good moments, but overall it was a bit dull."
   Truncated Phrase: "a bit dull"
   Output:
   \\label{1}, Sentiment: \\sentiment{somewhat negative}
   
   Review: "The cinematography was decent, but the story lacked depth."
   Truncated Phrase: "decent"
   Output:
   \\label{2}, Sentiment: \\sentiment{neutral}
   
   Review: "The acting was surprisingly good, though the script could have been better."
   Truncated Phrase: "surprisingly good"
   Output:
   \\label{3}, Sentiment: \\sentiment{somewhat positive}
   
   Review: "This movie was an absolute masterpiece. The performances were outstanding."
   Truncated Phrase: "absolute masterpiece"
   Output:
   \\label{4}, Sentiment: \\sentiment{positive}
   
   Instructions:
   Focus only on the truncated phrase, not the full review.
   Ignore context outside the phrase (e.g., sarcasm, hyperbole, or mixed sentiments).
   Use the labels and sentiment text exactly as formatted above.
   Think step by step, and answer based on your thinking.
   ```
   

# Result

