package ph.org.androideman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ph.org.androideman.helper.DatabaseHelper;
import ph.org.androideman.model.Student;

public class AddStudentActivity extends AppCompatActivity {

 private   EditText nameEditText,courseEditText, addressEditText ;
    private Button saveButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        dbHelper = new DatabaseHelper(this);
        nameEditText =  findViewById(R.id.studentName);
        courseEditText= findViewById(R.id.studentCourse);
        addressEditText=findViewById(R.id.studentAddress);
        saveButton=findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                addStudent();

            }
        });
    }

    private void addStudent(){
        String name = nameEditText.getText().toString().trim();
        String course = courseEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();

        // Validate input
        if (name.isEmpty() || course.isEmpty() || address.isEmpty()) {
            // Show an error message or handle invalid input
            return;
        }

        // Create a new Student object
        Student newStudent = new Student( name, course, address);

        long result = dbHelper.insertStudent(newStudent);

        if (result != -1) {
            // Successfully inserted
            // Optionally, you can return to the previous activity or finish the current activity
            finish();
        } else {
            // Insert failed, handle error
            // You can show an error message or log the error
        }
    }

}