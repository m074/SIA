package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.Position;
import ar.edu.itba.sia.Model.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class IDDFS{

    //analize the choice of targetdepth and how increase
    public static void resolve(Level level, int initial_depth){

        HashMap<State, Integer> visitedStates = new HashMap<>();

        int targetDepth = initial_depth;


    Node root = new Node(level.startingState, null);

    HashSet<Node> borderNodes = new HashSet<Node>();
    boolean finish =  iddfs(root, level, visitedStates, targetDepth, borderNodes);


    while(!finish){
        HashSet<Node> newBorderNodes = new HashSet<Node>();
        targetDepth += initial_depth;

        System.out.println("depth: " + targetDepth);

        for(Node node : borderNodes){
            finish = iddfs(node, level, visitedStates, targetDepth, newBorderNodes);
            if(finish)
                break;
        }
        borderNodes = newBorderNodes;
    }

    }



    public static boolean iddfs(Node node, Level level, HashMap<State, Integer> visitedStates, int targetDepth, HashSet<Node> borderNodes ){

        if(node.depth == targetDepth){
            borderNodes.add(node);
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
            Integer depth = visitedStates.get(currSt);
            if(node.depth >= depth){
                return false;
            }
        }
        visitedStates.put(currSt, node.depth);


        Node newNode = null;
        ArrayList<String> moves = level.possibleMoves(node.state);
        for(String s : moves){
            newNode = level.move(node, s);
            if (iddfs(newNode, level, visitedStates, targetDepth, borderNodes))
                return true;

        }

        return iddfs(newNode, level, visitedStates, targetDepth, borderNodes);
    }


}
