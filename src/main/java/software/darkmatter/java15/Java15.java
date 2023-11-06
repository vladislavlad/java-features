package software.darkmatter.java15;

public class Java15 {

    public void sealedClass() {
        Person person = new Employee(1);

        switch (person) {
            case Employee e -> System.out.println("Department id: " + e.getDepartmentId());
            case Manager m -> System.out.println("Grade id: " + m.getGradeId());
        }
    }

    public abstract sealed static class Person permits Employee, Manager {}

    public static final class Employee extends Person {

        private final int departmentId;

        public Employee(int departmentId) {
            this.departmentId = departmentId;
        }

        public int getDepartmentId() {
            return departmentId;
        }
    }

    public non-sealed static class Manager extends Person {

        private final int gradeId;

        public Manager(int gradeId) {
            this.gradeId = gradeId;
        }

        public int getGradeId() {
            return gradeId;
        }
    }
}
