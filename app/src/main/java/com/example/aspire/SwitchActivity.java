package com.example.aspire;

import android.content.Context;
import android.content.Intent;

public class SwitchActivity {
    public static void goToNewFeed(final Context context) {
        //Starting a new Intent
<<<<<<< HEAD
        Intent intent = new Intent(context, NewFeedActivity.class);
=======
        Intent intent = new Intent(context, CreateGroupActivity.class);
>>>>>>> Minh-Nguyễn
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }
}
