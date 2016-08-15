package za.co.social_engineer.www.socialengineer.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

        final ArrayAdapter<String> trainingDocumentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                trainingDocumentList);

        trainingDocumentsListView.setAdapter(trainingDocumentAdapter);

        trainingDocumentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTrainingDocument = trainingDocumentAdapter.getItem(position);

                if (isExternalStorageWritable()) {
                    File trainingDir = getDocumentStorageDir(view.getContext(), "training");

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

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }
            }
        });
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public File getDocumentStorageDir(Context context, String documentStorageSubDir) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), documentStorageSubDir);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }
}
