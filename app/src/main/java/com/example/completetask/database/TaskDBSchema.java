package com.example.completetask.database;

public class TaskDBSchema {
    public static final String NAME = "task.db";

    public static final class Doing {
        public static final String NAME = "Doing";

        public static final class Cols {
            public static final String _ID = "_id";
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String TIME = "time";
            public static final String DISCRIPTION = "discription";
            public static final String USERNAME = "username";
            public static final String DONECHECKBOX = "donecheckbox";
            public static final String DOINGCHECKBOX = "doingcheckbox";
            public static final String TODOCHECKBOX = "todocheckbox";

        }
    }
    public static final class Done {
        public static final String NAME = "Done";

        public static final class Cols {
            public static final String _ID = "_id";
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String TIME = "time";
            public static final String DISCRIPTION = "discription";
            public static final String USERNAME = "username";
            public static final String DONECHECKBOX = "donecheckbox";
            public static final String DOINGCHECKBOX = "doingcheckbox";
            public static final String TODOCHECKBOX = "todocheckbox";

        }
    }
    public static final class ToDo {
        public static final String NAME = "ToDo";

        public static final class Cols {
            public static final String _ID = "_id";
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String TIME = "time";
            public static final String DISCRIPTION = "discription";
            public static final String USERNAME = "username";
            public static final String DONECHECKBOX = "donecheckbox";
            public static final String DOINGCHECKBOX = "doingcheckbox";
            public static final String TODOCHECKBOX = "todocheckbox";

        }
    }
    public static final class User {
        public static final String NAME = "User";

        public static final class Cols {
            public static final String _ID = "_id";
            public static final String UUID = "uuid";
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";

        }
    }

}
