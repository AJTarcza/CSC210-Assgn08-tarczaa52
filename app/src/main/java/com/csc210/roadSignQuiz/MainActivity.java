// MainActivity.java
// Hosts the MainActivityFragment on a phone and both the
// MainActivityFragment and SettingsActivityFragment on a tablet
package com.csc210.roadSignQuiz;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
   // keys for reading data from SharedPreferences
   public static final String CHOICES = "pref_numberOfChoices";

   private boolean phoneDevice = true; // used to force portrait mode

   // configure the MainActivity
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      // determine screen size
      int screenSize = getResources().getConfiguration().screenLayout &
         Configuration.SCREENLAYOUT_SIZE_MASK;

      // if device is a tablet, set phoneDevice to false
      if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE ||
         screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE)
         phoneDevice = false; // not a phone-sized device

      // if running on phone-sized device, allow only portrait orientation
      if (phoneDevice)
         setRequestedOrientation(
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
   }

   // called after onCreate completes execution
   @Override
   protected void onStart() {
      super.onStart();

         // now that the default preferences have been set,
         // initialize MainActivityFragment and start the quiz
         MainActivityFragment quizFragment = (MainActivityFragment)
                 getSupportFragmentManager().findFragmentById(R.id.quizFragment);
         quizFragment.updateGuessRows();
         quizFragment.resetQuiz();
      }
   }
