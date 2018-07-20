package scheduling;

public class C_Look extends DiskScheduling
{
	public C_Look(int[] a, int pointer) {
		super(a, pointer);
	}
	public void SequenceOfserveRequest()
	{
		removeDuplicates();
		int count=countTracksLessThanHeadPointer();
		int index=0;
		
		int nextPointer=0;
		setMovementAccess(getHeadPointer(), index);
		index++;
		for(int i=count;i<getQueue().length-1;i++)
		{
			nextPointer=getQueue()[i];
			setTotalNumOfMovement(getTotalNumOfMovement()+Math.abs(getHeadPointer()-nextPointer));
			setHeadPointer(nextPointer);
			setMovementAccess(getHeadPointer(), index);
			index++;
		}
		for(int i=1;i<count;i++)
		{
			nextPointer=getQueue()[i];
			setTotalNumOfMovement(getTotalNumOfMovement()+Math.abs(getHeadPointer()-nextPointer));
			setHeadPointer(nextPointer);
			setMovementAccess(getHeadPointer(), index);
			index++;
		}
		printSolutionOfAccessMovement("C-LOOK");
	}

}
