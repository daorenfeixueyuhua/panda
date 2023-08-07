import pandas as pd

data = {
    'age': [12, 23, 34],
    'name': ['onw1', 'tome', 'cat']
}

df = pd.DataFrame(data, columns=['name', 'age'])
# df.to_csv('./data/demo1.scv')
df.to_json('./data/demo1.json')
