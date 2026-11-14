
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Comparator;

// Student class with the hashCode() correction
class Student {
    private final String regNo;
    private final String name;

    Student(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;
        return regNo.equals(student.regNo);
    }

    // Correct hashCode() implementation
    @Override
    public int hashCode() {
        return regNo.hashCode();
    }

    @Override
    public String toString() {
        return regNo + " " + name;
    }
}

// Deliberately incorrect class for comparison
class BrokenStudent {
    String regNo;
    String name;

    BrokenStudent(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BrokenStudent)) return false;
        return regNo.equals(((BrokenStudent) o).regNo);
    }

    // hashCode() is intentionally not overridden
}

public class Attendance {

    public static void main(String[] args) {

        System.out.println("TEST 1: Without hashCode()");
        Set<BrokenStudent> brokenSet = new HashSet<>();

        brokenSet.add(new BrokenStudent("192311001", "Arun"));
        brokenSet.add(new BrokenStudent("192311002", "Divya"));
        brokenSet.add(new BrokenStudent("192311001", "Arun"));

        System.out.println("Unique students: " + brokenSet.size());

        System.out.println("\nTEST 2: With corrected hashCode()");
        Set<Student> present = new HashSet<>();

        present.add(new Student("192311001", "Arun"));
        present.add(new Student("192311002", "Divya"));
        present.add(new Student("192311001", "Arun"));

        System.out.println("Unique students: " + present.size());
        System.out.println(present);

        System.out.println("\nTEST 3: Using HashMap");
        Map<String, Student> attendance = new HashMap<>();

        attendance.put("192311001",
                new Student("192311001", "Arun"));
        attendance.put("192311002",
                new Student("192311002", "Divya"));
        attendance.put("192311001",
                new Student("192311001", "Arun"));

        System.out.println("Unique students: " + attendance.size());

        System.out.println("\nTreeSet alternative:");
        Set<Student> sortedStudents = new TreeSet<>(
                Comparator.comparing(s -> s.toString().split(" ")[0])
        );

        sortedStudents.add(new Student("192311001", "Arun"));
        sortedStudents.add(new Student("192311002", "Divya"));
        sortedStudents.add(new Student("192311001", "Arun"));

        System.out.println("Unique students: " + sortedStudents.size());
    }
}
