package com.warrantix.main.sqlitedb;

public class Table {
    public static final String ID = "_id", TRAINER_ID = "trainer_id",
            DELETED = "deleted", UPDATED = "updated", ACTIVE = "active",
            TITLE = "title", SYNC_ID = "sync_id", CREATED = "created";

    public static final String DROP_TABLE = "drop table if exists %s";

    public static final String UPDATE_TRIGGER = "CREATE TRIGGER %s_change AFTER UPDATE ON %s"
            + " BEGIN "
            + " UPDATE %s SET updated = CURRENT_TIMESTAMP WHERE _id = new._id; "
            + " END";

    public static final String DROP_TRIGGER = "DROP TRIGGER %s_change";


    public static class Message {
        public static final String TABLE_NAME = "message";

        public static final String ID = "_id",
                CREATE_AT = "create_at",
                UPDATE_AT = "update_at",
                CONTENT = "content",
                TO = "to",
                FROM = "from",
                ROLE = "role",
                ROLE_ID = "id";

        public static final String CREATE_TABLE = "create table "
                + TABLE_NAME
                + " ( "
                + ID + " integer primary key autoincrement, "
                + CREATE_AT + " integer, "
                + UPDATE_AT + " text not null, "
                + CONTENT + " text, "
                + TO + "  text not null, "
                + FROM+ "  text,)";

    }
}