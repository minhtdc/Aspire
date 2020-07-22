package com.example.aspire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.aspire.adapter.MyAdapter;
import com.example.aspire.data_models.NhanSu;

import java.util.ArrayList;

public class MemberReviewActivity extends AppCompatActivity {
    private MyAdapter adapter;
    private ArrayList<NhanSu> listMembers;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_review_layout);
        setTitle("Duyệt thành viên");
        listView = findViewById(R.id.listMember);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        listMembers = new ArrayList<NhanSu>();
        NhanSu nhanSu1 = new NhanSu("minh", "cho vo nhom di");
        listMembers.add(nhanSu1);
        NhanSu nhanSu2 = new NhanSu("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(nhanSu2);
        NhanSu nhanSu4 = new NhanSu("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(nhanSu4);
        NhanSu nhanSu23 = new NhanSu("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(nhanSu23);
        NhanSu nhanSu24 = new NhanSu("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(nhanSu24);
        NhanSu nhanSu25 = new NhanSu("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(nhanSu25);
        NhanSu nhanSu21 = new NhanSu("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(nhanSu21);
        NhanSu nhanSu29 = new NhanSu("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(nhanSu29);
        NhanSu nhanSu27 = new NhanSu("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(nhanSu27);
        NhanSu nhanSu3 = new NhanSu("nhuw", "cho vo nhom di nhaaaaaajfdjbdshbjhdsbjhdshcvdhjsdhjdshchdsbckagdfyúeùy" +
                "gưeỳgeukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkwgfywegecygeuge" +
                "ydgưguygứccùacrfvđfcscsca");
        listMembers.add(nhanSu3);

        adapter = new MyAdapter(this, R.layout.list_member_review_layout, listMembers);
        //adapter = new ArrayAdapter<NhanSu>(this,android.R.layout.simple_list_item_1, arrNhanSu);
        listView.setAdapter(adapter);

    }
}
