package quick.brain.exercise.trainer;

import static quick.brain.exercise.trainer.utils.Constants.KEY_PREF_LOGIC;
import static quick.brain.exercise.trainer.utils.Constants.KEY_PREF_MATH;
import static quick.brain.exercise.trainer.utils.Constants.KEY_PREF_MEMORY;
import static quick.brain.exercise.trainer.utils.Constants.KEY_PREF_STARS_COUNT_ALL;
import static quick.brain.exercise.trainer.utils.Constants.KEY_PRIVACY_AGREE;
import static quick.brain.exercise.trainer.utils.Constants.NAME_PREF;
import static quick.brain.exercise.trainer.utils.Constants.URL_FOR_REQUEST;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import quick.brain.exercise.trainer.game_activities.LogicalChallengesActivity;
import quick.brain.exercise.trainer.game_activities.MathsTestsActivity;
import quick.brain.exercise.trainer.game_activities.MemoryTrainingActivity;
import quick.brain.exercise.trainer.policy_activities.OfflinePolicyActivity;
import quick.brain.exercise.trainer.utils.CircularProgressBar;

public class MainActivity extends AppCompatActivity {
    private CircularProgressBar maths_progress_bar, logical_progress_bar, memory_progress_bar;
    private TextView txt_v_count_main;
    private LinearLayout ll_memory_training, ll_logical_challenge, ll_maths_test;
    private SharedPreferences prefs;
    private int memoryProgress = 0, mathProgress = 0, logicProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAllComponents();
        ll_memory_training.setOnClickListener(v -> {
            if (memoryProgress >= 3) {
                Toast.makeText(MainActivity.this, R.string.end_question, Toast.LENGTH_LONG).show();
                return;
            }
            Intent goMemoryTraining = new Intent(MainActivity.this, MemoryTrainingActivity.class);
            startActivity(goMemoryTraining);
        });
        ll_logical_challenge.setOnClickListener(v -> {
            if (logicProgress == 10) {
                Toast.makeText(MainActivity.this, R.string.end_question, Toast.LENGTH_LONG).show();
                return;
            }
            Intent goLogicalChallenges = new Intent(MainActivity.this, LogicalChallengesActivity.class);
            startActivity(goLogicalChallenges);
        });
        ll_maths_test.setOnClickListener(v -> {
            if (mathProgress == 5) {
                Toast.makeText(MainActivity.this, R.string.end_question, Toast.LENGTH_LONG).show();
                return;
            }
            Intent goMathsTests = new Intent(MainActivity.this, MathsTestsActivity.class);
            startActivity(goMathsTests);
        });
    }

    private void initAllComponents() {
        prefs = getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE);
        ll_memory_training = findViewById(R.id.ll_memory_training);
        ll_logical_challenge = findViewById(R.id.ll_logical_challenge);
        ll_maths_test = findViewById(R.id.ll_maths_test);
        txt_v_count_main = findViewById(R.id.txt_v_count_main);
        maths_progress_bar = findViewById(R.id.maths_progress_bar);
        logical_progress_bar = findViewById(R.id.logical_progress_bar);
        memory_progress_bar = findViewById(R.id.memory_progress_bar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPolicy();
        mathProgress = prefs.getInt(KEY_PREF_MATH, 0);
        logicProgress = prefs.getInt(KEY_PREF_LOGIC, 0);
        memoryProgress = prefs.getInt(KEY_PREF_MEMORY, 0);
        int starProgress = prefs.getInt(KEY_PREF_STARS_COUNT_ALL, 0);

        txt_v_count_main.setText(String.valueOf(starProgress));

        memory_progress_bar.setProgressText(3);
        memory_progress_bar.setProgress(memoryProgress);

        logical_progress_bar.setProgressText(10);
        logical_progress_bar.setProgress(logicProgress);

        maths_progress_bar.setProgressText(5);
        maths_progress_bar.setProgress(mathProgress);
    }

    private void checkPolicy() {
        boolean isAgree = prefs.getBoolean(KEY_PRIVACY_AGREE, false);
        ll_memory_training.setClickable(false);
        ll_maths_test.setClickable(false);
        ll_logical_challenge.setClickable(false);
        if (!isAgree) {
            Intent goPrivacy = new Intent(MainActivity.this, OfflinePolicyActivity.class);
            startActivity(goPrivacy);
            return;
        }
        ll_memory_training.setClickable(true);
        ll_maths_test.setClickable(true);
        ll_logical_challenge.setClickable(true);
    }
}