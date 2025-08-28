package com.example.semaforo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView rojo1, amarillo1, verde1;//Semaforo 1
    private ImageView rojo2, amarillo2, verde2;//Semaforo 2
    private Button button; //boton porque me olvide la fase 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Unir el xml con el java
        rojo1 = findViewById(R.id.rojo1);
        amarillo1 = findViewById(R.id.amarillo1);
        verde1 = findViewById(R.id.verde1);

        rojo2 = findViewById(R.id.rojo2);
        amarillo2 = findViewById(R.id.amarillo2);
        verde2 = findViewById(R.id.verde2);

        button = findViewById(R.id.button);

        Thread hiloSemaforo1 = new Thread(() -> {
            int contador1 = 0;
            while (true) {
                try {
                    switch (contador1) {
                        case 0: // Rojo
                            runOnUiThread(() -> {
                                rojo1.setImageResource(R.drawable.circle_red);
                                amarillo1.setImageResource(R.drawable.circle_grey);
                                verde1.setImageResource(R.drawable.circle_grey);
                            });
                            Thread.sleep(5000);
                            contador1 = 1;
                            break;

                        case 1: // Amarillo
                            runOnUiThread(() -> {
                                rojo1.setImageResource(R.drawable.circle_grey);
                                amarillo1.setImageResource(R.drawable.circle_yellow);
                                verde1.setImageResource(R.drawable.circle_grey);
                            });
                            Thread.sleep(5000);
                            contador1 = 2;
                            break;

                        case 2://Verde
                            runOnUiThread(() -> {
                                rojo1.setImageResource(R.drawable.circle_grey);
                                amarillo1.setImageResource(R.drawable.circle_grey);
                                verde1.setImageResource(R.drawable.circle_green);
                            });
                            Thread.sleep(5000);
                            contador1 = 3;
                            break;
                        case 3://Amarillo otra ves
                            runOnUiThread(() -> {
                                rojo1.setImageResource(R.drawable.circle_grey);
                                amarillo1.setImageResource(R.drawable.circle_yellow);
                                verde1.setImageResource(R.drawable.circle_grey);
                            });
                            Thread.sleep(5000);
                            contador1 = 0;
                            break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        hiloSemaforo1.start();

        Thread hiloSemaforo2 = new Thread(() -> {
            int contador2 = 2;
            while (true) {
                try {
                    switch (contador2) {
                        case 0:
                            runOnUiThread(() -> {
                                rojo2.setImageResource(R.drawable.circle_red);
                                amarillo2.setImageResource(R.drawable.circle_grey);
                                verde2.setImageResource(R.drawable.circle_grey);
                            });
                            Thread.sleep(5000);
                            contador2 = 1;
                            break;

                        case 1:
                            runOnUiThread(() -> {
                                rojo2.setImageResource(R.drawable.circle_grey);
                                amarillo2.setImageResource(R.drawable.circle_yellow);
                                verde2.setImageResource(R.drawable.circle_grey);
                            });
                            Thread.sleep(5000);
                            contador2 = 2;
                            break;

                        case 2:
                            runOnUiThread(() -> {
                                rojo2.setImageResource(R.drawable.circle_grey);
                                amarillo2.setImageResource(R.drawable.circle_grey);
                                verde2.setImageResource(R.drawable.circle_green);
                            });
                            Thread.sleep(5000);
                            contador2 = 3;
                            break;
                        case 3:
                            runOnUiThread(() -> {
                                rojo2.setImageResource(R.drawable.circle_grey);
                                amarillo2.setImageResource(R.drawable.circle_yellow);
                                verde2.setImageResource(R.drawable.circle_grey);
                            });
                            Thread.sleep(5000);
                            contador2 = 0;
                            break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        hiloSemaforo2.start();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Fase_1.class);
            startActivity(intent);
        });
    }
}
