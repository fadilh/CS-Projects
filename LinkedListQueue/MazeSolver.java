import java.util.*;

public abstract class MazeSolver {
    final static String SOLVED = "solved";
    final static String NOTYETSOLVED = "not yet solved";
    final static String UNSOLVABLE = "unsolvable";
    private Maze maze;
    private String mazeSolveStatus; // "." - solved, "," - not yet solved, "x" - unsolvable
    private boolean foundExit;
    private boolean isSolvable;
    public MazeSolver(Maze maze) {
        this.maze = maze;
        mazeSolveStatus = NOTYETSOLVED;
    }

    public abstract void makeEmpty();
    public abstract boolean isEmpty();
    public abstract void add(Square s);
    public abstract Square next();

    public boolean isSolved() {
        return mazeSolveStatus.equals(SOLVED);
    }

    public void step(){
        if (isEmpty()) {
            mazeSolveStatus = SOLVED;
            isSolvable = false;
        }
        if (!isEmpty()) {
            Square nextLoc = next();
            nextLoc.setStatus(Square.EXPLORED);
            if (nextLoc.getType() != Square.EXIT) {
                List<Square> neighbors = maze.getNeighbors(nextLoc);
                for (Square neighbor : neighbors) {
                    if (neighbor.getType() != Square.WALL && neighbor.getStatus() != Square.EXPLORED) {
                        neighbor.setStatus(Square.WORKING);
                        neighbor.setPrevious(nextLoc);
                        add(neighbor);
                    }
                }
            } else if (nextLoc.getType() == Square.EXIT) {
                mazeSolveStatus = SOLVED;
            }
        }
        else {
            mazeSolveStatus = UNSOLVABLE;
        }
    }

    public String getPath() {
        String path="";
        Square current = this.maze.getExit();

        while (current.getPrevious() != current) {
            current.setStatus(Square.ON_EXIT_PATH);
            path += "[" + current.getRow() + ", " + current.getCol() + "]";
            current = current.getPrevious();
        }
        return path;
    }

    // public void solve() {
    //     while (!mazeSolveStatus.equals(SOLVED) && !mazeSolveStatus.equals(UNSOLVABLE)) {
    //         step();
    //     }
    // }
}
