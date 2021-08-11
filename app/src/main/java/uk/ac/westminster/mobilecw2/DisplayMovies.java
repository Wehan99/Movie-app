package uk.ac.westminster.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayMovies extends AppCompatActivity {
    private ArrayList<String> arrayList;
    private ArrayList<String> itemList;
    private ArrayAdapter adapter;
    private MyDataBase db;
    private Dialog dialog;
    private ListView listView;
    private TextView tx;
    private Button btn;
    String favN;
    private Cursor cursor;
    MyDataBase myDataBase;
    ArrayList<String> arrayFavList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);

        listView = (ListView) findViewById(R.id.titleList);
        tx = (TextView) findViewById(R.id.displayMovieTitle);
        btn = (Button) findViewById(R.id.testbtn);
        dialog = new Dialog(this);

        //Initialize MyDataBase
        myDataBase = new MyDataBase(DisplayMovies.this);

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

        ArrayAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, array);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {     //checking which are selected or not
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = ((TextView) view).getText().toString();
                if (arrayFavList.contains(selected)) {
                    arrayFavList.remove(selected);
                } else {
                    arrayFavList.add(selected);
                }
            }
        });
        favN = "Yes";
        //updating favourite column as yes
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < arrayFavList.size(); i++) {
                    System.out.println(arrayFavList.get(i));
                    myDataBase.updateFavourites(arrayFavList.get(i), favN);
                    successMethod();
                }
            }

        });
    }


    private void wrongMethod() {//showing a dialog box if error

        dialog.setContentView(R.layout.nodata);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.buttonOK);
        dialog.show();

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


    }
    private void successMethod() {                                      //showing a dialog box if correct
        dialog.setContentView(R.layout.win_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk=dialog.findViewById(R.id.buttonOK);
        dialog.show();

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });
        //nextMovie();
    }
}


