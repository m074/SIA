package ar.edu.itba.sia.Algorithm.Heuristic;

import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.Position;

import java.util.Objects;

public class AStarValueF implements Comparable{
    int f_value;
    int h_value;
    public Node node;

    public AStarValueF(int f_value, int h_value, Node node){
        this.f_value = f_value;
        this.h_value = h_value;
        this.node = node;
    }


    public int getF_value() {
        return f_value;
    }

    public int getH_value() {
        return h_value;
    }

    @Override
    public int compareTo(Object o) {
        AStarValueF asd = (AStarValueF) o;
        int f_value_diff = this.f_value - asd.f_value;
        if(f_value_diff == 0)
            return this.h_value - asd.h_value;
        else
            return f_value_diff;
    }
}
