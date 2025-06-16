import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) throws IOException {
        ArrayList<String> tasks = loadTasks();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. Display Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Clear invalid input
                continue;
            }

            if (choice == 1) {
                System.out.print("Enter a Task: ");
                String task = sc.nextLine();
                tasks.add(task);
                saveTask(tasks);
                System.out.println("Task added.");
            } else if (choice == 2) {
                if (tasks.isEmpty()) {
                    System.out.println("No tasks added.");
                } else {
                    System.out.println("To-Do List:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
            } else if (choice == 3) {
                if (tasks.isEmpty()) {
                    System.out.println("No tasks to delete.");
                    continue;
                }
                System.out.print("Enter the task number to delete: ");
                int taskNumber;
                try {
                    taskNumber = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid task number.");
                    sc.nextLine(); // Clear invalid input
                    continue;
                }

                if (taskNumber > 0 && taskNumber <= tasks.size()) {
                    tasks.remove(taskNumber - 1);
                    saveTask(tasks);
                    System.out.println("Task deleted.");
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    private static ArrayList<String> loadTasks() throws IOException {
        ArrayList<String> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
            reader.close();
        }
        return tasks;
    }

    private static void saveTask(ArrayList<String> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (String task : tasks) {
            writer.write(task);
            writer.newLine();
        }
        writer.close();
    }
}
