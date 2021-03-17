package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class BFS extends Algorithm {
    Level level;

    public BFS(Level level){
        this.level = level;
    }

    @Override
    public Node resolve() {
        LinkedList<Node> nodes_to_visit = new LinkedList<>();
        HashSet<State> old_states = new HashSet<>();
        nodes_to_visit.add(new Node(level.startingState, null));
        while(!nodes_to_visit.isEmpty()){
            Node node = nodes_to_visit.poll();
            if(old_states.contains(node.state)){
                continue;
            }
            old_states.add(node.state);
            if(level.hasWon(node.state)) {
                System.out.println("Solución hallada:");
                //System.out.println("Nodos creados:" + Node.id_count);
                System.out.println("Nodos creados:" + (Node.id_count - nodes_to_visit.size()));
                System.out.println("Nodos frontera:" + nodes_to_visit.size());
//                level.printSolution(node);
                return node;
            }
            ArrayList<String> pausibles_moves = level.possibleMoves(node.state);
            for (String s: pausibles_moves) {
                Node new_node = level.move(node, s);
                if(new_node != null)
                    nodes_to_visit.add(new_node);
            }
        }
        System.out.println("No se halló solución.");
        return null;

    }
}

