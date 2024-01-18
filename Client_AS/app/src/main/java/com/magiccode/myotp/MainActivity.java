package com.magiccode.myotp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import  android.content.Intent;

public class MainActivity extends AppCompatActivity {

    public EditText ip;
    public EditText port;
    public Button connectBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ip = findViewById(R.id.ip);
        this.port = findViewById(R.id.port);
        this.connectBt = findViewById(R.id.connectbt);
        Intent intent = new Intent(this, ConnectionActivity.class);
        this.connectBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("ip", MainActivity.this.ip.getText().toString());
                intent.putExtra("port", Integer.valueOf(MainActivity.this.port.getText().toString()));
                startActivity(intent);
            }
        });

    }


}