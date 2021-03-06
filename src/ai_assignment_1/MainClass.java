package ai_assignment_1;

public class MainClass 
{

	public static void letterB()
	{
		VacuumAgent vacuum = new VacuumAgent();
		PhysicalEnvironment env = new PhysicalEnvironment("All Clean");
//		PhysicalEnvironment env = new PhysicalEnvironment("First Clean");		
//		PhysicalEnvironment env = new PhysicalEnvironment("Second Clean");		
//		PhysicalEnvironment env = new PhysicalEnvironment("All Dirty");		
		
		env.insertCell(0, POSITIONS_FLAGS.RIGHT);
		vacuum.memory.insertCell(0, POSITIONS_FLAGS.RIGHT);
		
		env.rooms.elementAt(0).state = true; //true for Second Clean and All Dirty
		env.rooms.elementAt(1).state = true; //true for First Clean and All Dirty
		
		for (int i = 0; i < 4; ++i)
		{
			vacuum.memory.rooms.get(0).checkedAdjacency[i] = true;
			vacuum.memory.rooms.get(1).checkedAdjacency[i] = true;
		}
		
		vacuum.memory.rooms.get(0).adjacencyExistanceFlag[0] = false;
		vacuum.memory.rooms.get(0).adjacencyExistanceFlag[1] = false;
		vacuum.memory.rooms.get(0).adjacencyExistanceFlag[2] = false;
		vacuum.memory.rooms.get(1).adjacencyExistanceFlag[0] = false;
		vacuum.memory.rooms.get(1).adjacencyExistanceFlag[2] = false;
		vacuum.memory.rooms.get(1).adjacencyExistanceFlag[3] = false;
		
		vacuum.memory.rooms.get(0).nextRight = vacuum.memory.rooms.get(1);
		vacuum.memory.rooms.get(1).nextLeft = vacuum.memory.rooms.get(0);
		vacuum.memory.rooms.get(0).adjacencyExistanceFlag[3] = true;
		vacuum.memory.rooms.get(1).adjacencyExistanceFlag[1] = true;
		
		vacuum.start(env, 0, false, true);
//		vacuum.start(env, 1, false, true);

	}
	
	public static void letterE()
	{
		PhysicalEnvironment physEnv = new PhysicalEnvironment("My home");
		physEnv.insertCell(0, POSITIONS_FLAGS.LEFT);
		physEnv.insertCell(1, POSITIONS_FLAGS.DOWN);
		physEnv.printCells();
		VacuumAgent vacuum = new VacuumAgent();
		vacuum.start(physEnv, 0, true, false);
	}
		
	public static void main(String[] args) 
	{
		letterB();
		//letterE();
	}

}