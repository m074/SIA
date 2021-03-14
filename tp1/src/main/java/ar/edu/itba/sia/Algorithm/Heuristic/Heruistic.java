package ar.edu.itba.sia.Algorithm.Heuristic;

import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.State;

abstract class Heuristic {
    abstract int calculate_h(Node node);
    abstract int calculate_h(State state);
}
