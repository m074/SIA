package tp1.src.main.java.ar.edu.itba.sia.Model;

import java.util.HashSet;
import java.util.Objects;

public class State {
    //habria que saber la ubicacion del jugador y de las cajas para saber el estado
    //considerando coordenadas (x,y)
    Position player;
    //una o mas variables para las coordenadas para las cajas (unicas)
    HashSet<Position> boxes;

    public State(Position player, HashSet<Position> boxes) {
        this.player = player;
        this.boxes = boxes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(player, state.player) &&
                Objects.equals(boxes, state.boxes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, boxes);
    }
}
