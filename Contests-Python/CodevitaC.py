__author__ = 'Shin'
import collections

def OrderedSet(alist):

    """ Creates an ordered set from a list of tuples or other hashable items """

    mmap = {} # implements hashed lookup

    oset = [] # storage for set

    for item in alist:

            #Save unique items in input order

            if item not in mmap:

                    mmap[item] = 1

                    oset.append(item)

    return oset

def ero(n):
    multiples = set()
    for i in range(2, n+1):
        if i not in multiples:
            yield i
            multiples.update(range(i*i, n+1, i))

n = 1200000000
no = 0

prime = OrderedSet(ero(n))
sum = 0
count = 0
finCount = 0
for i in prime:
	sum += i
	finCount += 1
	if sum in prime and finCount > 1:
		count += 1
print(count)
