package com.example.agastya.hellorealm.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by AgastyA on 1/26/2018.
 */

public class Utils {
    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}