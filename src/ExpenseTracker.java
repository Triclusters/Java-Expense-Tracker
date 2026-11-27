import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ExpenseTracker{
    private ArrayList<Expense> expenses;
    private String fileName = "expenses.csv";

    public ExpenseTracker(){
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense e){
        expenses.add(e);
    }

    public void viewExpenses(){
        if(expenses.isEmpty()){
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("\nRecorded Expenses:");

        for(int i = 0; i < expenses.size(); i++){
            System.out.println((i + 1) + ". " + expenses.get(i));
        }
    }

    public double getTotal(){
        double total = 0;

        for(int i = 0; i < expenses.size(); i++){
            total += expenses.get(i).getAmount();
        }

        return total;
    }

    public void saveExpenses(){
        try(PrintWriter out = new PrintWriter(fileName)){
            for(int i = 0; i < expenses.size(); i++){
                Expense e = expenses.get(i);

                out.println(e.getName() + "," +
                        e.getCategory() + "," +
                        e.getAmount() + "," +
                        e.getDate());
            }

            if(out.checkError()){
                System.out.println("Error saving expenses.");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Error opening expense file.");
        }
    }

    public void loadExpenses(){
        try(BufferedReader in = new BufferedReader(new FileReader(fileName))){
            expenses.clear();
            String line;

            while((line = in.readLine()) != null){
                String[] data = line.split(",", -1);

                if(data.length != 4){
                    System.out.println("Skipping invalid expense record.");
                    continue;
                }

                try{
                    String name = data[0];
                    String category = data[1];
                    double amount = Double.parseDouble(data[2]);
                    LocalDate date = LocalDate.parse(data[3]);

                    if(name.isBlank() || category.isBlank() ||
                            amount <= 0 || !Double.isFinite(amount)){
                        System.out.println("Skipping invalid expense record.");
                        continue;
                    }

                    Expense e = new Expense(name, category, amount, date);
                    expenses.add(e);
                }
                catch(NumberFormatException | DateTimeParseException e){
                    System.out.println("Skipping invalid expense record.");
                }
            }

            System.out.println(expenses.size() + " expenses loaded.");
        }
        catch(FileNotFoundException e){
            // No saved file exists when the application is first used.
            System.out.println("No saved expenses found.");
        }
        catch(IOException e){
            System.out.println("Error loading expenses.");
        }
    }
}