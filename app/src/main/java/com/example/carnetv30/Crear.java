package com.example.carnetv30;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Crear extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView foto;
    Button capturar, guardar;
    TextView nombre,apellido,cargo;
    Bitmap imagenBitmap;
    Estudiante estudiante;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        foto = findViewById(R.id.imgFoto);
        capturar=findViewById(R.id.btnCapturar);
        guardar=findViewById(R.id.btnSave);
        nombre=findViewById(R.id.txtName);
        apellido=findViewById(R.id.txtLastName);
        cargo=findViewById(R.id.txtCharge);
        // Agrega un OnClickListener a tu botón de captura
        capturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad "Crear"
                capturarFoto();
            }
        });

        findViewById(R.id.btnCapturar).setOnClickListener(view -> capturarFoto());

    }

    private void capturarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null && extras.containsKey("data")) {
                imagenBitmap = (Bitmap) extras.get("data");

                foto.setImageBitmap(imagenBitmap);

                // Convertir la imagen a Base64
                String imagenBase64 = convertirImagenABase64(imagenBitmap);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Obtén los valores de los EditText
                        String nombreEstudiante = nombre.getText().toString();
                        String apellidoEstudiante = apellido.getText().toString();
                        String cargoEstudiante = cargo.getText().toString();

                        // Verifica si los campos están vacíos
                        if (!nombreEstudiante.isEmpty() && !apellidoEstudiante.isEmpty() && !cargoEstudiante.isEmpty()) {
                            // Aquí puedes llamar a la función escribir para almacenar en la base de datos
                            // Además, puedes pasar la imagen en Base64 como uno de los parámetros
                            escribir(nombreEstudiante, apellidoEstudiante, cargoEstudiante, imagenBase64);


                        } else {
                            Toast.makeText(Crear.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

    }
    private String convertirImagenABase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
    public void escribir(final String nombre, final String apellido, final String cargo, final String imagenBase64) {
        //Log.d("Datos Enviados", "Nombre: " + nombre + ", Apellido: " + apellido + ", Cargo: " + cargo + ", Foto: " + imagenBase64);
        String url = "http://10.40.19.168:80/Conexiones/almacenarBD.php"; // Agrega "http://" al principio de la URL

        StringRequest respuesta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Crear.this, "Se almacenó correctamente", Toast.LENGTH_LONG).show(); // Corrige el método "show()"
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Crear.this, "Error al almacenar", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("nombre_estudiante", nombre);
                params.put("apellido_estudiante", apellido);
                params.put("cargo_estudiante", cargo);
                params.put("foto_estudiante", imagenBase64);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(respuesta);
}}

/*


    // Tu código existente...

    public void cargar() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Asegúrate de que hay una aplicación de cámara disponible para manejar la acción
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "No se encontró una aplicación de cámara", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // La foto ha sido capturada, puedes acceder a la imagen en el intent data
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            // Puedes hacer algo con la imagen capturada, por ejemplo, mostrarla en un ImageView
            // imageView.setImageBitmap(imageBitmap);
        }
    }

 */