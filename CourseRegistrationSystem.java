import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public void decreaseCapacity() {
        capacity--;
    }

    public void increaseCapacity() {
        capacity++;
    }

    @Override
    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description
                + "\nCapacity: " + capacity + "\nSchedule: " + schedule;
    }
}

class Student {
    private int id;
    private String name;
    private List<Course> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
        course.decreaseCapacity();
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
        course.increaseCapacity();
    }

    @Override
    public String toString() {
        return "Student ID: " + id + "\nName: " + name;
    }
}

public class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseRegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
            System.out.println("------------------------------");
        }
    }

    public void displayRegisteredCourses(Student student) {
        System.out.println("Registered Courses for " + student.getName() + ":");
        for (Course course : student.getRegisteredCourses()) {
            System.out.println(course.getTitle());
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        // Add some sample courses
        system.addCourse(new Course("CSCI101", "Introduction to Computer Science", "Fundamental concepts of computer science", 30, "Mon/Wed 9:00-10:30"));
        system.addCourse(new Course("MATH201", "Calculus I", "Introduction to differential and integral calculus", 25, "Tue/Thu 11:00-12:30"));

        // Add some sample students
        system.addStudent(new Student(1001, "John Doe"));
        system.addStudent(new Student(1002, "Jane Smith"));

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Display Registered Courses for a Student");
            System.out.println("3. Register a Course for a Student");
            System.out.println("4. Drop a Course for a Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.displayAvailableCourses();
                    break;
                case 2:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    Student student = system.findStudentByName(studentName);
                    if (student != null) {
                        system.displayRegisteredCourses(student);
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 3:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String code = scanner.next();
                    Student studentToAdd = system.findStudentByName(name);
                    Course courseToAdd = system.findCourseByCode(code);
                    if (studentToAdd != null && courseToAdd != null) {
                        studentToAdd.registerCourse(courseToAdd);
                        System.out.println("Course successfully registered!");
                    } else {
                        System.out.println("Student or course not found!");
                    }
                    break;
                case 4:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter student name: ");
                    String studentName2 = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.next();
                    Student studentToRemove = system.findStudentByName(studentName2);
                    Course courseToRemove = system.findCourseByCode(courseCode);
                    if (studentToRemove != null && courseToRemove != null) {
                        studentToRemove.dropCourse(courseToRemove);
                        System.out.println("Course successfully dropped!");
                    } else {
                        System.out.println("Student or course not found!");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }

    public Student findStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    public Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }
}
