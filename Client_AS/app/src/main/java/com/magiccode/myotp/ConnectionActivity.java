package com.magiccode.myotp;

import androidx.appcompat.app.AppCompatActivity;
import  android.os.Bundle;
import  android.content.Intent;
import android.widget.TextView;
import android.widget.TextView;
import android.view.View;
import dev.gustavoavila.websocketclient.WebSocketClient;
import java.net.URI;
import java.net.URISyntaxException;

import dev.gustavoavila.websocketclient.WebSocketClient;


public class ConnectionActivity extends AppCompatActivity {
    private WebSocketClient webSocketClient;
    public TextView logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        this.logs = findViewById(R.id.logs);

        // Recupere os dados passados da Activity anterior
        Intent intent = getIntent();
        String ip = intent.getStringExtra("ip");
        int port = intent.getIntExtra("port",0);

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
                logs.setText("ligacao criada");
                //webSocketClient.send("Hello World!");
            }

            @Override
            public void onTextReceived(String s) {
                //Log.i("WebSocket", "Message received");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            logs.setText("Mensagem texto: "+s);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            }

            @Override
            public void onBinaryReceived(byte[] data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            logs.setText("Mensagem binaria");
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            }

            @Override
            public void onPingReceived(byte[] data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            logs.setText("Mensagem ping");
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onPongReceived(byte[] data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            logs.setText("Mensagem pong");
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            }

            @Override
            public void onException(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            logs.setText("Erro: "+e.toString());
                        } catch (Exception e){
                            e.printStackTrace();
                            logs.setText("Erro: "+e.toString());
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
                            logs.setText("Encerrado o fechamendo da ligacao");
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
            public void onCloseReceived() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            logs.setText("Ligacao encerrada");
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            }
        };

        //webSocketClient.setConnectTimeout(10000);
        // webSocketClient.setReadTimeout(60000);
        //webSocketClient.enableAutomaticReconnection(5000);
        webSocketClient.connect();
    }
}
