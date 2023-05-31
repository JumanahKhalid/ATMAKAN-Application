package com.example.atmakan.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atmakan.MemoryData;
import com.example.atmakan.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    List<ChatItem> chatItemList;
    final Context context;
    String useremail;
    String senderId;

    public ChatAdapter(List<ChatItem> chatItemList, Context context, String senderId) {
        this.chatItemList = chatItemList;
        this.context = context;
        this.useremail = MemoryData.getData(context);
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {

        ChatItem item = chatItemList.get(position);

        if (item.getSenderID().equals(senderId)) {

            holder.oppoLayout.setVisibility(View.GONE);
            holder.oppoMessage.setVisibility(View.GONE);
            holder.oppoTime.setVisibility(View.GONE);

            holder.myLayout.setVisibility(View.VISIBLE);
            holder.myMessage.setVisibility(View.VISIBLE);
            holder.myTime.setVisibility(View.VISIBLE);

            holder.myMessage.setText(item.getMessage() + "");
            holder.myTime.setText(item.getDate() + "");

        } else {

            holder.myLayout.setVisibility(View.GONE);
            holder.myMessage.setVisibility(View.GONE);
            holder.myTime.setVisibility(View.GONE);

            holder.oppoLayout.setVisibility(View.VISIBLE);
            holder.oppoMessage.setVisibility(View.VISIBLE);
            holder.oppoTime.setVisibility(View.VISIBLE);

            holder.oppoMessage.setText(item.getMessage() + "");
            holder.oppoTime.setText(item.getDate() + "");

        }

    }

    @Override
    public int getItemCount() {
        return chatItemList.size();
    }

    public void updatechatList(List<ChatItem> chatItemList) {

        this.chatItemList = chatItemList;

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout oppoLayout, myLayout;
        TextView oppoMessage, myMessage, oppoTime, myTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            oppoLayout = itemView.findViewById(R.id.oppoLayout);
            myLayout = itemView.findViewById(R.id.myLayout);
            oppoMessage = itemView.findViewById(R.id.oppomessage);
            myMessage = itemView.findViewById(R.id.mymessage);
            oppoTime = itemView.findViewById(R.id.oppomessageTime);
            myTime = itemView.findViewById(R.id.mymessageTime);
        }
    }
}
