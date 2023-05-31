package com.example.atmakan.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atmakan.R;
import com.example.atmakan.User;
import com.example.atmakan.chat.ChatAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UserSettingsAdapter extends RecyclerView.Adapter<UserSettingsAdapter.ViewHolder> {

    private Context context;
    private List<User> userList;

    public UserSettingsAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserSettingsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_settings_item_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user = userList.get(position);

        holder.userEmailSTV.setText(user.getEmail() + "");

        holder.userStatusTrueIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("users").child(user.getID())
                        .child("userStatus").setValue("true");
                notifyDataSetChanged();
            }
        });

        holder.userStatusRejectIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("users").child(user.getID())
                        .child("userStatus").setValue("reject");
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userEmailSTV;
        ImageView userStatusTrueIV, userStatusRejectIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userEmailSTV = itemView.findViewById(R.id.userEmailSTV);
            userStatusTrueIV = itemView.findViewById(R.id.userStatusTrueIV);
            userStatusRejectIV = itemView.findViewById(R.id.userStatusRejectIV);

        }
    }
}
