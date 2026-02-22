import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=================================");
        System.out.println("     STUDENT GRADE TRACKER");
        System.out.println("=================================");

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {

            System.out.println("\nEnter details for Student " + i);

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Marks (0-100): ");
            int marks = sc.nextInt();
            sc.nextLine();

            students.add(new Student(name, marks));
        }

        int total = 0;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        String highestStudent = "";
        String lowestStudent = "";

        System.out.println("\n========== REPORT ==========");

        for (Student s : students) {

            total += s.getMarks();

            if (s.getMarks() > highest) {
                highest = s.getMarks();
                highestStudent = s.getName();
            }

            if (s.getMarks() < lowest) {
                lowest = s.getMarks();
                lowestStudent = s.getName();
            }

            System.out.println("Name: " + s.getName() +
                    " | Marks: " + s.getMarks() +
                    " | Grade: " + s.getGrade());
        }

        double average = (double) total / students.size();

        System.out.println("\n========== SUMMARY ==========");
        System.out.println("Total Students: " + students.size());
        System.out.println("Average Marks: " + String.format("%.2f", average));
        System.out.println("Highest Marks: " + highest + " (" + highestStudent + ")");
        System.out.println("Lowest Marks: " + lowest + " (" + lowestStudent + ")");
        System.out.println("=============================");

        sc.close();
    }
}