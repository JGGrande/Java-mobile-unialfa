package br.com.alfaumuarama.aula07.datasource;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.alfaumuarama.aula07.models.Aluno;

public class TBAluno {
    public TBAluno(Context context){
        BancoDados.getInstance().abrirBanco(context);

        String sql = "CREATE TABLE IF NOT EXISTS tbAluno(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "ra TEXT" +
                ")";
        BancoDados.getInstance().executarSql(sql);
    }

    private void inserir(Aluno aluno){
//        String sql = "INSERT INTO tbAluno(nome, ra) VALUES ('" +
//                aluno.nome + "'" + "'" + aluno.ra + "')" ;
//        BancoDados.getInstance().executarSql(sql);
        BancoDados.getInstance().getDb().insert("tbAluno", null, aluno.toDados());
    }

    private void alterar(Aluno aluno){
        BancoDados.getInstance().getDb().update(
                "tbAluno",
                aluno.toDados(),
                "id = ?",
                new String[] { String.valueOf(aluno.id) }
        );
    }

    public void excluir(Integer id){
        BancoDados.getInstance().getDb().delete(
                "tbAluno",
                "id = ?",
                new String[] {String.valueOf(id)}
        );
    }

    public void gravar(Aluno aluno){
        ArrayList<Aluno> lista = new ArrayList<>();

        if(aluno.id > 0)
            lista = buscar(aluno.id);
        if(lista.size() > 0)
            alterar(aluno);
        else
            inserir(aluno);
    }

    public  ArrayList<Aluno> buscarTodos(){
        return buscar(0);
    }
    public Aluno buscarPorId(Integer id){
        ArrayList<Aluno> lista = buscar(id);
        if(lista.size() > 0)
            return lista.get(0);
        return new Aluno();
    }

    private ArrayList<Aluno> buscar(Integer id){
        String condicaoSql = "";
        if(id > 0)
            condicaoSql = "id = " + id;
        Cursor cursor = BancoDados.getInstance().getDb().query(
          "tbAluno",
          new String[] {"id, nome, ra"},
          condicaoSql,
          null,
          null,
          null,
          "nome",
          null
        );

        return retornaLista(cursor);
    }

    private ArrayList<Aluno> retornaLista(Cursor cursor){
        ArrayList<Aluno> alunoList = new ArrayList<>();

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            int indiceCampoId = cursor.getColumnIndex("id");
            int indiceCampoNome = cursor.getColumnIndex("nome");
            int indiceCampoRA = cursor.getColumnIndex("ra");

            for (int i = 0; i < cursor.getCount(); i++){
                Aluno aluno = new Aluno();
                aluno.id = cursor.getInt(indiceCampoId);
                aluno.nome = cursor.getString(indiceCampoNome);
                aluno.ra = cursor.getString(indiceCampoRA);

                alunoList.add(aluno);

                cursor.moveToNext();
            }
        }

        return alunoList;
    }
}
