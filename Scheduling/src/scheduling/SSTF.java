package scheduling;

import org.apache.commons.lang3.ArrayUtils;

public class SSTF extends DiskScheduling
{
	public SSTF(int[] a, int pointer) {
		super(a, pointer);
	}

	public void SequenceOfserveRequest()
	{
		SortQueue();
		removeDuplicates();
		int length=getQueue().length;
		int nextPointer = 0;
		for(int i=1;i<length-1;i++)
		{
			setMovementAccess(getHeadPointer(), i-1);
			int MIN=Integer.MAX_VALUE;
			for(int j=1;j<(getQueue().length);j++)
			{
				if(Math.abs(getHeadPointer()-getQueue()[j])<MIN)
				{
					MIN=Math.abs(getHeadPointer()-getQueue()[j]);
					nextPointer=getQueue()[j];
				}
			}
			setQueue(ArrayUtils.removeElement(getQueue(), nextPointer));
			setTotalNumOfMovement(getTotalNumOfMovement()+MIN);
			setHeadPointer(nextPointer);
		}
		setMovementAccess(getHeadPointer(), length-2);
		printSolutionOfAccessMovement("SSTF");
	}
}
