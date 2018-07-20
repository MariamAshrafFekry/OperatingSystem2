/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package bankeralgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class BankerAlgorithm {

    private int[][] Allocation;
    private int[][] Max;
    private int[][] Need;
    private int[] Available;
    private int numOfResources;
    private int numOfProcesses;

    public BankerAlgorithm(File inputFile, int[] available) throws FileNotFoundException, Exception {

        numOfResources = available.length;
        Available = Arrays.copyOf(available, numOfResources);

        Vector<String> data = new Vector<>();
        Scanner input = new Scanner(inputFile);
        while (input.hasNext()) {
            data.add(input.nextLine());
            numOfProcesses++;
        }

        Allocation = new int[numOfProcesses][numOfResources];
        Max = new int[numOfProcesses][numOfResources];
        Need = new int[numOfProcesses][numOfResources];

        for (int i = 0; i < numOfProcesses; ++i) {
            String[] tmp = data.elementAt(i).split(",");
            for (int j = 0; j < numOfResources; ++j) {
                Max[i][j] = Integer.parseInt(tmp[j]);
                Need[i][j]=Max[i][j];
            }
        }

//        numOfProcesses = numOfR;
//        numOfProcesses = numOfP;
//        Allocation = new int[numOfP][numOfR];
//        Max = new int[numOfP][numOfR];
//        Available = new int[numOfR];
    }

    public void Status() {

        System.out.println("Available:");
        for (int i = 1; i <= numOfResources; ++i) {
            System.out.print(NumberGenerator.Generate(i) + "   ");
        }
        System.out.println();
        for (int i = 0; i < numOfResources; ++i) {
            System.out.print(Available[i] + "   ");
        }

        System.out.println("\n\nAllocation:");
        for (int i = 0; i < numOfProcesses; i++) {
            for (int j = 0; j < numOfResources; j++) {
                System.out.print(Allocation[i][j] + "   ");
            }
            System.out.println();
        }

        System.out.println("\nNeed:");
        for (int i = 0; i < numOfProcesses; i++) {
            for (int j = 0; j < numOfResources; j++) {
                System.out.print(Need[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println("\nMaximum Need:");
        for (int i = 0; i < numOfProcesses; i++) {
            for (int j = 0; j < numOfResources; j++) {
                System.out.print(Max[i][j] + "   ");
            }
            System.out.println();
        }

    }

    boolean isSafe() {
        int[] tmpAvailable = Arrays.copyOf(Available, numOfResources);
        
        boolean Finish[] = new boolean[numOfProcesses];
        boolean flag=true;
        while (flag) {
            flag=false;
            for (int i = 0; i < numOfProcesses; ++i) {
                if(Finish[i])
                    continue;
                boolean valid = true;
                for (int j = 0; j < numOfResources; ++j) {
                    if (Need[i][j] > tmpAvailable[j]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    flag=true;
                    Finish[i]=true;
                    for (int j = 0; j < numOfResources; ++j) {
                        tmpAvailable[j]+=Max[i][j];
                    }
                }
            }
        }
        for(int i=0;i<numOfProcesses;++i){
            if(!Finish[i])
                return false;
        }
        return true;
    }

    boolean Request(int processNumber, int[] resources) {

        for (int i = 0; i < resources.length; ++i) {
            if (resources[i] > Available[i]||resources[i] > Need[processNumber][i]) {
                return false;
            }
        }

        for (int i = 0; i < resources.length; ++i) {
            Available[i] -= resources[i];
            Need[processNumber][i]-=resources[i];
            Allocation[processNumber][i]+=resources[i];
        }
        
        if(!isSafe()){
            for (int i = 0; i < resources.length; ++i) {
                Available[i] += resources[i];
                Need[processNumber][i]+=resources[i];
                Allocation[processNumber][i]-=resources[i];
            }
            return false;
        }

        return true;
    }

    boolean Release(int processNumber, int resources[]) {
        boolean flag = true;
        for (int i = 0; i < numOfResources; i++) {
            if (Allocation[processNumber][i] < resources[i]) {
                return false;

            }
        }
        for (int i = 0; i < numOfResources; i++) {
            Allocation[processNumber][i] -= resources[i];
            Need[processNumber][i] += resources[i];
            Available[i] += resources[i];
        }
        return true;
    }
}
