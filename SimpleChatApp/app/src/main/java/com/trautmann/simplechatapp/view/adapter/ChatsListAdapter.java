package com.trautmann.simplechatapp.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.trautmann.simplechatapp.R;
import com.trautmann.simplechatapp.databinding.ChatListItemBinding;
import com.trautmann.simplechatapp.rest.model.Chat;
import com.trautmann.simplechatapp.viewmodel.ChatListItemViewModel;

import java.util.List;

/**
 * Created by Brandon Trautmann
 */

public class ChatsListAdapter extends RecyclerView.Adapter<ChatsListAdapter.ChatBindingHolder> {

    private List<Chat> chats;
    private Context context;

    public ChatsListAdapter(List<Chat> chats, Context context) {
        this.chats = chats;
        this.context = context;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public ChatBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ChatListItemBinding postBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.chat_list_item,
                parent,
                false);
        return new ChatBindingHolder(postBinding);
    }

    @Override
    public void onBindViewHolder(ChatBindingHolder holder, int position) {
        ChatListItemBinding chatListItemBinding = holder.chatListItemBinding;
        chatListItemBinding.setViewModel(new ChatListItemViewModel(context, chats.get(position)));
    }

    @Override
    public int getItemCount() {
        return chats == null ? 0 : chats.size();
    }

    public static class ChatBindingHolder extends RecyclerView.ViewHolder {
        private ChatListItemBinding chatListItemBinding;

        public ChatBindingHolder(ChatListItemBinding chatListItemBinding) {
            super(chatListItemBinding.chatContainer);
            this.chatListItemBinding = chatListItemBinding;
        }
    }
}
