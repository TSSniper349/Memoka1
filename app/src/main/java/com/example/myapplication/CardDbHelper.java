package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myapplication.CardContract.*;
import java.util.ArrayList;
import java.util.List;
public class CardDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeCard.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db1;
    public CardDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db1) {
        this.db1 = db1;
        final String SQL_CREATE_CARD_TABLE = "CREATE TABLE " +
                CardTable.TABLE_NAME + " ( " +
                CardTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CardTable.COLLUM_WORD + " TEXT, " +
                CardTable.COLLUM_DEFINITION + " TEXT, " +
                CardTable.COLLUM_ANSWER + " INTEGER" +
                ")";
        db1.execSQL(SQL_CREATE_CARD_TABLE);
        fillCardTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {
        db1.execSQL("DROP TABLE IF EXISTS " + CardTable.TABLE_NAME);
        onCreate(db1);
    }
    private void fillCardTable() {
        CardInfo c1 = new CardInfo("Acquaintance","Người quen",1);
        addCard(c1);
        CardInfo c2 = new CardInfo("Admire","Ngưỡng mộ",2);
        addCard(c2);
        CardInfo c3 = new CardInfo("Aim","Mục đích",3);
        addCard(c3);
        CardInfo c4 = new CardInfo("Appearance","Vẻ bề ngoài",4);
        addCard(c4);
        CardInfo c5 = new CardInfo("Attraction","Sự thu hút",5);
        addCard(c5);
        CardInfo c6 = new CardInfo("Based on","Dựa vào",6);
        addCard(c6);
        CardInfo c7 = new CardInfo("Benefit","Lợi ích",7);
        addCard(c7);
        CardInfo c8 = new CardInfo("Calm","Điềm tĩnh",8);
        addCard(c8);
        CardInfo c9 = new CardInfo("Caring","Chu đáo",9);
        addCard(c9);
        CardInfo c10 = new CardInfo("Condition","Điều kiện",10);
        addCard(c10);
    }
    private void addCard(CardInfo cardInfo) {
        ContentValues cv = new ContentValues();
        cv.put(CardTable.COLLUM_WORD, cardInfo.getWord());
        cv.put(CardTable.COLLUM_DEFINITION, cardInfo.getDefinition());
        cv.put(CardTable.COLLUM_ANSWER, cardInfo.getAnswer());
        db1.insert(CardTable.TABLE_NAME, null, cv);
    }
    public List<CardInfo> getAllCard() {
        List<CardInfo> cardInfoList = new ArrayList<>();
        db1 = getReadableDatabase();
        Cursor c = db1.rawQuery("SELECT * FROM " + CardTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                CardInfo cardInfo = new CardInfo();
                cardInfo.setWord(c.getString(c.getColumnIndex(CardTable.COLLUM_WORD)));
                cardInfo.setDefinition(c.getString(c.getColumnIndex(CardTable.COLLUM_DEFINITION)));
                cardInfo.setAnswer(c.getInt(c.getColumnIndex(CardTable.COLLUM_ANSWER)));
                cardInfoList.add(cardInfo);
            } while (c.moveToNext());
        }
        c.close();
        return cardInfoList;
    }
}