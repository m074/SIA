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
  * _ae_: El algoritmo de autoencoder con el dataset de los simbolos y numeros.
  * _dae_: El algoritmo de autoencoder para eliminacion de ruido, utilizando el mismo dataset de simbolos y numeros.
  * _vae_: El algoritmo de autoencoder con Hopfield utilizando el dataset de las caras.



* layers: las cantidades de neuronas por capa, separados por una coma. En caso de uno sin multicapas se define como _1_
* epochs: la cantidad de iteraciones dentro del algoritmo de Oja
* noise: el nivel de ruido de los caracteres dentro del algoritmo de Hopfield. Siendo este un número natural dentro del 0 a 100.



Para la ejecucion solamente se debe hacer un:

```java -jar tp5-1.0-SNAPSHOT.jar```