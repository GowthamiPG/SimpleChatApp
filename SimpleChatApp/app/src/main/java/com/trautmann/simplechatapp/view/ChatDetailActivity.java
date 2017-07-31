package com.trautmann.simplechatapp.view;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.trautmann.simplechatapp.R;
import com.trautmann.simplechatapp.databinding.ChatDetailActivityBinding;
import com.trautmann.simplechatapp.model.Chat;
import com.trautmann.simplechatapp.util.Constants;
import com.trautmann.simplechatapp.view.adapter.ChatMessageItemDecoration;
import com.trautmann.simplechatapp.view.adapter.ChatMessagesListAdapter;
import com.trautmann.simplechatapp.view.dialog.ChatActionDialog;
import com.trautmann.simplechatapp.viewmodel.ChatDetailActivityViewModel;

/**
 * Created by Brandon Trautmann
 */

public class ChatDetailActivity extends AppCompatActivity implements ChatActionDialog.IChatAction, LifecycleRegistryOwner {

    private ChatDetailActivityBinding binding;
    private ChatMessagesListAdapter adapter;
    private LifecycleRegistry lifecycleRegistry;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifecycleRegistry = new LifecycleRegistry(this);

        int chatId = getIntent().getExtras().getInt(Constants.IntentArguments.CHAT_ID);
        String chatName = getIntent().getExtras().getString(Constants.IntentArguments.CHAT_NAME);

        binding = DataBindingUtil.setContentView(this, R.layout.chat_detail_activity);

        ChatDetailActivityViewModel viewModel = new ChatDetailActivityViewModel(new Chat(chatId, chatName, null, null));
        binding.setViewModel(viewModel);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.getViewModel().getTitleLiveData().observe(this, title -> {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }
        });

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.chatMessagesListRecyclerView.addItemDecoration(new ChatMessageItemDecoration(this));
        binding.chatMessagesListRecyclerView.setLayoutManager(lm);
        adapter = new ChatMessagesListAdapter(null, this);
        binding.chatMessagesListRecyclerView.setAdapter(adapter);

        binding.getViewModel().getChatMessageLiveData().observe(this, chatMessages -> {
            adapter.setChatMessages(chatMessages);
            adapter.notifyDataSetChanged();
        });

        handleSendClicks();

    }

    private void handleSendClicks() {
        binding.chatDetailSendImageButton.setOnClickListener(view -> {
            if (binding.getViewModel().canSendMessage(binding.chatDetailInputEditText
                    .getEditableText().toString())) {
                binding.getViewModel().createChatMessage(binding.chatDetailInputEditText
                        .getEditableText().toString())
                        .subscribe(createChatMessage -> binding.chatDetailInputEditText.setText(""),
                                throwable -> Toast.makeText(this, "Couldn't send message!",
                                        Toast.LENGTH_SHORT).show());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_detail_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editChatButton:
                ChatActionDialog chatActionDialog = new ChatActionDialog();
                Bundle args = new Bundle();
                args.putString(Constants.DialogArguments.CHAT_ACTION,
                        Constants.DialogArguments.CHAT_ACTION_RENAME);
                chatActionDialog.setArguments(args);
                chatActionDialog.show(getSupportFragmentManager(), "chatAction");
                return true;
        }
        return false;
    }

    @Override
    public void onCreateClicked(String name, String message) {
        // Not implemented
    }

    @Override
    public void onRenameClicked(String name) {
        binding.getViewModel().updateChat(name)
                .subscribe(updateChat -> {},
                        throwable -> Toast.makeText(this,
                                "Unable to rename chat", Toast.LENGTH_SHORT).show());

    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
