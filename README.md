# Mini Hospital Emergency Management System

A console-based Java application built for **CIT300 – Data Structures and Algorithms**
(Individual Mid Assignment). It simulates patient registration, emergency treatment
requests, treatment completion, and patient visit history using four core data
structures implemented **from scratch** (no built-in `java.util` collections used
for the core logic).

## Data Structures Used

| # | Feature | Data Structure | File |
|---|---------|-----------------|------|
| 1 | Patient Records | Binary Search Tree (BST) | `PatientBST.java` |
| 2 | Emergency Patient Queue | Queue (FIFO, linked-list based) | `EmergencyQueue.java` |
| 3 | Treatment History | Stack (LIFO, linked-list based) | `TreatmentStack.java` |
| 4 | Patient Visit History | Singly Linked List | `VisitLinkedList.java` |

## Project Structure

```
HospitalMS/
├── README.md
├── sample_output.txt          <- example console run (for screenshots)
└── src/
    ├── Patient.java            <- patient model
    ├── Visit.java               <- visit model
    ├── VisitLinkedList.java     <- singly linked list (visit history)
    ├── PatientBST.java          <- binary search tree (patient records)
    ├── EmergencyQueue.java      <- queue (emergency waiting list)
    ├── TreatmentRecord.java     <- treatment record model
    ├── TreatmentStack.java      <- stack (treatment history)
    └── HospitalManagementSystem.java  <- main() / console menu
```

## Requirements

- Java JDK 11 or later (tested with JDK 21)
- No external libraries needed

## How to Compile and Run

### Option A — Command line (any OS)

```bash
cd HospitalMS/src
javac *.java
java HospitalManagementSystem
```

### Option B — IntelliJ IDEA / Eclipse / VS Code

1. Create a new Java project.
2. Copy all `.java` files from `src/` into your project's source folder.
3. Set `HospitalManagementSystem` as the run configuration's main class.
4. Run the project (Shift+F10 in IntelliJ, or the Run button).

## Using the System

Once running, you'll see a numbered menu:

```
 1.  Register New Patient (BST insert)
 2.  Search Patient by ID (BST search)
 3.  Delete Patient (BST delete)
 4.  Display All Patients - Ascending ID (BST in-order)
 5.  Add Patient to Emergency Queue (Enqueue)
 6.  Call Next Patient for Treatment (Dequeue)
 7.  Display Emergency Waiting Queue
 8.  Display Treatment History (Stack)
 9.  Undo Last Completed Treatment (Pop)
10.  Add Visit to Patient History (Linked List add)
11.  Remove Visit from Patient History (Linked List remove)
12.  Search Visit in Patient History (Linked List search)
13.  Display Patient Visit History (Linked List display)
 0.  Exit
```

A typical workflow:
1. Register a patient (Option 1) — this stores them in the **BST**.
2. Add them to the emergency queue (Option 5) — this enqueues them in the **Queue**.
3. Call the next patient for treatment (Option 6) — this dequeues the patient
   and, once you enter treatment details, pushes a completed record onto the
   **Stack**.
4. Add a past visit for a patient (Option 10) — this appends to that patient's
   own **Singly Linked List** of visits.
5. Use options 2/3/4/7/8/9/11/12/13 to exercise search, delete, display,
   dequeue-handling, pop, remove, search, and display operations on each
   structure respectively.

See `sample_output.txt` for a full example run you can use as a reference
for your required output screenshots.

## Suggested Git Commit History

Commit progressively as you build each piece — do **not** upload everything
as one final commit. Suggested order:

1. `Created project structure`
2. `Added Patient and Visit model classes`
3. `Implemented patient BST (insert)`
4. `Added BST search and in-order traversal`
5. `Added BST deletion`
6. `Implemented emergency queue (enqueue/dequeue/display)`
7. `Implemented treatment stack (push/pop/display)`
8. `Implemented patient visit singly linked list`
9. `Built main menu-driven console program`
10. `Added testing / sample run output`
11. `Updated README`

## Setting Up Your Own GitHub Repository

```bash
cd HospitalMS
git init
git add README.md
git commit -m "Created project structure"

git add src/Patient.java src/Visit.java
git commit -m "Added Patient and Visit model classes"

git add src/PatientBST.java
git commit -m "Implemented patient BST"

git add src/EmergencyQueue.java
git commit -m "Implemented emergency queue"

git add src/TreatmentRecord.java src/TreatmentStack.java
git commit -m "Implemented treatment stack"

git add src/VisitLinkedList.java
git commit -m "Implemented patient visit linked list"

git add src/HospitalManagementSystem.java
git commit -m "Built main menu-driven console program"

git add sample_output.txt
git commit -m "Added testing / sample run output"

# Create the repo on GitHub first (via github.com), then:
git branch -M main
git remote add origin https://github.com/<your-username>/<repo-name>.git
git push -u origin main
```

> Tip: make each commit right after you actually finish that piece of work,
> rather than doing them all back-to-back at the end — the assignment brief
> says your commit history may be reviewed as evidence of your development
> process.

## What to Cover in Your Demo Video (5–10 min)

1. Brief intro with your face visible.
2. Short explanation of what the system does.
3. Show your GitHub repo and commit history.
4. Explain how each data structure is used (BST for records, Queue for
   waiting patients, Stack for completed treatments, Linked List for visit
   history) and *why* those are appropriate choices.
5. Run the program live and demonstrate:
   - BST: register a patient, search for one, delete one, show in-order list.
   - Queue: enqueue a couple of patients, show the queue, dequeue one.
   - Stack: complete a treatment (push), show the stack, pop the top.
   - Linked List: add a visit, search a visit, remove a visit, display history.
6. Briefly explain any design decisions (e.g., why a singly linked list per
   patient, why treatment history is LIFO).
7. A short reflection on what you learned.

## Notes on Academic Integrity

This code is provided as a **learning reference and starting point**. Per
the assignment brief, this is individual work — make sure you read through
every file, understand how each data structure operates, and be ready to
explain and modify the code yourself (you'll need to for the demo video and
the "explain your design decisions" section). Consider extending it — e.g.
adding input validation, saving/loading from a file, or a GUI — to make it
clearly your own.
