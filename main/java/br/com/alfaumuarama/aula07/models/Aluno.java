package br.com.alfaumuarama.aula07.models;

import android.content.ContentValues;

import java.util.HashMap;

public class Aluno {
    public  int id;
    public  String nome;
    public  String ra;

    public ContentValues toDados(){
        ContentValues dados = new ContentValues();

        if(id > 0)
            dados.put("id", id);
        dados.put("nome", nome);
        dados.put("ra", ra);

        return  dados;
    }

    public HashMap<String, String> toMap(){
        HashMap<String, String> dados = new  HashMap<String, String>();

        dados.put("id",String.valueOf(id));
        dados.put("nome", nome);
        dados.put("ra", ra);

        return  dados;
    }
}
