package ai_assignment_1;
import java.util.Vector;

import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;

public class VacuumAgent 
{
	private int score;
	private int steps;
	public static int BAG_CAPACITY = 50;
	
	private java.util.ListIterator<Cell> actualRoomReference;
	public class memCell extends Cell
	{
		private boolean firstLeft;
		private boolean lastRight;
	};
	
	private Vector<memCell> memory;
	
	private void checkEnvironmentCellState(){}
	private void replaceBag(){}
	private void suck(){}
	private void noOp(){}
	private void moveLeft(){}
	private void moveRight(){}
	
	public VacuumAgent()
	{
		memory = new Vector<memCell>();
	}
	
	public void start(Environment environment, int initialEnvRoom)
	{
		System.out.println("Vacuum says: Hello human! I'm ready to work!");
		
		actualRoomReference = environment.rooms.listIterator(initialEnvRoom);
		
		if(initialEnvRoom > environment.rooms.size())
		{
			System.out.println("Vacuum couldn't have been initialised. The referenced " +
					"room doesn't exist");
			return;
		}
		
		memory.add(new memCell());
		memory.elementAt(0).state = environment.rooms.elementAt(initialEnvRoom).state;
		memory.elementAt(0).label = environment.rooms.elementAt(initialEnvRoom).label;
		printMemoryContent();
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
			if (memory.elementAt(i).firstLeft)
				System.out.print("   IS FIRST LEFT!\n");
			if (memory.elementAt(i).lastRight)
				System.out.print("   IS LAST RIGHT!\n");
				
		}
	}
	public void showPerformance(){}
}