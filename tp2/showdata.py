import random
from itertools import count
import pandas as pd
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation

plt.style.use('fivethirtyeight')

x_vals = []
y_vals = []

index = count()
plt.figure(figsize=(10, 10), dpi=150)

def animate(i):
    data = pd.read_csv('data.csv')
    x = data['generation']
    y1 = data['maxfitness']
    y2 = data['minfitness']
    y3 = data['mean']
    y4 = data['std']

    # plt.cla()

    plt.plot(x, y1, label='máximo', color='C1')
    plt.plot(x, y2, label='mínimo', color='C3')
    plt.errorbar(x, y3, y4, color='C2', ecolor='wheat', elinewidth=5, label='media')

    plt.legend(loc='lower right')
    # plt.tight_layout()


# ani = FuncAnimation(plt.gcf(), animate, interval=1000) #0.1 seg
animate(1)
plt.xlabel('Generación')
plt.ylim(bottom=0,top=35)
plt.ylabel('Fitness')
# plt.tight_layout()
plt.show()