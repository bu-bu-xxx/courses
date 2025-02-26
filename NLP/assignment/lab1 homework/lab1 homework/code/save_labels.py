import pandas as pd


# save the labels generated by *call_api.py to the specific DataFrame
class Save(object):
    def __init__(self, df: pd.DataFrame):
        assert isinstance(df, pd.DataFrame)
        self.df = df

    def add_labels(self, new_col_name: str, labels: list):
        assert isinstance(new_col_name, str)
        assert isinstance(labels, list)
        assert len(labels) == len(self.df)
        self.df[new_col_name] = labels
        # return self.df
    
    def save_df(self, path: str):
        self.df.to_csv(path, index=False, sep='\t')
        print(f"DataFrame has been saved to {path}")
        