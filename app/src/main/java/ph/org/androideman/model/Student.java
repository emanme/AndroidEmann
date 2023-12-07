package ph.org.androideman.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import ph.org.androideman.helper.DatabaseHelper;

public class Student implements Parcelable {
    private String id;
    private String name;
    private String course;
    private String address;

    public Student(String id, String name, String course, String address) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.address = address;
    }
    public Student( String name, String course, String address) {
        this.id = "";
        this.name = name;
        this.course = course;
        this.address = address;
    }
    protected Student(Parcel in) {
        id = in.readString();
        name = in.readString();
        course = in.readString();
        address = in.readString();
    }
    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(course);
        dest.writeString(address);
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
    public String getAddress() {
        return address;
    }


    public Student(Cursor cursor) {
        this.id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
        this.name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
        this.course = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COURSE));
        this.address = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ADDRESS));
    }

}
