REQUISITOS
------------
El requisito es tener Java 8

CONFIGURACION Y EJECUCION
------------------------
Previo a la ejecución del programa, se debe primero crear un archivo _configuration.properties_ con las configuraciones de la ejecución, que puede ser por ejemplo:

```
#Configuration File

#Paths
europeFile=normalizedCountries.csv

#Method
algorithm=oja

#Kohonen
startingRadius=1
matrixSize=5
#Oja
eta=0.0001
epochs=1000
#Hopfield
noise=30

```
Donde:
* europeFile: La dirección del archivo de los datos de países de Europa estandarizados.
* algorithm: El algoritmo a utilizar este puede ser:
  * _kohonen_
  * _oja_
  * _hopfield_



* startingRadius: el radio según el algoritmo de Kohonen
* matrixSize: el tamaño de la matriz a mostrar dentro del algoritmo de Kohonen
* eta: el ratio de aprendizaje dentro del algoritmo de Oja
* epochs: la cantidad de iteraciones dentro del algoritmo de Oja
* noise: el nivel de ruido de los caracteres dentro del algoritmo de Hopfield. Siendo este un número natural dentro del 0 a 100.


Para la ejecucion solamente se debe hacer un:

```java -jar tp4-1.0-SNAPSHOT.jar```