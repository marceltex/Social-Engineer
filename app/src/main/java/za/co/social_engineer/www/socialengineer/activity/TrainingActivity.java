package za.co.social_engineer.www.socialengineer.activity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import za.co.social_engineer.www.socialengineer.R;

public class TrainingActivity extends AppCompatActivity {

    private static final String TAG = "TrainingActivity";

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0;

    private String selectedTrainingDocument;

    private ListView trainingDocumentsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        trainingDocumentsListView = (ListView) findViewById(R.id.list_view_training_documents);

        String [] trainingDocumentList = null;
        try {
            trainingDocumentList = getAssets().list("training");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        // Remove .pdf extension from file names
        for (int i = 0; i < trainingDocumentList.length; i++) {
            int posOfPeriod = trainingDocumentList[i].lastIndexOf(".");
            trainingDocumentList[i] = trainingDocumentList[i].substring(0, posOfPeriod);
        }

        final ArrayAdapter<String> trainingDocumentAdapter = new ArrayAdapter<String>(this,
                R.layout.list_item_white_text, R.id.list_item, trainingDocumentList);

        trainingDocumentsListView.setAdapter(trainingDocumentAdapter);

        trainingDocumentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTrainingDocument = trainingDocumentAdapter.getItem(position);

                // Check if app has permission to write to external storage, if not, request permission
                if (ContextCompat.checkSelfPermission(TrainingActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(TrainingActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                } else {
                    displayPDF();
                }
            }
        });
    }

    /* Launches external PDF reader to view the selected training document */
    public void displayPDF() {
        if (isExternalStorageWritable()) {
            File trainingDir = getDocumentStorageDir("SEPTT/Training");

            File file = new File(trainingDir.getAbsolutePath() + "/" + selectedTrainingDocument
                    + ".pdf");

            if (!file.exists()) {
                try {
                    InputStream inputStream = getResources().getAssets().open("training/" +
                            selectedTrainingDocument + ".pdf");

                    int size = inputStream.available();
                    byte[] buffer = new byte[size];
                    inputStream.read(buffer);
                    inputStream.close();

                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(buffer);
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }

            //intent.setDataAndType(FileProvider.getUriForFile(TrainingActivity.this,
            //BuildConfig.APPLICATION_ID + ".provider", file.get), "application/pdf");

            //Uri uri = Uri.parse("content://za.co.social_engineer.www.socialengineer/" + file.getAbsolutePath());

            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri uri = FileProvider.getUriForFile(TrainingActivity.this,
                            "za.co.social_engineer.www.socialengineer.fileProvider", file);
                    intent.setDataAndType(uri, "application/pdf");
                } else {
                    intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                }
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Snackbar noPDFViewerSnackbar = Snackbar.make(getCurrentFocus(), "No PDF Viewer installed on device. Please install a PDF Viewer and try again.", Snackbar.LENGTH_LONG);
                noPDFViewerSnackbar.show();
            }
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public File getDocumentStorageDir(String septtStorageSubDir) {
        // Get the directory for the app's public directory.
        File file = new File(Environment.getExternalStorageDirectory(),
                septtStorageSubDir);
        if (!file.mkdirs()) {
            Log.i(TAG, "Directory not created");
        }
        return file;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch(requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                // If the request is cancelled, the grantResults array is empty
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    displayPDF();
                } else {
                    // Permission denied by user.
                    Snackbar permissionDeniedSnackbar = Snackbar.make(getCurrentFocus(), "Please allow SEPTT access to your files, in order to view the training documents.", Snackbar.LENGTH_LONG);
                    permissionDeniedSnackbar.show();
                }
                return;
        }
    }
}
