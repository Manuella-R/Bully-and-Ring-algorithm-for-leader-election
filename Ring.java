// Ring Algorithm with Detailed Logs for Presentation
import java.util.Scanner;

public class Ring {

    public static void main(String[] args) {
        int i, j, temp;
        Scanner in = new Scanner(System.in);

        // Array of processes
        Rr proc[] = new Rr[10];
        for (i = 0; i < proc.length; i++) {
            proc[i] = new Rr();
        }

        System.out.println("Enter the number of processes: ");
        int num = in.nextInt();

        // Get process IDs from user
        for (i = 0; i < num; i++) {
            proc[i].index = i;
            System.out.println("Enter the ID of process " + (i + 1) + ": ");
            proc[i].id = in.nextInt();
            proc[i].state = "active";
            proc[i].f = 0;
        }

        // Sort processes by ID
        for (i = 0; i < num - 1; i++) {
            for (j = 0; j < num - 1; j++) {
                if (proc[j].id > proc[j + 1].id) {
                    temp = proc[j].id;
                    proc[j].id = proc[j + 1].id;
                    proc[j + 1].id = temp;
                }
            }
        }

        // Initial coordinator is the highest ID
        proc[num - 1].state = "inactive";
        System.out.println("\nInitial coordinator is process with ID " + proc[num - 1].id + " 🎉");
        printActiveProcesses(proc, num);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1) Start an election");
            System.out.println("2) Quit");
            int ch = in.nextInt();

            for (i = 0; i < num; i++) {
                proc[i].f = 0; // reset flags
            }

            switch (ch) {
                case 1:
                    System.out.println("\nEnter the process number (index 0-" + (num - 1) + ") that will initiate election: ");
                    int init = in.nextInt();
                    int temp1 = (init + 1) % num;
                    int temp2 = init;
                    int arr[] = new int[10];
                    i = 0;

                    System.out.println("\nElection started by process " + proc[init].id);

                    // circulate election messages in the ring
                    while (temp2 != temp1) {
                        if ("active".equals(proc[temp1].state) && proc[temp1].f == 0) {
                            System.out.println("Message: process " + proc[init].id + " → process " + proc[temp1].id);
                            proc[temp1].f = 1;
                            init = temp1;
                            arr[i] = proc[temp1].id;
                            i++;
                        }
                        temp1 = (temp1 + 1) % num; // move to next process
                    }

                    System.out.println("Message: process " + proc[init].id + " → process " + proc[temp1].id);
                    arr[i] = proc[temp1].id;
                    i++;

                    // find max ID as new coordinator
                    int max = -1;
                    for (j = 0; j < i; j++) {
                        if (max < arr[j]) {
                            max = arr[j];
                        }
                    }

                    System.out.println("\nNew coordinator elected: process with ID " + max + " 🎉");

                    // set new coordinator state to inactive
                    for (i = 0; i < num; i++) {
                        if (proc[i].id == max) {
                            proc[i].state = "inactive";
                        }
                    }
                    printActiveProcesses(proc, num);
                    break;

                case 2:
                    System.out.println("Exiting program...");
                    in.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
                    break;
            }
        }
    }

    // Print all active processes
    public static void printActiveProcesses(Rr proc[], int num) {
        System.out.print("Active processes: ");
        for (int i = 0; i < num; i++) {
            if ("active".equals(proc[i].state)) {
                System.out.print(proc[i].id + " ");
            }
        }
        System.out.println();
    }
}

class Rr {
    public int index;  // index of process
    public int id;     // process ID
    public int f;      // flag
    String state;      // active or inactive
}
