package quick.brain.exercise.trainer.game_activities;

import static quick.brain.exercise.trainer.utils.Constants.KEY_PREF_MEMORY;
import static quick.brain.exercise.trainer.utils.Constants.KEY_PREF_STARS_COUNT_ALL;
import static quick.brain.exercise.trainer.utils.Constants.NAME_PREF;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import quick.brain.exercise.trainer.R;
import quick.brain.exercise.trainer.utils.ImageAdapter;

public class MemoryTrainingActivity extends AppCompatActivity {
    private ImageButton img_btn_back_memory;
    private TextView txt_v_count_memory, txt_v_save_memory, txt_v_explain_memory, txt_v_animation_memory;
    private ImageAdapter imageAdapter;
    private SharedPreferences.Editor editor;
    private GridView gridView;
    private int levelCounterMemory = 0, starCounterMemory = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_training);
        initAllComponents();
        img_btn_back_memory.setOnClickListener(v -> {
            onBackPressed();
        });
        txt_v_save_memory.setOnClickListener(v -> {
            if (checkPositions()) {
                starCounterMemory++;
                levelCounterMemory++;
                addCountProgress(levelCounterMemory, starCounterMemory);
                if (levelCounterMemory == 3) {
                    TextView txt_v_the_end_memory = findViewById(R.id.txt_v_the_end_memory);
                    txt_v_the_end_memory.setVisibility(View.VISIBLE);
                    setAllComponentsVisible(false);
                    img_btn_back_memory.setClickable(true);
                    gridView.setVisibility(View.GONE);
                    return;
                }
                showNextLevel(levelCounterMemory);
            }
        });
    }

    @SuppressLint("WrongViewCast")
    private void initAllComponents() {
        SharedPreferences prefs = getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE);
        editor = prefs.edit();
        txt_v_animation_memory = findViewById(R.id.txt_v_animation_memory);
        txt_v_explain_memory = findViewById(R.id.txt_v_explain_memory);
        txt_v_save_memory = findViewById(R.id.txt_v_save_memory);
        img_btn_back_memory = findViewById(R.id.img_btn_back_memory);
        txt_v_count_memory = findViewById(R.id.txt_v_count_memory);
        txt_v_count_memory.setText(String.valueOf(prefs.getInt(KEY_PREF_STARS_COUNT_ALL, 0)));
        starCounterMemory = Integer.parseInt(txt_v_count_memory.getText().toString().trim());
        levelCounterMemory = prefs.getInt(KEY_PREF_MEMORY, 0);
        gridView = findViewById(R.id.gridView);
        imageAdapter = createAdapterBasedLevel(levelCounterMemory);
        imageAdapter.shuffleImages();
        gridView.setAdapter(imageAdapter);
        setAllComponentsVisible(false);
        startCountdownAnimation();
    }

    private void setAllComponentsVisible(boolean visOrNo) {
        if (visOrNo) {
            gridView.setVisibility(View.VISIBLE);
            gridView.setClickable(true);
            img_btn_back_memory.setClickable(true);
            txt_v_save_memory.setClickable(true);
            txt_v_explain_memory.setClickable(true);
            txt_v_explain_memory.setVisibility(View.VISIBLE);
        } else {
            gridView.setVisibility(View.GONE);
            gridView.setClickable(false);
            img_btn_back_memory.setClickable(false);
            txt_v_save_memory.setClickable(false);
            txt_v_explain_memory.setClickable(false);
            txt_v_explain_memory.setVisibility(View.GONE);
        }
    }

    private void showNextLevel(int levelProgress) {
        setAllComponentsVisible(false);
        imageAdapter = createAdapterBasedLevel(levelProgress);
        gridView.setAdapter(imageAdapter);
        startCountdownAnimation();
    }

    private ImageAdapter createAdapterBasedLevel(int currentLevel) {
        switch (currentLevel) {
            case 0: {
                return new ImageAdapter(this, Arrays.asList(
                        R.drawable.memory_img_0, R.drawable.memory_img_1, R.drawable.memory_img_2
                ));
            }
            case 1: {
                return new ImageAdapter(this, Arrays.asList(
                        R.drawable.memory_img_0, R.drawable.memory_img_1, R.drawable.memory_img_2,
                        R.drawable.memory_img_3, R.drawable.memory_img_4, R.drawable.memory_img_5
                ));
            }
            case 2: {
                return new ImageAdapter(this, Arrays.asList(
                        R.drawable.memory_img_0, R.drawable.memory_img_1, R.drawable.memory_img_2,
                        R.drawable.memory_img_3, R.drawable.memory_img_4, R.drawable.memory_img_5,
                        R.drawable.memory_img_6, R.drawable.memory_img_7, R.drawable.memory_img_8
                ));
            }
            default: {
                TextView txt_v_the_end_memory = findViewById(R.id.txt_v_the_end_memory);
                txt_v_the_end_memory.setVisibility(View.VISIBLE);
                setAllComponentsVisible(false);
                img_btn_back_memory.setClickable(true);
                gridView.setVisibility(View.GONE);
                return new ImageAdapter(this, Arrays.asList(
                        R.drawable.memory_img_0, R.drawable.memory_img_1, R.drawable.memory_img_2
                ));
            }
        }
    }

    private boolean checkPositions() {
        if (imageAdapter.checkCorrectOrder()) {
            Toast.makeText(this, "Correct!!!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Wrong!!!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void addCountProgress(int level, int starCount) {
        txt_v_count_memory.setText(String.valueOf(starCount));
        editor.putInt(KEY_PREF_STARS_COUNT_ALL, starCount);
        editor.putInt(KEY_PREF_MEMORY, level);
        editor.commit();
    }

    private void startCountdownAnimation() {
        final int endSize = 10;
        final long duration = 1000;
        final long delayBetweenAnimations = 500;

        for (int i = 10; i >= 0; i--) {
            final int currentDigit = i;

            ValueAnimator animator = ValueAnimator.ofFloat(150, endSize);
            animator.setDuration(duration);

            animator.addUpdateListener(animation -> {
                float animatedValue = (float) animation.getAnimatedValue();
                txt_v_animation_memory.setTextSize(animatedValue);
                txt_v_animation_memory.setText(String.valueOf(currentDigit));
            });

            animator.setStartDelay((10 - i) * delayBetweenAnimations);

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    txt_v_animation_memory.setVisibility(View.VISIBLE);
                    gridView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (currentDigit == 0) {
                        setAllComponentsVisible(true);
                        txt_v_animation_memory.setVisibility(View.GONE);
                        gridView.setVisibility(View.GONE);
                        imageAdapter.saveInitialOrder();
                        imageAdapter.shuffleImages();
                        gridView.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });

            animator.start();
        }
    }
}