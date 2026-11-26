import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();

        System.out.println("Welcome to Java Expense Tracker!");

        while(true){
            System.out.println("\n1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine();

            if(input.equals("1")){
                try{
                    System.out.print("Enter expense name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = Double.parseDouble(sc.nextLine());

                    if(amount <= 0 || !Double.isFinite(amount)){
                        System.out.println("Invalid amount.");
                        continue;
                    }

                    System.out.print("Enter date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(sc.nextLine());

                    if(name.isBlank() || category.isBlank()){
                        System.out.println("Name and category cannot be empty.");
                        continue;
                    }

                    Expense e = new Expense(name, category, amount, date);
                    tracker.addExpense(e);

                    System.out.println("Expense added successfully!");
                }
                catch(NumberFormatException e){
                    System.out.println("Please enter a valid amount.");
                }
                catch(DateTimeParseException e){
                    System.out.println("Please enter a valid date.");
                }
            }
            else if(input.equals("2")){
                tracker.viewExpenses();
            }
            else if(input.equals("3")){
                System.out.println("Goodbye!");
                break;
            }
            else{
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}