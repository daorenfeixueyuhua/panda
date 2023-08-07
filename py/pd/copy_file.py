import os
from shutil import copy

source = "D:\Game\Steam\steamapps\workshop\content\\431960"
target = 'E:\Steam\steamapps\workshop\content\\431960'

# copy(source, target)
for root, dirs, files in os.walk(source, topdown=False):
    for name in files:
        if name.endswith(".mp4") and os.path.exists(os.path.join(target, name)) == False:
            path = os.path.join(root, name)
            print(path)
            copy(path, target)
            break
