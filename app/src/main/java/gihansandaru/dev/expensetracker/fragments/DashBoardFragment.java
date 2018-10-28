package gihansandaru.dev.expensetracker.fragments;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gihansandaru.dev.expensetracker.Adapters.DayViewExpenseAdapter;
import gihansandaru.dev.expensetracker.R;
import gihansandaru.dev.expensetracker.models.DayView;
import gihansandaru.dev.expensetracker.models.Expense;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class DashBoardFragment extends Fragment {
    @BindView(R.id.recyclerview_main)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(new Expense(1,"Expense One",1500l,new Date()));

        DayView dayView = new DayView();
        dayView.setDayView_date(new Date());
        dayView.setExpenseList(expenseList);


        List<DayView> dayViewList  = new ArrayList<>();
        dayViewList.add(dayView);
        DayViewExpenseAdapter dayViewExpenseAdapter = new DayViewExpenseAdapter(getContext(),dayViewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(dayViewExpenseAdapter);
        recyclerView.bringToFront();

    }
}
