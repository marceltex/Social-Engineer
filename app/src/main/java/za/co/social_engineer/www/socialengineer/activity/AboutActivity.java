package za.co.social_engineer.www.socialengineer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import za.co.social_engineer.www.socialengineer.R;

public class AboutActivity extends AppCompatActivity {

    private static final String TAG = "AboutActivity";

    private TextView aboutTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutTextView = (TextView) findViewById(R.id.text_view_about);

        String about = "";

        try {
            Scanner read = new Scanner(new File("about.txt"));

            while (read.hasNext()) {
                about += read.next();
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }

        aboutTextView.setText(about);
    }
}
