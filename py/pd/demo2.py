import pandas as pd

file = 'D:\Projects\Founder\JWZH\doc\BUG记录表-合并-日期V1.0 - 20221109.xlsx'
df = pd.read_excel(file)

result = df['关闭']
print(result)
