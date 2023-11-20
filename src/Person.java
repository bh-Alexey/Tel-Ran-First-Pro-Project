import java.util.Objects;

public abstract class Person {

    private String name;

    private String surname;
    private int age;

    private double weight;

    private int height;

    private double money;

    private Gender gender;

    public Person(String name, String surname, int age, double weight, int height) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public Person(String name, String surname, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public Person() {

    }

    public void printInfo() {
        System.out.println("Person's name " + name);
        System.out.println("Person's age " + age);
    }

    public void goToWork() {
        if (age < 18 || age > 70) {
            System.out.println("Retire");
        }
        else {
            System.out.println("Damn... I have to work");
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void die() {
        System.out.println("Person passed away");
    }

    public abstract void die(int years);

    public double getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Double.compare(person.weight, weight) == 0 && height == person.height && Double.compare(person.money, money) == 0 && Objects.equals(name, person.name) && gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, weight, height, money, gender);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", money=" + money +
                ", gender=" + gender +
                '}';
    }
}
