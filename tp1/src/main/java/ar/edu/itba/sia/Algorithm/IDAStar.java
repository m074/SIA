package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Algorithm.Heuristic.AStarValueF;
import ar.edu.itba.sia.Algorithm.Heuristic.Heuristic;
import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class IDAStar extends Algorithm{


    Heuristic h;
    Level level;
    Node solutionNode;


    int totalBorders = 0;

    public IDAStar(Level level, Heuristic heuristic){
        this.level = level;
        this.h = heuristic;
    }


    public Node resolve() {

        HashMap<State, Integer> visitedStates = new HashMap<>();
        PriorityQueue<AStarValueF> borders = new PriorityQueue<>();

        Node initial_node = new Node(level.startingState, null);
        int node_h = h.calculate_h(initial_node);
        AStarValueF as= new AStarValueF(initial_node.depth+node_h,node_h, initial_node);

        int target = as.getF_value();

        boolean finish =  idastar(initial_node, level, visitedStates, target, borders, h);

        while(!finish){

            PriorityQueue<AStarValueF> newBorders = new PriorityQueue<>();
            boolean first = true;

            while(!borders.isEmpty()){

                if(first){
                    AStarValueF asv = borders.poll();
                    target = asv.getF_value();

                    if (idastar(asv.node, level, visitedStates, target, newBorders, h)){
                        totalBorders = borders.size() + newBorders.size();
                        System.out.println("IDA*:");
                        //System.out.println("Nodos creados:" + Node.id_count);
                        System.out.println("Nodos expandidos:" + (Node.id_count - totalBorders));
                        System.out.println("Nodos frontera: " + totalBorders);
                        return solutionNode;
                    }

                    first = false;
                }
                else{

                    AStarValueF asv = borders.poll();
                    int t = asv.getF_value() + asv.getH_value();

                    if (idastar(asv.node, level, visitedStates, target, newBorders, h)){
                        totalBorders = borders.size() + newBorders.size();
                        System.out.println("IDA*:");
                        //System.out.println("Nodos creados:" + Node.id_count);
                        System.out.println("Nodos expandidos:" + (Node.id_count - totalBorders));
                        System.out.println("Nodos frontera: " + totalBorders);
                        return solutionNode;
                    }
                }
            }
            borders = newBorders;

            if(newBorders.isEmpty()){
                return solutionNode;
            }
        }
        return solutionNode;
    }

    public boolean idastar(Node node, Level level, HashMap<State, Integer>  visitedStates, int targetF, PriorityQueue<AStarValueF> borders, Heuristic h ){

        if(level.hasWon(node.state)) {
//            level.printSolution(node);
            solutionNode = node;
            return true;
        }

        int node_h = h.calculate_h(node);
        AStarValueF as= new AStarValueF(node.depth+node_h,node_h, node);
        int F = as.getF_value();

        if(F > targetF){
            borders.add(as);
            return false;
        }

        State currSt = node.state;
        if(visitedStates.containsKey(currSt)){
            if(node.depth >= visitedStates.get(currSt)) {
                return false;
            }
        }
        visitedStates.put(currSt, node.depth);


        ArrayList<String> moves = level.possibleMoves(node.state);
            PriorityQueue<AStarValueF> pq = new PriorityQueue<>();
            for(String s : moves){

                Node newNode = level.move(node, s);
                node_h = h.calculate_h(newNode);
                pq.add(new AStarValueF(newNode.depth+node_h, node_h, newNode));

            }
            while(!pq.isEmpty()) {
                if (idastar(pq.poll().node, level, visitedStates, targetF, borders, h))
                    return true;
            }
        return false;
    }


}
