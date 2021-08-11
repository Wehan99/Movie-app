package uk.ac.westminster.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditMovies extends AppCompatActivity {
    private ListView favList;
    MyDataBase myDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movies);
        favList=(ListView)findViewById(R.id.listViewEdit);

        //Initialize MyDataBase
        myDataBase = new MyDataBase(EditMovies.this);

        //add database values to array
        ArrayList<String> array = new ArrayList<>();

        //getting return cursor
        Cursor data = myDataBase.getData();
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        }
        while (data.moveToNext()) {
            array.add(data.getString(0));
        }

        ArrayAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        favList.setAdapter(listAdapter);
    }
}