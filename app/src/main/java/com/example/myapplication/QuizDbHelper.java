package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myapplication.QuizContract.*;
import java.util.ArrayList;
import java.util.List;
public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 5;
    private SQLiteDatabase db;
    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLLUM_QUESTION + " TEXT, " +
                QuestionsTable.COLLUM_OPTION1 + " TEXT, " +
                QuestionsTable.COLLUM_OPTION2 + " TEXT, " +
                QuestionsTable.COLLUM_OPTION3 + " TEXT, " +
                QuestionsTable.COLLUM_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    private void fillQuestionsTable() {
        Question q1 = new Question("Acquaintance", "Sự thu hút", "Mục đích", "Người quen", 3);
        addQuestion(q1);
        Question q2 = new Question("Admire", "Vẻ bề ngoài", "Ngưỡng mộ", "Dựa vào", 2);
        addQuestion(q2);
        Question q3 = new Question("Aim", "Sự thu hút", "Lợi ích", "Mục đích", 3);
        addQuestion(q3);
        Question q4 = new Question("Appearance", "Vẻ bề ngoài", "Dựa vào", "Điềm tĩnh", 1);
        addQuestion(q4);
        Question q5 = new Question("Attraction", "Lợi ích", "Sự thu hút", "Chu đáo", 2);
        addQuestion(q5);
        Question q6 = new Question("Based on", "Dựa vào", "Điềm tĩnh", "(Sự) Thay đổi", 1);
        addQuestion(q6);
        Question q7 = new Question("Benefit", "Lợi ích", "Chu đáo", "Gần gũi, thân thiết", 1);
        addQuestion(q7);
        Question q8 = new Question("Calm", "(Sự) thay đổi", "Điềm tĩnh", "Quan tâm", 2);
        addQuestion(q8);
        Question q9 = new Question("Caring", "Gần gũi, thân thiết", "Điều kiện", "Chu đáo", 3);
        addQuestion(q9);
        Question q10 = new Question("Change", "(Sự) thay đổi", "Quan tâm", "Sự kiên định", 1);
        addQuestion(q10);
        Question q11 = new Question("Close", "Điều kiện", "Gần gũi, thân thiết", "Cong", 2);
        addQuestion(q11);
        Question q12 = new Question("Concerned", "Sự kiên định", "Vui mừng", "Quan tâm", 3);
        addQuestion(q12);
        Question q13 = new Question("Condition", "Điều kiện", "Cong", "Lòng nhiệt tình", 1);
        addQuestion(q13);
        Question q14 = new Question("Constancy", "Vui mừng", "Sự kiên định", "Đặc điểm", 2);
        addQuestion(q14);
        Question q15 = new Question(" Crooked", "Lòng nhiệt tình", "Trán", "Cong", 3);
        addQuestion(q15);
        Question q16 = new Question("Delighted", "Vui mừng", "Đặc điểm", "Rộng rãi, rộng lượng", 1);
        addQuestion(q16);
        Question q17 = new Question("Enthusiasm", "Trán", "Lòng nhiệt tình", "Ra khỏi ", 2);
        addQuestion(q17);
        Question q18 = new Question("Feature", "Đặc điểm", "Rộng rãi, rộng lượng", "Sự nhường nhịn", 1);
        addQuestion(q18);
        Question q19 = new Question("Forehead", "Ra khỏi", "Trán", "Dễ nhìn", 2);
        addQuestion(q19);
        Question q20 = new Question("Generous", "Sự nhường nhịn", "Tốt bụng", "Rộng rãi, rộng lượng", 3);
        addQuestion(q20);
        Question q21 = new Question("Get out of", "Ra khỏi", "Dễ nhìn", "Mách lẻo", 1);
        addQuestion(q21);
        Question q22 = new Question("Give-and-take", "Tốt bụng", "Sự nhường nhịn", "Chiều cao", 2);
        addQuestion(q22);
        Question q23 = new Question("Good-looking", "Mách lẻo", "Giúp ích", "Dễ nhìn", 3);
        addQuestion(q23);
        Question q24 = new Question("Good-natured", "Tốt bụng", "Chiều cao", "Trung thực", 1);
        addQuestion(q24);
        Question q25 = new Question("Gossip", "Giúp ích", "Mách lẻo", "Hiếu khách", 2);
        addQuestion(q25);
        Question q26 = new Question("Height", "Trung thực", "Hài hước", "Chiều cao", 3);
        addQuestion(q26);
        Question q27 = new Question("Helpful", "Giúp ích", "Hiếu khách", "Người quen", 1);
        addQuestion(q27);
        Question q28 = new Question("Honest", "Hài hước", "Trung thực", "Ngưỡng mộ", 2);
        addQuestion(q28);
        Question q29 = new Question("Hospitable", "Người quen", "Mục đích", "Hiếu khách", 3);
        addQuestion(q29);
        Question q30 = new Question("Humorous", "Ngưỡng mộ", "Vẻ bề ngoài", "Hài hước", 3);
        addQuestion(q30);
    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLLUM_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLLUM_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLLUM_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLLUM_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLLUM_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLLUM_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLLUM_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLLUM_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLLUM_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLLUM_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}