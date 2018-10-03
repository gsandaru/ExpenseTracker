package gihansandaru.dev.expensetracker;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import gihansandaru.dev.expensetracker.Adapters.ExpenseAdapter;
import gihansandaru.dev.expensetracker.models.Expense;
import gihansandaru.dev.expensetracker.viewmodels.ExpensesViewModel;

public class MainActivity extends AppCompatActivity {

    public ExpensesViewModel expensesViewModel;
    private RecyclerView recyclerView;
    private ExpenseAdapter expenseAdapter;
    private Spinner spinner;
    private EditText txtAmount;
    private Button btnNewExpense;
    private TextView txtNoExpensesToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner = findViewById(R.id.dropdown_Expenses_category);
        txtAmount = findViewById(R.id.text_Amount);
        btnNewExpense = findViewById(R.id.btnAddExpense);
        txtNoExpensesToDisplay = findViewById(R.id.txtNoExpensesToDisplay);
        recyclerView = findViewById(R.id.Recyclerview_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        expensesViewModel = ViewModelProviders.of(this).get(ExpensesViewModel.class);

        expensesViewModel.getListLiveData().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(@Nullable List<Expense> expenses) {
                if( expenses.size() == 0 ){
                    recyclerView.setVisibility(View.GONE);
                }else{
                    recyclerView.setVisibility(View.VISIBLE);
                    txtNoExpensesToDisplay.setVisibility(View.GONE);
                    recyclerView.bringToFront();
                    expenseAdapter = new ExpenseAdapter(getApplicationContext(),expenses);

                    recyclerView.setAdapter(expenseAdapter);
                }
            }
        });

        btnNewExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amt = txtAmount.getText().toString();
                Expense expense = new Expense(0,spinner.getSelectedItem().toString(),Long.valueOf(amt),new Date());
                expensesViewModel.insertExpense(expense);
            }
        });
    }
}
