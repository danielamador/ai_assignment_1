package ai_assignment_1;
import java.util.Vector;

import sun.security.provider.certpath.AdjacencyList;

import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;

public class VacuumAgent 
{
	private int score;
	private int steps;
	public static int BAG_CAPACITY = 50;
	private Memory memory;
	
	//This subclass will be the Vacuums memory. It will store and model it's memory
	//while it will discovering a physical Environment
	private class Memory extends AbstractEnvironment
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
			if(cell.adjacencyExistanceFlag[0] == false)
				System.out.println("\tUP side unknown");
			else
				System.out.println("\tCell " + cell.nextUp.label + " on TOP");
			
			if(cell.adjacencyExistanceFlag[1] == false)
				System.out.println("\tLEFT side unknown");
			else
				System.out.println("\tCell " + cell.nextLeft.label + " at LEFT");
			
			if(cell.adjacencyExistanceFlag[2] == false)
				System.out.println("\tDOWN side unknown");
			else
				System.out.println("\tCell " + cell.nextDown.label + " BELOW");
			
			if(cell.adjacencyExistanceFlag[3] == false)
				System.out.println("\tRIGHT side unknown");
			else
				System.out.println("\tCell " + cell.nextRight.label + " at RIGHT");
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
				if(rooms.elementAt(i).state == false)
					return false;
			}
			return true;
		}
		
	}
	
	public void checkEnvironmentCellState()
	{
		memory.currentCell.label = memory.physicalCellReference.label;
		memory.currentCell.state = memory.physicalCellReference.state;
	}
	private void replaceBag(){}
	private void suck()
	{
		System.out.println("Vacuum says: CLEANING...");
		steps++;
		score++;
	}
	
	private void noOp()
	{
		System.out.println("Vacuum says: I'll do nothing this time");
		steps++;
	}
	
	//This is public just for testing purposes. It will be private later
	public void moveRandDirection()
	{
		int dir = memory.generateRand0_3();
		System.out.println("DEBUG: Generated number 0_3: " + dir);
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
		System.out.println("\tDEBUG: checkedAdjacency[dir]: " + memory.currentCell.checkedAdjacency[dir]);
		if(memory.currentCell.checkedAdjacency[dir] == true)
		{
			System.out.println("\tI know this direction already!");
			if (flag == POSITIONS_FLAGS.UP)
			{
				memory.currentCell = memory.currentCell.nextUp;
			}
			else if(flag == POSITIONS_FLAGS.LEFT)
				memory.currentCell = memory.currentCell.nextLeft;
			else if(flag == POSITIONS_FLAGS.DOWN)
				memory.currentCell = memory.currentCell.nextDown;
			else
				memory.currentCell = memory.currentCell.nextRight;
			//memory.currentCell =  memory.insertCell(memory.rooms.size(), flag);
		}
		//else, you must check if there's room in the chosen direction. If is there, you
		//must insert this new cell in the memory
		else
		{
			System.out.println("\tI still don't know this direction! Let's try!");
			
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
				}
				else
					System.out.println("\tOUCH! Trying to go UP, I just crashed a wall!");
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
				}	
				else
					System.out.println("\tOUCH! Trying to go LEFT, I just crashed a wall!");
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
				}	
				else
					System.out.println("\tOUCH! Trying to go DOWN, I just crashed a wall!");
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
				}	
				else
					System.out.println("\tOUCH! Trying to go RIGHT, I just crashed a wall!");
			}
		}
	}
	
	public VacuumAgent()
	{
		memory = new Memory();
	}
	
	public void start(PhysicalEnvironment environment, int initialEnvRoom)
	{
		System.out.println("Vacuum says: Hello human! I'm ready to work!");
		
		
		if(initialEnvRoom > environment.rooms.size())
		{
			System.out.println("Vacuum couldn't be initialised. The referenced " +
					"room doesn't exist");
			return;
		}
		memory.currentCell = memory.rooms.elementAt(0);
		memory.physicalCellReference = environment.rooms.elementAt(initialEnvRoom);
		//Don't forget the label
		memory.rooms.elementAt(0).label = memory.physicalCellReference.label;
		memory.printCells();
	}
	
	public void showMemory(){memory.printCells();}
	
	public void stop(){}
	public void showPerformance(){}
}