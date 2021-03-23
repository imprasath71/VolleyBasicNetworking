package com.example.networkingwithvolleylibrary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
private TextView textView;
private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData() {
        Gson gson = new Gson();
        String url = "https://jsonplaceholder.typicode.com/posts";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // textView.setText(response);
              //  Post [] post = gson.fromJson(response,Post[].class);
                //for (Post p:post){
                  //  textView.setText(textView.getText() + "/n"+ p.getTitle());
                Post post = gson.fromJson(response,Post.class);
                textView.setText(post.getTitle());
                }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String ,String > post = new HashMap<>();
                post.put("id","6");
                post.put("userId","102");
                post.put("title", "New Post");
                post.put("body","Body of new post");
                return post;

            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
        queue.start();
    }
}