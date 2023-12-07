package ph.org.androideman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ph.org.androideman.adapter.StudentAdapter;
import ph.org.androideman.helper.DatabaseHelper;
import ph.org.androideman.model.Student;

public class StudentListActivity extends AppCompatActivity implements StudentAdapter.OnItemClickListener  {

    private StudentAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlist);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        ////// TO CREATE SAMPLE STUDENT DATA
        insertSampleStudents();

        adapter = new StudentAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void insertSampleStudents() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, "John Doe");
        values.put(DatabaseHelper.COLUMN_COURSE, "Computer Science");
        values.put(DatabaseHelper.COLUMN_ADDRESS, "Mingllanilla");
        db.insert(DatabaseHelper.TABLE_STUDENTS, null, values);

        values.clear();

        values.put(DatabaseHelper.COLUMN_NAME, "Jane Smith");
        values.put(DatabaseHelper.COLUMN_COURSE, "Electrical Engineering");
        values.put(DatabaseHelper.COLUMN_ADDRESS, "CEBU");
        db.insert(DatabaseHelper.TABLE_STUDENTS, null, values);

        // Add more students as needed

        db.close();
    }

    @Override
    public void onItemClick(Student student) {
        // Handle the click event here
        String message = "Selected Student: " +
                "ID: " + student.getId() +
                ", Name: " + student.getName() +
                ", Course: " + student.getCourse();

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.d("CLICK", message);



        Intent intent = new Intent(this, StudentDetailActivity.class);
        intent.putExtra(StudentDetailActivity.EXTRA_STUDENT, (Parcelable) student);
        startActivity(intent);

    }

}