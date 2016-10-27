T=int(raw_input())
for iCase in xrange(T):
  n=int(raw_input())
  st=raw_input()
  isplayful=True
  for i in xrange(1,len(st)):
  	if abs(ord(st[i-1])-ord(st[i])) not in [n,26-n]:
  	  isplayful=False
  print "YES" if isplayful else "NO"
