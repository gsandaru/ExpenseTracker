package gihansandaru.dev.expensetracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import gihansandaru.dev.expensetracker.fragments.DashBoardFragment;

public class MainActivity extends AppCompatActivity {


    private BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        loadHomeFragment();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id){
                    case R.id.action_home:
                        loadHomeFragment();
                        break;
                    case R.id.action_expenses:
                        openDialog();
                        break;
                    case R.id.action_settings:
                        break;
                }
                return false;
            }
        });


//        spinner = findViewById(R.id.dropdown_Expenses_category);
//        txtAmount = findViewById(R.id.text_Amount);
//        btnNewExpense = findViewById(R.id.btnAddExpense);
//        txtNoExpensesToDisplay = findViewById(R.id.txtNoExpensesToDisplay);
//        recyclerView = findViewById(R.id.Recyclerview_main);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        expensesViewModel = ViewModelProviders.of(this).get(ExpensesViewModel.class);
//
//        expensesViewModel.getListLiveData().observe(this, new Observer<List<Expense>>() {
//            @Override
//            public void onChanged(@Nullable List<Expense> expenses) {
//                if( expenses.size() == 0 ){
//                    recyclerView.setVisibility(View.GONE);
//                }else{
//                    recyclerView.setVisibility(View.VISIBLE);
//                    txtNoExpensesToDisplay.setVisibility(View.GONE);
//                    recyclerView.bringToFront();
//                    expenseAdapter = new ExpenseAdapter(getApplicationContext(),expenses);
//
//                    recyclerView.setAdapter(expenseAdapter);
//                }
//            }
//        });
//
//        btnNewExpense.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String amt = txtAmount.getText().toString();
//                Expense expense = new Expense(0,spinner.getSelectedItem().toString(),Long.valueOf(amt),new Date());
//                expensesViewModel.insertExpense(expense);
//            }
//        });
    }

    private void loadHomeFragment() {
        Fragment fragment = new DashBoardFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

    private void openDialog() {
        View view = getLayoutInflater().inflate(R.layout.bottomsheet, null);
        dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        TextView btnFood = (TextView) view.findViewById(R.id.bsheet_food);
        TextView btnTransport = (TextView) view.findViewById(R.id.bsheet_transport);
        TextView btnGrocery = (TextView) view.findViewById(R.id.bsheet_grocery);
        TextView btnShopping = (TextView) view.findViewById(R.id.bsheet_shopping);
        TextView btnFuel = (TextView) view.findViewById(R.id.bsheet_Fuel);
        TextView btnOther = (TextView) view.findViewById(R.id.bsheet_other);
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        btnTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });




        dialog.show();
    }
}
