package bankeralgorithm;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Andrew
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        File inputFile=new File(args[0]);
        int avail[]=new int[args.length-1];
        for(int i=1;i<args.length;++i){
            avail[i-1]=Integer.parseInt(args[i]);
        }
        
        BankerAlgorithm bankerAlgorithm = new BankerAlgorithm(inputFile, avail);
        
        Scanner input = new Scanner(System.in);
        while(true){
            String[] cmd = input.nextLine().split(" ");
            if(cmd[0].equals("Request")){
                int resources[] = new int [cmd.length-2] ;
                for(int i=2;i<cmd.length;++i){
                    resources[i-2]=Integer.parseInt(cmd[i]);
                }
                if(bankerAlgorithm.Request(Integer.parseInt(cmd[1]), resources)){
                    System.out.println("Request Accepted !");
                }
                else {
                    System.out.println("Request Denied !");
                }
            }
            else if(cmd[0].equals("Release")){
                int resources[] = new int [cmd.length-2] ;
                for(int i=2;i<cmd.length;++i){
                    resources[i-2]=Integer.parseInt(cmd[i]);
                }
                if(bankerAlgorithm.Release(Integer.parseInt(cmd[1]), resources)){
                    System.out.println("Release Accepted !");
                }
                else {
                    System.out.println("Release Denied !");
                }
            }
            else if(cmd[0].equals("Status")){
                bankerAlgorithm.Status();
            }
            else if(cmd[0].equals("Quit")){
                return;
            }
            else{
                System.out.println("Wrong Command !");
            }
        }
    }
    
}
