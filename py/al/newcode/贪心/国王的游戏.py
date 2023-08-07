class E:
    left = 0
    right = 0
    core = 0

    def __init__(self, _left, _right):
        self.left = _left
        self.right = _right

    def __str__(self):
        return self.left + ' ' + self.right


n = int(input())
srcList = []
n += 1
while n != 0:
    left, right = input().split(' ')
    srcList.append(E(left, right))
    n -= 1

print(srcList)
