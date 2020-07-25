package com.example.aspire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aspire.data_models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_email, edt_password;
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private Dialog epicDialog;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        this.edt_email = findViewById(R.id.edt_email);
        this.edt_password = findViewById(R.id.edt_password);
        epicDialog = new Dialog(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("users").push().getKey();
        String userId = "tESTid", username = "TestUsername", password = "passTest", userAvata = "T";
        Users post = new Users(userId, username, password, userAvata);
        Map<String, Object> postValues = null;
        try {
            postValues = post.toMap(post.toJSON());
        } catch (JSONException e) {

        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);


        //get view frm layout
        Button btnLogin;
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Users user = new Users();
                    String email = edt_email.getText().toString();
                    String password = edt_password.getText().toString();

                    user.loginUserWithEmailAndPassword(email, password, epicDialog, LoginActivity.this);
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đủ và đúng thông tin đăng nhập!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), StartingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}