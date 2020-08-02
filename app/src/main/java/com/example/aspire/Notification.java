package com.example.aspire;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class Notification {
    public static void error(final Dialog epicDialog, final Context context, String title, String desNotification) {
        epicDialog.setContentView(R.layout.epic_popup_negative);
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Set content
        TextView txt_title = epicDialog.findViewById(R.id.txt_title);
        TextView txt_desNotification = epicDialog.findViewById(R.id.txt_desNotification);
        Button btn_success = epicDialog.findViewById(R.id.btn_success);

        //Set config notification
        btn_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });

        txt_title.setText(title);
        txt_desNotification.setText(desNotification);
        epicDialog.show();
    }
}
