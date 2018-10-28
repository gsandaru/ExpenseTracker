package gihansandaru.dev.expensetracker.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import gihansandaru.dev.expensetracker.R;
import gihansandaru.dev.expensetracker.models.DayView;
import gihansandaru.dev.expensetracker.models.Expense;

public class DayViewExpenseAdapter extends RecyclerView.Adapter<DayViewExpenseAdapter.ViewHolder> {

    Context _mContext;
    List<DayView> dayViewList;

    public DayViewExpenseAdapter(Context _mContext, List<DayView> dayViewList) {
        this._mContext = _mContext;
        this.dayViewList = dayViewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(_mContext).inflate(R.layout.day_view_adapter_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DayView dayView = dayViewList.get(i);
        viewHolder.textView_title.setText(dayView.getDayView_date().toString());
        if(dayView.getExpenseList().isEmpty())
            return;
        for (Expense expense:dayView.getExpenseList()) {
            LinearLayout linearLayout = new LinearLayout(_mContext);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setBackgroundColor(_mContext.getResources().getColor(R.color.app_green));
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            TextView expenseName = new TextView(_mContext);
            expenseName.setTextColor(_mContext.getResources().getColor(R.color.app_black));
            expenseName.setText(expense.getName());

            linearLayout.addView(expenseName);
            viewHolder.containerView.addView(linearLayout);
        }
    }

    @Override
    public int getItemCount() {
        return dayViewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout containerView;
        TextView textView_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_title = itemView.findViewById(R.id.day_view_title_text);
            containerView = itemView.findViewById(R.id.container_subitems);
        }
    }
}
