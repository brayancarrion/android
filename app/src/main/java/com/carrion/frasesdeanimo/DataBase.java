package com.carrion.frasesdeanimo;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DataBase extends SQLiteOpenHelper
{

    //Attributes
    protected SQLiteDatabase sqLiteDatabase;

    //Creation and version data base
    public DataBase(Context context)
    {
        super(context, "frasesanimo", null, 1);
    }

    //Creation table data base
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("CREATE TABLE frasesanimo (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, frase TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS frases");
    }

    /* < ----------------- METHODS -------------------- > */

    //Know number of phrases inside the "frasesanimo" data base
    public int numberOfPhrases()
    {
        /* Counter */
        int num = 0;
        /* Open in mode lecture */
        sqLiteDatabase = this.getReadableDatabase();

        /* Number of rows */
        num = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, "frasesanimo");
        sqLiteDatabase.close();
        return num;
    }

    //Insert new rows inside data base "frasesanimo"
    public void insertPhrases(String frase)
    {
        /* Open in mode lecture */
        sqLiteDatabase = this.getReadableDatabase();
        /* Execute query */
        sqLiteDatabase.execSQL("INSERT INTO frasesanimo (frase) VALUES ('"+frase+"')");
    }

    //Obtaion all information of data base
    @SuppressLint("Range")
    public ArrayList<String> getAllPhrases()
    {
        /* ArrayList where the content will be saved */
        ArrayList<String> phrases = new ArrayList<String>();

        /* Variable that will contained all information */
        String content = "";

        /* Create class Cursor */
        Cursor cursor = null;

        /* Condition for obtain all information */
        if (numberOfPhrases() > 0)
        {
            /* Open in mode lecture */
            sqLiteDatabase = this.getReadableDatabase();
            /* We will define the cursor */
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM frasesanimo", null);
            /* We will define the position of element at first */
            cursor.moveToFirst();

            /* Create the loop while */
            while (!cursor.isAfterLast())
            {
                /* Obtain the content */
                content = cursor.getString(cursor.getColumnIndex("frase"));
                /* We will add the content inside "phrases" */
                phrases.add(content);
                /* We will move between the phrases */
                cursor.moveToNext();
            }
        }
        /* Return phrases (ArrayList) */
        return phrases;
    }

    //Delete all phrases
    public void deleteAllPhrases()
    {
        sqLiteDatabase = this.getWritableDatabase(); //Como vamos a modificar se abre en modo lectura
        sqLiteDatabase.execSQL("DELETE FROM frasesanimo WHERE frase LIKE '%a%' ");
    }

    //Close data base
    public void close()
    {
        sqLiteDatabase.close();
    }


}


