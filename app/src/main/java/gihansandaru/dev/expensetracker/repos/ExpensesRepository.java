package gihansandaru.dev.expensetracker.repos;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import gihansandaru.dev.expensetracker.daos.ExpenseDao;
import gihansandaru.dev.expensetracker.db.AppDatabase;
import gihansandaru.dev.expensetracker.models.Expense;

public class ExpensesRepository  {
    private ExpenseDao expenseDao;
    private LiveData<List<Expense>>  listLiveData;

    public ExpensesRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.expenseDao = db.expenseDao();
        listLiveData = expenseDao.selectAllData();
    }

    public LiveData<List<Expense>> getExpenses (){
        return this.listLiveData;
    }

    public void insert (Expense expense) {
        new insertAsyncTask(expenseDao).execute(expense);
    }

    private class insertAsyncTask extends AsyncTask<Expense, Void, Void> {

        private ExpenseDao expenseDao;

        public insertAsyncTask(ExpenseDao expenseDao) {

            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            expenseDao.insert(expenses[0]);
            return null;
        }
    }
}
