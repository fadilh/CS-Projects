public class FireModel
{
    public static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;

    public FireModel(FireView view)
    {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int r=0; r<SIZE; r++)
        {
            for (int c=0; c<SIZE; c++)
            {
                myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

    /*
        recursiveFire method here
     */
    public void fireSpread(int r, int c) {
        if (r >= SIZE || r < 0 || c >= SIZE || c < 0) {
            return;
        } 
        if (myGrid[r][c].getStatus() == FireCell.DIRT || myGrid[r][c].getStatus() == FireCell.BURNING) {
            return;
        }
        myGrid[r][c].setStatus(FireCell.BURNING);
        fireSpread(r+1, c);
        fireSpread(r-1, c);
        fireSpread(r, c+1);
        fireSpread(r, c-1);
    }

    public void solve()
    {
        for (int i = 0; i < FireModel.SIZE; i++) {
            fireSpread(FireModel.SIZE-1, i);
        }
        boolean safe = true;
        for (int i = 0; i < SIZE; i++) {
            if (myGrid[0][i].getStatus() == FireCell.BURNING) { safe = false; }
        }
        System.out.println(safe ? "Onett is safe" : "Onett is in danger");
        myView.updateView(myGrid);
    }

}
