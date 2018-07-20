package scheduling;

public class C_Scan extends DiskScheduling
{
	public C_Scan(int[] a, int pointer) {
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
		for(int i=count;i<getQueue().length;i++)
		{
			nextPointer=getQueue()[i];
			setTotalNumOfMovement(getTotalNumOfMovement()+Math.abs(getHeadPointer()-nextPointer));
			setHeadPointer(nextPointer);
			setMovementAccess(getHeadPointer(), index);
			index++;
		}
		for(int i=0;i<count;i++)
		{
			nextPointer=getQueue()[i];
			setTotalNumOfMovement(getTotalNumOfMovement()+Math.abs(getHeadPointer()-nextPointer));
			setHeadPointer(nextPointer);
			setMovementAccess(getHeadPointer(), index);
			index++;
		}
		printSolutionOfAccessMovement("C-SCAN include overhead (199-0)");
		printSolutionOfAccessMovement("C-SCAN notInclude overhead (199-0)");
	}

}
