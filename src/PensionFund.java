import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//Protected
//Классы упаковщики

public class PensionFund {
    private String name;

    private boolean isState;

    private final String dateOfCreation;

    private List<Worker> persons;

    private Map<DayOfWeek, Boolean> workDays;

    public PensionFund(String name, boolean isState, String dateOfCreation, List<Worker> persons) {
        this.name = name;
        this.isState = isState;
        this.dateOfCreation = dateOfCreation;
        this.persons = persons;
        this.workDays = new HashMap<>();
    }
    public PensionFund(String string, String dateOfCreation, List<Worker> persons) {
        String[] array = string.split(", ");
        this.name = array[0];
        this.isState = Boolean.parseBoolean(array[1]);
        this.persons = getPersons();
        this.dateOfCreation = dateOfCreation;
    }

    public Map<DayOfWeek, Boolean> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(Map<DayOfWeek, Boolean> workDays) {
        this.workDays = workDays;
    }

    public List<Worker> getPersons() {
        return persons;
    }

    public void setPersons(List<Worker> persons) {
        this.persons = persons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return isState;
    }

    public void setState(boolean state) {
        isState = state;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PensionFund that = (PensionFund) o;
        return isState == that.isState && Objects.equals(name, that.name) && Objects.equals(dateOfCreation, that.dateOfCreation) && Objects.equals(persons, that.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isState, dateOfCreation, persons);
    }

    @Override
    public String toString() {
        return "PensionFund{" +
                "name='" + name + '\'' +
                ", isState=" + isState +
                ", dateOfCreation='" + dateOfCreation + '\'' +
                ", persons=" + persons +
                '}';
    }

    public void info() {
        System.out.println("Fund's name " + name);

        int count = (persons != null) ? persons.size() : 0;

        if (isState) {
            System.out.println("People invested in the fund: " + count / 1000 + " thousand");
        }
        else {
            System.out.println("People invested in the fund: " + count);
        }
    }

    public double calculatePensionFor(AbleToCalculatePension object) {
        if (isState && isWorkDayToday()) {
            return object.calculatePension();
        }
        else {
            return 0.0;
        }
    }

    private boolean isWorkDayToday() {
        LocalDate localDate = LocalDate.now();
        DayOfWeek dayOfWeekNow = localDate.getDayOfWeek();

        if (workDays == null) {
            return false;
        }

        boolean isWorkDay = workDays.get(dayOfWeekNow);
        return isWorkDay;
    }

    public double calculateMedianPension() {
        if (persons == null || persons.size() == 0) {
            return 0.0;
        }

        double sum = 0.0;

        for (Worker worker : persons) {
            sum += calculatePensionFor(worker);
        }

        return sum / persons.size();
    }

}
