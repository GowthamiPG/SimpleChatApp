package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.trautmann.simplechatapp.view.MainActivity;

/**
 * Created by Brandon Trautmann
 */

public class InitSessionViewModel {

    private Context context;

    public InitSessionViewModel(Context context) {
        this.context = context;
    }

    public View.OnClickListener onClickLogin(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("InitSessionViewModel", "Click!");
                logUserIn();
            }
        };
    }

    public void logUserIn() {
        //TODO: Actually log the user in
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void registerUser() {

    }
}
