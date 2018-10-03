package gihansandaru.dev.expensetracker.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import gihansandaru.dev.expensetracker.R;
import gihansandaru.dev.expensetracker.models.Expense;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private final Context context;
    private List<Expense> expenses;

    public ExpenseAdapter(Context context,List<Expense> expenses) {

        this.context = context;
        this.expenses = expenses;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cell_expense_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Expense expense = expenses.get(i);
        String year = new SimpleDateFormat("yyyy", Locale.US).format(expense.getDateinserted());
        String month = new SimpleDateFormat("MMMM", Locale.US).format(expense.getDateinserted());
        String date = new SimpleDateFormat("dd", Locale.US).format(expense.getDateinserted());
        viewHolder.txtYear.setText(year);
        viewHolder.txtMonth.setText(month);
        viewHolder.txtDate.setText(date);
    }

    @Override
    public int getItemCount() {
        return  expenses == null ? 0 : expenses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtYear,txtMonth,txtDate;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtYear = itemView.findViewById(R.id.txtYear);
            txtMonth = itemView.findViewById(R.id.txtMonth);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
