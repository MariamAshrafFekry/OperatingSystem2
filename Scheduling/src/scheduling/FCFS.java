package scheduling;

public class FCFS extends DiskScheduling
{
	public FCFS(int[] a, int pointer) 
	{
		super(a, pointer);
	}
	public void SequenceOfserveRequest()
	{
		int nextPointer;
		int length=getQueue().length;
		for(int i=1;i<length-1;i++)
		{
			setMovementAccess(getHeadPointer(), i-1);
			nextPointer=getQueue()[i];
			setTotalNumOfMovement(getTotalNumOfMovement()+Math.abs(nextPointer-getHeadPointer()));
			setHeadPointer(nextPointer);
		}
		setMovementAccess(getHeadPointer(), length-2);
		printSolutionOfAccessMovement("FCFS");
	}
}
