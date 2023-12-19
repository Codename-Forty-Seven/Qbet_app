package quick.brain.exercise.trainer.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import quick.brain.exercise.trainer.R;

public class CircularProgressBar extends View {
    private Paint backgroundPaint;
    private Paint progressPaint;
    private Paint textPaint;
    private int progress;
    private String progressText;

    public CircularProgressBar(Context context) {
        super(context);
        init();
    }

    public CircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(25);
        backgroundPaint.setColor(getResources().getColor(R.color.bg_for_un_complete_pb));

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(25);
        progressPaint.setColor(getResources().getColor(R.color.bg_for_complete_pb));

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setFontFeatureSettings(String.valueOf(R.font.aldrich));
        textPaint.setColor(getResources().getColor(R.color.bg_for_text));
        textPaint.setTextSize(40);

        progress = 0;
        progressText = "0/" + progressText;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        this.progressText = progress + "/" + progressText;
        invalidate();
    }

    public void setProgressText(int progressText) {
        this.progressText = String.valueOf(progressText);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(centerX, centerY) - 10;

        float angle = 360 * progress / Integer.parseInt(progressText.split("/")[1]);
        float startAngle = 90;

        canvas.drawCircle(centerX, centerY, radius, backgroundPaint);

        canvas.drawArc(centerX - radius, centerY - radius, centerX + radius, centerY + radius,
                startAngle, angle, false, progressPaint);

        float textWidth = textPaint.measureText(progressText);
        float textX = centerX - textWidth / 2;
        float textY = centerY + (textPaint.getTextSize() / 2) - textPaint.descent();
        canvas.drawText(progressText, textX, textY, textPaint);
    }
}
