package dao;

import db.DBConnection;
import model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public void addStudent(Student student) {
        String query = "INSERT INTO students (name, age, department, email) VALUES (?, ?, ?, ?)";

        try {
            try (Connection con = DBConnection.getConnection() ) {
                if (con == null) {
                    System.out.println("Cannot add student: no DB connection.");
                    return;
                }
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, student.getName());
                    ps.setInt(2, student.getAge());
                    ps.setString(3, student.getDepartment());
                    ps.setString(4, student.getEmail());

                    ps.executeUpdate();
                    System.out.println("Student Added Successfully!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();

        String query = "SELECT * FROM students";

        try {
            try (Connection con = DBConnection.getConnection()) {
                if (con == null) {
                    System.out.println("Cannot list students: no DB connection.");
                    return list;
                }
                try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
                    while (rs.next()) {
                        Student s = new Student(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getString("department"),
                                rs.getString("email")
                        );
                        list.add(s);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateStudent(int id, String name) {
        String query = "UPDATE students SET name=? WHERE id=?";

        try {
            try (Connection con = DBConnection.getConnection()) {
                if (con == null) {
                    System.out.println("Cannot update student: no DB connection.");
                    return;
                }
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, name);
                    ps.setInt(2, id);

                    int changed = ps.executeUpdate();
                    if (changed > 0) {
                        System.out.println("Student Updated!");
                    } else {
                        System.out.println("No student found with id: " + id);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id=?";

        try {
            try (Connection con = DBConnection.getConnection()) {
                if (con == null) {
                    System.out.println("Cannot delete student: no DB connection.");
                    return;
                }
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setInt(1, id);

                    int changed = ps.executeUpdate();
                    if (changed > 0) {
                        System.out.println("Student Deleted!");
                    } else {
                        System.out.println("No student found with id: " + id);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

