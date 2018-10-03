package gihansandaru.dev.expensetracker.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import gihansandaru.dev.expensetracker.models.Expense;

@Dao
public interface ExpenseDao {

    @Insert
    void insert(Expense user);

    @Query("SELECT * FROM expense WHERE ID = :id")
    LiveData<List<Expense>> selectById(String id);

    @Query("SELECT * FROM expense")
    LiveData<List<Expense>> selectAllData();

    @Query("Delete from expense")
    void deleteAll();

}
