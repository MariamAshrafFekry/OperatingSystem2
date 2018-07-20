package virtualFileSystem;

import java.util.Vector;

public class LinkedAllocation extends Disk
{
	
	public LinkedAllocation(){
		super();
	}
	
	public LinkedAllocation(Vector<Folder> Folders, Vector<File> Files) {
		super(Folders, Files);
	}
	
	@Override
	protected int addFile(int size) {
		if(size<=0)
			return -1;
		for(int i=0;i<blocks.length;++i){
			if(blocks[i]==0){
				blocks[i]=-1;
				blocks[i]=addFile(size-BLOCK_SIZE);
				if(blocks[i]==-BLOCK_SIZE)
				{
					blocks[i]=0;
					return -BLOCK_SIZE;
				}
				return i;
			}
		}
		return -BLOCK_SIZE;
	}

	@Override
	protected void removeFile(int start,int size) {
		int next=start;
		while(next!=-1){
			int tmp=blocks[next];
			blocks[next]=0;
			next=tmp;

		}
	}

}
