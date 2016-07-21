package com.gomen.test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.gomen.test.view.SelfAdaptiveEditText;

public class SelfAdaptiveActivity extends AppCompatActivity implements View.OnClickListener, SelfAdaptiveEditText.OnTextSizeChangeListener{

    private Button mButton;
    private SelfAdaptiveEditText mCalculatorEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview(){
        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mCalculatorEditText = (SelfAdaptiveEditText) findViewById(R.id.selfAdaptiveEditText);
        mCalculatorEditText.setOnTextSizeChangeListener(this);
    }

    @Override
    public void onTextSizeChanged(TextView textView, float oldSize) {
        final float textScale = oldSize / textView.getTextSize();
        final float translationX = (1.0f - textScale) *
                (textView.getWidth() / 2.0f - textView.getPaddingRight());
        final float translationY = (1.0f - textScale) *
                (textView.getHeight() / 2.0f - textView.getPaddingBottom());

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(textView, View.SCALE_X, textScale, 1.0f),
                ObjectAnimator.ofFloat(textView, View.SCALE_Y, textScale, 1.0f),
                ObjectAnimator.ofFloat(textView, View.TRANSLATION_X, translationX, 0.0f),
                ObjectAnimator.ofFloat(textView, View.TRANSLATION_Y, translationY, 0.0f));
        animatorSet.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
    }

    StringBuffer mStringBuffer = new StringBuffer();
    @Override
    public void onClick(View v) {
        mStringBuffer.append("6");
        mCalculatorEditText.setText(mStringBuffer.toString());
    }
}
