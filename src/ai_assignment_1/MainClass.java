package ai_assignment_1;

public class MainClass 
{

	public static void letterB()
	{
		VacuumAgent vacuum = new VacuumAgent();
		PhysicalEnvironment env1 = new PhysicalEnvironment("All Clean");
		env1.insertCell(0, POSITIONS_FLAGS.RIGHT);
		env1.rooms.elementAt(0).state = false;
		env1.rooms.elementAt(1).state = false;
		vacuum.start(env1, 0);
		vacuum.start(env1, 1);
				
//		PhysicalEnvironment env2 = new PhysicalEnvironment("First Clean");
//		env2.insertCell(0, POSITIONS_FLAGS.RIGHT);
//		env2.rooms.elementAt(0).state = false;
//		env2.rooms.elementAt(1).state = true;
//		vacuum.start(env2, 0);
//		vacuum.start(env2, 1);
//		
//		PhysicalEnvironment env3 = new PhysicalEnvironment("Second Clean");
//		env3.insertCell(0, POSITIONS_FLAGS.RIGHT);
//		env3.rooms.elementAt(0).state = true;
//		env3.rooms.elementAt(1).state = false;
//		vacuum.start(env3, 0);
//		vacuum.start(env3, 1);
//		
//		PhysicalEnvironment env4 = new PhysicalEnvironment("All Dirty");
//		env4.insertCell(0, POSITIONS_FLAGS.RIGHT);
//		env4.rooms.elementAt(0).state = true;
//		env4.rooms.elementAt(1).state = true;
//		vacuum.start(env4, 0);
//		vacuum.start(env4, 1);
	}
	
	public static void main(String[] args) 
	{

		letterB();
//		PhysicalEnvironment physEnv = new PhysicalEnvironment("My home");
//		physEnv.insertCell(0, POSITIONS_FLAGS.LEFT);
//		physEnv.insertCell(1, POSITIONS_FLAGS.DOWN);
//		physEnv.printCells();
//		VacuumAgent vacuum = new VacuumAgent();
//		vacuum.start(physEnv, 0);
		//vacuum.moveRandDirection();
		//vacuum.checkEnvironmentCellState();
		//vacuum.showMemory();
	}

}