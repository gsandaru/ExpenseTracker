package gihansandaru.dev.expensetracker.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gihansandaru.dev.expensetracker.Adapters.ExpenseAdapter;
import gihansandaru.dev.expensetracker.R;
import gihansandaru.dev.expensetracker.models.Expense;
import gihansandaru.dev.expensetracker.viewmodels.ExpensesViewModel;

public class HomeFragment extends Fragment {


    public ExpensesViewModel expensesViewModel;
    private ExpenseAdapter expenseAdapter;

    @BindView(R.id.recyclerview_main)
    RecyclerView recyclerView;

    @BindView(R.id.dropdown_Expenses_category)
    Spinner spinner;

    @BindView(R.id.text_Amount)
    EditText txtAmount;

    @BindView(R.id.btnAddExpense)
    Button btnNewExpense;

    @BindView(R.id.txtNoExpensesToDisplay)
    TextView txtNoExpensesToDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        expensesViewModel = ViewModelProviders.of(this).get(ExpensesViewModel.class);

        expensesViewModel.getListLiveData().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(@Nullable List<Expense> expenses) {
                if (expenses.size() == 0) {
                    recyclerView.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    txtNoExpensesToDisplay.setVisibility(View.GONE);
                    recyclerView.bringToFront();
                    expenseAdapter = new ExpenseAdapter(getContext(), expenses);

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
