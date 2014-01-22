package ai_assignment_1;
import java.util.Vector;

import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;

public class VacuumAgent 
{
	private int score;
	private int steps;
	public static int BAG_CAPACITY = 50;
	private Vector<Cell> memory;
	
	private void checkEnvironmentCellState(){}
	private void replaceBag(){}
	private void suck(){}
	private void noOp(){}
	private void moveLeft(){}
	private void moveRight(){}
	
	public VacuumAgent()
	{
		memory = new Vector<Cell>();
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
	
	public void stop(){}
	public void printMemoryContent()
	{
		System.out.println("Vacuum Memory: ");
		for (int i = 0; i < memory.size(); ++i)
		{
			System.out.println("Cell " + i + " (" + memory.elementAt(i).label + "): ");
			if (memory.elementAt(i).state == true)
				System.out.print("Dirty");
			else
				System.out.print("Clean");
				
		}
	}
	public void showPerformance(){}
}