package src;

public class Node {
    public State state;
    //el nodo contiene al estado
    public Node parent;
    //segun lo que entiendo habria que ir "armando" el arbol a medida que se busca con las posibles acciones
    //habria que calcular las acciones dependiendo del state
    public Node(State state, Node parent) {
        this.state = state;
        this.parent = parent; //null if root
    }

    @Override
    public boolean equals(Object o) {
        if(getClass() != o.getClass())
            return false;
        return (this.state == ((Node) o).state);
    }
}
