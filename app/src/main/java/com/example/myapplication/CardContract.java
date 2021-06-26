package com.example.myapplication;

import android.provider.BaseColumns;

public final class CardContract {

    private CardContract() {}

    public static class CardTable implements BaseColumns {
        public static final String TABLE_NAME = "card_questions";
        public static final String COLLUM_WORD = "word";
        public static final String COLLUM_DEFINITION = "definition";
        public static final String COLLUM_ANSWER = "answer";
    }

}
