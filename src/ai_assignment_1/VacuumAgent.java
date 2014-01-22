package ai_assignment_1;
import java.util.Vector;

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
				System.out.println("\tCell " + cell.nextLeft.label + " BELOW");
			
			if(cell.adjacencyExistanceFlag[3] == false)
				System.out.println("\tRIGHT side unknown");
			else
				System.out.println("\tCell " + cell.nextLeft.label + " at RIGHT");
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
		
	}
	
	private void checkEnvironmentCellState(){}
	private void replaceBag(){}
	private void suck(){}
	private void noOp(){}
	private void moveLeft(){}
	private void moveRight(){}
	
	public VacuumAgent()
	{
		memory = new Memory();
	}
	
	public void start(PhysicalEnvironment environment, int initialEnvRoom)
	{
		System.out.println("Vacuum says: Hello human! I'm ready to work!");
		
		
		if(initialEnvRoom > environment.rooms.size())
		{
			System.out.println("Vacuum couldn't have been initialised. The referenced " +
					"room doesn't exist");
			return;
		}
		
	}
	
	public void showMemory(){memory.printCells();}
	
	public void stop(){}
	public void showPerformance(){}
}