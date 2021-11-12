'''
Wordchains etude2

@author Jacob Gear, Timothy Varsanyi.
'''
import sys, random
from numpy import *
all_inputs = []
chains = set()
stdin = []
vertices = []

'''
A class to initialise words as nodes.
'''
class vertex:
    def __init__(self, value, dis, pred):
        self.value = value
        self.dis = dis
        self.pred = pred

'''
A process for bfs to print out the word chains by recursing 
through the words nodes predecessors.
'''
def bfs_print(gra):
    target = None
    stdout = []
    for i in range(len(gra)):
        if stdin[1] == gra[i].value:
            target = gra[i]
    if(target != None):
        while(target.pred != None):
            stdout.append(target.value)
            target = target.pred
        stdout.append(stdin[0])
        stdout.reverse()
        while(stdout):
            word = stdout.pop(0)
            print(word, end = ' ')
        print('')
    else:
        print(stdin[0], stdin[1], "impossible")

'''
A process for dfs to print out the word chains.
'''
def dfs_print(chain):
    for word in chain:
        print(word, end = ' ')
    print("")

'''
A process to find all possible words one letter off
from the original word.
This process is used for efficiency.
'''
def all_possible(str):
    l = []
    a = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"]
    for i in range(len(str)):
        chars = list(str)
        for j in range(26):
            chars[i] = a[j]
            new_string = "".join(chars)
            l.append(new_string)
    return l

'''
A function bfs that searches for a word chain by using
an algorithm that finds the shortest path to all other nodes.
'''
def bfs():
    v = vertex(stdin[0], 0, None)
    vertices.append(v)
    queue = [v]
    seen = [stdin[0]]

    while(queue):
        curr = queue.pop(0)
        l = all_possible(curr.value)
        for item in l:
            if(curr.value != item and item in chains and item not in seen):
                seen.append(item)
                new_vert = vertex(item, curr.dis+1, curr)
                vertices.append(new_vert)
                queue.append(new_vert)
    bfs_print(vertices)

'''
A function dfs that searches for a word chain by using 
an algorithm that finds a path with the correct amount of
steps dictated by the input.
'''
def dfs():
    stack = []
    stack.append([stdin[0]])
    while(stack):
        curr = stack.pop(-1)
        l = all_possible(curr[-1])
        for word in l:
            if word in chains and word not in curr:
                c = curr.copy()
                if c[-1] == stdin[1] and len(c) == int(stdin[2]):
                    dfs_print(c)
                    return
                c.append(word)
                if len(c) <= int(stdin[2]):
                    stack.append(c)      
    print(stdin[0], stdin[1], stdin[2], "impossible")

'''
A process that converts a 2D array into a 1D array
'''
def convert_1d(arr):
    new = []
    for i in arr:
        for j in i:
            new.append(j)
    return new

'''
A process that checks if a word is one character
different to another word
'''
def one_letter(w1, w2):
    if(len(w1) != len(w2)):
        return False
    else:
        count = 0
        for i in range(len(w1)) :
            if(w1[i] == w2[i]):
                count += 1
        if(count != len(w1)-1):
            return False
    return True

'''
A process that obtains all inputs in lists
'''
def inputs():
    global chains
    global stdin
    global vertices
    for line in sys.stdin:
        if (line.strip()): # Line is not empty
            all_inputs.append(line.split())
        else:
            break
    for line in sys.stdin:
        if (line.strip()): # Line is not empty
            if '\n' in line:
                chains.add(line[:-1])
            else:
                chains.add(line)
    while(all_inputs):
        global stdin 
        stdin = all_inputs.pop(0)
        if len(stdin) > 1 and chains: 
            if len(stdin) == 3:
                if(stdin[2].isalpha() or int(stdin[2]) < 1):
                    print("Error: invalid input")
                else:
                    dfs()
                    stdin = []
                    vertices = []
            else:
                bfs()
                stdin = []
                vertices = []
        else:
            print("Error: invalid input")

inputs()
