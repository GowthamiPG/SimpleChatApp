package com.trautmann.simplechatapp.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Brandon Trautmann
 */

public class ScaDialogFragment extends DialogFragment {

    private static String DATA = "data";

    public interface ClickHandler {
        void doOnOk(String dialogTag);

        void doOnCancel(String dialogTag);
    }

    public static ScaDialogFragment newInstance(ScaDialogBuilder builder) {
        ScaDialogFragment frag = new ScaDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(DATA, builder);
        frag.setArguments(args);
        return frag;
    }

    private Dialog apply(final ScaDialogBuilder builder) {
        setCancelable(builder.isCancelable());
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setMessage(builder.getMessage());
        alertBuilder.setTitle(builder.getTitle());
        alertBuilder.setPositiveButton(builder.getPositiveButtonText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((ClickHandler) getActivity()).doOnOk(builder.getTag());
            }
        });
        alertBuilder.setNegativeButton(builder.getNegativeButtonText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((ClickHandler) getActivity()).doOnCancel(builder.getTag());
            }
        });
        return alertBuilder.create();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ScaDialogBuilder builder = getArguments().getParcelable(DATA);
        return apply(builder);
    }

    public static void showDialog(ScaDialogBuilder builder, AppCompatActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        ScaDialogFragment fragment = ScaDialogFragment.newInstance(builder);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(fragment, builder.getTag());
        ft.commitAllowingStateLoss();

    }
}
