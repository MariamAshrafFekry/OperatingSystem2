package virtualFileSystem;

public class File 
{
	private String name;
	private String type;
	private int size;
	private String creationDate;
	private String lastModificationDate;
	
	public File(String name, String type,  int size, String creationDate, String lastModificationDate) 
	{
		this.name = name;
		this.type = type;
		this.size = size;
		this.creationDate = creationDate;
		this.lastModificationDate = lastModificationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(String lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}
	
}
