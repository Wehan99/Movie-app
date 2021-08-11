package uk.ac.westminster.mobilecw2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDataBase extends SQLiteOpenHelper {

    //creating a context and SQLiteDatabase objects
    public MyDataBase(Context context) {
        super(context, "MovieData.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creating table
        // String table ="create Table "+DB_TABLE+"(_id integer primary key autoincrement, movie_title text, movie_director text, movie_year text, movie_act text, movie_rating text, movie_review text,movie_favourite text)";
        //sqLiteDatabase.execSQL(table);
        sqLiteDatabase.execSQL( "create Table MovieDetails(movie_title text primary key, movie_director text, movie_year text, movie_act text, movie_rating text, movie_review text,movie_favourite text)");
        Log.i("Data base","Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //if another column will add refreshing the table
        sqLiteDatabase.execSQL("drop Table if exists MovieDetails");
        onCreate(sqLiteDatabase );

    }
    //inserting data to the table
    public boolean addData(String title, String director, String year, String act, String rating, String review,String favourites){

        //getting writable database
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        //creating contentValues
        ContentValues contentValues=new ContentValues();
        contentValues.put("movie_title",title);
        contentValues.put("movie_director",director);
        contentValues.put("movie_year",year);
        contentValues.put("movie_act",act);
        contentValues.put("movie_rating",rating);
        contentValues.put("movie_review",review);
        contentValues.put("movie_favourite",favourites);

        //add values to database
        long result=sqLiteDatabase.insert("MovieDetails",null,contentValues);

        if(result==-1){
           return false;
        }else
        return true;

    }
    //for update the favourites
    public Boolean updateFavourites(String title, String favourites){

        //getting writable database
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        //creating contentValues
        ContentValues contentValues=new ContentValues();

        contentValues.put("movie_favourite",favourites);
        //collecting the rawQuery
        Cursor cursor=sqLiteDatabase.rawQuery("Select * from MovieDetails Where movie_title = ?",new String[]{title});
        if(cursor.getCount()>0) {
            //add values to database
            long result = sqLiteDatabase.update("MovieDetails", contentValues, "movie_title = ?", new String[] { title });
            if (result == -1) {
                return false;
            } else
                return true;
        }else {
            return false;
        }
    }

    public Cursor getInfo(String title,String favourites) {

        //getting writable database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //collecting the rawQuery
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from MovieDetails" , null);
        return cursor;
    }


//        Toast.makeText(context2,sb.toString(),Toast.LENGTH_LONG).show();
    public Cursor getData(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from MovieDetails",null);
        return cursor;
    }


}




