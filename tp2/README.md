COMPILACION
------------
Para la compilación al ser un proyecto de maven con su correspondiente _pom.xml_, solamente es necesario pararse en al carpeta _tp2_ y ejecutar un _mvn package_ donde generará un archivo jar del programa
* El único requisito es tener Java 8

CONFIGURACION Y EJECUCION
------------------------
Previo a la ejecución del programa, se debe primero crear un archivo _configuration.properties_ con las configuraciones de la ejecución, que puede ser por ejemplo:

```
weaponPath=armas.tsv
bootsPath=botas.tsv
helmetPath=cascos.tsv
glovesPath=guantes.tsv
vestPath=pecheras.tsv

#Selection Methods: roulette, elite, universal, boltzmann, tournament, deterministictournament, ranking
selectionA=universal
selectionB=roulette
replacementA=ranking
replacementB=tournament

#Crossover Methods: onepointcross, twopointcross, anularcross, uniformcross
crossover=anularcross
crossOverProbability = 0.6
#Mutation Methods: gen, limitedmulti, uniformmulti, complete
mutation=limitedmulti
mutationProbability=0.2
#variation +- in mutations
itemVariation=5
heightVariation=0.1

#Implementation Options: fillall, fillparent
implementation=fillall


#finishConditions
maxGenerations=500
time=80000
gensWithoutFitnessChange = 10
gensWithoutPopulationChange = 10
unchangedPopulationMargin = 0.8
acceptableFitness = 30.0
fitnessMargin = 0.3

#Values
initialPopulation=1000
selectionMethodAPercentage = 0.5
replacementMethodAPercentage = 0.5
tournamentThreshold = 0.5
tournamentMValue = 300


#Class: warrior, archer, defender, infiltrator
characterClass=infiltrator
```
Donde:
* itemPath: La dirección del archivo de item en cuestion.
* selectionA/selectionB y replacementA/replacementB: El algoritmo a utilizar este puede ser:
  * _roulette_: Utilizando la implementación de Roulette
  * _elite_: Utilizando la implementación de Elite
  * _universal_: Utilizando la implementación de Universal
  * _boltzmann_: Utilizando la implementación de Boltzmann
  * _tournament_: Utilizando la implementación de torneo probabilistico
  * _deterministictournament_: Utilizando la implementación de torneo deterministico
  

* crossover:
  * _onepointcross_: Utilizando el cruze One Point
  * _twopointcross_: Utilizando el cruze Two Point
  * _anularcross_: Utilizando el cruze Anular
  * _uniformcross_: Utilizando el cruze Uniform
* crossOverProbability : la probabilidad de un cruce
  

* mutation:
  * _gen_: Utilizando la mutacion gen
  * _limitedmulti_: Utilizando la mutacion multigen limitado
  * _uniformmulti_: Utilizando la mutacion multigen uniforme
  * _complete_: Utilizando la mutacion completa
* mutationProbability: se define la probabilidad que una mutatcion ocurra.
  

* itemVartiation: se define el rango del variacion de item, que sera segun el id del item.
* heightVariation: se define el rango del variacion de item, se define como un double.
* implementation:
  * _fillall_: Utilizando Fill All
  * _fillparent_: Utilizando Fill Parent
  

* maxGenerations: la cantidad maxima de generaciones antes de cortar la ejecucion
* time: numero entero que representa los milisegundos que va correr el programa como maximo
* gensWithoutFitnessChange: la cantidad de generaciones que correra como maximo si el fitness no varia.
* gensWithoutPopulationChange: la cantidad de generaciones que correra como maximo si la poblacion no se modifica.
* unchangedPopulationMargin: se define el porcentaje de la poblacion que no varia entre generacion a generacion.
* fitnessMargin: la varianza maxima para considerarse como un mismo fitness entre generacion.
* acceptableFitness: se define como corte de la ejecucion si llega a superar al fitness definido


* initialPopulation: La poblacion inicial que tendra la priemra generacion al ejecutar el programa.
* selectionMethodAPercentage: La probabilidad de seleccionar el metodo de seleccion A.
* replacementMethodAPercentage: La probabilidad de seleccionar el metodo de reemplazo A.


* tournamentThreshold: Valor especifico del metodo tournament, donde definimos el threshold.
* tournamentMValue: Valor especifico del metodo tournament deterministico, donde se define el valor de M.

* characterClass: Se define la clase de los personajes a mutar. Que pueden ser
  * _warrior_
  * _archer_
  * _defender_
  * _infiltrator_

Otro ejemplo de configuracion tomando de seleccion solamente el elite:
```
weaponPath=armas.tsv
bootsPath=botas.tsv
helmetPath=cascos.tsv
glovesPath=guantes.tsv
vestPath=pecheras.tsv

#Selection Methods: roulette, elite, universal, boltzmann, tournament, deterministictournament, ranking
selectionA=elite
selectionB=elite
replacementA=ranking
replacementB=tournament

#Crossover Methods: onepointcross, twopointcross, anularcross, uniformcross
crossover=anularcross
crossOverProbability = 0.6
#Mutation Methods: gen, limitedmulti, uniformmulti, complete
mutation=limitedmulti
mutationProbability=0.2
#variation +- in mutations
itemVariation=5
heightVariation=0.1

#Implementation Options: fillall, fillparent
implementation=fillall


#finishConditions
maxGenerations=500
time=80000
gensWithoutFitnessChange = 10
gensWithoutPopulationChange = 10
unchangedPopulationMargin = 0.8
acceptableFitness = 30.0
fitnessMargin = 0.3

#Values
initialPopulation=1000
selectionMethodAPercentage = 0.5
replacementMethodAPercentage = 0.5
tournamentThreshold = 0.5
tournamentMValue = 300


#Class: warrior, archer, defender, infiltrator
characterClass=infiltrator
```

Para la ejecucion solamente se debe hacer un:

```java -jar tp1-2.0-SNAPSHOT.jar```