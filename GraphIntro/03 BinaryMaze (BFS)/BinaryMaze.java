import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Location {
    int row, col, steps;

    Location(int row, int col, int steps) {
        this.row = row;
        this.col = col;
        this.steps = steps;
    }
}

public class BinaryMaze {
    static int[][] maze;
    static boolean[][] visited;
    static int rows, cols;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("03 BinaryMaze (BFS)/Data files/maze.dat"));
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        int testCases = scanner.nextInt();
        maze = new int[rows][cols];
        visited = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                maze[row][col] = scanner.nextInt();
            }
        }
        for (int test = 0; test < testCases; test++) {
            int startRow = scanner.nextInt();
            int startCol = scanner.nextInt();
            int endRow = scanner.nextInt();
            int endCol = scanner.nextInt();
            int result = bfs(startRow, startCol, endRow, endCol);
            System.out.println(result);
        }
    }

    static int bfs(int startRow, int startCol, int endRow, int endCol) {
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(startRow, startCol, 0));
        visited[startRow][startCol] = true;
        while (!queue.isEmpty()) {
            Location current = queue.remove();
            if (current.row == endRow && current.col == endCol) {
                return current.steps;
            }
            for (int i = 0; i < 4; i++) {
                int nextRow = current.row + dr[i];
                int nextCol = current.col + dc[i];
                if (isValid(nextRow, nextCol) && maze[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.add(new Location(nextRow, nextCol, current.steps + 1));
                }
            }
        }
        return -1;
    }

    static boolean isValid(int row, int col) {
        return (row >= 0 && row < rows) && (col >= 0 && col < cols);
    }
}
