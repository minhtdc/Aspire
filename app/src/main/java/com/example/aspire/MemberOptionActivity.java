package com.example.aspire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aspire.adapter.AdapterMemberOption;
import com.example.aspire.data_models.Requests;
import com.example.aspire.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MemberOptionActivity extends AppCompatActivity {
    private AdapterMemberOption adapter;
    private ArrayList<Requests> listMembers;
    ListView listView;
    Button btnAccept, btnDeny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_option_layout);
        setTitle("Duyệt thành viên");
        listView = findViewById(R.id.listMember);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        listMembers = new ArrayList<Requests>();
        adapter = new AdapterMemberOption(this, R.layout.list_member_option_layout, listMembers);
        listView.setAdapter(adapter);

        //lấy đối tượng FirebaseDatabase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Kết nối tới node có tên là contacts (node này do ta định nghĩa trong CSDL Firebase)
        DatabaseReference myRef = database.getReference("requests");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                //vòng lặp để lấy dữ liệu khi có sự thay đổi trên Firebase
                for (DataSnapshot data : snapshot.getChildren()) {
                    //lấy key của dữ liệu
                    String key = data.getKey();
                    //lấy giá trị của key (nội dung)
                    Requests request = data.getValue(Requests.class);
                    request.setAdminID(key);
                    adapter.add(request);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
}
