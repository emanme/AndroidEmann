package ph.org.androideman.model;

import android.database.Cursor;

import ph.org.androideman.helper.DatabaseHelper;

public class Student {
    private String id;
    private String name;
    private String course;

    public Student(String id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }


    public Student(Cursor cursor) {
        this.id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
        this.name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
        this.course = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COURSE));
    }

}
