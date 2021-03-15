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
            if(!level.goals.contains(new Position(x, y))){
                if(level.walls.contains(new Position(x-1, y)) && level.walls.contains(new Position(x, y-1)))
                    return 9999;    //arriba e izq
                if(level.walls.contains(new Position(x-1, y)) && level.walls.contains(new Position(x, y+1)))
                    return 9999;    //arriba y der
                if(level.walls.contains(new Position(x+1, y)) && level.walls.contains(new Position(x, y-1)))
                    return 9999;    //abajo e izq
                if(level.walls.contains(new Position(x+1, y)) && level.walls.contains(new Position(x, y+1)))
                    return 9999;    //abajo y der

                if(level.walls.contains(new Position(x-1, y-1)) && level.walls.contains(new Position(x-1, y))
                        && level.walls.contains(new Position(x-1, y+1)) && level.walls.contains(new Position(x, y-2))
                        && level.walls.contains(new Position(x, y+2))&& !level.walls.contains(new Position(x, y-1))
                        && !level.walls.contains(new Position(x, y+1)))
                    return 9999;    //arriba y costados

                if(level.walls.contains(new Position(x+1, y-1)) && level.walls.contains(new Position(x+1, y))
                        && level.walls.contains(new Position(x+1, y+1)) && level.walls.contains(new Position(x, y-2))
                        && level.walls.contains(new Position(x, y+2))&& !level.walls.contains(new Position(x, y-1))
                        && !level.walls.contains(new Position(x, y+1)))
                    return 9999;    //abajo y costados
                if(level.walls.contains(new Position(x-1, y-1)) && level.walls.contains(new Position(x, y-1))
                        && level.walls.contains(new Position(x+1, y-1)) && level.walls.contains(new Position(x-2, y))
                        && level.walls.contains(new Position(x+2, y))&& !level.walls.contains(new Position(x-1, y))
                        && !level.walls.contains(new Position(x+1, y)))
                    return 9999;    //izq arriba abajo
                if(level.walls.contains(new Position(x-1, y+1)) && level.walls.contains(new Position(x, y+1))
                        && level.walls.contains(new Position(x+1, y+1)) && level.walls.contains(new Position(x-2, y))
                        && level.walls.contains(new Position(x+2, y))&& !level.walls.contains(new Position(x-1, y))
                        && !level.walls.contains(new Position(x+1, y)))
                    return 9999;    //der arriba abajo

            }
        }
        return 0;
    }

}
