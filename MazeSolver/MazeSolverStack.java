public class MazeSolverStack extends MazeSolver {
    private MyStack stack;

    public MazeSolverStack(Maze maze) {
        super(maze);
        stack = new MyStack();
        add(maze.getStart());
    }

    @Override
    public void makeEmpty() {
        while (!isEmpty()) {
            stack.pop();
        }        
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void add(Square s) {
        stack.push(s);
    }

    @Override
    public Square next() {
        return stack.pop();
    }
    
}
