package ai_assignment_1;
import java.util.Vector;

public class VacuumAgent {
	private int score;
	private int steps;
	public static int BAG_CAPACITY = 50;
	
	public class memCell extends Cell{
		public boolean firstLeft;
		public boolean lastRight;
	};
	
	private Vector<memCell> memory;
	
	public VacuumAgent(){}
	public void start(Environment environment){}
	public void stop(){}
	public void showPerformance(){}
	public void checkEnvironmentCellState(){}
	private void replaceBag(){}
	private void suck(){}
	private void noOp(){}
	private void moveLeft(){}
	private void moveRight(){}
	
}
