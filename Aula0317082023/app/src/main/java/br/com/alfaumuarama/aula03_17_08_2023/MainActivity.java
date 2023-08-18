package br.com.alfaumuarama.aula03_17_08_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnContato = findViewById(R.id.btnContato);

        btnContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaContato();
            }
        });
    }
    private void abrirTelaContato(){
        Intent telaContato = new Intent(MainActivity.this, ContatoActivity.class);

        startActivity(telaContato);
    }
}