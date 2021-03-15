package ar.edu.itba.sia.Model;

import java.util.*;

public class Level {
    //clase para almacenar nivel del sokoban
    //de alguna forma habria que recibir de un archivo el mapa (con ascii quizas) y
    //guardarse las posiciones
    public HashSet<Position> goals;
    public HashSet<Position> walls;
    public State startingState;    //tendria la posicion inicial del jugador y las cajas

    public Level(HashSet<Position> goals, HashSet<Position> walls, State startingState) {
        this.goals = goals;
        this.walls = walls;
        this.startingState = startingState;
    }

    //funcion que devuelve posibles movimientos dependiendo del estado
    //up, down, left, right
    //cuando se haga la busqueda deberan crearse los nodos con los estados nuevos al moverse
    public ArrayList<String> possibleMoves(State s) {
        HashSet<Position> boxes = s.box_positions;
        ArrayList<String> moves = new ArrayList<>();
        int x = s.player_position.x;
        int y = s.player_position.y;

        //checkear que cada direccion sea valida, si lo es la agrego
        //considerar que no hayan dos cajas juntas

        //derecha
        Position newPlayerPos = new Position(x, y + 1);
        Position otherBox = new Position(x, y + 2);   //para la segunda caja
        if (!walls.contains(newPlayerPos)) {
            if (!(boxes.contains(newPlayerPos) && (walls.contains(otherBox) || boxes.contains(otherBox))))
                moves.add("r");
        }


        //izquierda
        newPlayerPos = new Position(x, y-1);
        otherBox = new Position(x, y-2);   //para la segunda caja
        if(!walls.contains(newPlayerPos)){

            if(!(boxes.contains(newPlayerPos) && (walls.contains(otherBox) || boxes.contains(otherBox) )))
                moves.add("l");
        }

        //arriba
        newPlayerPos = new Position(x-1, y);
        otherBox = new Position(x-2, y);   //para la segunda caja
        if(!walls.contains(newPlayerPos)){
            if(!(boxes.contains(newPlayerPos) && (walls.contains(otherBox) || boxes.contains(otherBox) )))
                moves.add("u");

        }

        newPlayerPos = new Position(x+1, y);
        otherBox = new Position(x+2, y);   //para la segunda caja
        if(!walls.contains(newPlayerPos)){
            if(!(boxes.contains(newPlayerPos) && (walls.contains(otherBox) || boxes.contains(otherBox) )))
                moves.add("d");

        }

        return moves;
    }

    //funcion que devuelve un nodo con el estado siguiente al realizar una accion
    //con esta funcion se expanden los nodos
    public Node move(Node n, String move){
        @SuppressWarnings("unchecked") //se queja del casteo si no
        HashSet<Position> boxes = (HashSet<Position>) n.state.box_positions.clone();
        int x = n.state.player_position.x;
        int y = n.state.player_position.y;
        Position newPlayerPos = n.state.player_position;
        char m = move.charAt(0);
        switch(m){
            case 'u':
                newPlayerPos = new Position(x-1, y);
                if(boxes.contains(newPlayerPos)){   //si hay caja la muevo
                    Position newBoxPos = new Position(x-2, y);
                    boxes.remove(newPlayerPos); //remuevo la caja de donde esta el jugador
                    boxes.add(newBoxPos); //y la pongo en su nueva posicion
                }
                break;

            case 'd':
                newPlayerPos = new Position(x+1, y);
                if(boxes.contains(newPlayerPos)){   //si hay caja la muevo
                    Position newBoxPos = new Position(x+2, y);
                    boxes.remove(newPlayerPos); //remuevo la caja de donde esta el jugador
                    boxes.add(newBoxPos); //y la pongo en su nueva posicion
                }
                break;

            case 'l':
                newPlayerPos = new Position(x, y-1);
                if(boxes.contains(newPlayerPos)){   //si hay caja la muevo
                    Position newBoxPos = new Position(x, y-2);
                    boxes.remove(newPlayerPos); //remuevo la caja de donde esta el jugador
                    boxes.add(newBoxPos); //y la pongo en su nueva posicion
                }
                break;
            case 'r':
                newPlayerPos = new Position(x, y+1);
                if(boxes.contains(newPlayerPos)){   //si hay caja la muevo
                    Position newBoxPos = new Position(x, y+2);
                    boxes.remove(newPlayerPos); //remuevo la caja de donde esta el jugador
                    boxes.add(newBoxPos); //y la pongo en su nueva posicion
                }
                break;
        }
        return new Node(new State(newPlayerPos, boxes), n);
    }



    //funcion que se fije si teniendo un estado se gano (estan las cajas en las goals)

    public boolean hasWon(State s){
        /*
        for(Position box : s.box_positions){
            if(!goals.contains(box))
                return false;       //si hay una caja que no esta en goal no se ganó
        }*/
        return goals.equals(s.box_positions);
    }

    //funcion que teniendo un estado se fije si hay deadlock


    public void printSolution(Node n){

        if(n == null){
            System.out.println("No solution found.\n");
            return;
        }
        Stack<Node> nodeQueue = new Stack<>();
        while(n.parent != null){
            nodeQueue.push(n);
            n = n.parent;
        }
        int maxX = 0;
        int maxY = 0;
        for(Position p : walls){
            if(p.getX() > maxX)
                maxX = p.getX();
            if(p.getY() > maxY)
                maxY = p.getY();
        }

        while(!nodeQueue.isEmpty()){
            State s = nodeQueue.pop().state;
            for(int i = 0; i<= maxX; i++){
                for(int j = 0; j<=maxY; j++){
                    Position pos = new Position(i, j);
                    if(walls.contains(pos))
                        System.out.print("*");
                    else if(s.box_positions.contains(pos))
                        System.out.print("o");
                    else if(s.player_position.equals(pos))
                        System.out.print("p");
                    else if(goals.contains(pos))
                        System.out.print("x");
                    else
                        System.out.print(" ");
                }
                System.out.print("\n");
            }
            System.out.println("\n");
        }
    }
}
