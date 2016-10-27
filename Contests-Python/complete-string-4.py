#https://www.hackerearth.com/problem/algorithm/complete-string-4/

N = int(input())
for num in range(N):
    d = set()
    str = input().lower()
    for ch in str:
        d.add(ch)
        
    v = False
    char = 'a'
    
    for i in range(26):
        char = chr(ord('a') + i)
        if char not in d:
            v = True
            break
            
    if v == True:
        print('NO')
    else:
        print('YES')
    num += 1

			
