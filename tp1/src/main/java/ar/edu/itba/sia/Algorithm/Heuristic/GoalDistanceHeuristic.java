package ar.edu.itba.sia.Algorithm.Heuristic;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Position;
import ar.edu.itba.sia.Model.State;

public class GoalDistanceHeuristic extends Heuristic {
    private Level level;


    public GoalDistanceHeuristic(Level level){
        this.level = level;
    }


    @Override
    public int calculate_h(State state) {
        int total = 0;
        for (Position goal_postion: level.goals) {
            int min = Integer.MAX_VALUE;
            for (Position box_position: state.box_positions) {
                int distance = box_position.distance(goal_postion);
                if(min > distance){
//                    System.out.println(distance);
                    min = distance;
                }
            }
            total += min;
        }
        int min = Integer.MAX_VALUE;
        for (Position box_position: state.box_positions) {
            int distance = state.player_position.distance(box_position);
            if(min > distance){
                min = distance;
            }

        }
        total += min;
        return total;
    }
}
