package br.com.farmasim.farmasim;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.farmasim.farmasim._functions.RecyclerViewAdapter;
import br.com.farmasim.farmasim._obj.Usuario;

public class LogadoActivity extends AppCompatActivity {
    HashMap<String, String> minhaLista;
    Usuario userData;
    private ArrayList<String> mNomeRemedio = new ArrayList<>();
    private ArrayList<String> mHoraRemedio = new ArrayList<>();
    private ArrayList<Integer> mBola = new ArrayList<>();
    private Context mContext;
    //mNomeRemedio("");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);
        Log.d("myTag","onCreate: started.");
        Intent intent = getIntent();
        this.minhaLista = (HashMap<String, String>)intent.getSerializableExtra("MinhaLista");
        this.userData = (Usuario) intent.getSerializableExtra("UserData");

        initNomes();
    }

    private void initNomes(){
        Log.d("myTag","initNomes: started.");
        mNomeRemedio.add("aaaaaaaaaaaaaaa");
        mHoraRemedio.add("111111111111");
        mBola.add(Color.parseColor("#FFC9CCF1"));
        mNomeRemedio.add("bbbbbbbbbbbbbb");
        mHoraRemedio.add("22222222222");
        mBola.add(Color.parseColor("#FFC9CCF1"));
        mNomeRemedio.add("ccccccccccccc");
        mHoraRemedio.add("3333333333333");
        mBola.add(Color.parseColor("#FFC9CCF1"));
        mNomeRemedio.add("dddddddddddd");
        mHoraRemedio.add("4444444444444");
        mBola.add(Color.parseColor("#FFC9CCF1"));
        mNomeRemedio.add("eeeeeeeeeeeee");
        mHoraRemedio.add("555555555555");
        mBola.add(Color.parseColor("#FFC9CCF1"));
        mNomeRemedio.add("ffffffffffffff");
        mHoraRemedio.add("66666666666666");
        mBola.add(Color.parseColor("#FFC9CCF1"));

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
}
