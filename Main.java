import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import dao.StudentDAO;
import db.DBConnection;
import model.Student;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Quick connectivity check
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("DB connected successfully!");
            try { conn.close(); } catch (Exception ignored) {}
        } else {
            System.out.println("DB connection FAILED! Please ensure the DB is running and the JDBC driver is on the classpath.");
            // We still allow the menu to run (DAO will show errors when operations are attempted)
        }

        StudentDAO dao = new StudentDAO();

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addStudent(dao);
                    break;
                case "2":
                    listStudents(dao);
                    break;
                case "3":
                    updateStudent(dao);
                    break;
                case "4":
                    deleteStudent(dao);
                    break;
                case "5":
                    System.out.println("Exiting. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown option, please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== Student Management ===");
        System.out.println("1) Add student");
        System.out.println("2) List students");
        System.out.println("3) Update student name (by id)");
        System.out.println("4) Delete student (by id)");
        System.out.println("5) Exit");
        System.out.print("Choose an option: ");
    }

    private static void addStudent(StudentDAO dao) {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Age: ");
        int age = readIntOrDefault(0);
        System.out.print("Department: ");
        String department = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        Student s = new Student(name, age, department, email);
        dao.addStudent(s);
    }

    private static void listStudents(StudentDAO dao) {
        List<Student> students = dao.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("ID | Name | Age | Department | Email");
        for (Student s : students) {
            System.out.printf("%d | %s | %d | %s | %s%n",
                    s.getId(), s.getName(), s.getAge(), s.getDepartment(), s.getEmail());
        }
    }

    private static void updateStudent(StudentDAO dao) {
        System.out.print("Student ID to update: ");
        int id = readIntOrDefault(-1);
        if (id < 0) {
            System.out.println("Invalid id.");
            return;
        }
        System.out.print("New name: ");
        String newName = scanner.nextLine().trim();
        dao.updateStudent(id, newName);
    }

    private static void deleteStudent(StudentDAO dao) {
        System.out.print("Student ID to delete: ");
        int id = readIntOrDefault(-1);
        if (id < 0) {
            System.out.println("Invalid id.");
            return;
        }
        dao.deleteStudent(id);
    }

    private static int readIntOrDefault(int defaultValue) {
        String line = scanner.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
