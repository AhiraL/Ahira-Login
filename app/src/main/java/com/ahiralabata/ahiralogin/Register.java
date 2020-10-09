package com.ahiralabata.ahiralogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Register extends AppCompatActivity {
    EditText username, password, email, nama, sekolah, alamat;
    Button simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        nama = findViewById(R.id.nama);
        sekolah = findViewById(R.id.sekolah);
        alamat = findViewById(R.id.alamat);
        simpan = findViewById(R.id.simpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidation()) {
                    simpanFileData();
                } else {
                    Toast.makeText(Register.this, "Mohon Lengkapi Seluruh Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean isValidation() {
        if (username.getText().toString().equals("") || password.getText().toString().equals("") || email.getText().toString().equals("") || nama.getText().toString().equals("") || sekolah.getText().toString().equals("") || alamat.getText().toString().equals("")) {
            return false;
        }else {
            return  true;
        }
    }

    void simpanFileData() {
        String isiFile = username.getText().toString() + ";" + password.getText().toString() + ";" + email.getText().toString() + ";" + nama.getText().toString() + ";" + sekolah.getText().toString() + ";" + alamat.getText().toString();
        File file = new File(getFilesDir(), username.getText().toString());
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}