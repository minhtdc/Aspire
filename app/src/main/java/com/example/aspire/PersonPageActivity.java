package com.example.aspire;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aspire.data_models.Users;
import com.example.aspire.signUp.InputEmailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class PersonPageActivity extends AppCompatActivity {
    //Author: Tran Minh Phuc 06-08-2020
    Toolbar toolbar;
    TextView txt_inToolbar;
    ImageButton imgBtn_inToolbar;
    ImageView img_checked_hide_idUser;
    TextView txt_IDUser;
    android_2_func android_2_func = new android_2_func();

    Button btnGroupManager;
    Button btnYourGroup;
    Button btnSetting;
    Button btnLogOut;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            if (firebaseAuth.getCurrentUser() == null) {
                //Do anything here which needs to be done after signout is complete
                Intent intent = new Intent(PersonPageActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            } else {

            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_page_layout);


        //Author: Tran Minh Phuc 06-08-2020
        //Set information in toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Thông tin cá nhân");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonPageActivity.this, NewFeedActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });

        btnLogOut = findViewById(R.id.btnLogOut);
        btnGroupManager = findViewById(R.id.btnManageGroup);
        btnYourGroup = findViewById(R.id.btnYourGroup);
        btnSetting = findViewById(R.id.btnSetting);
        img_checked_hide_idUser = findViewById(R.id.img_checked_hide_idUser);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.addAuthStateListener(authStateListener);
                auth.signOut();
                finish();
            }
        });

        btnGroupManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonPageActivity.this, GroupManageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnYourGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonPageActivity.this, YourGroupActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonPageActivity.this, SettingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        img_checked_hide_idUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_IDUser.getText().equals("*********")) {
                    txt_IDUser.setText(Users.ID_USER_LOGGED_IN);
                    img_checked_hide_idUser.setImageResource(R.drawable.icon_eye_checked);
                } else {
                    txt_IDUser.setText("*********");
                    img_checked_hide_idUser.setImageResource(R.drawable.icon_eye_hide);
                }
            }
        });
    }


    /*
     * Author: Tran Minh Phuc
     * Time: 06-08-2020
     * */
    @Override
    protected void onResume() {
        super.onResume();

        txt_IDUser = findViewById(R.id.txtViewUserId);
        txt_IDUser.setText("*********");

        //Set information of user logged
        DatabaseReference db_ref_userLogged = FirebaseDatabase.getInstance()
                .getReference(String.format("/users/%s/", Users.ID_USER_LOGGED_IN));
        db_ref_userLogged.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    switch (data.getKey()) {
                        case "fullName":
                            TextView txt_fullName = findViewById(R.id.txtViewUsername);
                            txt_fullName.setText(data.getValue().toString());
                            break;
                        case "userName":
                            break;
                        case "colorFavorite":
                            break;
                        case "userAvatar":
                            de.hdodenhof.circleimageview.CircleImageView imgViewAva = findViewById(R.id.imgViewAvatar);
                            imgViewAva.setImageResource(android_2_func.getFileImgByName(data.getValue().toString()));
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
