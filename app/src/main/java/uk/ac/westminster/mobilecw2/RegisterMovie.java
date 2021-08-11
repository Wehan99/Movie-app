package uk.ac.westminster.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterMovie extends MainActivity {

    private EditText title;
    private EditText director;
    private EditText year;
    private EditText actor;
    private EditText review;
    private EditText rating;
    private Button button;
    private int intRate;
    private int intYear;
    private Button saveBtn;
    MyDataBase data;
    MainActivity mainActivity;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);

        //identify the variable to xml elements

        title = (EditText) findViewById(R.id.movieTitle);
        director = (EditText) findViewById(R.id.director);
        year= (EditText) findViewById(R.id.year);
        actor = (EditText) findViewById(R.id.actor);
        review = (EditText) findViewById(R.id.review);
        rating = (EditText) findViewById(R.id.rate);
        data = new MyDataBase(this);
        saveBtn=(Button)findViewById(R.id.buttonSv);
        dialog=new Dialog(this);

        // converting string value to integer and store them

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

            }
        });
    }


    public void saveData() {

        //getting editText data to Strings
        String yearCheck=year.getText().toString();
        String rateCheck=rating.getText().toString();

        //Strings data to integers
        try {
             intYear = Integer.parseInt(yearCheck);
             intRate = Integer.parseInt(rateCheck);
        }catch (NumberFormatException e){}

        if(title.getText().toString().equals("")||director.getText().toString().equals("")||year.getText().toString().equals("")
                ||actor.getText().toString().equals("")||review.getText().toString().equals("")||rating.getText().toString().equals("")){
            wrongMethod();
        }else if(intYear<1895 || intYear>2021){
            wrongMethod();
        }else if(intRate<=0 || intRate>10){
            wrongMethod();
        }else {
            Boolean check=data.addData(title.getText().toString(), director.getText().toString(), year.getText().toString(), actor.getText().toString(), rating.getText().toString(), review.getText().toString(),"No");
            if(check==true){
                Toast.makeText(RegisterMovie.this,"New Entry Added",Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(RegisterMovie.this,"New Entry Not Added",Toast.LENGTH_SHORT).show();
            //data.addData(title.getText().toString(), director.getText().toString(), year.getText().toString(), actor.getText().toString(), rating.getText().toString(), review.getText().toString(),"No");
            successMethod();

        }
    }

    public void loadData(View view) {

         //data.getData();
    }


    private void successMethod() {                                      //winning layout
        dialog.setContentView(R.layout.win_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk=dialog.findViewById(R.id.buttonOK);
        dialog.show();

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                saveBtn.setText("Register another movie");
                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent t =new Intent(RegisterMovie.this,RegisterMovie.class);
                        startActivity(t);
                        finish();
                    }
                });

            }
        });
        //nextMovie();
    }

//    private void nextMovie(){
//        button.setText("Next");                                                 //second click action for next model identify
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent t =new Intent(RegisterMovie.this,RegisterMovie.class);
//                startActivity(t);
//                finish();
//
//            }
//        });
//
//    }
    private void wrongMethod() {
        //losing layout
        dialog.setContentView(R.layout.lose_layout);
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