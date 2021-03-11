package ar.edu.itba.sia.Model;

import java.util.Objects;


public class Node {
    public static Integer id_count = 1; // Contador para el identificador unico

    public State state;
    public Node parent;
    public Integer id; // identificador unico ya que no podemos calcular el hashCode a partir del padre o el state

    public Node(State state, Node parent) {
        this.state = state;
        this.parent = parent; //null if root
        this.id = id_count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id.equals(node.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
