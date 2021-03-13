package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.Position;
import ar.edu.itba.sia.Model.State;

import java.util.ArrayList;
import java.util.HashSet;

public class IDDFS {

    //analize the choice of targetdepth and how increase
    public static void resolve(Level level){

        HashSet<State> visited = new HashSet<>();

        int targetDepth = 50;
        int currentDepth = -1;

    Node root = new Node(level.startingState, null);

    HashSet<Node> borderNodes = new HashSet<Node>();
    boolean finish =  iddfs(root, level, visited, currentDepth, targetDepth, borderNodes);

    while(!finish){
        HashSet<Node> newBorderNodes = new HashSet<Node>();
        currentDepth = targetDepth;
        targetDepth += 2*targetDepth;
        System.out.println(targetDepth);
        for(Node node : borderNodes){
            finish = iddfs(node, level, visited, currentDepth, targetDepth, newBorderNodes);
            if(finish)
                break;
        }
        borderNodes = newBorderNodes;
    }

    }



    public static boolean iddfs(Node node, Level level, HashSet<State> visited, int currentDepth, int targetDepth, HashSet<Node> borderNodes ){
        currentDepth += 1;

        if(currentDepth == targetDepth){
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
        if(visited.contains(node.state)){
            return false;
        }
        visited.add(node.state);

        Node newNode = null;
        ArrayList<String> moves = level.possibleMoves(node.state);
        for(String s : moves){
            newNode = level.move(node, s);
            if (iddfs(newNode, level, visited, currentDepth, targetDepth, borderNodes))
                return true;

        }

        return iddfs(newNode, level, visited, currentDepth, targetDepth, borderNodes);
    }


}
