import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private String name;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void updateProfile(String newName) {
        this.name = newName;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }
}

class Question {
    private String text;
    private String[] options;
    private int correctOption;

    public Question(String text, String[] options, int correctOption) {
        this.text = text;
        this.options = options;
        this.correctOption = correctOption;
    }

    public void display() {
        System.out.println(text);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public boolean isCorrect(int userChoice) {
        return userChoice == correctOption;
    }
}

class Exam {
    private Question[] questions;

    public Exam(Question[] questions) {
        this.questions = questions;
    }

    public int startExam() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ":");
            questions[i].display();
            System.out.print("Enter your choice: ");
            int userChoice = scanner.nextInt();

            if (questions[i].isCorrect(userChoice)) {
                score++;
            }
        }

        scanner.close();
        return score;
    }
}
public class onlineexamnationsystem {
    public static void main(String[] args) {
        Map<String, User> users = new HashMap<>();
        users.put("student1", new User("student1", "pass123", "Ritik Raj"));
        users.put("student2", new User("student2", "pass456", "Kanhaiya Kumar"));

        Question[] questions = {
                new Question("What is the capital of France?",
                        new String[]{"Paris", "Berlin", "Rome", "Madrid"}, 1),
                new Question("Which planet is known as the Red Planet?",
                        new String[]{"Venus", "Mars", "Jupiter", "Saturn"}, 2),
                // Add more questions here
        };

        Exam exam = new Exam(questions);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = users.get(username);

        if (user != null && user.authenticate(password)) {
            System.out.println("Welcome, " + user.getName() + "!");
            System.out.println("1. Start Exam");
            System.out.println("2. Update Password");
            System.out.println("3. Update Profile");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    int score = exam.startExam();
                    System.out.println("Your score: " + score + " out of " + questions.length);
                    break;
                case 2:
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.next();
                    user.updatePassword(newPassword);
                    System.out.println("Password updated successfully.");
                    break;
                case 3:
                    System.out.print("Enter new name: ");
                    scanner.nextLine(); // Consume newline
                    String newName = scanner.nextLine();
                    user.updateProfile(newName);
                    System.out.println("Profile updated successfully.");
                    break;
                case 4:
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("Invalid credentials.");
        }

        scanner.close();
    }
}

