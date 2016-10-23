package za.co.social_engineer.www.socialengineer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import za.co.social_engineer.www.socialengineer.R;
import za.co.social_engineer.www.socialengineer.util.MiscUtil;

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
            InputStream inputStream = getResources().getAssets().open("about.txt");

            about = MiscUtil.readTextFromInputStream(inputStream);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        Spanned result;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(about, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(about);
        }

        aboutTextView.setText(result);
    }
}
