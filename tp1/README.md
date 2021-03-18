COMPILACION
------------
Para la compilación al ser un proyecto de maven con su correspondiente _pom.xml_, solamente es necesario pararse en al carpeta _tp1_ y ejecutar un _mvn package_ donde generará un archivo jar del programa
* El único requisito es tener Java 8

CONFIGURACION Y EJECUCION
------------------------
Previo a la ejecución del programa, se debe primero crear un archivo configuration.txt con las configuraciones de la ejecución, que puede ser por ejemplo:

```
map_path=D:\dos.txt
algorithm=iddfs
heuristic=boxdistance
depth=10
```
Donde:
* map_path: La dirección del archivo de texto del mapa, donde dentro de este archivo se representa con un _*_ las paredes, una _x_ las cajas, una _o_ los objetivos y una _p_ al jugador.
* algorithm: El algoritmo a utilizar este puede ser:
  * _bfs_: Utilizando la implementación de BFS
  * _dfs_: Utilizando la implementación de DFS
  * _iddfs_: Utilizando la implementación de IDDFS
  * _astar_: Utilizando la implementación de A*
  * _greedy_: Utilizando la implementación de Global Greedy
  * _idastar_: Utilizando la implementación de IDA*
* heuristic:
  * _boxingoal_: La heurística que toma en consideración la cantidad de cajas que faltan en el objetivo.
  * _boxdistance_: La heurística que toma en consideración la distancia de las cajas a los objetivos.
  * _deadlock_: La heurística que considera si una caja esta bloqueda.
  * _merge_: La heurística que es el conjutno de la de deadlock y boxdistance.
* depth: el numero de profundidad que iterara IDDFS si este fuese elegido

Para la ejecucion solamente se debe hacer un:

```java -jar tp1-1.0-SNAPSHOT.jar```