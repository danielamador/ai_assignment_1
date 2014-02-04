package ai_assignment_1;

public class Cell {
	public boolean state;//True for Dirty, False for clean
	public String label;
	Cell nextUp;
	Cell nextLeft;
	Cell nextDown;
	Cell nextRight;
	
	//this will be used just for the vacuum cleaner memory. I don't want to use
	//inheritance there to create just one attribute
	//0 UP - 1 LEFT - 2 DOWN - 3 RIGHT
	boolean adjacencyExistanceFlag [];
	boolean checkedAdjacency [];
	boolean knowState;
	
	public Cell()
	{
		adjacencyExistanceFlag = new boolean [4];
		checkedAdjacency = new boolean [4];
		
		for (int i=0; i < 4; ++i)
		{
			adjacencyExistanceFlag[i] = false;
			checkedAdjacency[i] = false;
		}
	}
	
}
