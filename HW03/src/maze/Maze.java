package maze;

import java.io.IOException;
import java.util.ArrayList;

import radar.Node;
import radar.Radar;

public class Maze {
    private Radar r;


    public Maze(String fileName) {
        try {
            r = new Radar(fileName);
        } catch (IOException e) {
            r = null;
        }
    }


    public ArrayList<Character> solve() {

        // TODO

    }
    
    
    public static void main(String[] args) {
        Maze m = new Maze("15.in");
        ArrayList<Character> result = m.solve();
        System.out.println("Result: " + result);
    }
    
}