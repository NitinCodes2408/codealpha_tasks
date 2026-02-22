import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentGradeTrackerGUI extends JFrame {

    private JTextField nameField, marksField;
    private JTextArea outputArea;
    private ArrayList<Student> students;

    public StudentGradeTrackerGUI() {

        students = new ArrayList<>();

        setTitle("Student Grade Tracker");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Input Panel =====
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Marks (0-100):"));
        marksField = new JTextField();
        inputPanel.add(marksField);

        JButton addButton = new JButton("Add Student");
        JButton reportButton = new JButton("Generate Report");

        inputPanel.add(addButton);
        inputPanel.add(reportButton);

        add(inputPanel, BorderLayout.NORTH);

        // ===== Output Area =====
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // ===== Bottom Panel (New Buttons) =====
        JPanel bottomPanel = new JPanel();

        JButton deleteButton = new JButton("Delete Student");
        JButton clearButton = new JButton("Clear All");

        bottomPanel.add(deleteButton);
        bottomPanel.add(clearButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // ===== Button Actions =====
        addButton.addActionListener(e -> addStudent());
        reportButton.addActionListener(e -> generateReport());
        deleteButton.addActionListener(e -> deleteStudent());
        clearButton.addActionListener(e -> clearAll());

        setVisible(true);
    }

    private void addStudent() {
        try {
            String name = nameField.getText();
            int marks = Integer.parseInt(marksField.getText());

            if (marks < 0 || marks > 100) {
                JOptionPane.showMessageDialog(this, "Marks must be between 0-100");
                return;
            }

            students.add(new Student(name, marks));

            nameField.setText("");
            marksField.setText("");

            JOptionPane.showMessageDialog(this, "Student Added Successfully!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter valid marks!");
        }
    }

    private void deleteStudent() {

        String nameToDelete = JOptionPane.showInputDialog(this, "Enter Student Name to Delete:");

        if (nameToDelete == null || nameToDelete.isEmpty())
            return;

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equalsIgnoreCase(nameToDelete)) {
                students.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            JOptionPane.showMessageDialog(this, "Student Deleted Successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Student Not Found!");
        }
    }

    private void clearAll() {
        students.clear();
        outputArea.setText("");
        JOptionPane.showMessageDialog(this, "All Data Cleared!");
    }

    private void generateReport() {

        if (students.isEmpty()) {
            outputArea.setText("No students added yet!");
            return;
        }

        int total = 0;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        String highestStudent = "";
        String lowestStudent = "";

        StringBuilder report = new StringBuilder();
        report.append("========== STUDENT REPORT ==========\n\n");

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

            report.append("Name: ").append(s.getName())
                    .append(" | Marks: ").append(s.getMarks())
                    .append(" | Grade: ").append(s.getGrade())
                    .append("\n");
        }

        double average = (double) total / students.size();

        report.append("\n========== SUMMARY ==========\n");
        report.append("Total Students: ").append(students.size()).append("\n");
        report.append("Average Marks: ").append(String.format("%.2f", average)).append("\n");
        report.append("Highest Marks: ").append(highest)
                .append(" (").append(highestStudent).append(")\n");
        report.append("Lowest Marks: ").append(lowest)
                .append(" (").append(lowestStudent).append(")\n");

        outputArea.setText(report.toString());
    }

    public static void main(String[] args) {
        new StudentGradeTrackerGUI();
    }
}