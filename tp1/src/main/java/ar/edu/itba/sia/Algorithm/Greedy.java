package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Algorithm.Heuristic.AStarValueF;
import ar.edu.itba.sia.Algorithm.Heuristic.BoxDistanceHeuristic;
import ar.edu.itba.sia.Algorithm.Heuristic.Heuristic;
import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Greedy extends Algorithm{
    Level level;
    Heuristic h;

    public Greedy(Level level, Heuristic h){
        this.level = level;
        this.h = h;
    }

    @Override
    public Node resolve() {
        PriorityQueue<AStarValueF> nodes_to_visit = new PriorityQueue<>(); //le pongo 0 en f_value y lo puedo usar igual
        HashSet<State> old_states = new HashSet<>();
        Node initial_node = new Node(level.startingState, null);
        nodes_to_visit.add(new AStarValueF(0,0, initial_node));

        while(!nodes_to_visit.isEmpty()){
            AStarValueF asf = nodes_to_visit.poll();
            Node node = asf.node;

            if(old_states.contains(node.state)){
                continue;
            }
            old_states.add(node.state);
            if(level.hasWon(node.state)) {
                System.out.println("Greedy:");
                //System.out.println("Nodos creados:" + Node.id_count);
                System.out.println("Nodos expandidos:" + (Node.id_count - nodes_to_visit.size()));
                System.out.println("Nodos frontera:" + nodes_to_visit.size());
//                level.printSolution(node);
                return node;
            }
            ArrayList<String> pausibles_moves = level.possibleMoves(node.state);
            for (String s: pausibles_moves) {
                Node new_node = level.move(node, s);
                if(new_node != null){
                    int node_h = h.calculate_h(new_node);
                    nodes_to_visit.add(new AStarValueF(0, node_h, new_node));   //0 pues greedy solo se fija en h
                }
//                    nodes_to_visit.add(new_node);

            }
        }
        System.out.println("No se halló solución.");
        return null;
    }
}
