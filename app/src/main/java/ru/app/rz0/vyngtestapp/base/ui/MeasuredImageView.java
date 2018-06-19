package ru.app.rz0.vyngtestapp.base.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import ru.app.rz0.vyngtestapp.R;

public class MeasuredImageView extends AppCompatImageView {

  private float heightOfWidth = 0.5322f;

  public MeasuredImageView(Context context) {
    super(context);
    init(context, null);
  }

  public MeasuredImageView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public MeasuredImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MeasuredImageView);
    heightOfWidth = a.getFloat(R.styleable.MeasuredImageView_heightOfWidth, heightOfWidth);
    a.recycle();
  }

  @Override
  protected void onMeasure(int widthSpec, int heightSpec) {
    int height = MeasureSpec.makeMeasureSpec(
        (int) (MeasureSpec.getSize(widthSpec) * heightOfWidth),
        MeasureSpec.EXACTLY
    );
    super.onMeasure(widthSpec, height);
  }
}
