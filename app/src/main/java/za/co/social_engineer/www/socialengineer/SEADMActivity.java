package za.co.social_engineer.www.socialengineer;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Activity to guide the user through the SEADMv2 to detect whether the user is the victim of a social
 * engineering attack or not.
 *
 * Created by Marcel Teixeira on 2016/06/19
 */

public class SEADMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seadm);

        SplashActivity.splash.finish(); // Finish Splash Activity, so that if user presses back button, it is not displayed
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seadm, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Todo...", Snackbar.LENGTH_LONG);

        switch (id) {
            case R.id.menu_item_help:
            case R.id.menu_item_about:
                snackbar.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
