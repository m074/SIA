package ar.edu.itba.sia.Algorithm.Heuristic;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Position;
import ar.edu.itba.sia.Model.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DeadlockHeuristic extends Heuristic {
    Level level;
    public DeadlockHeuristic(Level level){
        this.level = level;

    }


    @Override
    public int calculate_h(State state) {   //si hay deadlock da un numero muy alto para priorizar el fallo, si no da 0
        HashSet<Position> stuckBoxes = new HashSet<>();
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
            if(state.box_positions.contains(new Position(x-1, y)) || state.box_positions.contains(new Position(x+1, y)) || level.walls.contains(new Position(x-1, y)) || level.walls.contains(new Position(x+1, y))){
                if(state.box_positions.contains(new Position(x, y-1)) || state.box_positions.contains(new Position(x, y+1)) || level.walls.contains(new Position(x, y-1)) || level.walls.contains(new Position(x, y+1))){
                    stuckBoxes.add(box);
                }
            }
            Position stuckTest = new Position(x-1, y);
            if(state.box_positions.contains(stuckTest) && stuckBoxes.contains(stuckTest) && stuckBoxes.contains(box)){
                return 9999;
            }
            stuckTest = new Position(x+1, y);
            if(state.box_positions.contains(stuckTest) && stuckBoxes.contains(stuckTest) && stuckBoxes.contains(box)){
                return 9999;
            }
            stuckTest = new Position(x, y-1);
            if(state.box_positions.contains(stuckTest) && stuckBoxes.contains(stuckTest) && stuckBoxes.contains(box)){
                return 9999;
            }
            stuckTest = new Position(x, y+1);
            if(state.box_positions.contains(stuckTest) && stuckBoxes.contains(stuckTest) && stuckBoxes.contains(box)){
                return 9999;
            }
        }
        return 0;
    }

}
