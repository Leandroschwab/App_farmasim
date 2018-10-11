package br.com.farmasim.farmasim._obj;

import java.io.Serializable;

public class Usuario implements Serializable{

    public String user;
    public String nome;
    public String senha;
    int Qtderemedios;
    void addRemedio(){
        this.Qtderemedios+=1;
    }
}
