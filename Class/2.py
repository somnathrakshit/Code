t=int(raw_input())
for i in range(t):
    list1 = map(int,raw_input().split())
    list2 = map(int,raw_input().split())
    common = list(set(list1).intersection(set(list2)))
    print common
