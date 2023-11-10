package br.com.alfaumuarama.aula07;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.alfaumuarama.aula07.datasource.TBAluno;
import br.com.alfaumuarama.aula07.models.Aluno;

public class CadastroActivity extends AppCompatActivity {

    EditText edtNome, edtRa;
    Button btnSalvar, btnExcluir, btnCancelar;

    int idAluno = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = findViewById(R.id.edtnome);
        edtRa = findViewById(R.id.edtRa);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnCancelar = findViewById(R.id.btnCancelar);

        Intent caminhoRecebido = getIntent();

        if(caminhoRecebido != null){
            Bundle params = caminhoRecebido.getExtras();
            if(params != null){
                idAluno = params.getInt("id");
                edtNome.setText(params.getString("nome"));
                edtRa.setText(params.getString("ra"));

            }
        }


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluir();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });

    }

    private void salvar(){

        if(edtNome.getText().toString().isEmpty()){
            ShowMessage("O Campo nome é obrigatorio");
            return;
        }if(edtRa.getText().toString().isEmpty()){
            ShowMessage("O Campo ra é obrigatorio");
            return;
        }

        Aluno aluno = new Aluno();
        aluno.id = idAluno;
        aluno.nome = edtNome.getText().toString();
        aluno.ra = edtRa.getText().toString();

        TBAluno tbAluno = new TBAluno(CadastroActivity.this);
        tbAluno.gravar(aluno);

        onBackPressed();
    }

    private void excluir(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroActivity.this);
        alerta.setTitle("Atenção");
        alerta.setMessage("Deseja excluir este aluno?");
        alerta.setNegativeButton("não", null);
        alerta.setPositiveButton("sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                confirmarExclusao();
            }
        });
        alerta.show();

    }

    private void confirmarExclusao(){
        TBAluno tbAluno = new TBAluno(CadastroActivity.this);
        tbAluno.excluir(idAluno);

        ShowMessage("Excluido com sucesso!");

        onBackPressed();
    }
    private void cancelar(){
        onBackPressed();
    }
    private void ShowMessage(String texto){
        AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroActivity.this);
        alerta.setTitle("Atenção");
        alerta.setMessage(texto);
        alerta.setNeutralButton("OK", null);
        alerta.show();
    }

}