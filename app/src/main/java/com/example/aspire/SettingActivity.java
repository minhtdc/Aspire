package com.example.aspire;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingActivity extends AppCompatActivity {
    Button btnConfirm;

    com.google.android.material.textfield.TextInputEditText edtOldPass;
    com.google.android.material.textfield.TextInputEditText edtNewPass;
    EditText edtNewName;

    ProgressDialog dialog;

    protected FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser userCurrent = auth.getCurrentUser();
    DatabaseReference reference;

    //Author: Tran Minh Phuc 06-08-2020
    Toolbar toolbar;
    TextView txt_inToolbar;
    ImageButton imgBtn_inToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_user_layout);

        reference = FirebaseDatabase.getInstance().getReference("users");
        dialog = new ProgressDialog(this);
        btnConfirm = findViewById(R.id.btnConfirm);
        edtNewPass = findViewById(R.id.edtNewPass);
        edtOldPass = findViewById(R.id.edtOldPass);
        edtNewName = findViewById(R.id.edtNewName);

        /*
         * Author: Tran Minh Phuc 06-08-2020
         * Set information in toolbar
         */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_inToolbar = toolbar.findViewById(R.id.txt_title);
        imgBtn_inToolbar = toolbar.findViewById(R.id.imgBtn_inToolbar);
        txt_inToolbar.setText("Cài đặt thông tin");
        imgBtn_inToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchActivity.goToPersonPage(SettingActivity.this);
                finish();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Kiểm tra nếu tất cả ô nhập liệu trống
                if(edtNewPass.getText().toString().equals("") && edtOldPass.getText().toString().equals("") && edtNewName.getText().toString().equals("")){
                    Toast.makeText(SettingActivity.this, "Không có dữ liệu để thay đổi!!!", Toast.LENGTH_SHORT).show();
                }
                //Kiểm tra nếu nhập đầy đủ thông tin đổi mật khẩu
                else if(!edtNewPass.getText().toString().equals("") && !edtOldPass.getText().toString().equals("")){
                    //Nếu ô nhập tên mới rỗng
                    if(edtNewName.getText().toString().equals("")){
                        //Dùng kiểm tra đăng nhập có sẵn trong FireBaseAuth để kiểm tra mật khẩu
                        auth.signInWithEmailAndPassword(userCurrent.getEmail(), edtOldPass.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        //Kiểm tra nếu đúng điều kiện thì thay đổi mật khẩu mới
                                        if (task.isSuccessful()) {
                                            changePassword(btnConfirm);
                                        }
                                        //Nếu không thành công có nghĩa mật khẩu cũ đã nhập sai
                                        else {
                                            Toast.makeText(SettingActivity.this, "Đổi mật khẩu thất bại, mật khẩu cũ nhập sai", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    //Nếu ô nhập tên mới không rỗng
                    else {
                        //Dùng kiểm tra đăng nhập có sẵn trong FireBaseAuth để kiểm tra mật khẩu
                        auth.signInWithEmailAndPassword(userCurrent.getEmail(), edtOldPass.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        //Kiểm tra nếu đúng điều kiện thì thay đổi mật khẩu mới
                                        if (task.isSuccessful()) {
                                            changUsername(btnConfirm);
                                            changePassword(btnConfirm);
                                        }
                                        //Nếu không thành công có nghĩa mật khẩu cũ đã nhập sai
                                        else {
                                            Toast.makeText(SettingActivity.this, "Đổi thông tin thất bại, mật khẩu cũ nhập sai", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
                //Nếu ô nhập tên mới không rỗng
                else if(!edtNewName.getText().toString().equals("")){
                    changUsername(btnConfirm);
                }
            }
        });
    }

    //Hàm đổi mật khẩu
    public void changePassword(View v){
        if(userCurrent != null){
            //Trong lúc đang chờ đợi đổi mật khẩu, hiện trang thái cho người dùng biết
            dialog.setMessage("Vui lòng đợi");
            dialog.show();
            //Đổi mật khẩu
            userCurrent.updatePassword(edtNewPass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                //Nếu đổi mật khẩu thành công thì bỏ qua Dialog
                                dialog.dismiss();
                                Toast.makeText(SettingActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                //Đăng xuất sau khi đổi mật khẩu thành công
                                auth.signOut();
                                finish();
                                //Chuyển về màn hình đăng nhập sau khi đăng xuất
                                Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                startActivity(intent);
                            }
                            else {
                                //Nếu đổi mật khẩu không thành công cũng bỏ qua Dialog
                                dialog.dismiss();
                                Toast.makeText(SettingActivity.this, "Đổi mật khẩu thất bại, chương trình lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    //Hàm đổi tên người dùng
    public void  changUsername(View v){
        if(reference.child(userCurrent.getUid()).child("fullName").equals(edtNewName.getText().toString())){
            Toast.makeText(SettingActivity.this, "Tên mới trùng lặp với tên cũ", Toast.LENGTH_SHORT).show();
            edtNewName.setText("");
        }
        else {
            reference.child(userCurrent.getUid()).child("fullName").setValue(edtNewName.getText().toString());
            edtNewName.setText("");
            Toast.makeText(SettingActivity.this, "Tên mới đã được thay đổi", Toast.LENGTH_SHORT).show();
        }
    }
}
