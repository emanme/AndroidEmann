package ph.org.androideman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginOfflineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_offline);
    }
    public void checkLogin(View arg0) {

        Intent intent = new Intent(LoginOfflineActivity.this, HomeActivity.class);
        startActivity(intent);
        LoginOfflineActivity.this.finish();

    }
}