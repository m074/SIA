package tp1.src.main.java.ar.edu.itba.sia.Model;

import java.util.Objects;

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class Node {
    public static Integer id_count = 1; // Contador para el identificador unico

    public State state;
    public Node parent;
    public Direction direction; // este es necesario para hacer un backtracking de la solucion encontrada
    public Integer id; // identificador unico ya que no podemos calcular el hashCode a partir del padre o el state

    public Node(State state, Node parent, Direction direction) {
        this.state = state;
        this.parent = parent; //null if root
        this.direction = direction; //null if root
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
