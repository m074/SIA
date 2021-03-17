package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.State;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DFS extends Algorithm{
    Level level;

    public DFS(Level level){
        this.level = level;
    }

    @Override
    public Node resolve() {
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
                System.out.println("Solución hallada:");
                //System.out.println("Nodos creados:" + Node.id_count);
                System.out.println("Nodos creados:" + (Node.id_count - stack.size()));
                System.out.println("Nodos frontera:" + stack.size());
//                level.printSolution(node);
                return node;
            }
            visited.add(node.state);
            ArrayList<String> moves = level.possibleMoves(node.state);
            for(String s : moves){
                Node newNode = level.move(node, s);
                if(newNode != null)
                    stack.push(newNode);
            }
        }
        System.out.println("No se halló solución.");
        return null;
    }
}
