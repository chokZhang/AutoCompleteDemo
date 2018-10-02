package com.example.boyzhang.autocompletedemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.otaliastudios.autocomplete.Autocomplete;
import com.otaliastudios.autocomplete.AutocompleteCallback;

import java.io.BufferedWriter;

import auto_complete.SimpleAutocompleteCallback;
import auto_complete.SimplePolicy;
import auto_complete.SimpleRecyclerViewPresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText textView = (EditText) findViewById(R.id.text);
        Button button = (Button) findViewById(R.id.addText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = textView.getText().toString();
                content = content + " add";
                textView.setText(content);
            }
        });

        AutocompleteCallback temp = new SimpleAutocompleteCallback();
        Drawable backgroundDrawable = new ColorDrawable(Color.WHITE);
        float elevation = 6f;
        Autocomplete.on(textView)
                .with(new SimplePolicy())
                .with(temp)
                .with(elevation)
                .with(backgroundDrawable)
                .with(new SimpleRecyclerViewPresenter(getApplicationContext()))
                .build();
    }
}
