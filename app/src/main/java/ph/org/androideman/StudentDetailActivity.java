package ph.org.androideman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import ph.org.androideman.adapter.StudentAdapter;
import ph.org.androideman.model.Student;

public class StudentDetailActivity extends AppCompatActivity {
    public static final String EXTRA_STUDENT  = "extra_student";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);


        TextView detailsTextView = findViewById(R.id.detailsTextView);
        TextView nameTextView = findViewById(R.id.textViewName);
        TextView courseTextView = findViewById(R.id.textViewCourse);
        ImageView avatar =  findViewById(R.id.imageViewAvatarDetail);

        // Retrieve student ID from Intent
        Student student = getIntent().getParcelableExtra(EXTRA_STUDENT);

        // Use the student ID to fetch details from the database or any other source
        // For simplicity, we'll display the student ID in the details TextView

        if (student != null) {
            String details = "Student Details:\n" +
                    "ID: " + student.getId() +
                    "\nName: " + student.getName() +
                    "\nCourse: " + student.getCourse();
            nameTextView.setText(student.getName() );
            courseTextView.setText(student.getCourse());

            StudentAdapter.getAvatarResource(student.getId(), avatar);
            detailsTextView.setText(details);
        }

    }
}