package gihansandaru.dev.expensetracker.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
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

        viewHolder.textView_title.setText(new SimpleDateFormat("dd MMM yyyy").format(dayView.getDayView_date()));
        if(dayView.getExpenseList().isEmpty())
            return;
        for (Expense expense:dayView.getExpenseList()) {
            LinearLayout row = new LinearLayout(_mContext);
            row.setOrientation(LinearLayout.VERTICAL);
            row.setBackgroundColor(_mContext.getResources().getColor(R.color.app_green));
            row.setPadding(5,5,5,5);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));



            TextView expenseName = new TextView(_mContext);
            expenseName.setTextColor(_mContext.getResources().getColor(R.color.app_black));
            expenseName.setText(expense.getName());
            expenseName.setPadding(15,5,5,5);

            ImageView expenseTypeImageView = new ImageView(_mContext);
            expenseTypeImageView.setLayoutParams(new ViewGroup.LayoutParams(15,15));
            expenseTypeImageView.setMaxHeight(15);
            expenseTypeImageView.setMaxWidth(15);
            expenseTypeImageView.setImageResource(R.drawable.ic_add_shopping_cart_black_24dp);




            LinearLayout col = new LinearLayout(_mContext);
            col.setOrientation(LinearLayout.HORIZONTAL);
            col.addView(expenseName);

            row.addView(col);
            viewHolder.containerView.addView(row);
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
