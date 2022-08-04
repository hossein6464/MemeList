package diana.soleil.hossein.db;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import diana.soleil.hossein.model.Memes;
import diana.soleil.hossein.utilities.Constants;

//  Attention: DBManager extends Application
public class DBManager extends Application {

// Manually must be added to AndroidManifest.xml
// <application
//        android:name=".db.DBManager"
// </application>

// DBOpenHelper extends SQLiteOpenHelper
public DBOpenHelper dbOpenHelper;
public SQLiteDatabase sqLiteDatabase;

// ................................
@Override
public void onCreate() {
    super.onCreate();

    // Create Database
    dbOpenHelper = new DBOpenHelper(this);
    // Create a reference to database for CRUD
    sqLiteDatabase = dbOpenHelper.getWritableDatabase();

    //sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON");
}

@Override
public void onTerminate() {
    super.onTerminate();
}
// ................................

public void insertInTable(String tableName,
                          ArrayList<ContentValues> values) {
    for (ContentValues value : values) {
        insertInTable(tableName, value);
    }
}

public long insertInTable(String tableName,
                          ContentValues values) {

    return sqLiteDatabase.insert(tableName,
            null, values);
}

public Cursor queryInTable(String tableName,
                           String[] columns,
                           String selection,
                           String[] selectionArgs) {

    return sqLiteDatabase.query(tableName,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null);
}

public int deleteRowFromTable(String tableName,
                              String selection,
                              String[] selectionArgs) {

    return sqLiteDatabase.delete(tableName,
            selection,
            selectionArgs);
}

public int updateTable(String tableName,
                       ContentValues values,
                       String whereClause,
                       String[] whereArgs) {

    return sqLiteDatabase.update(tableName,
            values,
            whereClause,
            whereArgs);
}

public ArrayList<ContentValues> javaObjectToContentValue(ArrayList<Memes> memes) {
    ArrayList<ContentValues> contentValuesArrayList = new ArrayList<>();

    for (Memes meme : memes) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.ID, meme.getId());
        contentValues.put(Constants.NAME, meme.getName());
        contentValues.put(Constants.URL, meme.getUrl());
        contentValues.put(Constants.WIDTH, meme.getWidth());
        contentValues.put(Constants.HEIGHT, meme.getHeight());
        contentValues.put(Constants.BOX_COUNT, meme.getBox_count());
        System.out.println(contentValues.containsKey("Constants.ID"));
        System.out.println(contentValues.toString());
        contentValuesArrayList.add(contentValues);
    }
    return contentValuesArrayList;
}

public ArrayList<Memes> cursorToArrayList(Cursor cursor) {
    ArrayList<Memes> memes = new ArrayList<>();
    cursor.moveToFirst();

    if (cursor.getCount() != 0) {
        do {
            Memes meme = new Memes();

            meme.setId(cursor.getInt(0));
            meme.setName(cursor.getString(1));
            meme.setUrl(cursor.getString(2));
            meme.setWidth(cursor.getInt(3));
            meme.setHeight(cursor.getInt(4));
            meme.setBox_count(cursor.getInt(5));

            memes.add(meme);

        } while (cursor.moveToNext());
    }
    return memes;
}
}