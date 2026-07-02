public class Employee {
    private String name;
    private String gender;
    private int age;

    public Employee(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    // Used by Solution 4 as a method reference target for filter()
    public boolean isEligible() {
        return gender.equals("F") && age >= 21;
    }

    @Override
    public String toString() {
        return name + " (" + gender + ", " + age + ")";
    }
}