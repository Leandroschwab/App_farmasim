package br.com.farmasim.farmasim;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.farmasim.farmasim._functions.Manipulador;
import br.com.farmasim.farmasim._functions.RecyclerViewAdapter;
import br.com.farmasim.farmasim._obj.Remedio;
import br.com.farmasim.farmasim._obj.Usuario;
import br.com.farmasim.farmasim._functions.Salvar;

public class LogadoActivity extends AppCompatActivity {
    Usuario userData;
    HashMap<String, Remedio> remedio;

    private ArrayList<String> mNomeRemedio = new ArrayList<>();
    private ArrayList<String> mHoraRemedio = new ArrayList<>();
    private ArrayList<Integer> mBola = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);
        Log.d("myTag","onCreate: started.");


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("myTag","onStart: started.");

        this.userData = Salvar.loadUsuario(getApplicationContext());
        this.remedio = Salvar.loadRemediosHashMap(getApplicationContext());
        if (remedio.isEmpty()){
            Log.d("myTag","remedio.isEmpty() : true.");
            syncRemedio();
        }
        initLogado();
        initNomes();
    }

    private void initLogado() {
        TextView t = (TextView) findViewById(R.id.nome_textView);
        t.setText(userData.nome);
        TextView a = (TextView) findViewById(R.id.anjo_textView);
        a.setText(userData.anjo);

    }

    private void initNomes(){
        Log.d("myTag","initNomes: started.");
        Log.d("myTag","qt remeido"+ userData.Qtderemedios);
        mHoraRemedio = new ArrayList<>();
        mBola = new ArrayList<>();
        mNomeRemedio =  new ArrayList<>();
        for (int i=0;i<userData.Qtderemedios;i++){

            Remedio rem = remedio.get(String.valueOf(i));
            mNomeRemedio.add(rem.nome);
            mBola.add(Color.parseColor(rem.cor));
            String textHora;
            textHora = "horarios: ";
            if (rem.hora[0]){
                textHora+="6:00   ";
            }
            if (rem.hora[1]){
                textHora+="7:00   ";
            }
            if (rem.hora[2]){
                textHora+="12:00   ";
            }
            if (rem.hora[3]){
                textHora+="19:00   ";
            }
            if (rem.hora[4]){
                textHora+="21:00   ";
            }
            mHoraRemedio.add(textHora);
            }
        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d("myTag","iniRecyclerView: started.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mNomeRemedio,mHoraRemedio,mBola);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("myTag","iniRecyclerView: finished.");
    }

    public void syncClick(View view){
        Log.d("myTag","syncClick: started.");
        SyncTCP task = new SyncTCP();
        task.execute();
    }
    public void syncRemedio(){
        Log.d("myTag","syncRemedio: started.");
        SyncTCP task = new SyncTCP();
        task.execute();
    }
    private class SyncTCP extends AsyncTask {

        private SyncTCP() {
            Log.d("myTag","SyncTCP: started.");

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String msgEnviar = "Remedio" + "-;-" + userData.user;
            Log.d("myTag", "Sync in backgroud: " + msgEnviar);
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
                Thread.sleep(500);
                String msgRecebida = din.nextLine();

                Log.d("myTag", "Cliente: recebido do servidor o valor " + msgRecebida);
                String msgRecSplita[]= msgRecebida.split("-:-");
                remedio = new HashMap<String, Remedio>();
                userData.Qtderemedios=0;
                for (int i=0;i<msgRecSplita.length;i++){
                    String msgRecSplitb[]= msgRecSplita[i].split("-;-");
                    Remedio rem = new Remedio();
                    rem.nome = msgRecSplitb[0];
                    rem.cor = msgRecSplitb[1];
                    for (int j= 0;j<5;j++){
                        if (msgRecSplitb[j+2].equals("0")) {
                            rem.hora[j] = false;
                        }else{
                            rem.hora[j]= true;
                        }
                    }
                    userData.addRemedio();
                    remedio.put(String.valueOf(i),rem);

                    }
                Salvar.saveUserdata(userData,getApplicationContext());


                Salvar.saveRemediosHashMap(remedio,getApplicationContext());
                reloadLogado();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }


    }
    public void carregarAnjo(View view){
        Log.d("myTag", "carregarAnjo: started");
        Intent intent = new Intent(this,CadastroAnjo.class);
        startActivity(intent);
    }
    public void reloadLogado(){
        Log.d("myTag", "reloadLogado: started");
        Intent intent = new Intent(this,LogadoActivity.class);
        startActivity(intent);
    }
}
