import numpy as np
import pandas as pd

data = np.array(['xx', 'xx2', 'xxx3', None])

s1 = pd.Series(data)
print(s1)

d2 = {
    'a': 0,
    'b': 1,
    'c': 2
}
s2 = pd.Series(d2)
print(s2)
print(s1[0], s2['a'], s1[:2])

s3 = pd.Series(np.random.rand(100))

print(s3.head(3))
print(s3.tail(3))
print(pd.isnull(data))
