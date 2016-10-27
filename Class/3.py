t=int(raw_input())
for num in range(t):
    str=raw_input()
    sum=0
    for char in str:
        if char.isdigit()==True:
            sum=sum+int(char)
    print sum
