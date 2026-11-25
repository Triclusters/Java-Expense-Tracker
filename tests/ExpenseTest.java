import java.time.LocalDate;

public class ExpenseTest{
    public static void main(String[] args){
        Expense e = new Expense("Lunch", "Food", 12.50, LocalDate.now());

        System.out.println(e.getName());
        System.out.println(e.getCategory());
        System.out.println(e.getAmount());
        System.out.println(e.getDate());
        System.out.println(e);
    }
}