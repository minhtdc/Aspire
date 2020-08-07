package com.example.aspire;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.aspire.signUp.InputEmailActivity;
import com.example.aspire.signUp.InputFullNameActivity;
import com.example.aspire.signUp.InputPasswordActivity;
import com.example.aspire.signUp.InputUsernameActivity;
import com.example.aspire.signUp.SignUpErrorActivity;
import com.example.aspire.signUp.SignUpSuccessActivity;
import com.example.aspire.signUp.StartSignUp;

public class SwitchActivity {
    public static void goToNewFeed(final Context context) {
        //Starting a new Intent
        Intent intent = new Intent(context, NewFeedActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToSignUp(final Context context) {
        //Starting a new Intent
        Intent intent = new Intent(context, StartSignUp.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToLogin(final Context context) {
        //Starting a new Intent
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToStart(final Context context) {
        //Starting a new Intent
        Intent intent = new Intent(context, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToInputFullName(final Context context) {
        //Starting a new Intent
        Intent intent = new Intent(context, InputFullNameActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToSignUpSuccess(final Context context) {
        //Starting a new Intent
        Intent intent = new Intent(context, SignUpSuccessActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToSignUpError(final Context context) {
        //Starting a new Intent
        Intent intent = new Intent(context, SignUpErrorActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToInputUsername(final Context context, String fullName) {
        //Starting a new Intent
        Intent intent = new Intent(context, InputUsernameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("fullName", fullName);

        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToInputEmail(final Context context, String fullName, String userName) {
        //Starting a new Intent
        Intent intent = new Intent(context, InputEmailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("fullName", fullName);
        bundle.putString("userName", userName);

        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToInputPassword(final Context context, String fullName, String userName, String email) {
        //Starting a new Intent
        Intent intent = new Intent(context, InputPasswordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("fullName", fullName);
        bundle.putString("userName", userName);
        bundle.putString("email", email);

        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToListComment(final Context context, String idPost, String idGroup) {
        //Starting a new Intent
        Intent intent = new Intent(context, CommentsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("idPost", idPost);
        bundle.putString("idGroup", idGroup);

        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToListPost(final Context context, String idGroup){
        //Starting a new Intent
        Intent intent = new Intent(context, PostListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("idGroup", idGroup);

        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToMembersActivity(final Context context, String groupID){
        //Starting a new Intent
        Intent intent = new Intent(context, MembersActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("groupID", groupID);

        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToAddPost(final Context context, String groupID){
        //Starting a new Intent
        Intent intent = new Intent(context, AddPostActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("groupID", groupID);

        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToMemberOption(final Context context, String groupID){
        //Starting a new Intent
        Intent intent = new Intent(context, MemberOptionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("groupID", groupID);

        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToPersonPage(final Context context){
        //Starting a new Intent
        Intent intent = new Intent(context, PersonPageActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static void goToGroupManage(final Context context){
        //Starting a new Intent
        Intent intent = new Intent(context, GroupManageActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }
}
