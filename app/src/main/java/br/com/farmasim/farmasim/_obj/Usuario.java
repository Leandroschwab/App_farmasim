package br.com.farmasim.farmasim._obj;

import java.io.Serializable;

public class Usuario implements Serializable{

    public String user;
    public String nome;
    public String senha;
    public String anjo;
    public int Qtderemedios=0;
    public void addRemedio(){
        this.Qtderemedios+=1;
    }

}
