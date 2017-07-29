package com.trautmann.simplechatapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.trautmann.simplechatapp.R;
import com.trautmann.simplechatapp.databinding.ProfileActivityBinding;
import com.trautmann.simplechatapp.viewmodel.ProfileActivityViewModel;

/**
 * Created by Brandon Trautmann
 */

public class ProfileActivity extends AppCompatActivity {


    ProfileActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.profile_activity);
        binding.setViewModel(new ProfileActivityViewModel(this));

        binding.getViewModel().getProfile();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editProfileButton:
                binding.getViewModel().setEditingProfile(!binding.getViewModel().isEditingProfile());
                return true;
        }
        return false;
    }
}
