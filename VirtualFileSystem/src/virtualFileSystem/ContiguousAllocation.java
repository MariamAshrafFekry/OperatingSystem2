package virtualFileSystem;

import java.util.Vector;

public class ContiguousAllocation extends Disk
{
	public ContiguousAllocation() 
	{
		super();
	}
	public ContiguousAllocation(Vector<Folder> Folders, Vector<File> Files) 
	{
		super(Folders, Files);
	}

	@Override
	protected int addFile(int size) {
		int numOfBlocks=(int)Math.ceil(size*1.0/BLOCK_SIZE);
		for(int i=0;i<blocks.length;i++)
		{
			int count=0;
			for(int j=i;j<blocks.length;j++)
			{
				if(blocks[j]==0)
				{
					count++;
				}
				if(count==numOfBlocks)
				{
					int start=Math.abs(j-(count-1));
					for(int k=start;k<count+numOfBlocks+1;k++)
					{
						if(BLOCK_SIZE<size)
						{
							blocks[k]=BLOCK_SIZE;
							size-=BLOCK_SIZE;
						}
						else
						{
							blocks[k]=size;
							return start;
						}
						
					}
				}
				else if(blocks[j]!=0)
				{
					count=0;
					break;
				}
			}
		}
		return 0;
	}
	@Override
	protected void removeFile(int start, int size) {
		int numOfBlocks=(int)Math.ceil(size*1.0/BLOCK_SIZE);
		for(int i=start;i<start+numOfBlocks;i++)
		{
			blocks[i]=0;
		}
	}

}
