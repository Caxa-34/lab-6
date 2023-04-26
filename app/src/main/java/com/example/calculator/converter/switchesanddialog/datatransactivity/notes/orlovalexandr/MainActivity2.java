package com.example.calculator.converter.switchesanddialog.datatransactivity.notes.orlovalexandr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText etTitle, etContent;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);

        Intent i = getIntent();
        pos = i.getIntExtra("id", 0);
        etTitle.setText(i.getStringExtra("title"));
        etContent.setText(i.getStringExtra("content"));
    }

    public void Ok(View v)
    {
        Intent i = new Intent();
        i.putExtra("id", pos);
        i.putExtra("title", etTitle.getText().toString());
        i.putExtra("content", etContent.getText().toString());

        setResult(RESULT_OK, i);
        finish();
    }

    public void Cancel(View v)
    {
        finish();
    }
}