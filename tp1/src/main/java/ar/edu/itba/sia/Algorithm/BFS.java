package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class BFS {
    public static void resolve(Level level){
        LinkedList<Node> nodos_a_visitar = new LinkedList<>();
        HashSet<State> estados_anteriores = new HashSet<>();
        nodos_a_visitar.add(new Node(level.startingState, null));
//        estados_anteriores.add(level.startingState);

        while(!nodos_a_visitar.isEmpty()){
            Node nodo = nodos_a_visitar.poll();
            if(estados_anteriores.contains(nodo.state)){
                continue;
            }
            estados_anteriores.add(nodo.state);
            if(level.hasWon(nodo.state)) {
                System.out.println("gane");
                return;
            }
            ArrayList<String> posibles_mov = level.possibleMoves(nodo.state);
            for (String s: posibles_mov) {
                Node new_node = level.move(nodo, s);
                nodos_a_visitar.add(new_node);
            }
        }

    }
}

