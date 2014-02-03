package ai_assignment_1;
import java.util.Vector;


public class VacuumAgent 
{
	private int score;
	private int steps;
	public static int TOTAL_STEPS = 1000;
	public static int BAG_CAPACITY = 50;
	public Memory memory;
	
	//This subclass will be the Vacuums memory. It will store and model it's memory
	//while it will discovering a physical Environment
	public class Memory extends AbstractEnvironment
	{
		Cell currentCell;
		//this reference will be needed to read the state of the physical environment
		Cell physicalCellReference;
		
		public Memory()
		{
			super();
		}
		
		public void printAdjacencyCellState(Cell cell)
		{
			//UP
			if(cell.checkedAdjacency[0] == false)
				System.out.println("\tUP side unknown");
			else
			{
				if(cell.adjacencyExistanceFlag[0] == false)
					System.out.println("\tThere's a wall on TOP");
				else
					System.out.println("\tCell " + cell.nextUp.label + " on TOP");
			}
			//LEFT
			if(cell.checkedAdjacency[1] == false)
				System.out.println("\tLEFT side unknown");
			else
			{
				if(cell.adjacencyExistanceFlag[1] == false)
					System.out.println("\tThere's a wall at LEFT");
				else
					System.out.println("\tCell " + cell.nextLeft.label + " at LEFT");
			}
			//DOWN
			if(cell.checkedAdjacency[2] == false)
				System.out.println("\tDown side unknown");
			else
			{
				if(cell.adjacencyExistanceFlag[2] == false)
					System.out.println("\tThere's a wall BELOW");
				else
					System.out.println("\tCell " + cell.nextDown.label + " BELOW");
			}
			//RIGHT
			if(cell.checkedAdjacency[3] == false)
				System.out.println("\tRight side unknown");
			else
			{
				if(cell.adjacencyExistanceFlag[3] == false)
					System.out.println("\tThere's a wall at RIGHT");
				else
					System.out.println("\tCell " + cell.nextRight.label + " at Right");
			}
			
			
		}
		
		@Override
		public void printCells() 
		{
			System.out.println("Vacuum says: Look what I have in my memory:");
			for (int i = 0; i < rooms.size(); ++i)
			{
				System.out.println("Cell " + i + " (" + rooms.elementAt(i).label + "): ");
				if (rooms.elementAt(i).state == true)
					System.out.print("\tDirty\n");
				else
					System.out.print("\tClean\n");
				
				printAdjacencyCellState(rooms.elementAt(i));
			}
		}
		
		public boolean knowsWholeEnvironment()
		{
			for (int i = 0; i < rooms.size(); ++i)
			{
				for (int j = 0; j < 4; ++j)
				{
					if(rooms.elementAt(i).checkedAdjacency[j] == false)
						return false;
				}
			}
			return true;
		}
		
		public boolean isEverythingClean()
		{
			for (int i = 0; i < rooms.size(); ++i)
			{
				if(rooms.elementAt(i).state == true)//Remember that cell state true is dirty
					return false;
			}
			return true;
		}
		
	}
	
	public void checkEnvironmentCellState()
	{
		memory.currentCell.label = memory.physicalCellReference.label;
		memory.currentCell.state = memory.physicalCellReference.state;
		System.out.println("Vacuum says: Checking Cell " + memory.currentCell.label + " state...");
		steps++;

	}
	private void suck()
	{
		System.out.println("Vacuum says: CLEANING...");
		memory.currentCell.state = false;
		memory.physicalCellReference.state = false;
		steps++;
		score++;
	}
	
	private void noOp()
	{
		System.out.println("Vacuum says: I'll do nothing this time");
	}
	
	//This is public just for testing purposes. It will be private later
	//true if can move to another room, false if hits a wall
	public moveTrialState moveRandDirection()
	{
		int dir = memory.generateRand0_3();
		//System.out.println("DEBUG: Generated number 0_3: " + dir);
		POSITIONS_FLAGS flag = null;
		
		System.out.println("Vacuum says: I'll move to a random direction");
		
		switch(dir)
		{
			case 0:
				flag = POSITIONS_FLAGS.UP;
				System.out.println("\tMove UP");
				break;
			case 1:
				flag = POSITIONS_FLAGS.LEFT;
				System.out.println("\tMove LEFT");
				break;
			case 2:
				flag = POSITIONS_FLAGS.DOWN;
				System.out.println("\tMove DOWN");
				break;
			case 3:
				flag = POSITIONS_FLAGS.RIGHT;
				System.out.println("\tMove RIGHT");
				break;
		}
		
		//if the vacuum knows the next cell in the direction, just move to there
		//System.out.println("\tDEBUG: checkedAdjacency[dir]: " + memory.currentCell.checkedAdjacency[dir]);
		if(memory.currentCell.checkedAdjacency[dir] == true)
		{
			System.out.println("\tI know this direction already!");
			if(memory.currentCell.adjacencyExistanceFlag[dir] == false)
			{
				System.out.println("There's a wall, so I won't move.");
				return moveTrialState.KNOWN_WALL;
			}
			else
			{
				steps++;
				if (flag == POSITIONS_FLAGS.UP)
				{
					memory.currentCell = memory.currentCell.nextUp;
					memory.physicalCellReference = memory.physicalCellReference.nextUp;
					System.out.println("\tI could move up with no problems");
				}
				else if(flag == POSITIONS_FLAGS.LEFT)
				{
					memory.currentCell = memory.currentCell.nextLeft;
					memory.physicalCellReference = memory.physicalCellReference.nextLeft;
					System.out.println("\tI could move left with no problems");
				}
					
				else if(flag == POSITIONS_FLAGS.DOWN)
				{
					memory.currentCell = memory.currentCell.nextDown;
					memory.physicalCellReference = memory.physicalCellReference.nextDown;
					System.out.println("\tI could move down with no problems");
				}
					
				else
				{
					memory.currentCell = memory.currentCell.nextRight;
					memory.physicalCellReference = memory.physicalCellReference.nextRight;
					System.out.println("\tI could move right with no problems");
				}
					
				return moveTrialState.MOVE_COMPLETE;
			}
			
		}
		//else, you must check if there's room in the chosen direction. If is there, you
		//must insert this new cell in the memory
		else
		{
			System.out.println("\tI still don't know this direction! Let's try!");
			steps++;
			memory.currentCell.checkedAdjacency[dir] = true;
			if (flag == POSITIONS_FLAGS.UP)
			{
				if(memory.physicalCellReference.nextUp != null)
				{
					memory.currentCell.adjacencyExistanceFlag[dir] = true;
					//alloc a new cell up
					memory.insertCell(memory.rooms.indexOf(memory.currentCell), flag);
					
					memory.currentCell.nextUp.checkedAdjacency[2] = true;
					memory.currentCell.nextUp.adjacencyExistanceFlag[2] = true;
					
					//now the current cell and reference are on the respective cells above
					memory.physicalCellReference = memory.physicalCellReference.nextUp;
					memory.currentCell = memory.currentCell.nextUp;
					
					System.out.println("\tI could move up with no problems");
					return moveTrialState.MOVE_COMPLETE;
				}
				else
				{
					System.out.println("\tOUCH! Trying to go UP, I just crashed a wall!");
					return moveTrialState.WALL_FOUND;
				}
			}
	
			else if (flag == POSITIONS_FLAGS.LEFT)
			{
				if(memory.physicalCellReference.nextLeft != null)
				{
					memory.currentCell.adjacencyExistanceFlag[dir] = true;
					memory.insertCell(memory.rooms.indexOf(memory.currentCell), flag);
					memory.currentCell.nextLeft.checkedAdjacency[3] = true;
					memory.currentCell.nextLeft.adjacencyExistanceFlag[3] = true;
					memory.physicalCellReference = memory.physicalCellReference.nextLeft;
					memory.currentCell = memory.currentCell.nextLeft;
					
					System.out.println("\tI could move left with no problems");
					return moveTrialState.MOVE_COMPLETE;
				}	
				else
				{
					System.out.println("\tOUCH! Trying to go LEFT, I just crashed a wall!");
					return moveTrialState.WALL_FOUND;
				}
			}
			
			else if (flag == POSITIONS_FLAGS.DOWN)
			{
				if(memory.physicalCellReference.nextDown != null)
				{
					memory.currentCell.adjacencyExistanceFlag[dir] = true;
					memory.insertCell(memory.rooms.indexOf(memory.currentCell), flag);
					memory.currentCell.nextDown.checkedAdjacency[0] = true;
					memory.currentCell.nextDown.adjacencyExistanceFlag[0] = true;
					memory.physicalCellReference = memory.physicalCellReference.nextDown;
					memory.currentCell = memory.currentCell.nextDown;
					
					System.out.println("\tI could move down with no problems");
					return moveTrialState.MOVE_COMPLETE;
				}	
				else
				{	
					System.out.println("\tOUCH! Trying to go DOWN, I just crashed a wall!");
					return moveTrialState.WALL_FOUND;
				}
			}
			
			else
			{
				if(memory.physicalCellReference.nextRight != null)
				{
					memory.currentCell.adjacencyExistanceFlag[dir] = true;
					memory.insertCell(memory.rooms.indexOf(memory.currentCell), flag);
					memory.currentCell.nextRight.checkedAdjacency[1] = true;
					memory.currentCell.nextRight.adjacencyExistanceFlag[1] = true;
					memory.physicalCellReference = memory.physicalCellReference.nextRight;
					memory.currentCell = memory.currentCell.nextRight;
					
					System.out.println("\tI could move right with no problems");
					return moveTrialState.MOVE_COMPLETE;
				}	
				else
				{
					System.out.println("\tOUCH! Trying to go RIGHT, I just crashed a wall!");
					return moveTrialState.WALL_FOUND;
				}
			}
		}
		
	}
	
	public VacuumAgent()
	{
		memory = new Memory();
	}
	
	public void start(PhysicalEnvironment environment, int initialEnvRoom, boolean periodicalDirty)
	{
		System.out.println("Vacuum says: Hello human! I'm ready to work!");
			
		if(initialEnvRoom > environment.rooms.size())
		{
			System.out.println("Vacuum could not be initialised. The referenced " +
					"room doesn't exist");
			return;
		}
		memory.currentCell = memory.rooms.elementAt(0);
		memory.physicalCellReference = environment.rooms.elementAt(initialEnvRoom);
		//Don't forget the label
		checkEnvironmentCellState();
		if(memory.currentCell.state == true)
			suck();
		memory.printCells();
		//main loop
		while((!memory.isEverythingClean() || !memory.knowsWholeEnvironment()) && steps < TOTAL_STEPS)
		{
			//System.out.println("DEBUG: isEverythingClean: " + memory.isEverythingClean());
			//System.out.println("DEBUG: knowsWholeEnvironment: " + memory.knowsWholeEnvironment());
			System.out.println("Vacuum says: I am in cell " + memory.currentCell.label);

			moveTrialState movState;
			movState = moveRandDirection();
			while(movState == moveTrialState.KNOWN_WALL)
			{
				movState = moveRandDirection();
			}
			
			if(movState == moveTrialState.MOVE_COMPLETE)
			{
				checkEnvironmentCellState();
				if(memory.currentCell.state == true)
					suck();
			}
			
			System.out.println("Vacuum says: Memory after this round:");
			memory.printCells();
			System.out.println("PRESS ENTER TO CONTINUE");
			new java.util.Scanner(System.in).nextLine();
		}
		System.out.println("Vacuum says: I'm done in cleaning!");
		showPerformance();
	}
	
	
	public void showMemory(){memory.printCells();}
	
	public void showPerformance()
	{
		System.out.println("Performance: " + steps + " steps\n" + score + " sucks");
		float effect = (score/(float)steps) * 100;
		System.out.println("Effectiveness: " + (effect) + "%");
	}
}