package gihansandaru.dev.expensetracker.models;

import java.util.Date;
import java.util.List;

public class DayView {

    private Date dayView_date;
    private List<Expense> expenseList;

    public Date getDayView_date() {
        return dayView_date;
    }

    public void setDayView_date(Date dayView_date) {
        this.dayView_date = dayView_date;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }
}
