package com.example.mybensin;

import android.content.Context;
import android.widget.Toast;

public class Pesan {

    public static void pesan(Context context,String msg)
    {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
