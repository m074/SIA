package ar.edu.itba.sia;


import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.State;
import com.sun.tools.javac.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class App
{
    public static void main( String[] args ){
        Set<Pair<Integer,Integer>> piezas = new HashSet<>();
        piezas.add(new Pair<>(1,1));
        State state = new State(new Pair<>(1,1), null);
        System.out.println(state.hashCode());

        Node node = new Node(state, null, null);
        Node node1 = new Node(state, null, null);

        Node node2 = new Node(state, null, null);

        System.out.println(node.id);
        System.out.println(node1.id);

        System.out.println(node2.id);

        System.out.println(node.hashCode());
        System.out.println(node1.hashCode());

        System.out.println(node2.hashCode());


    }

}
