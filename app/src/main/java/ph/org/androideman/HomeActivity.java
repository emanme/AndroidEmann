package ph.org.androideman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void openStudentList(View view) {
        Intent intent = new Intent(HomeActivity.this, StudentListActivity.class);
        startActivity(intent);


    }

    public void openOnlineLogin(View view) {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    public void openOfflineLogin(View view) {
        Intent intent = new Intent(HomeActivity.this, LoginOfflineActivity.class);
        startActivity(intent);

    }

    public void openAddStudent(View view) {
        Intent intent = new Intent(HomeActivity.this, AddStudentActivity.class);
        startActivity(intent);

    }
}