package gihansandaru.dev.expensetracker.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "expense")
public class Expense {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private Long value;

    @TypeConverters(DateConverter.class)
    private Date dateinserted;

    public Expense(int id,String name, Long value, Date dateinserted) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.dateinserted = dateinserted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Date getDateinserted() {
        return dateinserted;
    }

    public void setDateinserted(Date dateinserted) {
        this.dateinserted = dateinserted;
    }
}

