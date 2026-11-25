import java.util.ArrayList;

public class ExpenseTracker{
    private ArrayList<Expense> expenses;

    public ExpenseTracker(){
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense e){
        expenses.add(e);
    }
}