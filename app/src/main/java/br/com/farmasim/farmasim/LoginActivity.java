package br.com.farmasim.farmasim;


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
import java.util.Scanner;

import br.com.farmasim.farmasim._functions.Manipulador;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private class LoginTCP extends AsyncTask {
        private String loginName, senha;

        private LoginTCP(String loginName, String senha) {
            this.loginName = loginName;
            this.senha = senha;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String msgEnviar = "login" + "-;-" + loginName + "-;-" + senha;
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
                String valorIncrementado = din.nextLine();

                Log.d("myTag", "Cliente: recebido do servidor o valor " + valorIncrementado);
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
            t.setText("Usuario incorreto");
        } else if (senha.length() < 6) {
            t.setText("Senha incorreta");
        } else {
            t.setText("Conectando...");
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
