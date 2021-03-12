package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Position;
import ar.edu.itba.sia.Model.State;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OvitoGenerator {
    public static void saveDynamicFile(int frame, Level level, State state) {
        try(FileWriter fw = new FileWriter("D:\\frame-" + frame + ".xyz", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            int walls_size = level.walls.size();
            int boxes_size = state.box_positions.size();
            int goals_size = level.goals.size();
            // Se dice la cantidad de particulas
            out.println(boxes_size+walls_size+1+goals_size);
            out.println("");
            // Se crea en cada particula con su (posX, PosY, radio, color)
            out.println(state.player_position.getX() + " " + state.player_position.getY() + " " + 0.5 + " " + 3);
            for(Position b : state.box_positions) {
                out.println(b.getX() + " " + b.getY() + " " + 0.5 + " " + 2);
            }
            for(Position b : level.goals) {
                out.println(b.getX() + " " + b.getY() + " " + 0.25 + " " + 4);
            }
            for(Position w : level.walls) {
                out.println(w.getX() + " " + w.getY() + " " + 0.5 + " " + 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
