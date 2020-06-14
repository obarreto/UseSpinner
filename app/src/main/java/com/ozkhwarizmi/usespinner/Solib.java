package com.ozkhwarizmi.usespinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ozkhwarizmi.usespinner.entities.OperacoesInSitu;

import java.text.DecimalFormat;

public class Solib extends AppCompatActivity {
    private TextView texto;
    private Button botao;
    private Spinner menu;
    EditText textPesoCilindroSoloUmido;
    private TextView textDensidadeUmida;
    private TextView textDensidadeSeca;

    String[] opcoes = {"", "SCC.064", "opcaoTeste"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Solib.this.texto = (TextView) findViewById(R.id.textPosicao);
        Solib.this.botao = (Button) findViewById(R.id.button);
        Solib.this.menu =  findViewById(R.id.spinner);

        Solib.this.textPesoCilindroSoloUmido  = (EditText) findViewById(R.id.textpesoCilindroSoloUmido);
        Solib.this.textDensidadeUmida = (TextView) findViewById(R.id.textDensidadeUmida);
        Solib.this.textDensidadeSeca = (TextView) findViewById(R.id.textDensidadeSeca);

       final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, opcoes);
        menu.setAdapter(adapter);
      /* menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    texto.setText(opcoes[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


        botao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                texto.setText(opcoes[menu.getSelectedItemPosition()]);


                if(menu.getSelectedItemPosition() == 1) {
                    //String[] determinacao =  {"SCC.064"};
                    double volumeCilindro = 998;
                    double pesoCilindro = 997;
                    double pesoCilindroSoloUmido = Double.parseDouble(textPesoCilindroSoloUmido.getText().toString());

                    double soloUmido;
                    double densidadeUmida;
                    double densidadeSeca;

                    soloUmido = pesoCilindroSoloUmido - pesoCilindro;

                    densidadeUmida = soloUmido/ volumeCilindro;
                    densidadeSeca = densidadeUmida * 0.00;//fator de conversão;

                    OperacoesInSitu operacoesInSitu = new OperacoesInSitu( volumeCilindro, pesoCilindro, pesoCilindroSoloUmido, soloUmido, densidadeUmida, densidadeSeca);
                    DecimalFormat df = new DecimalFormat("0.00");
                    textDensidadeSeca.setText(df.format(operacoesInSitu.getDensidadeSeca()));
                    textDensidadeUmida.setText(df.format(operacoesInSitu.getDensidadeUmida()));
                    Toast.makeText(Solib.this, "Determinação Nº " + opcoes[menu.getSelectedItemPosition()], Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}