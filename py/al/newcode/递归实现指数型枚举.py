def dsf(cc, ll):
    if n == ll:
        print(''.join(cc))
    else:
        dsf(cc, ll + 1)
        dsf(cc + [str(ll + 1)], ll + 1)


n = int(input())
dsf([], 0)
