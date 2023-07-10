package xuan.code;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txt_view;
    EditText edtHo, edtTen, edtLop, edtid;
    Button btnThem, btnCapNhat, btnXoa,btn_xoatatca,btn_timkiem;

    sinhvien qlsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_view = findViewById(R.id.txt_view);
        edtHo = findViewById(R.id.edt_ho);
        edtTen = findViewById(R.id.edt_ten);
        edtLop = findViewById(R.id.edt_lop);
        edtid = findViewById(R.id.edtid);
        btnThem = findViewById(R.id.btn_them);
        btnCapNhat = findViewById(R.id.btn_capnhat);
        btnXoa = findViewById(R.id.btn_xoa);
        btn_xoatatca =findViewById(R.id.btn_xoatatca);
        btn_timkiem=findViewById(R.id.btn_timkiem);


        qlsv = new sinhvien(this);
//        qlsv.taosv("tran van", "dung", "K21");
//        qlsv.taosv("minh xuan", "dxuan", "K22");
//       qlsv.taosv("minh lu", "du", "K23");

        displayStudents();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ho = edtHo.getText().toString();
                String ten = edtTen.getText().toString();
                String lop = edtLop.getText().toString();

                if (!ho.isEmpty() && !ten.isEmpty() && !lop.isEmpty()) {
                    qlsv.taosv(ho, ten, lop);
                    Toast.makeText(MainActivity.this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                    clearFields();
                    displayStudents();
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtid.getText().toString();
                String ho = edtHo.getText().toString();
                String ten = edtTen.getText().toString();
                String lop = edtLop.getText().toString();

                if (!ho.isEmpty() && !ten.isEmpty() && !lop.isEmpty()) {
                    boolean result = qlsv.capnhatsv(id,ho,ten,lop);
                    if (result) {
                        Toast.makeText(MainActivity.this, "Cập nhật thông tin sinh viên thành công", Toast.LENGTH_SHORT).show();
                        clearFields();
                        displayStudents();
                    } else {
                        Toast.makeText(MainActivity.this, "Không tìm thấy sinh viên cần cập nhật", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_xoatatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlsv.xoaTatCaSinhVien();
                Toast.makeText(MainActivity.this, "Đã xóa tất cả sinh viên", Toast.LENGTH_SHORT).show();
                displayStudents();


            }
        });


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, xoa.class);
                startActivity(intent);

            }

        });
        btn_timkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtTen.getText().toString();

                if (!ten.isEmpty()) {
                    Cursor result = qlsv.timsv(ten);
                    if (result != null && result.getCount() > 0) {
                        // Display the search results
                        StringBuilder chuoi = new StringBuilder();
                        result.moveToFirst();
                        while (!result.isAfterLast()) {
                            chuoi.append(result.getString(0)).append("\t\t\t");
                            chuoi.append(result.getString(1)).append("\t\t\t");
                            chuoi.append(result.getString(2)).append("\t\t\t");
                            chuoi.append(result.getString(3)).append("\n");
                            result.moveToNext();
                        }
                        txt_view.setText(chuoi.toString());
                    } else {
                        Toast.makeText(MainActivity.this, "Không tìm thấy sinh viên", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên sinh viên", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void displayStudents() {
        Cursor cursor = qlsv.getAllsv();
        StringBuilder chuoi = new StringBuilder();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            chuoi.append(cursor.getString(0)).append("\t\t\t");
            chuoi.append(cursor.getString(1)).append("\t\t\t");
            chuoi.append(cursor.getString(2)).append("\t\t\t");
            chuoi.append(cursor.getString(3)).append("\n");
            cursor.moveToNext();
        }
        txt_view.setText(chuoi.toString());
    }

    private void clearFields() {
        edtHo.setText("");
        edtTen.setText("");
        edtLop.setText("");
        edtid.setText("");
    }
}
