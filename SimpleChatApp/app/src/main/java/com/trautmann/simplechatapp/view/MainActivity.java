package com.trautmann.simplechatapp.view;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
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
import com.trautmann.simplechatapp.util.Constants;
import com.trautmann.simplechatapp.view.adapter.ChatsListAdapter;
import com.trautmann.simplechatapp.view.dialog.ChatActionDialog;
import com.trautmann.simplechatapp.viewmodel.MainActivityViewModel;

/**
 * Created by Brandon Trautmann
 * Activity for the user to view their messages list
 */

public class MainActivity extends AppCompatActivity implements ChatActionDialog.IChatAction, LifecycleRegistryOwner {

    private MainActivityBinding binding;
    private ChatsListAdapter adapter;
    private LifecycleRegistry lifecycleRegistry;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifecycleRegistry = new LifecycleRegistry(this);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        MainActivityViewModel viewModel = ViewModelProviders.of(this)
                .get(MainActivityViewModel.class);
        binding.setViewModel(viewModel);

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.chatListRecyclerView.setLayoutManager(lm);
        adapter = new ChatsListAdapter(null, this);
        binding.chatListRecyclerView.setAdapter(adapter);
        viewModel.getChatLiveData().observe(this, chats -> {
            adapter.setChats(chats);
            adapter.notifyDataSetChanged();
        });

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

    public void onCreateChatFabClicked(View view) {
        ChatActionDialog chatActionDialog = new ChatActionDialog();
        Bundle args = new Bundle();
        args.putString(Constants.DialogArguments.CHAT_ACTION,
                Constants.DialogArguments.CHAT_ACTION_CREATE);
        chatActionDialog.setArguments(args);
        chatActionDialog.show(getSupportFragmentManager(), "chatAction");
    }

    @Override
    public void onCreateClicked(String name, String message) {
        binding.getViewModel().createChat(name, message)
                .subscribe(createChat -> {}, throwable -> {
                    //TODO: Recreate dialog if call fails
                    Toast.makeText(MainActivity.this, "Couldn't send message",
                            Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void onRenameClicked(String name) {
        // Not implemented
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
