package ph.org.androideman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import ph.org.androideman.adapter.StudentAdapter;
import ph.org.androideman.model.Student;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<Student> students = new ArrayList<>();
        students.add(new Student("1", "John Doe", "Computer Science"));
        students.add(new Student("2", "Jane Smith", "Electrical Engineering"));
        // Add more students as needed

        StudentAdapter adapter = new StudentAdapter(students);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}