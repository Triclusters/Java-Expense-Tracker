import java.util.ArrayList;

public class ExpenseTracker{
    private ArrayList<Expense> expenses;

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
}