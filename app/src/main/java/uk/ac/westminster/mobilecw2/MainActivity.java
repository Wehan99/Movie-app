package uk.ac.westminster.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //creating 6 methods for onclick actions
    //in onclick actions, creating 6 intents and calling them
    public void registerMovie(View view) {
        Intent intent = new Intent(this, RegisterMovie.class);
        startActivity(intent);

    }

    public void displayMovie(View view) {
        Intent intent = new Intent(this, DisplayMovies.class);
        startActivity(intent);
    }

    public void favourites(View view) {
        Intent intent = new Intent(this, Favourites.class);
        startActivity(intent);
    }

    public void editMovies(View view) {
        Intent intent = new Intent(this, EditMovies.class);
        startActivity(intent);
    }

    public void search(View view) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    public void ratings(View view) {
        Intent intent = new Intent(this, Ratings.class);
        startActivity(intent);
    }


}