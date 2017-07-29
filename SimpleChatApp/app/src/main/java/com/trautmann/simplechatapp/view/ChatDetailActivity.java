package com.trautmann.simplechatapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
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

public class ChatDetailActivity extends AppCompatActivity implements ChatActionDialog.IChatAction {

    private ChatDetailActivityBinding binding;
    private ChatMessagesListAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =
                DataBindingUtil.setContentView(this, R.layout.chat_detail_activity);
        int chatId = getIntent().getExtras().getInt(Constants.IntentArguments.CHAT_ID);
        String chatName = getIntent().getExtras().getString(Constants.IntentArguments.CHAT_NAME);
        binding.setViewModel(new ChatDetailActivityViewModel(this,
                new Chat(chatId, chatName, null, null)));

        setSupportActionBar(binding.chatDetailToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.chatMessagesListRecyclerView.addItemDecoration(new ChatMessageItemDecoration(this));
        binding.chatMessagesListRecyclerView.setLayoutManager(lm);
        adapter = new ChatMessagesListAdapter(null, this);
        binding.chatMessagesListRecyclerView.setAdapter(adapter);

        handleSendButtonClicks();

        getChatMessages(chatId);

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

    //TODO: Use Data binding to handle onClick
    // Can't figure out how to update RecyclerView using binding
    private void handleSendButtonClicks() {
        binding.chatDetailSendImageButton.setOnClickListener(view -> {
            if (!TextUtils.isEmpty(binding.chatDetailInputEditText.getEditableText())) {
                binding.getViewModel().createChatMessage(
                        binding.chatDetailInputEditText.getEditableText().toString())
                        .subscribe(createChatMessage -> {
                            adapter.getChatMessages().add(createChatMessage.getMessageSent());
                            adapter.notifyDataSetChanged();
                        }, throwable -> {
                            Toast.makeText(this, "Error sending message", Toast.LENGTH_SHORT).show();
                        });

            }
        });
    }

    private void getChatMessages(int chatId) {
        binding.getViewModel().getChatMessagesList(chatId)
                .doOnSubscribe(disposable -> Log.d("ChatDetailActivity", "Getting chat messages..."))
                .subscribe(getChatMessagesList -> {
                    if (getChatMessagesList.getChatMessages() != null) {
                        adapter.setChatMessages(getChatMessagesList.getChatMessages());
                        adapter.notifyDataSetChanged();
                    }
                }, throwable -> Toast.makeText(ChatDetailActivity.this, "Error getting" +
                        " messages!", Toast.LENGTH_SHORT).show());
    }


    @Override
    public void onCreateClicked(String name, String message) {
        // Not implemented
    }

    @Override
    public void onRenameClicked(String name) {
        binding.getViewModel().updateChat(name)
                .doOnSubscribe(disposable -> {
                    //TODO Progress
                })
                .subscribe(updateChat -> {

                }, throwable -> Toast.makeText(this,
                        "Unable to rename chat", Toast.LENGTH_SHORT).show());
    }
}
