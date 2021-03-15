package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Algorithm.Heuristic.AStarValueF;
import ar.edu.itba.sia.Algorithm.Heuristic.Heuristic;
import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class IDAStar {
    Heuristic h;

    public IDAStar(Level level, Heuristic h){
        this.h = h;
    }

    public void resolve(Level level){


    HashMap<State, AStarValueF> visitedStates = new HashMap<>();
    PriorityQueue<AStarValueF> borders = new PriorityQueue<>();

        Node initial_node = new Node(level.startingState, null);
        int node_h = h.calculate_h(initial_node);
        AStarValueF as= new AStarValueF(initial_node.depth,node_h, initial_node);

        int target = as.getF_value() + as.getH_value();

    boolean finish =  idastar(initial_node, level, visitedStates, target, borders, h);

    while(!finish){

        PriorityQueue<AStarValueF> newBorders = new PriorityQueue<>();
        boolean first = true;

        while(!borders.isEmpty()){

            if(first){
                AStarValueF asv = borders.poll();
                target = asv.getF_value() + asv.getH_value();
                if (idastar(asv.node, level, visitedStates, target, newBorders, h))
                    return;
                first = false;
            }
            else{
                if (idastar(borders.poll().node, level, visitedStates, target, newBorders, h))
                    return;
            }
        }
        borders = newBorders;
    }
        return;
}






    public static boolean idastar(Node node, Level level, HashMap<State, AStarValueF>  visitedStates, int targetF, PriorityQueue<AStarValueF> borders, Heuristic h ){


        int node_h = h.calculate_h(node);
        AStarValueF as= new AStarValueF(node.depth,node_h, node);
        int F = as.getF_value() + as.getH_value();

        if(F > targetF){
            borders.add(as);
            return false;
        }

        if(node == null){
            return true;
        }
        if(level.hasWon(node.state)) {
            level.printSolution(node);
            return true;
        }

        State currSt = node.state;
        if(visitedStates.containsKey(currSt)){
            AStarValueF asv = visitedStates.get(currSt);
            if(F > (asv.getF_value() + asv.getH_value())){
                return false;
            }
            else if (F == (asv.getF_value() + asv.getH_value())){
                if(node.depth >= asv.getF_value()){
                    return false;
                }
            }
        }
        visitedStates.put(currSt, as);



        ArrayList<String> moves = level.possibleMoves(node.state);
        Node newNode = null;
            PriorityQueue<AStarValueF> pq = new PriorityQueue<>();
            for(String s : moves){

                newNode = level.move(node, s);
                node_h = h.calculate_h(newNode);
                pq.add(new AStarValueF(newNode.depth, node_h, newNode));

            }
            while(!pq.isEmpty()) {
                if (idastar(pq.poll().node, level, visitedStates, targetF, borders, h))
                    return true;
            }

        return idastar(newNode, level, visitedStates, targetF, borders, h);
    }


}
