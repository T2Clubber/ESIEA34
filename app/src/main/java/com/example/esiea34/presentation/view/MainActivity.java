package com.example.esiea34.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.esiea34.R;

public class MainActivity extends AppCompatActivity {

    private ImageView play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.play = (ImageView) findViewById((R.id.play));

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), fish_list.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }


}
