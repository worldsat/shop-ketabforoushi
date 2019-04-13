package ir.shop1.shop1.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ir.shop1.shop1.R;

public class getway extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getway);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse("http://androidium.org"));
                startActivity(intent);
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("http://www.example.com"));
//                startActivity(i);
            }
        });

        Intent intent = getIntent();
        Uri url = intent.getData();
        if (url != null) {
            Log.i("moh3n", "onCreate: " + url.toString());
        }
    }
}
