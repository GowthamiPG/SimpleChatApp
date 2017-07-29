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
import com.trautmann.simplechatapp.util.Constants;

/**
 * Created by Brandon Trautmann
 */

public class ChatActionDialog extends DialogFragment {

    public interface ICreateChat {

        void onCreateClicked(String name, String message);
        void onRenameClicked(String name);

    }

    private ICreateChat listener;
    private String action;

    private boolean isCreateChat() {
        return action != null &&
                Constants.DialogArguments.CHAT_ACTION_CREATE.equals(action);
    }

    private void setAction() {
        if (getArguments() != null) {
            action = getArguments().getString(Constants.DialogArguments.CHAT_ACTION);
        } else {
            dismiss();
            Log.e("ChatActionDialog", getActivity().getClass().getSimpleName() + " does not" +
                    "specify an action");
        }
    }

    EditText messageEditText;
    EditText nameEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        try {
            listener = ((ICreateChat) getActivity());
        } catch (ClassCastException e) {
            dismiss();
            Log.e("ChatActionDialog", getActivity().getClass().getSimpleName() + " does not" +
                    "implement ICreateChat interface");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        setAction();

        View view;
        if (isCreateChat()) {
            builder.setTitle(R.string.new_chat_dialog_title);
            view = inflater.inflate(R.layout.create_chat_dialog, null);
            messageEditText = (EditText) view.findViewById(R.id.chatMessageEditText);

        } else {
            builder.setTitle(R.string.rename_chat_dialog_title);
            view = inflater.inflate(R.layout.rename_chat_dialog, null);
        }

        builder.setView(view);

        nameEditText = (EditText) view.findViewById(R.id.chatNameEditText);

        int positiveButtonText = isCreateChat() ? R.string.new_chat_dialog_create_button_text
                : R.string.rename_chat_dialog_rename_button_text;

        builder.setPositiveButton(positiveButtonText,
                isCreateChat() ? getCreateChatPositiveButtonClickListener()
                        : getRenameChatPositiveButtonClickListener());

        builder.setNegativeButton(R.string.new_chat_dialog_cancel_button_text, null);
        return builder.create();
    }

    private DialogInterface.OnClickListener getCreateChatPositiveButtonClickListener() {
        return (dialogInterface, i) -> {
            if (!TextUtils.isEmpty(nameEditText.getEditableText().toString())
                && (!TextUtils.isEmpty(messageEditText.getEditableText().toString()))) {
                listener.onCreateClicked(nameEditText.getEditableText().toString(),
                        messageEditText.getEditableText().toString());
            }
        };
    }

    private DialogInterface.OnClickListener getRenameChatPositiveButtonClickListener() {
        return (dialogInterface, i) -> {
            if (!TextUtils.isEmpty(nameEditText.getEditableText().toString())) {
                listener.onRenameClicked(nameEditText.getEditableText().toString());
            }
        };
    }
}
