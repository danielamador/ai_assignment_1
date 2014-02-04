package ai_assignment_1;


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
			
			printAdjacencyCellState(rooms.elementAt(i));
		}
		
	}
	
	public void printAdjacencyCellState(Cell cell)
	{
		if(cell.nextUp == null)
			System.out.println("\tUP side inexistant");
		else
			System.out.println("\tCell " + cell.nextUp.label + " on TOP");
		
		if(cell.nextLeft == null)
			System.out.println("\tLEFT side inexistant");
		else
			System.out.println("\tCell " + cell.nextLeft.label + " at LEFT");
		
		if(cell.nextDown == null)
			System.out.println("\tDOWN side inexistant");
		else
			System.out.println("\tCell " + cell.nextDown.label + " BELOW");
		
		if(cell.nextRight == null)
			System.out.println("\tRIGHT side inexistant");
		else
			System.out.println("\tCell " + cell.nextRight.label + " at RIGHT");
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
			rooms.elementAt(currentLastPosition).nextDown = rooms.elementAt(position);
			System.out.println("Cell " + rooms.elementAt(currentLastPosition).label + " was " +
					"inserted above Cell " + rooms.elementAt(position).label + "!");
		}
		else if(flag == POSITIONS_FLAGS.LEFT)
		{
			rooms.elementAt(position).nextLeft = rooms.elementAt(currentLastPosition);
			rooms.elementAt(currentLastPosition).nextRight = rooms.elementAt(position);
			System.out.println("Cell " + rooms.elementAt(currentLastPosition).label + " was " +
					"inserted at the left from Cell " + rooms.elementAt(position).label + "!");
		}
		else if(flag == POSITIONS_FLAGS.DOWN)
		{
			rooms.elementAt(position).nextDown = rooms.elementAt(currentLastPosition);
			rooms.elementAt(currentLastPosition).nextUp = rooms.elementAt(position);
			System.out.println("Cell " + rooms.elementAt(currentLastPosition).label + " was " +
					"inserted below Cell " + rooms.elementAt(position).label + "!");
		}
		else
		{
			rooms.elementAt(position).nextRight = rooms.elementAt(currentLastPosition);
			rooms.elementAt(currentLastPosition).nextLeft = rooms.elementAt(position);
			System.out.println("Cell " + rooms.elementAt(currentLastPosition).label + " was " +
					"inserted at the right from Cell " + rooms.elementAt(position).label + "!");
			
		}
		
		return rooms.elementAt(currentLastPosition);//returns the reference of the last cell added

	}
	
	@Override
	public int numberOfCleanCells()
	{
		int count = 0;
		for (int i = 0; i < rooms.size(); ++i)
		{
			if (rooms.elementAt(i).state == false)
				++count;
		}
		return count;
	}
	
	public void generateDirt()
	{
		for (int i = 0; i < rooms.size(); ++i)
		{
			if(rooms.elementAt(i).state == false)
				rooms.elementAt(i).state = generateRandBool();
		}
		System.out.println("DIRTY WAS GENERATED");
	}
}
