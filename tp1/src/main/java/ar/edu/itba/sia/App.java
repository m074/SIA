package ar.edu.itba.sia;


import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Position;
import ar.edu.itba.sia.Model.State;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class App
{
    //habria que leer de un archivo
    public static void main(String[] args) throws IOException {
        HashSet<Position> walls = new HashSet<>();
        HashSet<Position> goals = new HashSet<>();
        HashSet<Position> boxes = new HashSet<>();
        Position playerPos = null;
        State initialState;

        File file = new File("");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        int c;
        int x=0; int y=0;

        while((c = reader.read()) != -1){
            switch(c) {
                case '*': //pared
                    walls.add(new Position(x, y));
                    break;
                case ' ': //piso vacio
                    // code block
                    break;
                case 'p': //jugador
                    playerPos = new Position(x, y);
                    break;
                case 'x': //goal
                    goals.add(new Position(x, y));
                    break;
                case 'o': //caja
                    boxes.add(new Position(x,y));
                    break;
                case '\n':
                    y++;
                    x = 0;
                    break;
                default: break;
            }
            x++;
        }
        if(playerPos == null)
            System.exit(1);
        initialState = new State(playerPos, boxes);
        Level level = new Level(goals, walls, initialState);

    }

}
