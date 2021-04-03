import random
from itertools import count
import pandas as pd
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation

plt.style.use('fivethirtyeight')

x_vals = []
y_vals = []

index = count()


def animate(i):
    data = pd.read_csv('data.csv')
    x = data['generation']
    y1 = data['maxfitness']
    y2 = data['minfitness']
    ##y3 = data['promfitness']

    plt.cla()

    plt.plot(x, y1, label='max Fitness')
    plt.plot(x, y2, label='min Fitness')
   # plt.plot(x, y3, label='prom Fitness')

    plt.legend(loc='upper left')
    plt.tight_layout()


ani = FuncAnimation(plt.gcf(), animate, interval=100) #0.1 seg

plt.tight_layout()
plt.show()