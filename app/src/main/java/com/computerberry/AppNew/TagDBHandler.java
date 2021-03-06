package com.computerberry.AppNew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TagDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tags.db";
    public static final String TABLE_TAGS = "tags";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COLOR = "_col";
    public static final String COLUMN_TEXT = "_text";
    public static final String COLUMN_TAG = "_tag";

    public TagDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

//    public void addDefaultTags(){
//        fetchDatabaseEntries();
//        SQLiteDatabase db = getWritableDatabase();
//            for (int i = 0; i < FirstTimeInfoActivity.defaultTags.length; i++){
//                boolean found = false;
//                for (int j = 0; j < HomeActivity.tags.size(); j++){
//                    if (HomeActivity.tags.get(j).getText().equals(FirstTimeInfoActivity.defaultTags[i].getText())){
//                        found = true;
//                        break;
//                    }
//                }
//                if (!found){
//                    addEntry(FirstTimeInfoActivity.defaultTags[i]);
//                }
//            }
//        db.close();
//    }

    //tao bang
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_TAGS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COLOR + " TEXT, " +
                COLUMN_TEXT + " TEXT" +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAGS);
        onCreate(db);
    }

    //them the
    public void addEntry(Tag tag){

        ContentValues values = new ContentValues();
        values.put(COLUMN_COLOR, tag.getCol());
        values.put(COLUMN_TEXT, tag.getText());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TAGS, null, values);
        db.close();
    }

    //xoa the
    public void deleteEntry(int entryId){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_TAGS + " WHERE " + COLUMN_ID + " = \"" + entryId + "\";");
        db.close();
    }

    //cap nhat the
    public void updateEntry(Tag t){
        SQLiteDatabase db = getWritableDatabase();
        //UPDATE entries SET _id = 123 WHERE _id = 0;
        db.execSQL("UPDATE " + TABLE_TAGS + " SET " +
                COLUMN_COLOR + " = \"" + t.getCol() + "\", " +
                COLUMN_TEXT + " = \"" + t.getText() + "\" " +
                " WHERE " + COLUMN_ID + " = " + t.getId() +";");
        db.close();
    }

    public void dequeue(){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_TAGS + " WHERE " + COLUMN_ID + " = (SELECT min(" + COLUMN_ID +  ") FROM " + TABLE_TAGS + ");");
        db.close();

    }

    public String databaseToString(){

        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TAGS + ";";

        Cursor c = db.rawQuery(query, null);
        HomeActivity.tags.clear();
        c.moveToFirst();
        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_COLOR)) != null){
                Tag t = new Tag(c.getInt(c.getColumnIndex(COLUMN_ID)),
                        c.getString(c.getColumnIndex(COLUMN_COLOR)),
                        c.getString(c.getColumnIndex(COLUMN_TEXT)));
                dbString += t.getId() + ". ";
                dbString += t.getCol() + " ";
                dbString += t.getText() + "\n";
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbString;
    }

    public void fetchDatabaseEntries(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TAGS + ";";


        Cursor c = db.rawQuery(query, null);
        HomeActivity.tags.clear();
        c.moveToFirst();
        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_COLOR)) != null){
                Tag t = new Tag(c.getInt(c.getColumnIndex(COLUMN_ID)),
                        c.getString(c.getColumnIndex(COLUMN_COLOR)),
                        c.getString(c.getColumnIndex(COLUMN_TEXT)));

                HomeActivity.tags.add(t);
            }
            c.moveToNext();
        }
        c.close();
        db.close();
    }

}
