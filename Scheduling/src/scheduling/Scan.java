package scheduling;


public class Scan extends DiskScheduling
{
	public Scan(int[] a, int pointer) {
		super(a, pointer);
	}
	public void SequenceOfserveRequest_Left()
	{
		removeDuplicates();
		int nextPointer=0;
		int tmpPointer=getHeadPointer();
		int count=countTracksLessThanHeadPointer();
		int index=0;
		setMovementAccess(getHeadPointer(),index);
		index++;
		for(int i=count-1;i>=0;i--)
		{
			nextPointer=getQueue()[i];
			setTotalNumOfMovement(getTotalNumOfMovement()+Math.abs(tmpPointer-nextPointer));
			tmpPointer=nextPointer;
			setMovementAccess(tmpPointer,index);
			index++;
		}
		nextPointer=getQueue()[0];
		for(int i=count;i<getQueue().length-1;i++)
		{
			nextPointer=getQueue()[i];
			setTotalNumOfMovement(getTotalNumOfMovement()+Math.abs(tmpPointer-nextPointer));
			tmpPointer=nextPointer;
			setMovementAccess(tmpPointer, index);
			index++;
		}
		printSolutionOfAccessMovement("SCAN TO LEFT");
	}
	public void SequenceOfserveRequest_Right()
	{
		removeDuplicates();
		int nextPointer=0;
		int count=countTracksLessThanHeadPointer();
		int index=0;
		setMovementAccess(getHeadPointer(),index);
		index++;
		for(int i=count;i<getQueue().length;i++)
		{
			nextPointer=getQueue()[i];
			setTotalNumOfMovement(getTotalNumOfMovement()+Math.abs(getHeadPointer()-nextPointer));
			setHeadPointer(nextPointer);
			setMovementAccess(getHeadPointer(), index);
			index++;
		}
		nextPointer=getQueue()[getQueue().length-1];
		for(int i=count-1;i>0;i--)
		{
			nextPointer=getQueue()[i];
			setTotalNumOfMovement(getTotalNumOfMovement()+Math.abs(getHeadPointer()-nextPointer));
			setHeadPointer(nextPointer);
			setMovementAccess(getHeadPointer(), index);
			index++;
		}
		printSolutionOfAccessMovement("SCAN TO RIGHT");
	}
}
