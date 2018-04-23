package com.example.juanpedrog.laboratoriofibonacci;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView numero,resultado;
    Button calcular;
    String cad="0";
    int numAnt=0;
    int numAct=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numero=findViewById(R.id.numero);
        resultado=findViewById(R.id.resultado);
        calcular=findViewById(R.id.calcular);
        resultado.setText(cad);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numAct=1;
                numAnt=0;
                cad="0";
                AsyncTarea asyncTarea = new AsyncTarea();
                asyncTarea.execute();
            }
        });
    }
    private class  AsyncTarea extends AsyncTask<Void, Integer,Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            int aux=Integer.parseInt(numero.getText().toString());
            for (int i=1; i<aux; i++){
                if(i==1){
                  cad+="\n1";
                }else {
                    int index = numAct;
                    numAct = numAnt + numAct;
                    numAnt = index;
                    publishProgress(numAct);
                }

                if (isCancelled()){
                    break;
                }
            }
            return true;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            cad+="\n"+values[0].intValue();
            resultado.setText(cad);
            //Actualizar la barra de progreso
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            //super.onPostExecute(aVoid);

            if (aVoid){
                Toast.makeText(getApplicationContext(),"Tarea finaliza AsyncTask",Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();

            Toast.makeText(getApplicationContext(),"Tarea NO finaliza AsyncTask",Toast.LENGTH_SHORT).show();

        }


    }
}
