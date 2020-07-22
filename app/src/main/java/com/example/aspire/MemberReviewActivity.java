package com.example.aspire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.aspire.adapter.MyAdapter;
import com.example.aspire.data_models.Users;

import java.util.ArrayList;

public class MemberReviewActivity extends AppCompatActivity {
    private MyAdapter adapter;
    private ArrayList<Users> listMembers;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_option_layout);
        setTitle("Duyệt thành viên");
        listView = findViewById(R.id.listMember);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        listMembers = new ArrayList<Users>();
        Users users1 = new Users("minh", "cho vo nhom di");
        listMembers.add(users1);
        Users users2 = new Users("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(users2);
        Users users4 = new Users("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(users4);
        Users users23 = new Users("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(users23);
        Users users24 = new Users("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(users24);
        Users users25 = new Users("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(users25);
        Users users21 = new Users("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(users21);
        Users users29 = new Users("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(users29);
        Users users27 = new Users("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(users27);
        Users users3 = new Users("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(users3);

        adapter = new MyAdapter(this, R.layout.list_member_option_layout, listMembers);
        //adapter = new ArrayAdapter<NhanSu>(this,android.R.layout.simple_list_item_1, arrNhanSu);
        listView.setAdapter(adapter);

    }
}
