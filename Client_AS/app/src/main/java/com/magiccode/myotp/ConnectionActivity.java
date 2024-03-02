package com.magiccode.myotp;

import androidx.appcompat.app.AppCompatActivity;
import  android.os.Bundle;
import  android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import dev.gustavoavila.websocketclient.WebSocketClient;
import java.net.URI;
import java.net.URISyntaxException;

import dev.gustavoavila.websocketclient.WebSocketClient;


public class ConnectionActivity extends AppCompatActivity {
    private WebSocketClient webSocketClient;
    public TextView logs;
    private Gson gson;
    private Button reconect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        this.logs = findViewById(R.id.logs);
        this.reconect = findViewById(R.id.reconect);
        this.gson =  new Gson();
        // Recupere os dados passados da Activity anterior
        Intent intent = getIntent();
        String ip = intent.getStringExtra("ip");
        int port = intent.getIntExtra("port",0);

        //desabilita o botao para reconnectar
        this.reconect.setVisibility(View.INVISIBLE);

        //função de reconnectar
        this.reconect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        reconect.setVisibility(View.INVISIBLE);
                        createWebSocketClient(ip, port);
                    }
                });
            }
        });

        createWebSocketClient(ip,port);


    }

    //codigo do websockert geral
    private void createWebSocketClient(String ip,  int port) {
        URI uri;
        try {
            // Connect to local host
            uri = new URI("ws://"+ip+":"+port);
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen() {
                //Log.i("WebSocket", "Session is starting");
                //reconect.setVisibility(View.INVISIBLE);
                logs.setText("Ligação criada");
                //webSocketClient.send("Hello World!");
            }

            @Override
            public void onTextReceived(String s) {
                //Log.i("WebSocket", "Message received");

                // Use o método fromJson para converter JSON para um objeto Java

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            logs.setText(s);
                            Toast.makeText(ConnectionActivity.this,s,Toast.LENGTH_SHORT).show();
                            Mensagem m = gson.fromJson(s, Mensagem.class);
                            SendMessage.sendDirectSms(m.getCell(),m.getMessage());
                        } catch (Exception e){
                        Toast.makeText(ConnectionActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

            @Override
            public void onBinaryReceived(byte[] data) {


            }

            @Override
            public void onPingReceived(byte[] data) {

            }

            @Override
            public void onPongReceived(byte[] data) {


            }

            @Override
            public void onException(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            logs.setText("Desconectado");
                            Toast.makeText(ConnectionActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                            reconect.setVisibility(View.VISIBLE);
                        } catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(ConnectionActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

            @Override
            public void onCloseReceived(int reason, String description) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            logs.setText("Ligação Encerrada");
                            reconect.setVisibility(View.VISIBLE);
                        } catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(ConnectionActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            public void onCloseReceived() {


            }
        };

        //webSocketClient.setConnectTimeout(10000);
        // webSocketClient.setReadTimeout(60000);
        //webSocketClient.enableAutomaticReconnection(1);
        webSocketClient.connect();


    }
}
