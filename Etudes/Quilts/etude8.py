import turtle, sys

from turtle import Screen
x = 500
# Create a new turtle hide it and set its speed
t = turtle.Turtle()
t.hideturtle()
# Creating the screen object
screen = Screen()
# Setting the screen color-mode
screen.colormode(255)
screen.tracer(0)
screen.setup(x,x)

# List to hold the input
stdin = []

# Inital size set
global scale 
scale = 1
size = 1

# Queue to hold the cords
cords = [(0.0,0.0)] 

# Process of drawing the squares
def process(line, size, ex):
    c = 0
    while c < ex:
        curr = []
        curr = cords.pop(0)
        
        x = curr[0] + (-size/2)
        y = curr[1] + (-size/2)
        t.up()
        t.goto(x, y)
        t.down()
        
        t.fillcolor(int(line[1]), int(line[2]), int(line[3]))
        t.pencolor(int(line[1]), int(line[2]), int(line[3]))
        t.begin_fill()
        for i in range(4):
            cords.append(t.position())
            t.forward(size) # Forward turtle by s units
            t.left(90) # Turn turtle by 90 degree
        t.end_fill()
        c += 1

# Method for dealing with input
def inputs(stdin, size):
    ex = 1
    total_size = auto_scale()
    while stdin:
        line = stdin.pop(0)
        size = ((float(line[0])/x)/total_size)*(x-100)
        process(line, size, ex)
        ex *= 4

# method for getting the correct scale
def auto_scale():
    total_size = 0
    i = 0
    while(i < len(stdin)):        
        total_size += float(stdin[i][0])
        i += 1
    return total_size/x

# While theres input, put it in the list and when there is an empty line process the input
for line in sys.stdin:
    if (line.strip()): # Line is not empty
       stdin.append(line.split())
    else:
        inputs(stdin, size)
        screen.update()
        screen.mainloop()
        stdin = []
        cords = [(0.0,0.0)] 
if len(stdin) > 0:
    inputs(stdin, size)
    screen.update()
    screen.mainloop()
    stdin = []
    cords = [(0.0,0.0)] 













