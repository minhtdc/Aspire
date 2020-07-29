package com.example.aspire;

import android.content.Context;
import android.content.Intent;

public class SwitchActivity {
    public static void goToNewFeed(final Context context) {
        //Starting a new Intent
        Intent intent = new Intent(context, JoinGroupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }
}
