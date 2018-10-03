package gihansandaru.dev.expensetracker.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import gihansandaru.dev.expensetracker.models.Expense;
import gihansandaru.dev.expensetracker.repos.ExpensesRepository;

public class ExpensesViewModel extends AndroidViewModel {

    private ExpensesRepository expensesRepository;
    private LiveData<List<Expense>> listLiveData;

    public ExpensesViewModel(@NonNull Application application) {
        super(application);
        expensesRepository = new ExpensesRepository(application);
        listLiveData = expensesRepository.getExpenses();
    }

    public LiveData<List<Expense>> getListLiveData(){
        return listLiveData;
    }
    public void insertExpense(Expense expense){
        expensesRepository.insert(expense);
    }
}
