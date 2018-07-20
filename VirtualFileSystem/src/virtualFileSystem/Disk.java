package virtualFileSystem;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public abstract class Disk {
	protected Vector<Folder> folders;
	protected Vector<File> files;
	protected final int BLOCK_SIZE = 10;
	protected final int DISK_SIZE = 1000000;
	protected int freeSpace;
	protected Map<String, Integer> locations;
	protected int blocks[];

	public Disk() {
		folders = new Vector<>();
		files = new Vector<>();
		freeSpace = DISK_SIZE;
		blocks = new int[DISK_SIZE / BLOCK_SIZE];
		locations = new HashMap<String, Integer>();
		for (int i = 0; i < blocks.length; ++i) {
			blocks[i] = 0;
		}
	}

	public Disk(Vector<Folder> Folders, Vector<File> Files) {
		freeSpace = DISK_SIZE;

		int sizeOfFolder = Folders.size();
		for (int i = 0; i < sizeOfFolder; i++) {
			folders.addElement(Folders.get(i));
			freeSpace -= Folders.get(i).getSize();
		}
		int sizeOfFile = Files.size();
		for (int i = 0; i < sizeOfFile; i++) {
			files.addElement(Files.get(i));
			freeSpace -= Files.get(i).getSize();

		}
	}

	public boolean createFile(File file, String location) {
		String path[] = location.split("\\\\");
		Vector<Folder> folders = this.folders;
		int startPos;
		if (path.length == 1 && findFile(file.getName()) == -1) {
			startPos = addFile(file.getSize());
			if (startPos != -BLOCK_SIZE) {
				locations.put(file.getName(), startPos);
				freeSpace -= file.getSize();
				files.add(file);
				return true;
			}
		}
		for (int i = 1; i < path.length; ++i) {

			for (int j = 0; j < folders.size(); ++j) {
				if (folders.get(j).getFolderName().equals(path[i])) {
					if (i == path.length - 1 && folders.get(j).findFile(file.getName()) == -1) {
						startPos = addFile(file.getSize());
						if (startPos != -BLOCK_SIZE && folders.get(j).addFile(file)) {
							locations.put(file.getName(), startPos);
							freeSpace -= file.getSize();
							return true;
						}
					} else {
						folders = folders.get(j).getFolders();
						break;
					}
				}

			}
		}
		return false;
	}

	public boolean deleteFile(String fileName, String location) {
		String path[] = location.split("\\\\");
		Vector<Folder> folders = this.folders;
		if (path.length == 1) {
			if (locations.get(fileName) == null || findFile(fileName) == -1)
				return false;
			int start = locations.get(fileName);
			int size = files.get(findFile(fileName)).getSize();
			removeFile(start, size);
			freeSpace += size;
			files.remove(findFile(fileName));
			return true;
		}
		for (int i = 1; i < path.length; ++i) {
			for (int j = 0; j < folders.size(); ++j) {
				if (folders.get(j).getFolderName().equals(path[i])) {
					if (i == path.length - 1) {
						if (folders.get(j).findFile(fileName) == -1 || locations.get(fileName) == null)
							return false;
						int start = locations.get(fileName);
						int size = folders.get(j).getFiles().get(folders.get(j).findFile(fileName)).getSize();
						removeFile(start, size);
						freeSpace += size;
						locations.remove(fileName);
						folders.get(j).getFiles().remove(folders.get(j).findFile(fileName));
						return true;

					} else {
						folders = folders.get(j).getFolders();
						break;
					}
				}

			}
		}
		return false;
	}

	public boolean createFolder(Folder folder, String location) {
		String path[] = location.split("\\\\");
		Vector<Folder> folders = this.folders;
		if (path.length == 1) {
			folders.add(folder);
			freeSpace -= folder.getSize();
			return true;
		}
		for (int i = 1; i < path.length; ++i) {
			for (int j = 0; j < folders.size(); ++j) {
				if (folders.get(j).getFolderName().equals(path[i])) {
					if (i == path.length - 1) {
						freeSpace -= folder.getSize();
						folders.get(j).addFolder(folder);
						return true;
					} else {
						folders = folders.get(j).getFolders();
						break;
					}
				}

			}
		}
		return false;
	}

	public boolean deleteFolder(String folderName, String location) {
		String path[] = location.split("\\\\");
		Vector<Folder> folders = this.folders;
		if (path.length == 1) {
			if (findFolder(folderName) != -1) {
				int i = findFolder(folderName);
				Vector<Folder> v = folders.get(i).getFolders();
				for (int j = 0; j < v.size(); ++j) {

					deleteFolder(v.get(j).getFolderName(), location + "\\" + folders.get(i).getFolderName());
					j--;
				}
				for (int k = 0; folders.get(i).getFiles() != null && k < folders.get(i).getFiles().size(); ++k) {
					deleteFile(folders.get(i).getFiles().get(k).getName(),
							location + "\\" + folders.get(i).getFolderName());
					k--;
				}
				folders.remove(i);
				return true;
			} else
				return false;
		}
		for (int i = 1; i < path.length; ++i) {
			for (int j = 0; j < folders.size(); ++j) {
				if (folders.get(j).getFolderName().equals(path[i])) {
					if (i == path.length - 1) {
						int idx = folders.get(j).findFolder(folderName);
						if (idx == -1)
							return false;
						Vector<Folder> v = folders.get(j).getFolders().get(idx).getFolders();
						for (int k = 0; v != null && k < v.size(); ++k) {
							deleteFolder(v.get(k).getFolderName(), location + "\\" + folders.get(j).getFolderName());
							k--;
						}
						for (int k = 0; folders.get(j).getFolders().get(idx).getFiles() != null
								&& k < folders.get(j).getFolders().get(idx).getFiles().size(); ++k) {

							deleteFile(folders.get(j).getFolders().get(idx).getFiles().get(k).getName(),
									location + "\\" + folderName);
							k--;
						}
						folders.get(j).getFolders().remove(idx);
						return true;

					} else {
						folders = folders.get(j).getFolders();
						break;
					}
				}

			}
		}
		return false;

	}

	private int findFile(String fileName) {
		for (int i = 0; i < files.size(); ++i) {
			if (files.get(i).getName().equals(fileName))
				return i;
		}
		return -1;
	}

	private int findFolder(String folderName) {
		for (int i = 0; i < folders.size(); ++i) {
			if (folders.get(i).getFolderName().equals(folderName))
				return i;
		}
		return -1;
	}

	protected abstract int addFile(int size);

	protected abstract void removeFile(int start, int size);

	public void displayStatus() {
		System.out.println("Free Space = " + freeSpace);
		System.out.println("Occupied Space = " + (DISK_SIZE - freeSpace));
		int st = 0, en = 0;
		while (st <= en && en < blocks.length) {
			if (blocks[en] != 0) {
				if (st < en)
					System.out.println("Empty Slots : From " + st + " To " + (en -1));
				st = en + 1;
			}
			en++;
		}
		if (st < en)
			System.out.println("Empty Slots : From " + st + " To " + (en - 1));

	}

	public void displayTreeStructure() {
		System.out.println("Tree Structure:\n");
		for (int i = 0; i < folders.size(); ++i)
			printTree(folders.get(i), "VFSD:\\");
	}

	private void printTree(Folder f, String location) {
		Vector<Folder> folders = f.getFolders();

		for (int i = 0; folders != null && i < folders.size(); ++i)
			printTree(folders.get(i), location + "\\" + f.getFolderName());
		Vector<File> files = f.getFiles();
		if (folders == null && files == null) {
			System.out.println(location + "\\" + f.getFolderName());
			return;
		}

		for (int i = 0; files != null && i < files.size(); ++i) {
			System.out.println(
					location + "\\" + f.getFolderName() + "\\" + files.get(i).getName() + "." + files.get(i).getType());
		}

	}

	void printToFile(Folder f, String location, PrintWriter pw) throws ParseException {
		String loc = location;
		Vector<Folder> folders = f.getFolders();
		pw.println("Folder\t" + f.getFolderName() + "\t" + f.getCreationDate() + "\t"
				+ f.getLastModificationDate() + "\t" + loc + "\\");
			
		for (int i = 0; folders != null && i < folders.size(); ++i) {
			printToFile(folders.get(i), location + "\\" + f.getFolderName(), pw);
		}
		Vector<File> files = f.getFiles();
		for (int i = 0; files != null && i < files.size(); ++i) {
			pw.println("File " + files.get(i).getName() + "\t" + files.get(i).getType() + "\t" + files.get(i).getSize()
					+ "\t" + files.get(i).getCreationDate() + "\t" + files.get(i).getLastModificationDate() + "\t"
					+ location + "\\" + f.getFolderName() + "\\");
		}
	}

	public void Save() throws IOException, ParseException {
		java.io.File file = new java.io.File("DiskStructure.vfs");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(locations.size());
		for (Map.Entry<String, Integer> map : locations.entrySet()) {
			String key = map.getKey();
			int val = map.getValue();
			pw.print(key + "    ");
			pw.print(val);
			pw.println();
		}
	    
		pw.println(freeSpace);
		pw.println(blocks.length);
		for (int i = 0; i < blocks.length; i++) {
			pw.print(blocks[i] + " ");
		}
		pw.println("\r\n");
		
		for (int i = 0; i < folders.size(); i++)
			printToFile(folders.get(i), "VFSD:\\", pw);
		fw.close();
		pw.close();
	}
			
	public void Load() throws FileNotFoundException, ParseException
	{
		Vector<Folder> folder = new Vector<Folder>();
		Vector<String> folderLocation = new Vector<String>();
		Vector<File> Files = new Vector<File>();
		Vector<String> fileLocation = new Vector<String>();

		java.io.File file = new java.io.File("DiskStructure.vfs");
		Scanner input = new Scanner(file);
		if(!input.hasNext())return;
		int mapSize = input.nextInt();
		for (int i = 0; i < mapSize; i++) {
			locations.put(input.next(), input.nextInt());
		}
		freeSpace = input.nextInt();
		int blocksLength = input.nextInt();
		for (int i = 0; i < blocksLength; i++)
		{
			blocks[i] = input.nextInt();
		}
		
		while (input.hasNext()) {
			String type = input.next();
			if (type.equals("Folder")) 
			{
				Folder fo = new Folder(input.next(), input.next(), input.next(), new Vector<>(), new Vector<>());
				folder.add(fo);
				folderLocation.add(input.next());
			}
			else if (type.equals("File")) 
			{
				String name=input.next();
				String Type=input.next();
				int size=input.nextInt();
				String cDate=input.next();
				String modDate=input.next();
				File fi = new File(name,Type ,0,cDate ,modDate);
				Files.add(fi);
				fileLocation.add(input.next());
				fi.setSize(size);
			}
		}
		for (int i = 0; i < folder.size(); i++) {
			Folder fol = folder.get(i);
			String loc = folderLocation.get(i);
			createFolder(fol, loc);
		}
		for (int i = 0; i < Files.size(); i++)
		{
			File fi = Files.get(i);
			String loc = fileLocation.get(i);
			createFile(fi,loc);
		}
		input.close();
	}
}
