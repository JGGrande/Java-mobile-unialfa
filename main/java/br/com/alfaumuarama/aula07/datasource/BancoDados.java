package br.com.alfaumuarama.aula07.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoDados {
    private SQLiteDatabase _db;

    public SQLiteDatabase getDb(){
        return _db;
    }

    //sigleton
    private static final BancoDados bancoDados = new BancoDados();

    public static BancoDados getInstance(){
        return bancoDados;
    }

    public void abrirBanco(Context context){
        if(_db != null){
            if(_db.isOpen() == false){
                criarConexao(context);
            }
        }else {
            criarConexao(context);
        }

    }

    private void criarConexao(Context context){
        _db = context.openOrCreateDatabase("bancoDados.db", Context.MODE_PRIVATE, null);
    }

    private void fecharBanco(){
        if(_db != null){
            _db.close();
        }
    }

    public void executarSql(String sql){
        try {
            _db.execSQL(sql);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
