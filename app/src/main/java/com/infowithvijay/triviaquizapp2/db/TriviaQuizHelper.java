package com.infowithvijay.triviaquizapp2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.infowithvijay.triviaquizapp2.constants.Constants;
import com.infowithvijay.triviaquizapp2.data.TriviaQuestion;
import com.infowithvijay.triviaquizapp2.db.TriviaQuizContract.*;

import java.util.ArrayList;

public class TriviaQuizHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "TriviaQuiz.db";
    private static final int DATBASE_VERSION = 13;

    private SQLiteDatabase db;


    public TriviaQuizHelper(Context context) {
        super(context, DATABASE_NAME,null, DATBASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " TEXT, " +
                QuestionTable.COLUMN_CATEGORY + " TEXT " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);

    }


    private void fillQuestionsTable()
    {

        TriviaQuestion q1 = new TriviaQuestion("DIGITALMARKETING : Android is what ?","OS","Drivers","Software","Hardware","OS", Constants.DIGITALMARKETING);
        addQuestions(q1);
        TriviaQuestion q2 = new TriviaQuestion("DIGITALMARKETING : Full form of PC is ?","PComputer","PIPO","TIPU","XXXIV","PComputer",Constants.DIGITALMARKETING);
        addQuestions(q2);
        TriviaQuestion q3= new TriviaQuestion("DIGITALMARKETING : The father of computer is  ?","CBabbage","Oliver","LoveL","lice","CBabbage",Constants.DIGITALMARKETING);
        addQuestions(q3);
        TriviaQuestion q4= new TriviaQuestion("DIGITALMARKETING : Which of the following is not a computer language?","BASIC","FORTRAN","LOUTS","COBOL","LOUTS",Constants.DIGITALMARKETING);
        addQuestions(q4);
        TriviaQuestion q6= new TriviaQuestion("DIGITALMARKETING The first page displayed by Web browser after opening a Web site is called ?","Hpage","Browser page","Search page  ","Bookmark","Hpage",Constants.DIGITALMARKETING);
        addQuestions(q6);

        TriviaQuestion q5= new TriviaQuestion("MOTIVATION : The third generation computers were made with ?","BChips ","Transistors","IC","VTubes ","IC",Constants.MOTIVATION);
        addQuestions(q5);
        TriviaQuestion q7= new TriviaQuestion("MOTIVATION : DuckDuck Go is what ?","SEngine","Browser","Search page  ","Bookmark","SEngine",Constants.MOTIVATION);
        addQuestions(q7);
        TriviaQuestion q8= new TriviaQuestion("MOTIVATION : What is Norton?","Anitivirus","Browser","Vaccine","Program","Anitivirus",Constants.MOTIVATION);
        addQuestions(q8);
        TriviaQuestion q9= new TriviaQuestion("MOTIVATION : Who is the inventor of www?","Bill Gates","TBLee","Timothy Bil","Ray Tomlinson","TBLee",Constants.MOTIVATION);
        addQuestions(q9);

        TriviaQuestion q85= new TriviaQuestion("MOTIVATION : The third generation computers were made with ?","BChips ","Transistors","IC","VTubes ","IC",Constants.MOTIVATION);
        addQuestions(q85);
        TriviaQuestion q87= new TriviaQuestion("MOTIVATION : DuckDuck Go is what ?","SEngine","Browser","Search page  ","Bookmark","SEngine",Constants.MOTIVATION);
        addQuestions(q87);
        TriviaQuestion q88= new TriviaQuestion("MOTIVATION : What is Norton?","Anitivirus","Browser","Vaccine","Program","Anitivirus",Constants.MOTIVATION);
        addQuestions(q88);
        TriviaQuestion q89= new TriviaQuestion("MOTIVATION : Who is the inventor of www?","Bill Gates","TBLee","Timothy Bil","Ray Tomlinson","TBLee",Constants.MOTIVATION);
        addQuestions(q89);

        TriviaQuestion q10= new TriviaQuestion("ANIMATION : Ethernet is an example of ","MAN","LAN","WAN","Wi-Fi","LAN",Constants.ANIMATION);
        addQuestions(q10);
        TriviaQuestion q11= new TriviaQuestion("ANIMATION : HTML is used to create","Operating System","High Level Program","Web-Server","WPage","WPage",Constants.ANIMATION);
        addQuestions(q11);
        TriviaQuestion q12= new TriviaQuestion("ANIMATION : Speed of internet connection is measured in","GHz","dpi","ppm","Gbps","Gbps",Constants.ANIMATION);
        addQuestions(q12);
        TriviaQuestion q13= new TriviaQuestion("ANIMATION : Speed of internet connection is measured in","GHz","dpi","ppm","Gbps","Gbps",Constants.ANIMATION);
        addQuestions(q13);
        TriviaQuestion q123= new TriviaQuestion("ANIMATION : Speed of internet connection is measured in","GHz","dpi","ppm","Gbps","Gbps",Constants.ANIMATION);
        addQuestions(q123);


        TriviaQuestion q17= new TriviaQuestion("COMPUTERS : DuckDuck Go is what ?","SEngine","Browser page","Search page  ","Bookmark","SEngine",Constants.PROGRAMING);
        addQuestions(q17);
        TriviaQuestion q18= new TriviaQuestion("COMPUTERS : What is Norton?","Anitivirus","Browser page","Vaccine","Program","Anitivirus",Constants.PROGRAMING);
        addQuestions(q18);
        TriviaQuestion q19= new TriviaQuestion("COMPUTERS : Who is the inventor of www?","Bill Gates","TBLee","Timothy Bil","Ray Tomlinson","TBLee",Constants.PROGRAMING);
        addQuestions(q19);
        TriviaQuestion q110= new TriviaQuestion("COMPUTERS : Ethernet is an example of ","MAN","LAN","WAN","Wi-Fi","LAN",Constants.PROGRAMING);
        addQuestions(q110);
        TriviaQuestion q20= new TriviaQuestion("COMPUTERS : Ethernet is an example of ","MAN","LAN","WAN","Wi-Fi","LAN",Constants.PROGRAMING);
        addQuestions(q20);
        TriviaQuestion q167= new TriviaQuestion("COMPUTERS : DuckDuck Go is what ?","SEngine","Browser page","Search page  ","Bookmark","SEngine",Constants.PROGRAMING);
        addQuestions(q167);
        TriviaQuestion q168= new TriviaQuestion("COMPUTERS : What is Norton?","Anitivirus","Browser page","Vaccine","Program","Anitivirus",Constants.PROGRAMING);
        addQuestions(q168);
        TriviaQuestion q169= new TriviaQuestion("COMPUTERS : Who is the inventor of www?","Bill Gates","TBLee","Timothy Bil","Ray Tomlinson","TBLee",Constants.PROGRAMING);
        addQuestions(q169);
        TriviaQuestion q1610= new TriviaQuestion("COMPUTERS : Ethernet is an example of ","MAN","LAN","WAN","Wi-Fi","LAN",Constants.PROGRAMING);
        addQuestions(q1610);
        TriviaQuestion q620= new TriviaQuestion("COMPUTERS : Ethernet is an example of ","MAN","LAN","WAN","Wi-Fi","LAN",Constants.PROGRAMING);
        addQuestions(q620);

        TriviaQuestion q111= new TriviaQuestion("TESTING : HTML is used to create","OS","HLProgram","WServer","WPage","WPage",Constants.TESTING);
        addQuestions(q111);
        TriviaQuestion q112= new TriviaQuestion("TESTING : Speed of internet connection is measured in","GHz","dpi","ppm","Gbps","Gbps",Constants.TESTING);
        addQuestions(q112);
        TriviaQuestion q113= new TriviaQuestion("TESTING : Speed of internet connection is measured in","GHz","dpi","ppm","Gbps","Gbps",Constants.TESTING);
        addQuestions(q113);
        TriviaQuestion q1212= new TriviaQuestion("TESTING : Speed of internet connection is measured in","GHz","dpi","ppm","Gbps","Gbps",Constants.TESTING);
        addQuestions(q1212);
        TriviaQuestion q1213= new TriviaQuestion("TESTING : Speed of internet connection is measured in","GHz","dpi","ppm","Gbps","Gbps",Constants.TESTING);
        addQuestions(q1213);

    }

    private void addQuestions(TriviaQuestion question){

        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_CATEGORY,question.getCategory());
        db.insert(QuestionTable.TABLE_NAME,null,cv);

    }

    public ArrayList<TriviaQuestion> getAllQuestions() {

        ArrayList<TriviaQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR
        };



            Cursor c = db.query(QuestionTable.TABLE_NAME,
                    Projection,
                    null,
                    null,
                    null,
                    null,
                    null);


            if (c.moveToFirst()) {
                do {

                    TriviaQuestion question = new TriviaQuestion();
                    question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                    question.setAnswerNr(c.getString(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

                    questionList.add(question);

                } while (c.moveToNext());

            }
            c.close();
            return questionList;
        }

    public ArrayList<TriviaQuestion> getQuestionsWithCategory(String Category) {

        ArrayList<TriviaQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR,
                QuestionTable.COLUMN_CATEGORY
        };

        String selection = QuestionTable.COLUMN_CATEGORY + " = ? ";
        String selectionArgs[] = {Category};


        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                selection,
                selectionArgs,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                TriviaQuestion question = new TriviaQuestion();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getString(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setCategory(c.getString(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY)));

                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;
    }

}


