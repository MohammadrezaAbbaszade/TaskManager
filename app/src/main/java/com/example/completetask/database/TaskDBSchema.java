package com.example.completetask.database;

public class TaskDBSchema {
    public static final String NAME = "task.db";

    public static final class Task {
        public static final String NAME = "Task";

        public static final class Cols {
            public static final String _ID = "_id";
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String ADDRES = "addres";
            public static final String PHONENUMBER = "phonenumber";
            public static final String DISCRIPTION = "discription";
        }
    }

}
