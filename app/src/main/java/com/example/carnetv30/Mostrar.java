package com.example.carnetv30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Mostrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
    }
    Intent intent = getIntent();
    int idEstudiante = intent.getIntExtra("ID_ESTUDIANTE", 0);
}