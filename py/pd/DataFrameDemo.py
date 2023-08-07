import numpy as np
import pandas as pd

data = [1, 2, 3, 4, 5]
df = pd.DataFrame(data)
print(df)
# 使用嵌套列表创建 DataFrame 对象：
data = [['Alex', 10], ['Bob', 12], ['Clarke', 13]]
df = pd.DataFrame(data, columns=['Name', 'Age'])
print(df)
# 字典嵌套列表创建
data = {'Name': ['Tom', 'Jack', 'Steve', 'Ricky'], 'Age': [28, 34, 29, 42]}
df = pd.DataFrame(data, index=['rank1', 'rank2', 'rank3', 'rank4'])
print(df)
#
data = [{'a': 1, 'b': 2}, {'a': 5, 'b': 10, 'c': 20}]
df = pd.DataFrame(data)
df1 = pd.DataFrame(data, index=['first', 'second'], columns=['a', 'b'])
df2 = pd.DataFrame(data, index=['first', 'second'], columns=['a', 'b1'])
print(df)
print(df1)
print(df2)

data = {
    'a': pd.Series(np.random.rand(10)),
    'b': pd.Series(np.random.rand(11))
}
df = pd.DataFrame(data)
print(df)
print(df['a'])
df['c'] = pd.Series(np.random.rand(9))
print(df)
df['d'] = df['b'] + df['c']
print(df)
df.insert(1, column='k', value=np.random.rand(df['a'].size))
print(df)
print(df['a'])
print('============del=============')
del df['k']
print(df)
print('===========pop==============')

df.pop('c')
print(df)

print('==========================')
print(df.iloc[2])
print(df[0:6])

df1 = pd.DataFrame(np.random.rand(df.columns.size))
print(df1)
# print(pd.concat(df, df1))

print('================del rows============')
print(df.drop(0))

print('=============fun==================')
print(df.T)
print('================================')
print(df.axes)
print('================================')
print(df.dtypes)
print('================================')

print(df.empty)
print(df.ndim)
print(df.shape)
print(df.size)
print(df.values)
print(df.head(2))
print(df.tail(2))
print('=============shift==========')
print(df.shift(periods=2, axis=1, fill_value=0.111))

print(pd.__version__)
