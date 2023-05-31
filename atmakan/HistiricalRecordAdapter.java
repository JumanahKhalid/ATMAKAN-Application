package com.example.atmakan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistiricalRecordAdapter extends RecyclerView.Adapter<HistiricalRecordAdapter.ViewHolder> {

    private Context context;
    private List<Report> reportList;
    private List<User> userList;

    public HistiricalRecordAdapter(Context context, List<Report> reportList, List<User> userList) {
        this.context = context;
        this.reportList = reportList;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.historical_record_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Report report = reportList.get(position);

        for (User user : userList) {
            if (user.getID().equals(report.getTherapistID())) {
                holder.histiricalRecordMessageTV.setText(user.getFirstName() + " : " + report.getFeedback());
            }
        }

    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView histiricalRecordMessageTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            histiricalRecordMessageTV = itemView.findViewById(R.id.histiricalRecordMessageTV);

        }
    }
}
