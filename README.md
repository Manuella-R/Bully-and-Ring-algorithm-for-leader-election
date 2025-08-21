# Leader Election Algorithms (Bully & Ring) – Java Implementation

This project contains two classic distributed systems leader election algorithms implemented in Java:

Bully Algorithm

Ring Algorithm

Both algorithms simulate how processes in a distributed system elect a coordinator when failures occur.

## 📌 Requirements

Java JDK 8 or higher
Check installation:
```
java -version
javac -version
```
## 📂 Project Files

Bully.java → Bully election algorithm (interactive menu).

Ring.java → Ring election algorithm (interactive menu).

Rr (helper class) → Defined inside Ring.java, no separate file needed.

## ▶️ How to Compile and Run
1. Compile the programs

Open a terminal/command prompt in the project directory and run:
```
javac Bully.java
javac Ring.java
```

This generates Bully.class, Ring.class, and Rr.class.

2. Run Bully Algorithm
```
java Bully
```

You will see a menu like:
```
1) Up a process.
2) Down a process.
3) Send a message.
4) Exit
```

Option 1: Bring a process up (simulate recovery).

Option 2: Bring a process down (simulate crash).

Option 3: Send a message (check coordinator).

Option 4: Exit the program.

3. Run Ring Algorithm
```
java Ring
```
```
Program flow:

Enter the number of processes (e.g., 5).

Enter the IDs for each process (e.g., 10, 20, 30, 40, 50).

The program selects the process with the highest ID as the coordinator.

You will then see:

1.election 2.quit


1 → Start a new election by specifying a process to initiate.

2 → Quit program.

📖 Example Usage (Ring)
Enter the number of process :
5
Enter the id of process :
10
Enter the id of process :
20
Enter the id of process :
30
Enter the id of process :
40
Enter the id of process :
50
process 50 select as co-ordinator
1.election 2.quit
```
---

https://github.com/user-attachments/assets/4d01c451-b025-4b46-9efa-8c687671cb57

---
### 🧩 Notes

Both algorithms simulate message passing and process failures; they don’t use real networking.

Useful for learning how distributed systems handle fault tolerance and leader election.
