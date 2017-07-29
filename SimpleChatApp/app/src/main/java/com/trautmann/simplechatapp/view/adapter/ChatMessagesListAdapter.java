package com.trautmann.simplechatapp.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.trautmann.simplechatapp.R;
import com.trautmann.simplechatapp.databinding.ChatMessageListItemBinding;
import com.trautmann.simplechatapp.model.ChatMessage;
import com.trautmann.simplechatapp.viewmodel.ChatMessageListItemViewModel;

import java.util.List;

/**
 * Created by Brandon Trautmann
 */

public class ChatMessagesListAdapter extends RecyclerView.Adapter<ChatMessagesListAdapter.ChatMessageBindingHolder> {

    private List<ChatMessage> chatMessages;
    private Context context;

    public ChatMessagesListAdapter(List<ChatMessage> chatMessages, Context context) {
        this.chatMessages = chatMessages;
        this.context = context;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chats) {
        this.chatMessages = chats;
    }

    @Override
    public ChatMessageBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ChatMessageListItemBinding chatMessageListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.chat_message_list_item,
                parent,
                false);
        return new ChatMessageBindingHolder(chatMessageListItemBinding);
    }

    @Override
    public void onBindViewHolder(ChatMessageBindingHolder holder, int position) {
        ChatMessageListItemBinding chatMessageListItemBinding = holder.chatMessageListItemBinding;
        chatMessageListItemBinding.setViewModel(new ChatMessageListItemViewModel(chatMessages.get(position)));
    }

    @Override
    public int getItemCount() {
        return chatMessages == null ? 0 : chatMessages.size();
    }

    public static class ChatMessageBindingHolder extends RecyclerView.ViewHolder {
        private ChatMessageListItemBinding chatMessageListItemBinding;

        public ChatMessageBindingHolder(ChatMessageListItemBinding chatMessageListItemBinding) {
            super(chatMessageListItemBinding.chatMessageContainer);
            this.chatMessageListItemBinding = chatMessageListItemBinding;
        }
    }
}
