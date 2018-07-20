package scheduling;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public class DiskScheduling 
{
	private int queue[];
	private int movementAccess[];
	private int totalNumOfMovement;
	private int headPointer;
	public DiskScheduling(int a[],int pointer)
	{
		queue=new int[a.length+2];
		movementAccess=new int[a.length+4];
		totalNumOfMovement=0;
		headPointer=pointer;
		queue[0]=0;
		queue[a.length+1]=199;
		for(int i=0;i<a.length;i++)
		{
			queue[i+1]=a[i];
		}
		for(int i=0;i<movementAccess.length;i++)
		{
			movementAccess[i]=Integer.MAX_VALUE;
		}
	}
	
	public int[] getQueue()
	{
		return queue;
	}

	public void setQueue(int[] queue) 
	{
		this.queue = queue;
	}

	public int[] getMovementAccess()
	{
		return movementAccess;
	}

	public void setMovementAccess(int track,int index)
	{
		movementAccess[index] = track;
	}

	public int getTotalNumOfMovement()
	{
		return totalNumOfMovement;
	}

	public void setTotalNumOfMovement(int totalNumOfMovement)
	{
		this.totalNumOfMovement = totalNumOfMovement;
	}

	public int getHeadPointer()
	{
		return headPointer;
	}

	public void setHeadPointer(int headPointer)
	{
		this.headPointer = headPointer;
	}
	public void printSolutionOfAccessMovement(String algName)
	{
		int i=0;
		if(algName.contains("notInclude"))
		{
			totalNumOfMovement-=(queue[queue.length-1]-queue[0]);
			movementAccess=ArrayUtils.removeElement(movementAccess,0);
		}
		System.out.println("The total number of movement for "+algName+" = "+totalNumOfMovement);
		System.out.print("the sequence of head movement to access for "+algName+" is ");
		while(movementAccess[i]!=Integer.MAX_VALUE&&i<movementAccess.length)
		{
			System.out.print(" "+movementAccess[i]);
			i++;
		}
		System.out.println("\n");
	}
	public int countTracksLessThanHeadPointer()
	{
		SortQueue();
		int i=0;
		while(queue[i]<=headPointer)
		{
			i++;
		}
		return i;
	}
	public void SortQueue()
	{
		Arrays.sort(queue);
	}
	public void removeDuplicates()
	{
		for(int i=0;i<queue.length;i++)
		{
			if(queue[i]==headPointer)
			{
				queue=ArrayUtils.removeElement(queue,queue[i]);
			}
		}
		for(int i=0;i<queue.length;i++)
		{
			for(int j=i+1;j<queue.length;j++)
			{
				if(queue[i]==queue[j])
				{
					queue=ArrayUtils.removeElement(queue, queue[j]);
				}
			}
		}
	}
}
