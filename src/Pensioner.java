import java.util.Objects;

public class Pensioner extends Person {

    private int pension;


    public Pensioner(String name, String surname, int age, double weight, int height) {
        super(name, surname, age, weight, height);
    }

    @Override
    public void die() {
        int age = getAge();
        int result = (age - 50) * pension;
        System.out.println("This pensioner died, he earned: " + result);
    }

    @Override
    public void die(int years) {
        System.out.println("that pensioner will die in " + years + " years");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pensioner pensioner = (Pensioner) o;
        return pension == pensioner.pension;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pension);
    }

    @Override
    public String toString() {
        return "Pensioner{" +
                "pension=" + pension +
                '}';
    }
}
