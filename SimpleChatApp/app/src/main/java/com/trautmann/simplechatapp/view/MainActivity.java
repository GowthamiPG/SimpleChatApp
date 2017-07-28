package com.trautmann.simplechatapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.trautmann.simplechatapp.R;
import com.trautmann.simplechatapp.databinding.MainActivityBinding;
import com.trautmann.simplechatapp.view.adapter.ChatsListAdapter;
import com.trautmann.simplechatapp.viewmodel.MainActivityViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Brandon Trautmann
 * Activity for the user to view their messages list
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.chatListRecyclerView)
    RecyclerView chatListRecyclerView;

    private MainActivityBinding binding;
    private ChatsListAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setViewModel(new MainActivityViewModel());

        ButterKnife.bind(this);

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        chatListRecyclerView.setLayoutManager(lm);
        adapter = new ChatsListAdapter(null, this);
        chatListRecyclerView.setAdapter(adapter);

        getChats();

    }

    private void getChats() {
        binding.getViewModel().getChatList()
                .subscribe(getChatsList -> {
                    if (getChatsList.getChats() != null) {
                        Toast.makeText(MainActivity.this,
                                "Chats found: " + getChatsList.getChats().size(), Toast.LENGTH_SHORT).show();
                        adapter.setChats(getChatsList.getChats());
                        adapter.notifyDataSetChanged();
                    }
                }, throwable -> Toast.makeText(MainActivity.this,
                        "Error loading chats", Toast.LENGTH_SHORT).show());
    }

}
