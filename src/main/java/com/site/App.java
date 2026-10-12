package com.site;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String user = "root";
        String pwd = System.getenv("DB_PASSWORD");

        Connection con = DriverManager.getConnection(url, user, pwd);
        Scanner sc = new Scanner(System.in);

        System.out.println("Successfully connected!");

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Create Student");
            System.out.println("2. Read Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                // CREATE
                System.out.print("Enter name: ");
                String name = sc.next();

                System.out.print("Enter age: ");
                int age = sc.nextInt();

                String sql =
                        "INSERT INTO students (name, age) VALUES (?, ?)";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.executeUpdate();
                ps.close();

                System.out.println("Student added successfully!");

            } else if (choice == 2) {
                // READ
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT * FROM students"
                );

                while (rs.next()) {
                    System.out.println(
                            "ID: " + rs.getInt("id")
                                    + " | Name: " + rs.getString("name")
                                    + " | Age: " + rs.getInt("age")
                    );
                }

                rs.close();
                st.close();

            } else if (choice == 3) {
                // UPDATE
                System.out.print("Enter student ID: ");
                int id = sc.nextInt();

                System.out.print("Enter new age: ");
                int age = sc.nextInt();

                String sql =
                        "UPDATE students SET age = ? WHERE id = ?";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, age);
                ps.setInt(2, id);
                ps.executeUpdate();
                ps.close();

                System.out.println("Student updated successfully!");

            } else if (choice == 4) {
                // DELETE
                System.out.print("Enter student ID: ");
                int id = sc.nextInt();

                String sql =
                        "DELETE FROM students WHERE id = ?";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                ps.close();

                System.out.println("Student deleted successfully!");

            } else if (choice == 5) {
                con.close();
                sc.close();
                System.out.println("Program closed.");
                break;

            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }
}
