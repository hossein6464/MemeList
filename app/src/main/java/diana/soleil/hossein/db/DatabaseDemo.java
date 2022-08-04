package diana.soleil.hossein.db;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Arrays;

import diana.soleil.hossein.model.Memes;
import diana.soleil.hossein.utilities.Constants;

public class DatabaseDemo {

    ArrayList<Memes> memesArrayList, memesPassed;

    // Database .....................
    DBManager dbManager;
    Cursor cursor;
    //...............................

    public DatabaseDemo(DBManager dbManager, ArrayList<Memes> memesPassed) {
        this.dbManager = dbManager;
        this.memesPassed =  memesPassed;
    }

    public void insertDummyDataToDB(ArrayList<Memes> memesOf) {
        ArrayList<ContentValues> contentValuesArrayList = dbManager.javaObjectToContentValue(memesOf);
        dbManager.insertInTable(Constants.TABLE_NAME, contentValuesArrayList);
    }
    public Cursor readFromDB() {
        cursor = dbManager.queryInTable(
                Constants.TABLE_NAME,
                Constants.TABLE_COLUMNS,
                null,
                null);
        return  cursor;
    }

    public void deleteFromDB(int id) {
        dbManager.deleteRowFromTable(Constants.TABLE_NAME,
                Constants.ID + "=?"
                , new String[]{String.valueOf(id)});

        readFromDB();
    }
}