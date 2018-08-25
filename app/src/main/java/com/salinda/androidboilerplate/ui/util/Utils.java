package com.salinda.androidboilerplate.ui.util;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Salinda
 */
public class Utils {
    public static Intent getIntentForAction(String action) {
        Intent intent = new Intent();
        if (action != null) {
            intent.setAction(action);
        }
        return intent;
    }

    public static String trimInput(String input) {
        String output = null;
        if (input != null) {
            output = input.trim();
        }

        return output;
    }

    public static void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static String getString(Context context, int id) {
        String output = null;
        if (context != null) {
            output = context.getString(id);
        }
        return output;
    }
}
