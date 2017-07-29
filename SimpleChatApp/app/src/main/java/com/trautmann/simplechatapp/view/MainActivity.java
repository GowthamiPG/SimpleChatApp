package com.trautmann.simplechatapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.trautmann.simplechatapp.R;
import com.trautmann.simplechatapp.databinding.MainActivityBinding;
import com.trautmann.simplechatapp.view.adapter.ChatsListAdapter;
import com.trautmann.simplechatapp.view.dialog.ChatActionDialog;
import com.trautmann.simplechatapp.viewmodel.MainActivityViewModel;

/**
 * Created by Brandon Trautmann
 * Activity for the user to view their messages list
 */

public class MainActivity extends AppCompatActivity implements ChatActionDialog.ICreateChat {

    private MainActivityBinding binding;
    private ChatsListAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setViewModel(new MainActivityViewModel());

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.chatListRecyclerView.setLayoutManager(lm);
        adapter = new ChatsListAdapter(null, this);
        binding.chatListRecyclerView.setAdapter(adapter);

        getChats();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profileButton:
                binding.getViewModel().launchProfileActivity(MainActivity.this);
                return true;
        }
        return false;
    }

    private void getChats() {
        binding.getViewModel().getChatList()
                .subscribe(getChatsList -> {
                    if (getChatsList.getChats() != null) {
                        adapter.setChats(getChatsList.getChats());
                        adapter.notifyDataSetChanged();
                    }
                }, throwable -> Toast.makeText(MainActivity.this,
                        "Error loading chats", Toast.LENGTH_SHORT).show());
    }

    public void onCreateChatFabClicked(View view) {
        ChatActionDialog chatActionDialog = new ChatActionDialog();
        chatActionDialog.show(getSupportFragmentManager(), "createChat");
    }

    @Override
    public void onCreateClicked(String name, String message) {
        binding.getViewModel().createChat(name, message)
                .subscribe(createChat -> getChats(), throwable -> {
                    //TODO: Recreate dialog if call fails
                    Toast.makeText(MainActivity.this, "Couldn't send message",
                            Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void onRenameClicked(String name) {
        
    }
}
