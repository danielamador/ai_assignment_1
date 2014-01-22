package ai_assignment_1;

public class MainClass 
{

	public static void main(String[] args) 
	{
		PhysicalEnvironment physEnv = new PhysicalEnvironment("My home");
		physEnv.insertCell(0, POSITIONS_FLAGS.UP);
		physEnv.printCells();
		VacuumAgent vacuum = new VacuumAgent();
		vacuum.start(physEnv, 0);
		vacuum.showMemory();
	}

}