package com.example.aspire;

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
    public static void signUp(final Dialog epicDialog, final Context context, String title, String desNotification, boolean isSuccess) {
        epicDialog.setContentView(R.layout.epic_popup_negative);
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Set content
        CardView card = epicDialog.findViewById(R.id.card_notification);
        TextView txt_title = epicDialog.findViewById(R.id.txt_title);
        TextView txt_desNotification = epicDialog.findViewById(R.id.txt_desNotification);
        ImageView img_notification = epicDialog.findViewById(R.id.img_notification);
        Button btn_success = epicDialog.findViewById(R.id.btn_success);
        String colorIsSuccess = "";

        //Set config notification
        if (isSuccess) {
            colorIsSuccess = "#4CAF50";
            img_notification.setImageResource(R.mipmap.icon_success);
            btn_success.setText("Đăng nhập");

            btn_success.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Starting a new Intent
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    context.startActivity(intent);
                }
            });
        } else {
            colorIsSuccess = "#FF675C";
            img_notification.setImageResource(R.mipmap.icon_error);
            btn_success.setText("Đã hiểu");

            btn_success.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    epicDialog.dismiss();
                }
            });
        }

        txt_title.setText(title);
        txt_desNotification.setText(desNotification);
        card.setCardBackgroundColor(Color.parseColor(colorIsSuccess));
        epicDialog.show();
    }

    public static void error(final Dialog epicDialog, final Context context, String title, String desNotification) {
        epicDialog.setContentView(R.layout.epic_popup_negative);
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Set content
        CardView card = epicDialog.findViewById(R.id.card_notification);
        TextView txt_title = epicDialog.findViewById(R.id.txt_title);
        TextView txt_desNotification = epicDialog.findViewById(R.id.txt_desNotification);
        ImageView img_notification = epicDialog.findViewById(R.id.img_notification);
        Button btn_success = epicDialog.findViewById(R.id.btn_success);
        String colorIsSuccess = "";

        //Set config notification
        colorIsSuccess = "#FF675C";
        img_notification.setImageResource(R.mipmap.icon_error);
        btn_success.setText("Đã hiểu");

        btn_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });

        txt_title.setText(title);
        txt_desNotification.setText(desNotification);
        card.setCardBackgroundColor(Color.parseColor(colorIsSuccess));
        epicDialog.show();
    }

    public static void switchActivity(Context context){

    }
}
