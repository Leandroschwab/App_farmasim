package br.com.farmasim.farmasim;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.os.StrictMode;

import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import br.com.farmasim.farmasim._functions.Manipulador;

import android.os.Handler;

public class LoginActivity extends AppCompatActivity {
    Handler handler = new Handler();
    String resultadoLogin[] = {"false", ""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        HashMap<String, Object> hashMap = (HashMap<String, Object>)intent.getSerializableExtra("map");
        Log.d("HashMapTest", hashMap.get("key").toString());

        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Runnable r = new Runnable() {
            public void run() {
                Log.d("myTag", "Loop:" + resultadoLogin[0]);
                if (resultadoLogin[0].equals("true")){
                    TextView t = (TextView) findViewById(R.id.ErroLogin_text);
                    t.setText(resultadoLogin[1]);
                    resultadoLogin[0] = "false";
                    resultadoLogin[1] = "";
                }

                handler.postDelayed(this, 500);
            }
        };
        handler.postDelayed(r, 500);
    }

    private class LoginTCP extends AsyncTask {
        private String loginName, senha;

        private LoginTCP(String loginName, String senha) {
            this.loginName = loginName;
            this.senha = senha;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String msgEnviar = "Login" + "-;-" + loginName + "-;-" + senha;
            Log.d("myTag", msgEnviar);
            Socket socket = null;
            try {
                String host = Manipulador.getProperty("prop.server.host", getApplicationContext());
                int porta = Integer.parseInt(Manipulador.getProperty("prop.server.porta", getApplicationContext()));
                socket = new Socket(host, porta);
                Log.d("myTag", "Cliente: conectado ao servidor");

                OutputStream socketOut = socket.getOutputStream();
                PrintWriter dout = new PrintWriter(socket.getOutputStream(), true);
                dout.println(msgEnviar);
                Log.d("myTag", "Cliente: valor " + msgEnviar + " enviado para o servidor");


                Scanner din = new Scanner(socket.getInputStream());
                String msgRecebida = din.nextLine();

                Log.d("myTag", "Cliente: recebido do servidor o valor " + msgRecebida);
                String msgRecSplit[]= msgRecebida.split("-;-");
                if (msgRecSplit[1].equals("FalhaLogin")){
                    resultadoLogin[0] = "true";
                    resultadoLogin[1] = msgRecSplit[2];
                }else if (msgRecSplit[1].equals("SucessoLogin")){

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void loginClick(View view) {
        EditText txtLoginName = (EditText) findViewById(R.id.loginID_CX);
        String loginName = txtLoginName.getText().toString();
        EditText txtSenha = (EditText) findViewById(R.id.senha_CX);
        String senha = txtSenha.getText().toString();
        TextView t = (TextView) findViewById(R.id.ErroLogin_text);
        if (loginName.length() < 8) {
            t.setText("O usuario possui 8 numeros");
        } else if (senha.length() < 6) {
            t.setText("A Senha possui 6 caracteres");
        } else {
            t.setText("Conectando... aguarde");
            LoginTCP task = new LoginTCP(loginName, senha);
            task.execute();
        }
    }

    public void voltar(View view) throws IOException {
        String host;
        host = Manipulador.getProperty("prop.server.host", getApplicationContext()); //prop.getProperty("prop.server.host");
        Log.d("myTag", host);
    }
}
