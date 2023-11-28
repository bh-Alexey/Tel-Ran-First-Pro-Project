import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class CommandMenu {

    private final static int AMONG_AGE = 25;

    CalculationServices calculator = new CalculationServices();
    List<Worker> workers;

    {
        try {
            workers = WorkerInit.createWorkers();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    List<PensionFund> pensionFunds;

    {
        try {
            pensionFunds = FundInit.createFunds();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void showMenu() {
        System.out.println("choose one of the following option and press ENTER...");
        System.out.print("1. GET THE MOST POPULAR FUND");
        System.out.println("                       2. GET AVERAGE PENSION PAID BY FUNDS");
        System.out.print("3. GET THE MOST YOUNGER FUND'S DEPOSITOR");
        System.out.println("           4. GET THE MOST HIGHER PENSION AMONG UNDER 25 YEARS OLD");
        System.out.print("5. GET THE NAME OF THE HIGHEST PAID PENSIONER");
        System.out.println("      6. GET AVERAGE PENSION AMOUNT");
        System.out.print("7. GET A LIST OF UNSUCCESSFUL DEPOSITORS");
        System.out.println("           8. EXIT");
    }

    public void answer(int option) {
        switch (option) {
            case 1: {
                PensionFund mostPopularFund = calculator.mostPopularFund(pensionFunds);
                System.out.println(mostPopularFund);
                System.out.println();
                break;
            }
            case 2: {
                double avgPensionFundsPaid = calculator.avgPensionFundsPaid(pensionFunds);
                System.out.println("Average paid amount - " + Math.round(avgPensionFundsPaid) + "$");
                break;
            }
            case 3: {
                String mostYoungerDepositor = calculator.mostYoungerDepositor(pensionFunds);
                System.out.println("The most younger fund's member is - " + mostYoungerDepositor + " years old");
                break;
            }
            case 4: {
                double mostHigherPensionAmong = calculator.mostHigherPensionAmong(workers, AMONG_AGE);
                System.out.println("The most higher pension among under 25 years old members - " + Math.round(mostHigherPensionAmong) + "$");
                break;
            }
            case 5: {
                String workerHigherPension = calculator.workerHigherPension(workers);
                System.out.println("The most paid worker is - " + workerHigherPension);
                break;
            }
            case 6: {
                double workersAvgPension = calculator.workersAvgPension(workers);
                System.out.println("Average pension will amount - " + Math.round(workersAvgPension) + "$");
                break;
            }
            case 7: {
                List<Worker> getFraudVictims = calculator.getFraudVictims(pensionFunds);
                System.out.println("List of nonState government fund's members: " + getFraudVictims);
                System.out.println();
                break;
            }
            case 8: {
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid input: " + option);
        }
    }

    public void standBy() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("press ENTER to CONTINUE..");
        scanner.nextLine();
    }
}