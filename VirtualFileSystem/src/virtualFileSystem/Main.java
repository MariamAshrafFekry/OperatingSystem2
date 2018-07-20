package virtualFileSystem;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws ParseException, IOException 
	{
		int allocation;
	    Scanner input =new Scanner(System.in);
		System.out.println("1 - Contiguous Allocation ");
		System.out.println("2 - Linked Allocation ");
		System.out.print("Enter Your Choice : ");
		allocation=input.nextInt();
		if(allocation==1)
		{
			ContiguousAllocation contiguousAllocation=new ContiguousAllocation();
			contiguousAllocation.Load();
			Date s=new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(s);
			while(true)
			{
				String command=input.nextLine();
			    String cmd[]=command.split(" ");
			    if(cmd.length==0)continue;
			    if(cmd[0].equals("CFile"))
			    {
			    	String FileName="",Type="";
			    	int i=0;
			    	while(cmd[1].charAt(i)!='.')
			    	{
			    		FileName+=cmd[1].charAt(i);
			    		i++;
			    	}
			    	i++;
			    	for(;i<cmd[1].length();i++)
			    	{
			    		Type+=cmd[1].charAt(i);
			    	}
			    	File file=new File(FileName,Type,Integer.parseInt(cmd[2]),date,date);
			    	if(contiguousAllocation.createFile(file, cmd[3]))
			    		System.out.println("File Created");
			    	else
			    		System.out.println("File Not Created");
			    }
			    else if(cmd[0].equals("CFolder"))
			    {
			    	Folder folder=new Folder(cmd[1],date,date,new Vector<Folder>(),new Vector<File>());
			    	if(contiguousAllocation.createFolder(folder, cmd[2]))
			    		System.out.println("Folder Created");
			    	else
			    	{
			    		System.out.println("Folder not Created");
			    	}
			    }
			    else if(cmd[0].equals("DeleteFile"))
			    {
			    	String FileName="";
			    	String loc="";
			    	int i=cmd[1].length()-1;
			    	while(i>=0&&cmd[1].charAt(i)!='.')i--;
			    	i--;
			    	while(i>=0&&cmd[1].charAt(i)!='\\')
			    		{FileName+=cmd[1].charAt(i);i--;}
			    	for(int j=0;j<i;j++)
			    	{
			    		loc+=cmd[1].charAt(j);
			    	}
			    	FileName=new StringBuffer(FileName).reverse().toString();
			    	if(contiguousAllocation.deleteFile(FileName,loc))
			    	{
			    		System.out.println("File Deleted");
			    	}
			    	else
			    	{
			    		System.out.println("File Not Deleted");
			    	}
			    	
			    }
			    else if(cmd[0].equals("DeleteFolder"))
			    {
			    	if(contiguousAllocation.deleteFolder(cmd[1], cmd[2]))
			    	{
			    		System.out.println("Folder Deleted");
			    	}
			    	else
			    	{
			    		System.out.println("Folder not Deleted");
			    	}
			    }
			    else if(cmd[0].equals("DisplayDiskStatus"))
			    {
			    	contiguousAllocation.displayStatus();
			    }
			    else if(cmd[0].equals("DisplayDiskStructure"))
			    {
			    	contiguousAllocation.displayTreeStructure();
			    }
			    else if(cmd[0].equals("Exit"))
			    {
			    	contiguousAllocation.Save();
			    	System.exit(0);
			    }
			}
		}
		else if(allocation==2)
		{
			Date s=new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(s);
			LinkedAllocation linkedAllocation=new LinkedAllocation();
			linkedAllocation.Load();
			while(true)
			{
				String command=input.nextLine();
			    String cmd[]=command.split(" ");
			    if(cmd[0].equals("CFile"))
			    {
			    	String FileName="",Type="";
			    	int i=0;
			    	while(cmd[1].charAt(i)!='.')
			    	{
			    		FileName+=cmd[1].charAt(i);
			    		i++;
			    	}
			    	i++;
			    	for(;i<cmd[1].length();i++)
			    	{
			    		Type+=cmd[1].charAt(i);
			    	}
			    	File file=new File(FileName,Type,Integer.parseInt(cmd[2]),date,date);
			    	if(linkedAllocation.createFile(file, cmd[3]))
			    	{
			    		System.out.println("File Created");
			    	}
			    	else
			    	{
			    		System.out.println("File Not Created");
			    	}
			    }
			    else if(cmd[0].equals("CFolder"))
			    {
			    	Folder folder=new Folder(cmd[1],date,date,new Vector<Folder>(),new Vector<File>());
			    	if(linkedAllocation.createFolder(folder, cmd[2]))
			    	{
			    		System.out.println("Folder Created");
			    	}
			    	else
			    	{			    		
			    		System.out.println("Folder not Created");
			    		
			    	}
			    }
			    else if(cmd[0].equals("DeleteFile"))
			    {
			    	String FileName="";
			    	String loc="";
			    	int i=cmd[1].length()-1;
			    	while(i>=0&&cmd[1].charAt(i)!='.')i--;
			    	i--;
			    	while(i>=0&&cmd[1].charAt(i)!='\\')
			    		{FileName+=cmd[1].charAt(i);i--;}
			    	for(int j=0;j<i;j++)
			    	{
			    		loc+=cmd[1].charAt(j);
			    	}
			    	FileName=new StringBuffer(FileName).reverse().toString();
			    	if(linkedAllocation.deleteFile(FileName,loc))
			    	{
			    		System.out.println("File Deleted");
			    	}
			    	else
			    	{
			    		System.out.println("File Not Deleted");
			    	}
			    	
			    }
			    else if(cmd[0].equals("DeleteFolder"))
			    {
			    	if(linkedAllocation.deleteFolder(cmd[1], cmd[2]))
			    	{
			    		System.out.println("Folder Deleted");
			    	}
			    	else 
			    	{
			    		System.out.println("Folder Not Deleted");
			    	}
			    }
			    else if(cmd[0].equals("DisplayDiskStatus"))
			    {
			    	linkedAllocation.displayStatus();
			    }
			    else if(cmd[0].equals("DisplayDiskStructure"))
			    {
			    	linkedAllocation.displayTreeStructure();
			    }
			    else if(cmd[0].equals("Exit"))
			    {
			    	linkedAllocation.Save();
			    	System.exit(0);
			    }
			}
		}
		/*	Date date=new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(date);
		    File f=new File("file1","txt",30,s,s);
			Folder fd=new Folder("folder1",s,s,new Vector<>(),new Vector<>());
			Folder fd2=new Folder("folder2",s,s,new Vector<>(),new Vector<>());
			Folder fd3=new Folder("folder3",s,s,new Vector<>(),new Vector<>());
		ContiguousAllocation d=new ContiguousAllocation();
			d.createFolder(fd,"VFSD:\\");
			d.createFolder(fd2,"VFSD:\\folder1");
			d.createFolder(fd3,"VFSD:\\");
			d.createFile(f, "VFSD:\\folder1\\folder2");
			f=new File("file2","txt",20,null,null);
			d.createFile(f, "VFSD:\\folder1");
//			d.deleteFolder("folder2", "VFSD:\\folder1");
			d.displayTreeStructure();
			d.Save();
			//d.displayTreeStructure();
			//d.displayStatus();*/
    	input.close();

	}

}
