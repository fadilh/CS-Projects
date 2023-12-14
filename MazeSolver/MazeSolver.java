import java.util.*;

public abstract class MazeSolver {
    final static String SOLVED = "solved";
    final static String NOTYETSOLVED = "not yet solved";
    final static String UNSOLVABLE = "unsolvable";
    private Maze maze;
    private String mazeSolveStatus; // "." - solved, "," - not yet solved, "x" - unsolvable

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
        if (!isEmpty()) {
            Square nextLoc = next();
            nextLoc.setStatus(Square.EXPLORED);
            if (nextLoc.getType() != Square.EXIT) {
                List<Square> neighbors = maze.getNeighbors(nextLoc);
                for (Square neighbor : neighbors) {
                    if (neighbor.getType() != Square.WALL && neighbor.getStatus() != Square.EXPLORED) {
                        neighbor.setStatus(Square.WORKING);
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
        return mazeSolveStatus;
    }

    public void solve() {
        while (!getPath().equals(SOLVED) && !getPath().equals(UNSOLVABLE)) {
            step();
        }
    }
}
