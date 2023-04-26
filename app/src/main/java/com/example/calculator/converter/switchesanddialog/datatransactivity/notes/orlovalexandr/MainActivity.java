package com.example.calculator.converter.switchesanddialog.datatransactivity.notes.orlovalexandr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter <Note> adp;
    ListView list;
    int sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adp = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1);

        list = findViewById(R.id.list);
        list.setAdapter(adp);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sel = i;
            }
        });
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, @Nullable Intent data){
        if (data != null) {
            int pos = data.getIntExtra("id", -1);
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");

            Note note = adp.getItem(pos);
            note.title = title;
            note.content = content;

            adp.notifyDataSetChanged();
        }

        super.onActivityResult(reqCode, resCode, data);
    }


    public void New(View v)
    {
        Note note = new Note();
        note.title = "New note";
        note.content = "Some content";

        adp.add(note);
        int pos = adp.getPosition(note);

        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("id", pos);
        i.putExtra("title", note.title);
        i.putExtra("content", note.content);

        startActivityForResult(i, 0);
    }

    public void Edit(View v)
    {
        Note note = adp.getItem(sel);

        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("id", sel);
        i.putExtra("title", note.title);
        i.putExtra("content", note.content);

        startActivityForResult(i, 0);
    }

    public void Delete(View v)
    {
        Note note = adp.getItem(sel);

        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) { }
        });
        bld.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                adp.remove(note);
            }
        });
        AlertDialog dlg = bld.create();
        dlg.setTitle("Delete note");
        dlg.setMessage("Are you sure?");
        dlg.show();
    }
}