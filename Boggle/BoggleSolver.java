import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class BoggleSolver
{

	HashSet<String> dictionary;
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
	public BoggleSolver(String dictionaryName)
	{
		dictionary = new HashSet<>();
		try {
			Scanner scanner = new Scanner(new File(dictionaryName));
			while (scanner.hasNextLine()) {
				dictionary.add(scanner.nextLine());
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println("file not found");
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		boolean visited[][] = new boolean[board.rows()][board.cols()];
		HashSet<String> validWords = new HashSet<>();

		for (int r = 0; r < board.rows(); r++) {
			for (int c = 0; c < board.cols(); c++) {
				
			}
		}

		return null;
	}

	private String getValidWords(char boggle[][], Boolean visited[][], int i,
                   int j, String str)
	{
		visited[i][j] = true;
        str = str + boggle[i][j];
 
        // If str is present in dictionary, then print it
        if (dictionary.contains(str))
            return str;
 
        // Traverse 8 adjacent cells of boggle[i][j]
        for (int row = i - 1; row <= i + 1 && row < M; row++)
            for (int col = j - 1; col <= j + 1 && col < N; col++)
                if (row >= 0 && col >= 0 && !visited[row][col])
                    getValidWords(boggle, visited, row, col, str);
 
        // Erase current character from string and mark visited
        // of current cell as false
        str = "" + str.charAt(str.length() - 1);
        visited[i][j] = false;
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word)
	{
		if (!dictionary.contains(word) || word.length() <=2) { return 0; } 
		if (word.length() <= 4) { return 1; } 
		if (word.length() == 5) { return 2; } 
		if (word.length() == 6) { return 3; } 
		if (word.length() == 7) { return 5; } 

		return 11;
	}

	public static void main(String[] args) {
		System.out.println("WORKING");

		final String PATH   = "./data/";
		BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); //should print 84

		//new BoggleGame(4, 4);
	}

}
