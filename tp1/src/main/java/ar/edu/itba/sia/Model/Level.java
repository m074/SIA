package tp1.src.main.java.ar.edu.itba.sia.Model;

import java.util.HashSet;

public class Level {
    //clase para almacenar nivel del sokoban
    //de alguna forma habria que recibir de un archivo el mapa (con ascii quizas) y
    //guardarse las posiciones
    HashSet<Position> floor; //no se si guardar el piso vacio o no hace falta
    HashSet<Position> goals;
    HashSet<Position> walls;
    State startingState;    //tendria la posicion inicial del jugador y las cajas

    public Level(HashSet<Position> floor, HashSet<Position> goals, HashSet<Position> walls, State startingState) {
        this.floor = floor;
        this.goals = goals;
        this.walls = walls;
        this.startingState = startingState;
    }

    //funcion que calcule posibles acciones dependiendo del estado (moverse, empujar caja)

    //funcion que se fije si teniendo un estado se gano (estan las cajas en las goals)
    public boolean hasWon(State s){
        for(Position box : s.boxes){
            if(!goals.contains(box))
                return false;       //si hay una caja que no esta en goal no se ganó
        }
        return true;
    }

    //funcion que teniendo un estado se fije si hay deadlock
    public boolean testDeadlock(State s){
        for(Position box : s.boxes){
            int x = box.x;
            int y = box.y;

            /*
            habria que checkear cada posible deadlock:
            top left
            top right
            bottom left
            bottom right
            no se si hay otras

             */
        }
        return false;
    }
}
