package uk.ac.westminster.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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

public class Favourites extends AppCompatActivity {
    private Button saveButton;
    private ListView favList;
    private ArrayList<String> arrayFavList=  new ArrayList<>();
    MyDataBase myDataBase;
    private String favN;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        saveButton=(Button)findViewById(R.id.saveFav);
        favList=(ListView)findViewById(R.id.listFav);
        dialog = new Dialog(this);
        //Initialize MyDataBase
        myDataBase = new MyDataBase(Favourites.this);

        //add database values to array
        ArrayList<String> array = new ArrayList<>();

        //getting return cursor
        Cursor data=myDataBase.getData();
        if (data.getCount() == 0) {
            Toast.makeText(Favourites.this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
            return;
        }
        while (data.moveToNext()) {                                     //adding array to favourite movies
            if(data.getString(6).equals("Yes")) {
                array.add(data.getString(0));
            }
        }
        if(array.size()==0){                 //if no entries show the toast message
            Toast.makeText(Favourites.this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        }


        ArrayAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, array);

        favList.setAdapter(listAdapter);
        for(int i=0;i<array.size();i++){                            //setting checkedboxes are check
            favList.setItemChecked(i,true);
        }

        favList.setOnItemClickListener(new AdapterView.OnItemClickListener() {     //checking which are selected or not
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
        favN = "No";
        //updating favourite column as yes
        saveButton.setOnClickListener(new View.OnClickListener() {
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