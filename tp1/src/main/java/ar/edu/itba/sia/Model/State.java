package tp1.src.main.java.ar.edu.itba.sia.Model;


import javafx.util.Pair;
import java.util.HashSet;
import java.util.Objects;


public class State {

    public Position player_position;
    public HashSet<Position> box_positions;


    public State(Position player_position, HashSet<Position>  box_positions) {
        this.player_position = player_position;
        this.box_positions = box_positions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return player_position.equals(state.player_position) && box_positions.equals(state.box_positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player_position, box_positions);
    }
}
