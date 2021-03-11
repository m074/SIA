package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DFS {
    public static void resolve(Level level){
        Stack<Node> stack = new Stack<>();
        Node node = new Node(level.startingState, null);
        stack.push(node);
        HashSet<State> visited = new HashSet<>();

        while(!stack.isEmpty()){
            node = stack.pop();
            if(visited.contains(node.state)){
                continue;
            }
            if(level.hasWon(node.state)) {
                System.out.println("gane");
                return;
            }
            visited.add(node.state);
            ArrayList<String> moves = level.possibleMoves(node.state);
            for(String s : moves){
                Node newNode = level.move(node, s);
                if(newNode != null)
                    stack.push(newNode);
            }
        }
    }

}
