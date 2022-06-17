package com.example.je_attendancesystem.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.je_attendancesystem.R;
import com.example.je_attendancesystem.models.DateTimeModel;
import com.example.je_attendancesystem.models.TimesheetModel;

import java.util.ArrayList;

public class DateTimeAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<DateTimeModel> dateTimeModels;

    public DateTimeAdapter(Context context, ArrayList<DateTimeModel> dateTimeModels){
        this.context = context;
        this.dateTimeModels = dateTimeModels;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.date_time_inflater, parent, false);
        return new DateTimeAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DateTimeModel dateTimeModel = this.dateTimeModels.get(position);
        setAnimation(holder.itemView);
        ((DateTimeAdapterHolder) holder).bind(dateTimeModel);
    }

    @Override
    public int getItemCount() {
        return this.dateTimeModels.size();
    }

    private void setAnimation(View viewToAnimate)
    {
        // If the bound view wasn't previously displayed on screen, it's animated

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(animation);

    }

    public class DateTimeAdapterHolder extends  RecyclerView.ViewHolder{
        TextView date,day;
        ImageButton btn_drop_down;
        RecyclerView recyclerView;
        TimesheetAdapter adapter;
        public DateTimeAdapterHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.text_view_date);
            day = itemView.findViewById(R.id.text_view_day);
            btn_drop_down = itemView.findViewById(R.id.btn_drop_down);
            //Recycleview Instance
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_timesheet);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(manager);
        }

        void bind(DateTimeModel dateTimeModel){

            //List of employee
            ArrayList<TimesheetModel> timesheetModels = new ArrayList<>();
            // create adapter
            recyclerView.smoothScrollToPosition(timesheetModels.size());
            adapter = new TimesheetAdapter(context, timesheetModels);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            //Click listener for drop down
            btn_drop_down.setOnClickListener(new View.OnClickListener() {
                int x = 0;
                @Override
                public void onClick(View view) {
                    if(x % 2 == 0){
                        ArrayList<TimesheetModel> timesheetModels = new ArrayList<>();
                        timesheetModels.add(new TimesheetModel());
                        timesheetModels.add(new TimesheetModel());
                        adapter.getTimesheetModels().addAll(timesheetModels);
                        adapter.notifyDataSetChanged();
                        btn_drop_down.setBackground(context.getDrawable(R.drawable.ic_close_24));

                    }else {
                        adapter.getTimesheetModels().clear();
                        adapter.notifyDataSetChanged();
                        btn_drop_down.setBackground(context.getDrawable(R.drawable.ic_drop_down));
                    }
                    x++;

                }
            });
            //Get data here

        }
    }
}
