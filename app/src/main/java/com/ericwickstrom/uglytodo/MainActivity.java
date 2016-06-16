package com.ericwickstrom.uglytodo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> todos;
    EditText todoEditText;
    Button addButton;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todos = new ArrayList<>();
        todoEditText = (EditText) findViewById(R.id.add_todo_edittext);

        listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter todosAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, todos);
        listView.setAdapter(todosAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todos.remove(position);
                todosAdapter.notifyDataSetChanged();
                return false;
            }
        });

        addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                todos.add(todoEditText.getText().toString());
                todoEditText.setText("");

            }
        });

    }
}
