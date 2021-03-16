package ar.edu.itba.sia.Algorithm.Heuristic;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.State;

public class MergeLockDistance extends Heuristic {
    private Heuristic h1;
    private Heuristic h2;

    public MergeLockDistance(Level level){
        this.h1 = new DeadlockHeuristic(level);
        this.h2 =new BoxDistanceHeuristic(level);
    }


    @Override
    public int calculate_h(State state) {
        return Math.max(h1.calculate_h(state), h2.calculate_h(state));
    }
}
