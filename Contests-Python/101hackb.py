num = int(raw_input())
building_height = map(int, raw_input().split())
m = int(raw_input())
pos_of_lasers = map(int, raw_input().split())
pos_of_lasers.sort()
for i in range(len(pos_of_lasers)):
  start=0; end=0;
  if i == 0:
    start = 0
    end = pos_of_lasers[i] - 1
  else:
    start = pos_of_lasers[i - 1] - 1
    end = pos_of_lasers[i] - 1
  for j in range(start, end):
    building_height[j] = max(min(end - j, building_height[j]), 0)
print sum(building_height)