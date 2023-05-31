package com.example.atmakan.messages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atmakan.R;
import com.example.atmakan.Report;
import com.example.atmakan.User;
import com.example.atmakan.chat.ChatParent;

import java.util.List;

public class Messages2Adapter extends RecyclerView.Adapter<Messages2Adapter.MyViewHolder> {

    List<User> userList;
    final Context context;
    String userType;

    public Messages2Adapter(List<User> userList, Context context, String userType) {
        this.userList = userList;
        this.context = context;
        this.userType = userType;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = userList.get(position);

        holder.name.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        holder.lastmessage.setText("");

        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ChatParent.class);
                intent.putExtra("receiveId", user.getID());
                intent.putExtra("FirstName", user.getFirstName());
                intent.putExtra("LastName", user.getLastName());
                intent.putExtra("userType", userType);
                context.startActivity(intent);

            }
        });

    }

    public void updateData(List<User> users) {
        this.userList = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, lastmessage, unseenmessage;
        LinearLayout rootLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            lastmessage = itemView.findViewById(R.id.lastmessage);
            rootLayout = itemView.findViewById(R.id.rootLayout);
        }
    }
}
