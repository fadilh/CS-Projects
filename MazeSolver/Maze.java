import java.io.File;
import java.util.*;

public class Maze {
    private Square[][] maze;
    private String fileName;
    private Square start;
    private Square exit;

    public boolean loadMaze(String fileName) {
        this.fileName = fileName;
        try {
            File inputFile = new File(this.fileName);
            Scanner reader = new Scanner(inputFile);
            int rows = reader.nextInt();
            int cols = reader.nextInt();
            maze = new Square[rows][cols];
            for (int r = 0; r < maze.length; r++) {
                for (int c = 0; c < maze[r].length; c++) {
                    maze[r][c] = new Square(r, c, reader.nextInt());
                    if (maze[r][c].getType() == Square.START) {start = maze[r][c];}
                    if (maze[r][c].getType() == Square.EXIT) {exit = maze[r][c];}
                }
            }
            reader.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Square> getNeighbors(Square s) {
        List<Square> neighbors = new ArrayList<Square>();
        int r = s.getRow();
        int c = s.getCol();
        
        if (r != 0) {
            neighbors.add(maze[r-1][c]);
        }
        if (r != maze.length - 1) {
            neighbors.add(maze[r+1][c]);
        }
        if (c != 0) {
            neighbors.add(maze[r][c-1]);
        }
        if (c != maze[r].length - 1) {
            neighbors.add(maze[r][c+1]);
        }
        return neighbors;

    }

    public Square getStart() {
        return start;
    }

    public Square getExit() {
        return exit;
    }
    public void reset() {
        loadMaze(fileName);
    }

    public String toString(){
        String output = "";
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                output += maze[r][c].toString() + " ";
            }
            output += "\n";
        }
        return output;
    }
}
