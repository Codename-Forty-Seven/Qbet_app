package quick.brain.exercise.trainer.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import quick.brain.exercise.trainer.R;

public class ImageAdapter extends BaseAdapter {
    private final Context context;
    private final List<Integer> imageList;
    private List<Integer> initialOrder;
    private int draggedIndex = -1;
    private int secondPosition = -1;

    public ImageAdapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
        this.initialOrder = new ArrayList<>(imageList);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            Drawable backGround = context.getDrawable(R.drawable.box_with_photo);
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(backGround.getIntrinsicWidth(), backGround.getIntrinsicHeight()));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackground(backGround);
            imageView.setPadding(20, 20, 20, 20);
            imageView.setOnClickListener(new ImageTouchListener(position));
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(imageList.get(position));

        return imageView;
    }

    public void saveInitialOrder() {
        initialOrder.clear();
        initialOrder.addAll(imageList);
    }

    public boolean checkCorrectOrder() {
        return initialOrder.equals(imageList);
    }

    public void shuffleImages() {
        Collections.shuffle(imageList);
        notifyDataSetChanged();
    }

    public void swapImages(int from, int to) {
        if (from != to) {
            Integer temp = imageList.get(from);
            imageList.set(from, imageList.get(to));
            imageList.set(to, temp);
            notifyDataSetChanged();
        }
    }

    public int getSecondPosition() {
        return secondPosition;
    }

    public int getDraggedIndex() {
        return draggedIndex;
    }

    public void setSecondPosition(int position) {
        this.secondPosition = position;
    }

    private class ImageTouchListener implements View.OnClickListener {
        private final int position;

        public ImageTouchListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (draggedIndex == -1) {
                draggedIndex = position;
            } else {
                swapImages(draggedIndex, position);
                draggedIndex = -1;
            }
        }
    }
}
