package br.com.alfaumuarama.aula03_17_08_2023;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContatoActivity extends AppCompatActivity {

    EditText edtAssunto, edtTexto;
    Button btnEnviar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        edtAssunto = findViewById(R.id.edtAssunto);
        edtTexto = findViewById(R.id.edtTexto);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviar();
            }
        });
    }
    private void enviar(){
        try {
            String[] emailPara = new String[]{ "joao@govfacil.com"};
            String corpoMensagem = "Email enviado vai App Android<br />" + edtTexto.getText().toString();

            Intent telaEmail = new Intent(Intent.ACTION_SEND);
            telaEmail.setType("text/html");
            telaEmail.setType("text/rfc8222");

            telaEmail.putExtra(Intent.EXTRA_SUBJECT, edtAssunto.getText().toString());
            telaEmail.putExtra(Intent.EXTRA_TEXT, corpoMensagem);
            telaEmail.putExtra(Intent.EXTRA_EMAIL, emailPara);

            startActivity(Intent.createChooser(telaEmail, "Enviar E-mail"));
        }catch (Exception exp){

        }
    }


}
