package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.Position;
import ar.edu.itba.sia.Model.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class IDDFS extends Algorithm{

    Level level;
    int depth = 0;

    Node solutionNode = null;

    public IDDFS(Level level, int depth){
        this.level = level;
        this.depth = depth;
    }

    public  IDDFS(Level level){
        this.level = level;
    }

    public void setInitialDepth(int depth){
        this.depth = depth;
    }


    public Node resolve() {

        if(depth == 0){
            System.out.println("depth need to be setted first");
            return null;
        }

        HashMap<State, Integer> visitedStates = new HashMap<>();
        int targetDepth = this.depth;

        Node root = new Node(level.startingState, null);

        HashSet<Node> borderNodes = new HashSet<Node>();
        boolean finish =  iddfs(root, level, visitedStates, targetDepth, borderNodes);

        while(!finish){
            HashSet<Node> newBorderNodes = new HashSet<Node>();
            targetDepth += this.depth;

            for(Node node : borderNodes){
                finish = iddfs(node, level, visitedStates, targetDepth, newBorderNodes);
                if(finish){
                    System.out.println("IDDFS*:");
                    System.out.println("Nodos creados:" + Node.id_count);
                    System.out.println("Nodos frontera: " + borderNodes.size());
                    break;
                }


            }
            borderNodes = newBorderNodes;

            if (newBorderNodes.size() == 0){
                finish=true;
            }
        }
        return solutionNode;
    }

    public boolean iddfs(Node node, Level level, HashMap<State, Integer> visitedStates, int targetDepth, HashSet<Node> borderNodes ){

        if(level.hasWon(node.state)) {
            solutionNode = node;
//            level.printSolution(node);
            return true;
        }

        if(node.depth == targetDepth){
            borderNodes.add(node);
            return false;
        }

        State currSt = node.state;
        if(visitedStates.containsKey(currSt)){
            Integer depth = visitedStates.get(currSt);
            if(node.depth >= depth){
                return false;
            }
        }
        visitedStates.put(currSt, node.depth);

        ArrayList<String> moves = level.possibleMoves(node.state);
        for(String s : moves){
            Node newNode = level.move(node, s);
            if (iddfs(newNode, level, visitedStates, targetDepth, borderNodes))
                return true;
        }
        return false;
    }


}
