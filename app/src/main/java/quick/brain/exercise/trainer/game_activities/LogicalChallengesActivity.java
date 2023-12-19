package quick.brain.exercise.trainer.game_activities;

import static quick.brain.exercise.trainer.utils.Constants.EIGHT_RIGHT_ANSWER_LOGICAL;
import static quick.brain.exercise.trainer.utils.Constants.FIFTH_RIGHT_ANSWER_LOGICAL;
import static quick.brain.exercise.trainer.utils.Constants.FIRST_RIGHT_ANSWER_LOGICAL;
import static quick.brain.exercise.trainer.utils.Constants.FORTH_RIGHT_ANSWER_LOGICAL;
import static quick.brain.exercise.trainer.utils.Constants.KEY_PREF_LOGIC;
import static quick.brain.exercise.trainer.utils.Constants.KEY_PREF_STARS_COUNT_ALL;
import static quick.brain.exercise.trainer.utils.Constants.NAME_PREF;
import static quick.brain.exercise.trainer.utils.Constants.NINTH_RIGHT_ANSWER_LOGICAL;
import static quick.brain.exercise.trainer.utils.Constants.SECOND_RIGHT_ANSWER_LOGICAL;
import static quick.brain.exercise.trainer.utils.Constants.SEVENTH_RIGHT_ANSWER_LOGICAL;
import static quick.brain.exercise.trainer.utils.Constants.SIXTH_RIGHT_ANSWER_LOGICAL;
import static quick.brain.exercise.trainer.utils.Constants.TENTH_RIGHT_ANSWER_LOGICAL;
import static quick.brain.exercise.trainer.utils.Constants.THIRD_RIGHT_ANSWER_LOGICAL;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import quick.brain.exercise.trainer.R;

public class LogicalChallengesActivity extends AppCompatActivity {
    private ImageButton img_btn_back_logical;
    private TextView txt_v_count_logical, txt_v_save_logical, txt_v_question_logical;
    private EditText ed_txt_answer_logical;
    private SharedPreferences.Editor editor;
    private int progressLevel = 0, starCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logical_challenges);
        initAllComponents();
        showQuestion(progressLevel);
        img_btn_back_logical.setOnClickListener(v -> {
            onBackPressed();
        });
        txt_v_save_logical.setOnClickListener(v -> {
            if (checkAnswerFromUser(ed_txt_answer_logical.getText().toString(), progressLevel))
                showQuestion(progressLevel);
        });
    }

    private void initAllComponents() {
        SharedPreferences prefs = getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE);
        editor = prefs.edit();
        img_btn_back_logical = findViewById(R.id.img_btn_back_logical);
        txt_v_count_logical = findViewById(R.id.txt_v_count_logical);
        txt_v_save_logical = findViewById(R.id.txt_v_save_logical);
        txt_v_question_logical = findViewById(R.id.txt_v_question_logical);
        ed_txt_answer_logical = findViewById(R.id.ed_txt_answer_logical);
        txt_v_count_logical.setText(String.valueOf(prefs.getInt(KEY_PREF_STARS_COUNT_ALL, 0)));
        starCount = Integer.parseInt(txt_v_count_logical.getText().toString().trim());
        progressLevel = prefs.getInt(KEY_PREF_LOGIC, 0);
    }

    private boolean checkAnswerFromUser(String answer, int currentLevel) {
        switch (currentLevel) {
            case 0: {
                if (!answer.trim().equalsIgnoreCase(FIRST_RIGHT_ANSWER_LOGICAL)) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                starCount++;
                progressLevel++;
                addCountProgress(progressLevel, starCount);
                ed_txt_answer_logical.setText("");
                showQuestion(progressLevel);
                return true;
            }
            case 1: {
                if (!answer.trim().equalsIgnoreCase(SECOND_RIGHT_ANSWER_LOGICAL)) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                starCount++;
                progressLevel++;
                addCountProgress(progressLevel, starCount);
                ed_txt_answer_logical.setText("");
                showQuestion(progressLevel);
                return true;
            }
            case 2: {
                if (!answer.trim().equalsIgnoreCase(THIRD_RIGHT_ANSWER_LOGICAL)) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                starCount++;
                progressLevel++;
                addCountProgress(progressLevel, starCount);
                ed_txt_answer_logical.setText("");
                showQuestion(progressLevel);
                return true;
            }
            case 3: {
                if (!answer.trim().equalsIgnoreCase(FORTH_RIGHT_ANSWER_LOGICAL)) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                starCount++;
                progressLevel++;
                addCountProgress(progressLevel, starCount);
                ed_txt_answer_logical.setText("");
                showQuestion(progressLevel);
                return true;
            }
            case 4: {
                if (!answer.equalsIgnoreCase(FIFTH_RIGHT_ANSWER_LOGICAL)) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                starCount++;
                progressLevel++;
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                addCountProgress(progressLevel, starCount);
                ed_txt_answer_logical.setText("");
                showQuestion(progressLevel);
                return true;
            }
            case 5: {
                if (!answer.trim().equalsIgnoreCase(SIXTH_RIGHT_ANSWER_LOGICAL)) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                starCount++;
                progressLevel++;
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                addCountProgress(progressLevel, starCount);
                ed_txt_answer_logical.setText("");
                showQuestion(progressLevel);
                return true;
            }
            case 6: {
                if (!answer.trim().equalsIgnoreCase(SEVENTH_RIGHT_ANSWER_LOGICAL)) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                starCount++;
                progressLevel++;
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                addCountProgress(progressLevel, starCount);
                ed_txt_answer_logical.setText("");
                showQuestion(progressLevel);
                return true;
            }
            case 7: {
                if (!answer.equalsIgnoreCase(EIGHT_RIGHT_ANSWER_LOGICAL)) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                starCount++;
                progressLevel++;
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                addCountProgress(progressLevel, starCount);
                ed_txt_answer_logical.setText("");
                showQuestion(progressLevel);
                return true;
            }
            case 8: {
                if (!answer.trim().equalsIgnoreCase(NINTH_RIGHT_ANSWER_LOGICAL)) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                starCount++;
                progressLevel++;
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                addCountProgress(progressLevel, starCount);
                ed_txt_answer_logical.setText("");
                showQuestion(progressLevel);
                return true;
            }
            case 9: {
                if (!answer.equalsIgnoreCase(TENTH_RIGHT_ANSWER_LOGICAL)) {
                    Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                    return false;
                }
                starCount++;
                progressLevel++;
                Toast.makeText(this, R.string.correct_answer_logical, Toast.LENGTH_SHORT).show();
                addCountProgress(progressLevel, starCount);
                ed_txt_answer_logical.setText("");
                showQuestion(progressLevel);
                return true;
            }
            default:
                Toast.makeText(this, R.string.wrong_answer_logical, Toast.LENGTH_SHORT).show();
                return false;
        }
    }

    private void showQuestion(int progressLevel) {
        switch (progressLevel) {
            case 0: {
                txt_v_question_logical.setText(R.string.first_question_logical);
                break;
            }
            case 1: {
                txt_v_question_logical.setText(R.string.second_question_logical);
                break;
            }
            case 2: {
                txt_v_question_logical.setText(R.string.third_question_logical);
                break;
            }
            case 3: {
                txt_v_question_logical.setText(R.string.forth_question_logical);
                break;
            }
            case 4: {
                txt_v_question_logical.setText(R.string.fifth_question_logical);
                ed_txt_answer_logical.setHint(R.string.hint_for_fifth_question_logical);
                break;
            }
            case 5: {
                txt_v_question_logical.setText(R.string.sixth_question_logical);
                ed_txt_answer_logical.setHint(R.string.write_answer_txt);
                break;
            }
            case 6: {
                txt_v_question_logical.setText(R.string.seventh_question_logical);
                break;
            }
            case 7: {
                txt_v_question_logical.setText(R.string.eight_question_logical);
                break;
            }
            case 8: {
                txt_v_question_logical.setText(R.string.ninth_question_logical);
                break;
            }
            case 9: {
                txt_v_question_logical.setText(R.string.tenth_question_logical);
                ed_txt_answer_logical.setHint(R.string.hint_tenth_question_logical);
                break;
            }
            default:
                txt_v_question_logical.setText(R.string.end_question);
                ed_txt_answer_logical.setHint(R.string.hint_end_question_logical);
                ed_txt_answer_logical.setClickable(false);
                txt_v_save_logical.setClickable(false);
                break;
        }
    }

    private void addCountProgress(int level, int starCount) {
        txt_v_count_logical.setText(String.valueOf(starCount));
        editor.putInt(KEY_PREF_STARS_COUNT_ALL, starCount);
        editor.putInt(KEY_PREF_LOGIC, level);
        editor.commit();
    }
}