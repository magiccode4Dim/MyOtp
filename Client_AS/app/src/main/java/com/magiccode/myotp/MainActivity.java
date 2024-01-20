package com.magiccode.myotp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import  android.content.Intent;
import android.Manifest;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText ip;
    public EditText port;
    public Button connectBt;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pedindo solicitacao para enviar mensagem
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // Se a permissão ainda não foi concedida, solicitar permissão
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS
            );
        }


        this.ip = findViewById(R.id.ip);
        this.port = findViewById(R.id.port);
        this.connectBt = findViewById(R.id.connectbt);
        Intent intent = new Intent(this, ConnectionActivity.class);
        this.connectBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = MainActivity.this.ip.getText().toString();

                //validando os dados
                if(ip.length()==0){
                    Toast.makeText(MainActivity.this,"Ip invalido",Toast.LENGTH_SHORT).show();
                    return;
                }
                int port = 0;
                try {
                    port = Integer.valueOf(MainActivity.this.port.getText().toString());
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"porta invalida",Toast.LENGTH_SHORT).show();
                    return;
                }

                intent.putExtra("ip", ip);
                intent.putExtra("port", port);
                startActivity(intent);
            }
        });

    }




}