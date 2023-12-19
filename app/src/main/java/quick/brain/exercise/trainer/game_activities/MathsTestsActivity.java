package quick.brain.exercise.trainer.game_activities;

import static android.content.ContentValues.TAG;
import static quick.brain.exercise.trainer.utils.Constants.FIFTH_RIGHT_ANSWER_MATH;
import static quick.brain.exercise.trainer.utils.Constants.FIRST_RIGHT_ANSWER_LOGICAL;
import static quick.brain.exercise.trainer.utils.Constants.FIRST_RIGHT_ANSWER_MATH;
import static quick.brain.exercise.trainer.utils.Constants.FORTH_RIGHT_ANSWER_MATH;
import static quick.brain.exercise.trainer.utils.Constants.KEY_PREF_MATH;
import static quick.brain.exercise.trainer.utils.Constants.KEY_PREF_MEMORY;
import static quick.brain.exercise.trainer.utils.Constants.KEY_PREF_STARS_COUNT_ALL;
import static quick.brain.exercise.trainer.utils.Constants.KEY_VALUE_COUNT;
import static quick.brain.exercise.trainer.utils.Constants.NAME_PREF;
import static quick.brain.exercise.trainer.utils.Constants.SECOND_RIGHT_ANSWER_MATH;
import static quick.brain.exercise.trainer.utils.Constants.THIRD_RIGHT_ANSWER_MATH;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import quick.brain.exercise.trainer.R;

public class MathsTestsActivity extends AppCompatActivity {
    private ImageButton img_btn_back_maths;
    private TextView txt_v_count_maths, txt_v_save_maths, txt_v_question_maths, txt_v_animation_maths, txt_v_repeat_task_maths;
    private EditText ed_txt_answer_maths;
    private SharedPreferences.Editor editor;
    private ValueAnimator animator;
    private int levelCounterMaths = 0, starCounterMaths = 0;
    private boolean correctAnswer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_tests);
        initAllComponents();
        img_btn_back_maths.setOnClickListener(v -> {
            onBackPressed();
        });
        txt_v_save_maths.setOnClickListener(v -> {
            if (TextUtils.isEmpty(ed_txt_answer_maths.getText().toString().trim())) {
                Toast.makeText(MathsTestsActivity.this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                return;
            }
            if (checkAnswer(Integer.parseInt(ed_txt_answer_maths.getText().toString().trim()), levelCounterMaths)) {
                levelCounterMaths++;
                starCounterMaths++;
                addCountProgress(levelCounterMaths, starCounterMaths);
                correctAnswer = true;
                animator.cancel();
                setAllComponentsVisible(false);
                startCountdownAnimation(10);
            }
        });
        txt_v_repeat_task_maths.setOnClickListener(v -> {
            animator.cancel();
            setAllComponentsVisible(false);
            startCountdownAnimation(10);
        });
    }

    private void initAllComponents() {
        SharedPreferences prefs = getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE);
        editor = prefs.edit();
        txt_v_repeat_task_maths = findViewById(R.id.txt_v_repeat_task_maths);
        txt_v_animation_maths = findViewById(R.id.txt_v_animation_maths);
        img_btn_back_maths = findViewById(R.id.img_btn_back_maths);
        txt_v_count_maths = findViewById(R.id.txt_v_count_maths);
        txt_v_count_maths.setText(String.valueOf(prefs.getInt(KEY_PREF_STARS_COUNT_ALL, 0)));
        starCounterMaths = Integer.parseInt(txt_v_count_maths.getText().toString().trim());
        txt_v_save_maths = findViewById(R.id.txt_v_save_maths);
        ed_txt_answer_maths = findViewById(R.id.ed_txt_answer_maths);
        txt_v_question_maths = findViewById(R.id.txt_v_question_maths);
        levelCounterMaths = prefs.getInt(KEY_PREF_MATH, 0);
        setAllComponentsVisible(false);
        startCountdownAnimation(10);
    }

    private void setAllComponentsVisible(boolean visible) {
        if (visible) {
            img_btn_back_maths.setClickable(true);
            txt_v_save_maths.setClickable(true);
            ed_txt_answer_maths.setClickable(true);
            ed_txt_answer_maths.setVisibility(View.VISIBLE);
        } else {
            img_btn_back_maths.setClickable(false);
            txt_v_save_maths.setClickable(false);
            ed_txt_answer_maths.setClickable(false);
            ed_txt_answer_maths.setVisibility(View.GONE);
        }
    }

    private void addCountProgress(int level, int starCount) {
        txt_v_count_maths.setText(String.valueOf(starCount));
        editor.putInt(KEY_PREF_STARS_COUNT_ALL, starCount);
        editor.putInt(KEY_PREF_MATH, level);
        editor.commit();
    }

    private void showMathsQuestion(int currentLevel) {
        switch (currentLevel) {
            case 0: {
                startCountdownAnimation(20);
                break;
            }
            case 1: {
                startCountdownAnimation(15);
                break;
            }
            case 2: {
                startCountdownAnimation(30);
                break;
            }
            case 3:
            case 4: {
                setAllComponentsVisible(true);
                startCountdownAnimation(60);
                break;
            }
            default: {
                setAllComponentsVisible(false);
                txt_v_question_maths.setText(R.string.end_question);
                txt_v_animation_maths.setVisibility(View.GONE);
            }
        }
    }

    private boolean checkAnswer(int answer, int currentLevel) {
        switch (currentLevel) {
            case 0: {
                if (answer != FIRST_RIGHT_ANSWER_MATH) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                ed_txt_answer_maths.setText("");
                return true;
            }
            case 1: {
                if (answer != SECOND_RIGHT_ANSWER_MATH) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                ed_txt_answer_maths.setText("");
                return true;
            }
            case 2: {
                if (answer != THIRD_RIGHT_ANSWER_MATH) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                ed_txt_answer_maths.setText("");
                return true;
            }
            case 3: {
                if (answer != FORTH_RIGHT_ANSWER_MATH) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                ed_txt_answer_maths.setText("");
                return true;
            }
            case 4: {
                if (answer != FIFTH_RIGHT_ANSWER_MATH) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                ed_txt_answer_maths.setText("");
                return true;
            }
            default: {
                Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    private void startCountdownAnimation(int time) {
        final int endSize = 10;
        final long duration = 1000;
        final long delayBetweenAnimations = 500;

        for (int i = time; i >= 0; i--) {
            final int currentDigit = i;

            animator = ValueAnimator.ofFloat(150, endSize);
            animator.setDuration(duration);

            animator.addUpdateListener(animation -> {
                float animatedValue = (float) animation.getAnimatedValue();
                txt_v_animation_maths.setTextSize(animatedValue);
                txt_v_animation_maths.setText(String.valueOf(currentDigit));
            });

            animator.setStartDelay((time - i) * delayBetweenAnimations);

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (time == 10)
                        txt_v_save_maths.setClickable(false);

                    txt_v_question_maths.setText(setAnswer(levelCounterMaths));
                    txt_v_animation_maths.setVisibility(View.VISIBLE);
                    txt_v_repeat_task_maths.setVisibility(View.GONE);
                    correctAnswer = false;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (currentDigit == 0) {
                        switch (time) {
                            case 10: {
                                setAllComponentsVisible(true);
                                txt_v_animation_maths.setVisibility(View.GONE);
                                showMathsQuestion(levelCounterMaths);
                                break;
                            }
                            case 15:
                            case 20:
                            case 30:
                            case 60: {
                                if (correctAnswer) {
                                    txt_v_animation_maths.setVisibility(View.GONE);
                                    setAllComponentsVisible(false);
                                    return;
                                }
                                Log.d(TAG, "onAnimationEnd: Catch: " + time);
                                setAllComponentsVisible(false);
                                img_btn_back_maths.setClickable(true);
                                txt_v_repeat_task_maths.setVisibility(View.VISIBLE);
                                startRepeatAnimation();
                                break;
                            }
                        }
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.d(TAG, "onAnimationCancel: ");
                    txt_v_repeat_task_maths.setVisibility(View.GONE);
                    txt_v_animation_maths.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            animator.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        animator.end();
    }

    private int setAnswer(int currentLevel) {
        switch (currentLevel) {
            case 0: {
                return R.string.first_question_maths;
            }
            case 1: {
                return R.string.second_question_maths;
            }
            case 2: {
                return R.string.third_question_maths;
            }
            case 3: {
                return R.string.forth_question_maths;
            }
            case 4: {
                return R.string.fifth_question_maths;
            }
            default: {
                return R.string.end_question;
            }
        }
    }

    private void startRepeatAnimation() {
        animator = ValueAnimator.ofFloat(txt_v_repeat_task_maths.getTextSize(), convertSpToPixels(10));

        animator.addUpdateListener(animation -> {
            float textSize = (float) animation.getAnimatedValue();
            txt_v_repeat_task_maths.setTextSize(textSize);
        });

        animator.setDuration(1000);
        animator.start();
    }

    private float convertSpToPixels(float sp) {
        float scale = getResources().getDisplayMetrics().density;
        return sp * scale + 0.5f;
    }
}