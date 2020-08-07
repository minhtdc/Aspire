package com.example.aspire;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aspire.adapter.AdapterNewfeed;
import com.example.aspire.data_models.Users;
import com.example.aspire.data_models.Groups;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewFeedActivity extends AppCompatActivity {
    private android_2_func android_2_func;
    private AdapterNewfeed adapter;
    private ArrayList<Groups> listGroup;
    ListView listViewGroup;
    ImageButton btnSearch;
    EditText editSearch;
    ImageView img_setting;
    TextView txt_nameApp;
    boolean searching = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_feed_layout);

        //Loading
        android_2_func = new android_2_func();
        android_2_func.showLoading(NewFeedActivity.this);

        txt_nameApp = findViewById(R.id.txt_nameApp);
        img_setting = findViewById(R.id.img_setting);
        btnSearch = findViewById(R.id.btnSearch);
        editSearch = findViewById(R.id.edtSearch);
        listViewGroup = findViewById(R.id.listNew);

        listGroup = new ArrayList<Groups>();
        adapter = new AdapterNewfeed(this, R.layout.listview_newfeed_layout, listGroup);
        listViewGroup.setAdapter(adapter);

        //click setting
        img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewFeedActivity.this, PersonPageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        //Get event click button search group
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searching) {
                    txt_nameApp.setVisibility(View.INVISIBLE);
                    editSearch.setVisibility(View.VISIBLE);
                    searching = false;
                } else {
                    searching = true;
                    editSearch.setText("");
                    txt_nameApp.setVisibility(View.VISIBLE);
                    editSearch.setVisibility(View.INVISIBLE);
                }
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.clear();
                if (editSearch.getText().toString().equals("")) {
                    // đưa dữ liệu từ db lên listview
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myGroups = database.getReference("groups");
                    myGroups.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            adapter.clear();
                            for (DataSnapshot data : snapshot.getChildren()) {
                                String key = data.getKey();
                                Groups group = data.getValue(Groups.class);
                                group.setGroupID(key);
                                adapter.add(group);
                            }
                            android_2_func.closeLoading();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                } else {
                    // đưa dữ liệu từ db lên listview
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myGroups = database.getReference("groups");
                    myGroups.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot data : snapshot.getChildren()) {
                                String key = data.getKey();
                                Groups group = data.getValue(Groups.class);
                                if (android_2_func.isStrLike(editSearch.getText().toString(), group.getGroupName())) {
                                    group.setGroupID(key);
                                    adapter.add(group);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Set avatar for user logged
        Users user = new Users();
        DatabaseReference db_ref_userLogged = FirebaseDatabase.getInstance()
                .getReference(String.format("/users/%s/userAvatar/", user.getUserID()));
        db_ref_userLogged.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                de.hdodenhof.circleimageview.CircleImageView imgViewAva = findViewById(R.id.imgAVT);
                imgViewAva.setImageResource(android_2_func.getFileImgByName(snapshot.getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        // đưa dữ liệu từ db lên listview
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myGroups = database.getReference("groups");
        myGroups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    String key = data.getKey();
                    Groups group = data.getValue(Groups.class);
                    group.setGroupID(key);
                    adapter.add(group);
                }
                android_2_func.closeLoading();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}