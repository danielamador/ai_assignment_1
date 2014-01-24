package ai_assignment_1;
import java.util.Vector;


public class PhysicalEnvironment extends AbstractEnvironment 
{
	public String envName;
	private char nextLabel;
	
	@Override
	public void printCells()
	{
		System.out.println("Environment " + envName +" cells: ");
		for (int i = 0; i < rooms.size(); ++i)
		{
			System.out.println("Cell " + i + " (" + rooms.elementAt(i).label + "): ");
			if (rooms.elementAt(i).state == true)
				System.out.print("\tDirty\n");
			else
				System.out.print("\tClean\n");
		}
	}
	
	public PhysicalEnvironment(String name)
	{
		
		super();
		envName = name;
		nextLabel = 'A';
		
		rooms.elementAt(0).state = generateRandBool();
		rooms.elementAt(0).label = new StringBuilder().append(nextLabel).toString();
		nextLabel += 1;
		
		System.out.println("Initial Cell " + rooms.elementAt(0).label + " was created!");
	}
	
	@Override
	public Cell insertCell(int position, POSITIONS_FLAGS flag)
	{
		//If we have time, change this for one exception later
		if(position > rooms.size())
		{
			System.out.println("Invalid Cell Position!");
			return null;
		}
		
		rooms.add(new Cell());
		int currentLastPosition = rooms.size() - 1;
		rooms.elementAt(currentLastPosition).label = new StringBuilder().append(nextLabel).toString();
		rooms.elementAt(currentLastPosition).state = generateRandBool();
		nextLabel += 1;
		
		if(flag == POSITIONS_FLAGS.UP)
		{
			rooms.elementAt(position).nextUp = rooms.elementAt(currentLastPosition);
			System.out.println("Cell " + rooms.elementAt(currentLastPosition).label + " was " +
					"inserted above Cell " + rooms.elementAt(position).label + "!");
		}
		else if(flag == POSITIONS_FLAGS.LEFT)
		{
			rooms.elementAt(position).nextLeft = rooms.elementAt(currentLastPosition);
			System.out.println("Cell " + rooms.elementAt(currentLastPosition).label + " was " +
					"inserted at the left from Cell " + rooms.elementAt(position).label + "!");
		}
		else if(flag == POSITIONS_FLAGS.DOWN)
		{
			rooms.elementAt(position).nextDown = rooms.elementAt(currentLastPosition);
			System.out.println("Cell " + rooms.elementAt(currentLastPosition).label + " was " +
					"inserted below Cell " + rooms.elementAt(position).label + "!");
		}
		else
		{
			rooms.elementAt(position).nextRight = rooms.elementAt(currentLastPosition);
			System.out.println("Cell " + rooms.elementAt(currentLastPosition).label + " was " +
					"inserted at the right from Cell " + rooms.elementAt(position).label + "!");
			
		}
		
		return rooms.elementAt(currentLastPosition);//returns the reference of the last cell added

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
