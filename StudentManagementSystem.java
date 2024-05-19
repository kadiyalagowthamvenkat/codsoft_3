import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

/* <applet code="StudentManagementSystem.class" width=600 height=400></applet> */

class Student {
    private String name;
    private String rollNumber;
    private float grade;

    public Student(String name, String rollNumber, float grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public float getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}

public class StudentManagementSystem extends Applet {
    private Student[] students;
    private int studentCount = 0;

    private Label nameLabel;
    private TextField nameField;
    private Label rollNumberLabel;
    private TextField rollNumberField;
    private Label gradeLabel;
    private TextField gradeField;
    private Button addButton;
    private Button displayButton;
    private Button searchButton;
    private Button modifyButton;
    private Button removeButton;

    private TextArea displayArea;

    public void init() {
        setBackground(new Color(173, 216, 230)); // Light Blue background
        setLayout(new BorderLayout());
        
        Panel headingPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        Label headingLabel = new Label("Gowtham's Student Management System");
        headingLabel.setForeground(new Color(72, 61, 139)); // Dark Slate Blue
        headingLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        headingPanel.add(headingLabel);
        add(headingPanel, BorderLayout.NORTH);

        Panel inputPanel = new Panel(new GridLayout(10, 4, 5, 5));
        nameLabel = new Label("Name: ");
        nameLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        inputPanel.add(nameLabel);
        nameField = new TextField(15);
        nameField.setFont(new Font("Verdana", Font.PLAIN, 18));
        inputPanel.add(nameField);

        rollNumberLabel = new Label("Roll Number: ");
        rollNumberLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        inputPanel.add(rollNumberLabel);
        rollNumberField = new TextField(10);
        rollNumberField.setFont(new Font("Verdana", Font.PLAIN, 18));
        inputPanel.add(rollNumberField);

        gradeLabel = new Label("Grade: ");
        gradeLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        inputPanel.add(gradeLabel);
        gradeField = new TextField(5);
        gradeField.setFont(new Font("Verdana", Font.PLAIN, 18));
        inputPanel.add(gradeField);
        add(inputPanel, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        addButton = new Button("Add Student");
        addButton.setPreferredSize(new Dimension(200, 40));
        addButton.setFont(new Font("Verdana", Font.BOLD, 18));
        buttonPanel.add(addButton);
        displayButton = new Button("Display Students");
        displayButton.setPreferredSize(new Dimension(200, 40));
        displayButton.setFont(new Font("Verdana", Font.BOLD, 18));
        buttonPanel.add(displayButton);
        searchButton = new Button("Search Student");
        searchButton.setPreferredSize(new Dimension(200, 40));
        searchButton.setFont(new Font("Verdana", Font.BOLD, 18));
        buttonPanel.add(searchButton);
        modifyButton = new Button("Modify Student");
        modifyButton.setPreferredSize(new Dimension(200, 40));
        modifyButton.setFont(new Font("Verdana", Font.BOLD, 18));
        buttonPanel.add(modifyButton);
        removeButton = new Button("Remove Student");
        removeButton.setPreferredSize(new Dimension(200, 40));
        removeButton.setFont(new Font("Verdana", Font.BOLD, 18));
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        displayArea = new TextArea(15, 130);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Verdana", Font.BOLD, 18));
        add(displayArea, BorderLayout.EAST);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String rollNumber = rollNumberField.getText();
                float grade = Float.parseFloat(gradeField.getText());
                if (studentCount < students.length) {
                    students[studentCount++] = new Student(name, rollNumber, grade);
                    nameField.setText("");
                    rollNumberField.setText("");
                    gradeField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Maximum number of students reached!");
                }
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                for (int i = 0; i < studentCount; i++) {
                    displayArea.append(students[i].toString() + "\n");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog("Enter student name to search:");
                boolean found = false;
                for (int i = 0; i < studentCount; i++) {
                    if (students[i].getName().equalsIgnoreCase(searchTerm)) {
                        displayArea.setText(students[i].toString());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    displayArea.setText("Student not found!");
                } else {
                    displayArea.append("\nStudent found!");
                }
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog("Enter student name to modify:");
                boolean found = false;
                for (int i = 0; i < studentCount; i++) {
                    if (students[i].getName().equalsIgnoreCase(searchTerm)) {
                        String newName = JOptionPane.showInputDialog("Enter new name:");
                        students[i].setName(newName);
                        students[i].setRollNumber(JOptionPane.showInputDialog("Enter new roll number:"));
                        students[i].setGrade(Float.parseFloat(JOptionPane.showInputDialog("Enter new grade:")));
                        displayArea.setText("Student record modified!");
                        found = true;
                        break;
                    }
                }
                if (found) {
                    displayArea.append("\nStudent found!");
                } else {
                    displayArea.setText("Student not found!");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog("Enter student name to remove:");
                boolean found = false;
                for (int i = 0; i < studentCount; i++) {
                    if (students[i].getName().equalsIgnoreCase(searchTerm)) {
                        for (int j = i; j < studentCount - 1; j++) {
                            students[j] = students[j + 1];
                        }
                        studentCount--;
                        displayArea.setText("Student removed!");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    displayArea.setText("Student not found!");
                }
            }
        });

        students = new Student[100];
    }
}
