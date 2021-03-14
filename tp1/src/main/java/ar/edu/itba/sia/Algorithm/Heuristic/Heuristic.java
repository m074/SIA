package ar.edu.itba.sia.Algorithm.Heuristic;

import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.State;

public abstract class Heuristic {
    public abstract int calculate_h(Node node);
    public abstract int calculate_h(State state);
}
