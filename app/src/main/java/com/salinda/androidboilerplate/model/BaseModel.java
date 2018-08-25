package com.salinda.androidboilerplate.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Salinda
 */
public class BaseModel implements Parcelable {

    public static final Creator<BaseModel> CREATOR = new Creator<BaseModel>() {
        @Override
        public BaseModel createFromParcel(Parcel in) {
            return new BaseModel(in);
        }

        @Override
        public BaseModel[] newArray(int size) {
            return new BaseModel[size];
        }
    };

    protected BaseModel(Parcel in) {

    }

    protected BaseModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
