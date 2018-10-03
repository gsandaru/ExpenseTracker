package gihansandaru.dev.expensetracker.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import gihansandaru.dev.expensetracker.daos.ExpenseDao;
import gihansandaru.dev.expensetracker.models.Expense;

@Database(entities = {Expense.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends android.arch.persistence.room.RoomDatabase {
    public abstract ExpenseDao expenseDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "room_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

// When you modify the database schema, you'll need to update the version
// number and define how to handle migrations.

    private static AppDatabase.Callback sRoomDatabaseCallback =
            new AppDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ExpenseDao expenseDao;

        PopulateDbAsync(AppDatabase db) {
            expenseDao = db.expenseDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
           expenseDao.deleteAll();

            return null;
        }
    }
}