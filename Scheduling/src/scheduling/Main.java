package scheduling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Main 
{
	public static void main(String[]args) throws FileNotFoundException
	{
		File file=new File("input.txt");
		Scanner input=new Scanner(file);
		int head,length=0;
		while(input.hasNextInt())
		{
			head=input.nextInt();
			length++;
		}
		length--;
		input.close();
		input=new Scanner(file);
		int arr[]=new int[length];
		for(int i=0;i<length;i++)
		{
			arr[i]=input.nextInt();
		}
		head=input.nextInt();
		
		FCFS fcfs=new FCFS(arr,head);
	    fcfs.SequenceOfserveRequest();
		Arrays.sort(arr);

		SSTF sstf=new SSTF(arr,head);
		sstf.SequenceOfserveRequest();

		Scan scan=new Scan(arr,head);
		scan.SequenceOfserveRequest_Left();
		scan=new Scan(arr,head);
		scan.SequenceOfserveRequest_Right();
		
		C_Scan cscan=new C_Scan(arr,head);
		cscan.SequenceOfserveRequest();
		
		Look look=new Look(arr,head);
		look.SequenceOfserveRequest_Left();
		look=new Look(arr,head);
		look.SequenceOfserveRequest_Right();
		
		C_Look clook=new C_Look(arr,head);
		clook.SequenceOfserveRequest();
		input.close();
		
	}

}
