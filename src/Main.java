
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();

        System.out.println("Welcome to Java Expense Tracker!");

        // Prevent existing data from being overwritten after a failed load.
        if(!tracker.loadExpenses()){
            System.out.println("Unable to load expenses. Closing application.");
            sc.close();
            return;
        }

        while(true){
            System.out.println("\n1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Total Spending");
            System.out.println("4. Filter by Category");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if(!sc.hasNextLine()){
                System.out.println("Input ended. Closing application.");
                break;
            }

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

                    // Commas are reserved for separating CSV fields.
                    if(name.contains(",") || category.contains(",")){
                        System.out.println("Name and category cannot contain commas.");
                        continue;
                    }

                    Expense e = new Expense(name, category, amount, date);

                    if(tracker.addExpense(e)){
                        System.out.println("Expense added successfully!");
                    }
                    else{
                        System.out.println("Expense could not be added.");
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("Please enter a valid amount.");
                }
                catch(DateTimeParseException e){
                    System.out.println("Please enter a valid date.");
                }
                catch(NoSuchElementException e){
                    System.out.println("Input ended. Closing application.");
                    break;
                }
            }
            else if(input.equals("2")){
                tracker.viewExpenses();
            }
            else if(input.equals("3")){
                System.out.printf("Total spending: $%.2f%n", tracker.getTotal());
            }
            else if(input.equals("4")){
                System.out.print("Enter category: ");

                if(!sc.hasNextLine()){
                    System.out.println("Input ended. Closing application.");
                    break;
                }

                String category = sc.nextLine();

                if(category.isBlank()){
                    System.out.println("Category cannot be empty.");
                    continue;
                }

                tracker.filterCategory(category);
            }
            else if(input.equals("5")){
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
