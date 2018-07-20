package virtualFileSystem;

import java.util.Vector;

public class Folder
{
	private String folderName;
	private String creationDate;
	private String lastModificationDate;
	private Vector<Folder> folders;
	private Vector<File>files;
	int size;
	
	public Folder(){
		folders=new Vector<>();
		files=new Vector<>();
	}
	
	public Folder(String folderName,  String creationDate, String lastModificationDate,Vector<Folder>folders,Vector<File>files)
	{
		folders=new Vector<>();
		files=new Vector<>();
		this.folderName = folderName;
		this.creationDate = creationDate;
		this.lastModificationDate = lastModificationDate;
		size=0;
		setFolders(folders);
		setFiles(files);
	}
	public String getFolderName()
	{
		return folderName;
	}
	public void setFolderName(String folderName)
	{
		this.folderName = folderName;
	}
	public String getCreationDate()
	{
		return creationDate;
	}
	public void setCreationDate(String creationDate) 
	{
		this.creationDate = creationDate;
	}
	public String getLastModificationDate()
	{
		return lastModificationDate;
	}
	public void setLastModificationDate(String lastModificationDate) 
	{
		this.lastModificationDate = lastModificationDate;
	}
	public Vector<Folder> getFolders() {
		return folders;
	}
	public void setFolders(Vector<Folder> folders) {
		folders=new Vector<Folder>();
		for(int i=0;i<folders.size();++i)
		{
			addFolder(folders.get(i));
		}
			
	}
	public Vector<File> getFiles() {
		return files;
	}
	public void setFiles(Vector<File> files) {
		for(int i=0;i<files.size();++i)
		{
			addFile(files.get(i));
		}
		
	}
	
	public boolean addFolder(Folder folder){
		if(folders==null)
			folders=new Vector<Folder>();

		for(int i=0;i<folders.size();++i)
			if(folders.get(i).getFolderName().equals(folder.getFolderName()))
				return false;
		this.folders.add(folder);
		size+=folder.getSize();
		return true;
	}
	
	public int findFile(String fileName){
		if(files!=null)
		for(int i=0;i<files.size();++i){
			if(files.get(i).getName().equals(fileName))
				return i;
		}
		return -1;
	}
	

	public int findFolder(String folderName){
		if(folders!=null)
		for(int i=0;i<folders.size();++i){
			if(folders.get(i).getFolderName().equals(folderName))
				return i;
		}
		return -1;
	}
	
	
	
	public boolean addFile(File file){
		if(files==null)
			files=new Vector<>();
		for(int i=0;i<files.size();++i)
			if(files.get(i).getName().equals(file.getName()))
				return false;
		this.files.add(file);
		size+=file.getSize();
		return true;
	}
	public int getSize() {
		return size;
	}
	
}
