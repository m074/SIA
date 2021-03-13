package ar.edu.itba.sia.Algorithm.Heuristic;

import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.State;

interface Heuristic {
    int calculate_h(Node node);
    int calculate_h(State state);
}
