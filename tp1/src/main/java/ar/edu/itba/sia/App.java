package ar.edu.itba.sia;

import ar.edu.itba.sia.Algorithm.*;
import ar.edu.itba.sia.Algorithm.Heuristic.*;
import ar.edu.itba.sia.Model.Level;
import ar.edu.itba.sia.Model.Node;
import ar.edu.itba.sia.Model.Position;
import ar.edu.itba.sia.Model.State;
import ar.edu.itba.sia.utils.ConfigurationParser;
import ar.edu.itba.sia.utils.OvitoGenerator;
import ar.edu.itba.sia.utils.TimeMetric;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class App
{
    private static String CONFIGURATION_FILE_NAME = "configuration.txt";

    //habria que leer de un archivo
    public static void main(String[] args) throws IOException {

        ConfigurationParser configuration = new ConfigurationParser();
        configuration.parse(CONFIGURATION_FILE_NAME);


        TimeMetric m = new TimeMetric();
        HashSet<Position> walls = new HashSet<>();
        HashSet<Position> goals = new HashSet<>();
        HashSet<Position> boxes = new HashSet<>();
        Position playerPos = null;
        State initialState;

        File filePath = new File(configuration.map_path);
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

        m.startTime();

        Heuristic h = new BoxDistanceHeuristic(level);
        switch(configuration.heuristic) {
            case "boxdistance":
                h = new BoxDistanceHeuristic(level);
                break;
            case "goaldistance":
                h = new GoalDistanceHeuristic(level);
                break;
            case "deadlock":
                h = new DeadlockHeuristic(level);
                break;
            case "boxingoal":
                h = new BoxGoalHeuristic(level);
                break;
            case "merge":
                h = new MergeLockDistance(level);
                break;
            default:
        }


        Algorithm algo;
        switch(configuration.algorithm) {
            case "bfs":
                algo = new BFS(level);
                break;
            case "dfs":
                algo = new DFS(level);
                break;
            case "iddfs":
               algo = new IDDFS(level, configuration.depth);
                break;
            case "greedy":
                algo = new Greedy(level, h);
                break;
            case "astar":
                algo = new AStar(level, h);
                break;
            case "idastar":
                algo = new IDAStar(level, h);
                break;
            default:
                algo = new BFS(level); // supongo que si no esta es bfs
                break;

        }




        Node solution_node = algo.resolve();

        m.stopTime();

        System.out.println("Costo: " + solution_node.depth);
        System.out.println("Tiempo corrido en ms: " + m.getTime());

        if(configuration.ovito_dir!=null) {
            OvitoGenerator.saveSolution(configuration.map_path, level, solution_node);
        }else{
            level.printSolution(solution_node);
        }



    }

}
