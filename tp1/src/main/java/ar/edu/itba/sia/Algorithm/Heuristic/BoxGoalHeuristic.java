package ar.edu.itba.sia.Algorithm.Heuristic;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Position;
import ar.edu.itba.sia.Model.State;

public class BoxGoalHeuristic extends Heuristic {
    private Level level;


    public BoxGoalHeuristic(Level level){
        this.level = level;
    }


    @Override
    public int calculate_h(State state) {
        int total = 0;
        int min = Integer.MAX_VALUE;
        for (Position box_position: state.box_positions) {
            if (level.goals.contains(box_position)){
                total += 1;
            }

        }
        total += min;
        return total;
    }
}
