package ai_assignment_1;
import java.util.Vector;

abstract public class AbstractEnvironment 
{
	//These attributes are public to approach this implementation to domain's problem.
	//If we think a physical space, every entity can access and change its attributes.
	public Vector<Cell> rooms;
	
	abstract public void printCells();
	
	protected boolean generateRandBool()
	{
		double temp;
		temp = Math.random();
		if(temp < 0.5)
			return false;
		else
			return true;
	}
	
	public AbstractEnvironment()
	{
		rooms = new Vector<Cell>();
		rooms.add(new Cell());
		rooms.elementAt(0).state = generateRandBool();
	}
	
	public void insertCell(int position, POSITIONS_FLAGS flag)
	{
		//If we have time, change this for one exception later
		if(position > rooms.size())
		{
			System.out.println("Invalid Cell Position!");
			return;
		}
		
		rooms.add(new Cell());
		int currentLastPosition = rooms.size() - 1;
		rooms.elementAt(currentLastPosition).state = generateRandBool();
		
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
	}
	
}
