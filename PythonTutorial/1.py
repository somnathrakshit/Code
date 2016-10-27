#code
lim=int(raw_input())
for j in range(lim):
    arr=raw_input().title().split()
    l=len(arr)
    for i in range(l-1):
        print arr[i][0],
    print arr[-1]
