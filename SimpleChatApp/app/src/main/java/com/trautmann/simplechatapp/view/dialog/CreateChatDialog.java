package com.trautmann.simplechatapp.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.trautmann.simplechatapp.R;

/**
 * Created by Brandon Trautmann
 */

public class CreateChatDialog extends DialogFragment {

    public interface ICreateChat {

        void onCreateClicked(String name, String message);

    }

    private ICreateChat listener;

    public CreateChatDialog() {
        try {
            listener = ((ICreateChat)getActivity());
        } catch (ClassCastException e) {
            dismiss();
            Log.d("CreateChatDialog",getActivity().getClass().getSimpleName() + " does not" +
                    "implement ICreateChat interface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.create_chat_dialog, null);

        EditText nameEditText = (EditText) view.findViewById(R.id.chatNameEditText);
        EditText messageEditText = (EditText) view.findViewById(R.id.chatMessageEditText);

        builder.setView(view);

        builder.setPositiveButton(R.string.new_chat_create_button_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (isFormFilled(nameEditText, messageEditText)) {
                    listener.onCreateClicked(nameEditText.getEditableText().toString(),
                            messageEditText.getEditableText().toString());
                }
            }
        });
        return builder.create();
    }

    public boolean isFormFilled(EditText nameEditText, EditText messageEditText) {
        return !TextUtils.isEmpty(nameEditText.getEditableText().toString())
                && !TextUtils.isEmpty(messageEditText.getEditableText().toString());
    }
}
