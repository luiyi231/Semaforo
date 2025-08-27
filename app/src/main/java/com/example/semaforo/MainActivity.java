package com.example.semaforo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView semaforo;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        semaforo = findViewById(R.id.semaforo);

        Thread hiloSemaforo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (contador == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    semaforo.setImageResource(R.drawable.circle_green);
                                }
                            });
                            contador = 1;
                            Thread.sleep(5000);
                        } else if (contador == 1) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    semaforo.setImageResource(R.drawable.circle_yellow);
                                }
                            });
                            contador = 2;
                            Thread.sleep(5000);
                        } else if (contador == 2) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    semaforo.setImageResource(R.drawable.circle_red);
                                }
                            });
                            contador = 0;
                            Thread.sleep(5000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        hiloSemaforo.start();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}
