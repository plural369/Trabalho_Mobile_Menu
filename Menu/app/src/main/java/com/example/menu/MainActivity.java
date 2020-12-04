package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void CarregarCalculadora(){
        setContentView(R.layout.calculadora);
        ImageView imgfoto;
        final EditText numero1,numero2;
        final RadioGroup rgbgroup;
        Button btCalcular;

        numero1 = (EditText) findViewById(R.id.numero1);
        numero2 = (EditText) findViewById(R.id.numero2);
        rgbgroup = (RadioGroup) findViewById(R.id.rgbgroup);
        btCalcular = (Button) findViewById(R.id.btCalcular);
        imgfoto = findViewById(R.id.imgfoto);
        imgfoto.setImageResource(R.drawable.foto);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double n1 = Double.parseDouble(numero1.getText().toString());
                double n2 = Double.parseDouble(numero2.getText().toString());
                double total;
                int op = rgbgroup.getCheckedRadioButtonId();
                AlertDialog.Builder dialogo =new AlertDialog.Builder(MainActivity.this);
                if (op == R.id.rgbop1)
                {
                    total = n1 + n2;
                    dialogo.setTitle("Soma:");
                    dialogo.setMessage("Total: " + total );
                    dialogo.setNeutralButton("Ok",null);
                    dialogo.show();
                }

                else if(op == R.id.rgbop2)
                {
                    total = n1 - n2;
                    dialogo.setTitle("Subtração:");
                    dialogo.setMessage("Total: " + total);
                    dialogo.setNeutralButton("Ok",null);
                    dialogo.show();
                }

                else if(op == R.id.rgbop3)
                {
                    total = n1 * n2;
                    dialogo.setTitle("Multiplicação:");
                    dialogo.setMessage("Total:" + total);
                    dialogo.setNeutralButton("Ok",null);
                    dialogo.show();
                }

                else{
                    total = n1 / n2;
                    dialogo.setTitle("Divisão:");
                    dialogo.setMessage("Total: " + total);
                    dialogo.setNeutralButton("Ok",null);
                    dialogo.show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mapa) {
            ChamarMapa();
        }

        if (id == R.id.calculadora){
            CarregarCalculadora();
        }

        if (id == R.id.loja){
            CarregarCompras();
        }

        if (id == R.id.lista){
            ListaContato();
        }

        if (id == R.id.sair){
            Toast.makeText(getApplicationContext(),"Sair da aplicação",Toast.LENGTH_LONG).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void CarregarCompras(){
        setContentView(R.layout.loja_main);
        final CheckBox chkMarmitex,chkSelfService,chkBebida,chkSobremesa;
        Button btPagar ;
        final RadioGroup rgopcoes;

        btPagar = (Button) findViewById(R.id.btPagar);
        chkMarmitex = (CheckBox) findViewById(R.id.chkMarmitex);
        chkSelfService = (CheckBox) findViewById(R.id.chkSelfService);
        chkBebida = (CheckBox) findViewById(R.id.chkBebida);
        chkSobremesa = (CheckBox) findViewById(R.id.chkSobremesa);
        rgopcoes = (RadioGroup) findViewById(R.id.rgopcoes);

        btPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double total = 0;
                if (chkMarmitex.isChecked()) {
                    total += 14;
                }
                if (chkSelfService.isChecked()) {
                    total += 25;
                }
                if (chkBebida.isChecked()) {
                    total += 5;
                }
                if (chkSobremesa.isChecked()) {
                    total += 10;
                }
                int op = rgopcoes.getCheckedRadioButtonId();
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                if (op == R.id.rbAvista)
                {
                    dialogo.setTitle("Pagamento A Vista:");
                    dialogo.setMessage("Total Final R$: " + total + " Pagamento Realizado com Sucesso");
                    dialogo.setPositiveButton("Ok", null);
                    dialogo.show();

                }

                else if(op == R.id.rbCartao)
                {
                    dialogo.setTitle("Pagamento Cartão de Crédito:");
                    dialogo.setMessage("Total Final R$: " + total + " Pagamento Realizado com Sucesso");
                    dialogo.setPositiveButton("Ok", null);
                    dialogo.show();
                }

                else{
                    dialogo.setTitle("Pagamento Cartão de Débito:");
                    dialogo.setMessage("Total Final R$: " + total + " Pagamento Realizado com Sucesso");
                    dialogo.setPositiveButton("Ok", null);
                    dialogo.show();
                }
            }
        });
    }

    public void ListaContato(){
        ListView lista_contatos;
        setContentView(R.layout.lista);

        lista_contatos = (ListView) findViewById(R.id.lista_contatos);
        lista_contatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nome = ((TextView) view).getText().toString();
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                dialogo.setTitle("Contato");
                dialogo.setMessage("Contato Selecionado " + nome);
                dialogo.setNeutralButton("Ok",null);
                dialogo.show();
            }
        });
    }

    public void ChamarMapa(){
        Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
        startActivity(intent);
    }


}