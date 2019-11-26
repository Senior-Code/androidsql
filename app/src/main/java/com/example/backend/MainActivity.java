package com.example.backend;

import androidx.appcompat.app.AppCompatActivity;;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    TextView txtview;
    Button btn1;
    String text_url = "http://192.168.88.88:8080/android.php";
    String image_url = "http://192.168.88.88:8080/img/Barcelona.png";
    Button btn2;
    ImageView imgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtview = findViewById(R.id.txt1);
        btn1= findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final RequestQueue requestqueue = Volley.newRequestQueue(MainActivity.this);

                StringRequest strrequest = new StringRequest(Request.Method.POST, text_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                txtview.setText(response);
                                requestqueue.stop();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        txtview.setText("Error...");
                        error.printStackTrace();
                        requestqueue.stop();

                    }
                });
                requestqueue.add(strrequest);
            }
        });
        imgview = findViewById(R.id.imgview);
        btn2= findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageRequest imagerequest = new ImageRequest(image_url,
                        new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imgview.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                RequestQueue requestqueue =Volley.newRequestQueue(MainActivity.this);
                requestqueue.add(imagerequest);
            }
        });
    }
}
