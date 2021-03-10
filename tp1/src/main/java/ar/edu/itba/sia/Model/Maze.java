package ar.edu.itba.sia.Model;


import com.sun.tools.javac.util.Pair;

import java.util.HashSet;

enum Entity {
    P, //PLAYER
    W, //WALL
    B, //BLOCK
    O, //OBJECTIVE
    E //EMPTY
}


public class Maze {
    public Entity[][] matrix;
    public HashSet<Pair<Integer,Integer>> objectives;

    public Maze(){
        Entity[][] mat; // TODO delete esta gronchada
        mat = new Entity[][]{ { Entity.W, Entity.W, Entity.W, Entity.W },
                              { Entity.W, Entity.O, Entity.E, Entity.W },
                              { Entity.W, Entity.B, Entity.E, Entity.W },
                              { Entity.W, Entity.E, Entity.P, Entity.W },
                              { Entity.W, Entity.W, Entity.W, Entity.W }};
        this.matrix = mat;
        this.objectives = new HashSet<>();
        this.objectives.add((new Pair<>(2,1)));
    }

    public State get_initial_state(){
        HashSet<Pair<Integer,Integer>> boxes = new HashSet<>();
        boxes.add(new Pair<>(2,1));
        return new State(new Pair<>(3,2), boxes);
    }
}
