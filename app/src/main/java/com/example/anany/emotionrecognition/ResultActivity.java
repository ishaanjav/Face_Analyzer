/*
    Code written by ishaanjav
    github.com/ishaanjav
    App on Google Play Store: https://play.google.com/store/apps/details?id=app.anany.faceanalyzer
*/

package com.example.anany.emotionrecognition;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.microsoft.projectoxford.face.contract.Face;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String data = getIntent().getStringExtra("list_faces");

        Gson gson = new Gson();
        Face[] faces = gson.fromJson(data, Face[].class);

        ListView myListView = findViewById(R.id.listView);

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap orig = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        if (faces == null) {
            if (data == null) {
                Toast.makeText(getApplicationContext(), "Face array is null", Toast.LENGTH_LONG).show();
            } else {

            }
        } else {
            try {
                CustomAdapter customAdapter = new CustomAdapter(faces, this, orig);
                myListView.setAdapter(customAdapter);
            } catch (Exception e) {
                makeToast(e.getMessage());
            }
        }
    }

    private void makeToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        /*MenuItem item = menu.findItem(R.id.autoplot);
        item.setVisible(false);*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        makeToast("Face Analyzer app on the Google Play Store");
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=app.anany.faceanalyzer"));
        startActivity(browserIntent);
        return super.onOptionsItemSelected(item);
    }

}
