package ar.edu.itba.sia.Algorithm.Heuristic;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.Position;
import ar.edu.itba.sia.Model.State;

public class BoxDistanceHeuristic implements Heuristic{
    private Level level;

    public BoxDistanceHeuristic(Level level){
        this.level = level;
    }


    @Override
    public int calculate_h(Node node) {
        return calculate_h(node.state);
    }

    @Override
    public int calculate_h(State state) {
        int total = 0;
        for (Position box_position: state.box_positions) {
            int min = Integer.MAX_VALUE;
            for (Position goal_postion: level.goals) {
                int distance = box_position.distance(goal_postion);
                if(min > distance){
                    total += min;
                }
            }
        }
        return total;
    }
}
