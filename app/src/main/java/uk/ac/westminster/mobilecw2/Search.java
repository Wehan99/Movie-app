package uk.ac.westminster.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    private ListView favList;
    private ArrayList<String> arrayFavList=  new ArrayList<>();
    MyDataBase myDataBase;
    ListView listView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        myDataBase = new MyDataBase(Search.this);
    //identifyng items
        searchView =(SearchView) findViewById(R.id.searchBox);
        favList = (ListView) findViewById(R.id.listViewSearch);
        myDataBase = new MyDataBase(Search.this);
        Cursor data = myDataBase.getData();
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        }
        while (data.moveToNext()) {         //getting specific data to search
            String name =  data.getString(0);
            String actors=  data.getString(3);
            String director =  data.getString(1);
            String review =  data.getString(5);
            String add = name + " - " + actors + " - "+ director+  " - " + review;
            System.out.println(add);
            arrayFavList.add(add);
        }

        ArrayAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayFavList);
        favList.setAdapter(listAdapter);                                    //searching here

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listAdapter.getFilter().filter(newText);
                return false;
            }
        });


    }
}