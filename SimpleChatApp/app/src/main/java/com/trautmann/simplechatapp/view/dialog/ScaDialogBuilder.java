package com.trautmann.simplechatapp.view.dialog;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Brandon Trautmann
 */

public class ScaDialogBuilder implements Parcelable {

    private String tag;
    private String title;
    private String message;
    private String positiveButtonText;
    private String negativeButtonText;
    private boolean isCancelable;

    public String getTag() {
        return tag;
    }

    public ScaDialogBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ScaDialogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ScaDialogBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getPositiveButtonText() {
        return positiveButtonText;
    }

    public ScaDialogBuilder setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
        return this;
    }

    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public ScaDialogBuilder setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
        return this;
    }

    public boolean isCancelable() {
        return isCancelable;
    }

    public ScaDialogBuilder setCancelable(boolean cancelable) {
        isCancelable = cancelable;
        return this;
    }

    public static final Creator<ScaDialogBuilder> CREATOR = new Creator<ScaDialogBuilder>() {
        @Override
        public ScaDialogBuilder createFromParcel(Parcel in) {
            return new ScaDialogBuilder(in);
        }

        @Override
        public ScaDialogBuilder[] newArray(int size) {
            return new ScaDialogBuilder[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tag);
        parcel.writeString(title);
        parcel.writeString(message);
        parcel.writeString(positiveButtonText);
        parcel.writeString(negativeButtonText);
        parcel.writeInt(isCancelable ? 1 : 0);
    }

    private ScaDialogBuilder(Parcel in) {
        tag = in.readString();
        title = in.readString();
        message = in.readString();
        positiveButtonText = in.readString();
        negativeButtonText = in.readString();
        isCancelable = in.readInt() == 1;
    }
}
