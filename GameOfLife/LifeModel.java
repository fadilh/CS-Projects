import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Timer;
import javax.swing.text.View;

public class LifeModel implements ActionListener
{
	private static int SIZE = 60;
	private LifeCell[][] grid;
	
	LifeView myView;
	Timer timer;

	/** Construct a new model using a particular file */
	public LifeModel(LifeView view, String fileName) throws IOException
	{       
		reset(view, fileName);

	}

	/** Constructor a randomized model */
	public LifeModel(LifeView view) throws IOException
	{
		this(view, null);
	}

	/** pause the simulation (the pause button in the GUI */
	public void pause()
	{
		timer.stop();
	}
	
	/** resume the simulation (the pause button in the GUI */
	public void resume()
	{
		timer.restart();
	}

	public void reset(LifeView view, String fileName) throws FileNotFoundException
	{
		int r, c;
		grid = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new LifeCell();

		if ( fileName == null ) //use random population
		{                                           
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		}
		else
		{                 
			Scanner input = new Scanner(new File(fileName));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
		}

		myView = view;
		myView.updateView(grid);
	}

	public void randomizeColor() {
		myView.setRandomColor(!myView.getRandomColor());
		myView.updateView(grid);
	}
	
	/** run the simulation (the pause button in the GUI */
	public void run()
	{
		timer = new Timer(50, this);
		timer.setCoalesce(true);
		timer.start();
	}

	/** called each time timer fires */
	public void actionPerformed(ActionEvent e)
	{
		oneGeneration();
		myView.updateView(grid);
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration()
	{
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				ArrayList<LifeCell> lifeCells = new ArrayList<>();
				LifeCell currentCell = grid[r][c];

				if (r != 0) {
					lifeCells.add(grid[r-1][c]);
					if (c != 0) {
						lifeCells.add(grid[r-1][c-1]);
					}
					if (c != grid[r].length - 1) {
						lifeCells.add(grid[r-1][c+1]);
					}
				} 
				if (r != grid.length - 1) {
					lifeCells.add(grid[r+1][c]);
					if (c != 0) {
						lifeCells.add(grid[r+1][c-1]);
					}
					if (c != grid[r].length - 1) {
						lifeCells.add(grid[r+1][c+1]);
					}
				} 
				if (c != 0) {
					lifeCells.add(grid[r][c-1]);
				}
				if (c != grid[r].length - 1) {
					lifeCells.add(grid[r][c+1]);
				}
				
				int neighbors = checkNumOfAliveCells(lifeCells);
				if ((neighbors == 2 || neighbors == 3) && currentCell.isAliveNow()) {
					currentCell.setAliveNext(true);
				} else if ((neighbors == 3 && !currentCell.isAliveNow())) {
					currentCell.setAliveNext(true);
				} else if (neighbors == 0 || neighbors == 1 || neighbors == 4 || neighbors == 5 || neighbors == 6 || neighbors == 7 || neighbors == 8){
					currentCell.setAliveNext(false);
				}
			}
		}
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				grid[r][c].setAliveNow(grid[r][c].isAliveNext());
			}
		}
	}

	public int checkNumOfAliveCells(ArrayList<LifeCell> lifeCells) {
		int count = 0;
		for (LifeCell lifeCell : lifeCells) {
			if (lifeCell.isAliveNow()) {
				count++;
			}
		}

		return count;
	}
}

