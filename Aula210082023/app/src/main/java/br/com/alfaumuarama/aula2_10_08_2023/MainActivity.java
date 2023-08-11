package br.com.alfaumuarama.aula2_10_08_2023;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botaoCalcular;
    EditText edtEtanol, edtGasolina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEtanol = findViewById(R.id.edtEtanol);
        edtGasolina = findViewById(R.id.edtGasolina);
        botaoCalcular = findViewById(R.id.btnCalcular);

        botaoCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });

    }

    private void calcular() {
        double valorEtanol = Double.parseDouble(edtEtanol.getText().toString());
        double valorGasolina = Double.parseDouble(edtGasolina.getText().toString());

        double resultado = valorEtanol / valorGasolina;

        if(resultado <= 0.7){
            mostarMensagem("Melhor abastecer com etanol");
        }else{
            mostarMensagem("Melhor abastecer com gasolina");
        }
    }

    private void mostarMensagem(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setTitle("Resultado");
        alerta.setMessage(texto);
        alerta.setNeutralButton("OK", null);
        alerta.show();

    }

}