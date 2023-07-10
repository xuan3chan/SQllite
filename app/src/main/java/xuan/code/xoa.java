package xuan.code;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class xoa extends AppCompatActivity {
    Button btn_quayve, btn_xoa;
    EditText edtid;
    sinhvien qlsv;
    MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xoa);
        btn_quayve = findViewById(R.id.btn_quayve);
        btn_xoa = findViewById(R.id.btn_xoasv);
        edtid = findViewById(R.id.edtidxoa);
        qlsv = new sinhvien(this);

        btn_quayve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtid.getText().toString();
                if (!id.isEmpty()) {
                    boolean result = qlsv.xoasv(id);
                    if (result) {
                        Toast.makeText(xoa.this, "Xóa sinh viên thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(xoa.this, "Không tìm thấy sinh viên cần xóa", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(xoa.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(xoa.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
