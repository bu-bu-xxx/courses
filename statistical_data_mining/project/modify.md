下面这个是slice 2 的修改， 酌情修改：

## Main goal of our project

This study analyzes corporate annual financial reports to explore the predictive ability of textual features on future stock price volatility. We evaluate the performance of various textual data representations in a regression task applying the XGBoost model.

## Conclusion

In the representation extracted from textual datasets, we propose a novel representation that combines TF-IDF with LongFormer. The TF-IDF features effectively captures significant information from specific financial terminology but remains context-agnostic, whereas LongFormer provides a context-based representation. This combined approach demonstrates enhanced performance. 

-----------------------------------

图我等一下生成一个，你的图不是AI论文里面的通用格式，有点业余

1. ppt slice 8

**Fine-Tuning Step**

```mermaid
graph BT
A(Text) --> B[LongFormer]
B --> C("[CLS]")
D("logval+12") --> E[\Concat/]
C --> E
E --> F[MLP]
F --> G("Predicted logval+12")
H("True logval+12") --> I(Loss)
G --> I
I -.-> B
I -.-> F
```

```mermaid
graph LR
A(Text) --> B[LongFormer]
B --> C("[CLS]")
D("logval+12") --> E[\Concat/]
C --> E
E --> F[MLP]
F --> G("Predicted logval+12")
H("True logval+12") --> I(Loss)
G --> I
I -.-> B
I -.-> F
```

二选一，方向不同，内容完全一样





2. ppt slice P9

**Prediction Step based on XGBoost**

```mermaid
graph BT
A(Text) --> B2[Fine-Tuned LongFormer]
A --> B1[TF-IDF]
B1 --> C1[\concat/]
B2 --> C1
C1 --> C(representation)
D("logval-12") --> E[\Concat/]
C --> E

E --> G[XGBoost Model]
G --> H("Predicted log+12")
```

```mermaid
graph LR
A(Text) --> B2[Fine-Tuned LongFormer]
A --> B1[TF-IDF]
B1 --> C1[\concat/]
B2 --> C1
C1 --> C(representation)
D("logval-12") --> E[\Concat/]
C --> E

E --> G[XGBoost Model]
G --> H("Predicted log+12")
```



-----------------------------------



