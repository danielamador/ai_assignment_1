package ai_assignment_1;

public class MainClass 
{

	public static void main(String[] args) 
	{
		Environment environment = new Environment(2, "My home");
		VacuumAgent vacuum = new VacuumAgent();
		vacuum.start(environment, 0);
	}

}
