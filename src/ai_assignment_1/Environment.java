package ai_assignment_1;
import java.util.Vector;

public class Environment 
{
		
	//These attributes are public to approach this implementation to domain's problem.
	//If we think a physical space, every entity can access and change its attributes.
	public Vector<Cell> rooms;
	public String envName;
	
	private boolean generateRandBool()
	{
		double temp;
		temp = Math.random();
		if(temp < 0.5)
			return false;
		else
			return true;
	}
	
	private void printCells()
	{
		System.out.println("Environment " + envName +" cells: ");
		for (int i = 0; i < rooms.size(); ++i)
		{
			System.out.println("Cell " + i + " (" + rooms.elementAt(i).label + "): ");
			if (rooms.elementAt(i).state == true)
				System.out.print("Dirty\n");
			else
				System.out.print("Clean\n");
		}
	}
	
	public Environment(int numCells, String name)
	{
		
		envName = name;
		char tempLabel = 'A';
		rooms = new Vector<Cell>();
		
		for (int i = 0; i < numCells; ++i)
		{
			rooms.add(new Cell());
			rooms.elementAt(i).label = new StringBuilder().append(tempLabel).toString();
			rooms.elementAt(i).state = generateRandBool();
			tempLabel += 1;
		}
		printCells();
	}
	
	
	public void generateDirt()
	{
		for (int i = 0; i < rooms.size(); ++i)
		{
			if(rooms.elementAt(i).state == false)
				rooms.elementAt(i).state = generateRandBool();
		}
	}
}
