package ar.edu.itba.sia.Algorithm.Heuristic;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Position;
import ar.edu.itba.sia.Model.State;

import java.util.HashSet;

public class DeadlockHeuristic extends Heuristic {
    Level level;
    public DeadlockHeuristic(Level level){
        this.level = level;
    }


    @Override
    public int calculate_h(State state) {   //si hay deadlock da un numero muy alto para priorizar el fallo, si no da 0
        for(Position box : state.box_positions){
            int x = box.getX();
            int y = box.getY();
            if(!level.goals.contains(box)){
                if(level.walls.contains(new Position(x-1, y)) || level.walls.contains(new Position(x+1, y))){
                    if(level.walls.contains(new Position(x, y-1)) || level.walls.contains(new Position(x, y+1))){
                        return 9999;
                    }
                }
            }
        }
        return 0;
    }

}
