
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ExpenseTracker{
    private ArrayList<Expense> expenses;
    private String fileName = "expenses.csv";

    public ExpenseTracker(){
        expenses = new ArrayList<>();
    }

    public boolean addExpense(Expense e){
        expenses.add(e);

        if(!saveExpenses()){
            expenses.remove(expenses.size() - 1);
            return false;
        }

        return true;
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

    public boolean saveExpenses(){
        Path file = Path.of(fileName);
        Path temp = Path.of(fileName + ".tmp");

        try{
            // Write to a temporary file to protect existing expense data.
            try(BufferedWriter out = new BufferedWriter(
                    new FileWriter(temp.toFile()))){

                for(int i = 0; i < expenses.size(); i++){
                    Expense e = expenses.get(i);

                    out.write(e.getName() + "," +
                            e.getCategory() + "," +
                            e.getAmount() + "," +
                            e.getDate());

                    out.newLine();
                }
            }

            Files.move(temp, file, StandardCopyOption.REPLACE_EXISTING);
            return true;
        }
        catch(IOException e){
            System.out.println("Error saving expenses.");

            try{
                Files.deleteIfExists(temp);
            }
            catch(IOException ex){
                System.out.println("Unable to remove temporary file.");
            }

            return false;
        }
    }

    public boolean loadExpenses(){
        Path file = Path.of(fileName);

        if(Files.notExists(file)){
            System.out.println("No saved expenses found.");
            return true;
        }

        ArrayList<Expense> loaded = new ArrayList<>();

        try(BufferedReader in = new BufferedReader(new FileReader(fileName))){
            String line;

            while((line = in.readLine()) != null){
                String[] data = line.split(",", -1);

                if(data.length != 4){
                    System.out.println("Invalid expense record.");
                    return false;
                }

                try{
                    String name = data[0];
                    String category = data[1];
                    double amount = Double.parseDouble(data[2]);
                    LocalDate date = LocalDate.parse(data[3]);

                    if(name.isBlank() || category.isBlank() ||
                            amount <= 0 || !Double.isFinite(amount)){
                        System.out.println("Invalid expense record.");
                        return false;
                    }

                    Expense e = new Expense(name, category, amount, date);
                    loaded.add(e);
                }
                catch(NumberFormatException | DateTimeParseException e){
                    System.out.println("Invalid expense record.");
                    return false;
                }
            }
        }
        catch(IOException e){
            System.out.println("Error loading expenses.");
            return false;
        }

        expenses = loaded;
        System.out.println(expenses.size() + " expenses loaded.");
        return true;
    }
}
