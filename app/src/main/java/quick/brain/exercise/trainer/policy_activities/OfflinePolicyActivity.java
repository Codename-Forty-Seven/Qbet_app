package quick.brain.exercise.trainer.policy_activities;

import static quick.brain.exercise.trainer.utils.Constants.KEY_PRIVACY_AGREE;
import static quick.brain.exercise.trainer.utils.Constants.NAME_PREF;
import static quick.brain.exercise.trainer.utils.Constants.PRIVACY_POLICY_MAIN_TXT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import quick.brain.exercise.trainer.R;

public class OfflinePolicyActivity extends AppCompatActivity {
    private TextView txt_v_main_policy, txt_v_continue_policy;
    private Switch sw_agree_policy;
    private ImageButton img_btn_back_policy;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        initAllComponents();
        img_btn_back_policy.setOnClickListener(v -> {
            if (!sw_agree_policy.isChecked()) {
                Toast.makeText(OfflinePolicyActivity.this, R.string.explain_policy_txt, Toast.LENGTH_LONG).show();
                finish();
            }
        });
        sw_agree_policy.setOnCheckedChangeListener((textView, isChecked) -> {
            if (isChecked) {
                txt_v_continue_policy.setEnabled(true);
                txt_v_continue_policy.setBackgroundResource(R.drawable.anim_txt_press_enable);
            } else {
                txt_v_continue_policy.setEnabled(false);
                txt_v_continue_policy.setBackgroundResource(R.drawable.anim_txt_press_disable);
            }
        });
        txt_v_continue_policy.setOnClickListener(v -> {
            if (sw_agree_policy.isChecked()) {
                addPolicy();
                Toast.makeText(OfflinePolicyActivity.this, R.string.agree_license_txt, Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(OfflinePolicyActivity.this, R.string.explain_policy_txt, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!sw_agree_policy.isChecked()) {
            Toast.makeText(OfflinePolicyActivity.this, R.string.explain_policy_txt, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void initAllComponents() {
        SharedPreferences prefs = getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE);
        editor = prefs.edit();
        sw_agree_policy = findViewById(R.id.sw_agree_policy);
        img_btn_back_policy = findViewById(R.id.img_btn_back_offline_policy);
        txt_v_continue_policy = findViewById(R.id.txt_v_continue_policy);
        txt_v_main_policy = findViewById(R.id.txt_v_main_policy);
        txt_v_main_policy.setText(PRIVACY_POLICY_MAIN_TXT);
    }

    private void addPolicy() {
        editor.putBoolean(KEY_PRIVACY_AGREE, true);
        editor.commit();
    }
}