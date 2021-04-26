REQUISITOS
------------
El requisito es tener Java 8

CONFIGURACION Y EJECUCION
------------------------
Previo a la ejecución del programa, se debe primero crear un archivo _configuration.properties_ con las configuraciones de la ejecución, que puede ser por ejemplo:

```
#Configuration File

#Paths
inputFile=D:\entrada.txt
outputFile=D:\salida.txt


#Activation Function: linear, sigmoid, sign, tanh
activationFunction=linear

#Training

errorEps=0.1
numberIterations=1000
learningRate=0.05
sameBiasIterations=100
beta=1
momentum=0
layers=1
adaptLR=false
updateLRits=0
LRincrement=0
LRdecrement=0
```
Donde:
* inputPath: La dirección de la entrada del perceptron.
* outputPath: La dirección de la salida deseada del perceptron.
* activationFunction: El algoritmo a utilizar este puede ser:
  * _linear_: Utilizando la funcion linear
  * _sign_: Utilizando la funcion signo
  * _tanh_: Utilizando la funcion tangente hiperbolica
  * _sigmoid_: Utilizando la funcion sigmoid

  

* errorEps: la cantidad maxima de generaciones antes de cortar la ejecucion
* numberIterations: la cantidad maxima de iteraciones o epochs.
* learningRate: el ratio con el que el perceptron aprendera.
* beta: el valor de coma flotante definido dentor de las funciones tanh y sigmoid.
* fitnessMargin: la varianza maxima para considerarse como un mismo fitness entre generacion.
* layers: las cantidades de neuronas por capa, separados por una coma. En caso de uno sin multicapas se define como _1_
* adaptLR: flag que se define como "true" o "false", para activar la ejecucion del eta adaptativo.
* updateLRits: la cantidad de iteraciones con el que se va actulizar con el eta adaptativo.
* LRincrement: es un valor de coma flotante que define la cantidad a incrementar con eta adaptativo.
* LRdecrement: es un valor de coma flotante que define la cantidad a decrementar con eta adaptativo.



Para la ejecucion solamente se debe hacer un:

```java -cp tp3 ar.edu.itba.sia.App```
