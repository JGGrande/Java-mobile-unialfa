package br.com.alfaumuarama.aula07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.alfaumuarama.aula07.datasource.TBAluno;
import br.com.alfaumuarama.aula07.models.Aluno;

public class MainActivity extends ListActivity {

    Button btnAdd;

    ArrayList<Aluno> listaAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Aluno aluno = listaAluno.get(position);

        Intent tela = new Intent(MainActivity.this, CadastroActivity.class);

        Bundle params = new Bundle();
        params.putInt("id",aluno.id);
        params.putString("nome",aluno.nome);
        params.putString("ra", aluno.ra);

        tela.putExtras(params);

        startActivity(tela);
    }

    private void atualizarLista(){
        TBAluno tbAluno = new TBAluno(MainActivity.this);
        listaAluno = tbAluno.buscarTodos();

        ListAdapter adapter = new SimpleAdapter(
                MainActivity.this,
                listaToMap(listaAluno),
                R.layout.listview_modelo,
                new String[]{"nome", "ra"},
                new int[] {R.id.txtNome, R.id.txtRa}
        );

        setListAdapter(adapter);
    }

    private ArrayList<HashMap<String, String>> listaToMap(ArrayList<Aluno> lista){
        ArrayList<HashMap<String, String>> listaRetorno = new ArrayList<>();
        for (Aluno aluno: lista){
            listaRetorno.add(aluno.toMap());
        }
        return listaRetorno;
    }
}