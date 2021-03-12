package ar.edu.itba.sia;


import ar.edu.itba.sia.Algorithm.BFS;
import ar.edu.itba.sia.Algorithm.DFS;
import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.Position;
import ar.edu.itba.sia.Model.State;
import ar.edu.itba.sia.utils.OvitoGenerator;

import java.io.*;
import java.net.URL;
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

        File filePath = new File("C:/Users/guigo/SIA/tp1/src/main/java/ar/edu/itba/sia/maze.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
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
                    x++;
                    y = -1;
                    break;
                default:
                    y--;
                    break;
            }
            y++;
        }
        if(playerPos == null)
            System.exit(1);
        initialState = new State(playerPos, boxes);
        Level level = new Level(goals, walls, initialState);

//        Node solution_node = BFS.resolve(level);
//        Node node = solution_node;
//        int frame =0;
//        while(node!=null){
//            node = node.parent;
//            frame++;
//        }
//        node = solution_node;
//        while(node!=null){
//            OvitoGenerator.saveDynamicFile(frame--,level,node.state);
//            node = node.parent;
//        }

    }

}
