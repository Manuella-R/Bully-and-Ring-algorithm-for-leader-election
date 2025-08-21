// Bully Algorithm with Detailed Logs for Presentation
import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[5]; // process states (up or down)

    // Print all currently active processes
    public static void printActiveProcesses() {
        System.out.print("Active processes: ");
        for (int i = 0; i < state.length; i++) {
            if (state[i]) {
                System.out.print("p" + (i + 1) + " ");
            }
        }
        System.out.println();
    }

    // Bring a process UP
    public static void up(int process) {
        if (state[process - 1]) {
            System.out.println("Process " + process + " is already UP.");
        } else {
            state[process - 1] = true;
            System.out.println("Process " + process + " is now UP and holds election.");
            printActiveProcesses();

            // Send election messages to higher processes
            for (int i = process; i < 5; i++) {
                System.out.println("Election message sent: p" + process + " → p" + (i + 1));
            }

            // Wait for alive response
            for (int i = process + 1; i <= 5; i++) {
                if (state[i - 1]) {
                    System.out.println("Alive message sent: p" + i + " → p" + process);
                    break;
                }
            }
        }
    }

    // Bring a process DOWN
    public static void down(int process) {
        if (!state[process - 1]) {
            System.out.println("Process " + process + " is already DOWN.");
        } else {
            state[process - 1] = false;
            System.out.println("Process " + process + " is now DOWN.");
            printActiveProcesses();
        }
    }

    // Send a message from a process
    public static void mess(int process) {
        if (state[process - 1]) {
            if (state[4]) { // coordinator (p5) is alive
                System.out.println("Process " + process + " → Coordinator (p5): Message Sent");
                System.out.println("Coordinator (p5) → Process " + process + ": OK Received ✅");
            } else { // coordinator down, start election
                System.out.println("Coordinator (p5) is DOWN!");
                System.out.println("Process " + process + " starts an election...");

                // Send election messages
                for (int i = process; i < 5; i++) {
                    System.out.println("Election message: p" + process + " → p" + (i + 1));
                }

                // Elect the highest active process as new coordinator
                for (int i = 5; i >= process; i--) {
                    if (state[i - 1]) {
                        System.out.println("Coordinator elected: Process " + i + " 🎉");
                        break;
                    }
                }
            }
        } else {
            System.out.println("Process " + process + " is DOWN. Cannot send message.");
        }
    }

    // Main driver program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initially all processes are up
        for (int i = 0; i < 5; i++) {
            state[i] = true;
        }

        System.out.println("5 active processes are:");
        System.out.println("Process up  = p1 p2 p3 p4 p5");
        System.out.println("Process 5 is coordinator");
        printActiveProcesses();

        int choice, process;
        do {
            System.out.println(".........");
            System.out.println("1) Up a process");
            System.out.println("2) Down a process");
            System.out.println("3) Send a message");
            System.out.println("4) Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter process to bring UP:");
                    process = sc.nextInt();
                    if (process == 5) {
                        System.out.println("Process 5 is coordinator and is now UP.");
                        state[4] = true;
                        printActiveProcesses();
                    } else {
                        up(process);
                    }
                    break;

                case 2:
                    System.out.println("Enter process to bring DOWN:");
                    process = sc.nextInt();
                    down(process);
                    break;

                case 3:
                    System.out.println("Which process will send message?");
                    process = sc.nextInt();
                    mess(process);
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
                    break;
            }

        } while (choice != 4);

        sc.close();
    }
}
