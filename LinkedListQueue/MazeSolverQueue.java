public class MazeSolverQueue extends MazeSolver{
    private MyQueue queue;

    public MazeSolverQueue(Maze maze) {
        super(maze);
        queue = new MyQueue();
        makeEmpty();
        add(maze.getStart());
    }

    @Override
    public void makeEmpty() {
        while (isEmpty()) {
            queue.poll();
        }        
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void add(Square s) {
        queue.offer(s);        
    }

    @Override
    public Square next() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
