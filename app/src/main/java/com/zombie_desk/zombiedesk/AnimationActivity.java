package com.zombie_desk.zombiedesk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener
{

    private static final int DURACAO_ANIMACAO = 1000;

    private Spinner spnAnimator;
    private Spinner spnInterpolator;
    private Button buttonPlay;
    private ImageView img;
    private Animation[] animation;
    private Interpolator[] interpolators;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        spnAnimator = (Spinner) findViewById(R.id.spnAnimacoes);
        spnInterpolator = (Spinner) findViewById(R.id.spnInterpolator);
        img = (ImageView) findViewById(R.id.imageView);
        buttonPlay = (Button) findViewById(R.id.btnPlay);
        buttonPlay.setOnClickListener(this);

        initInterpolators();
        initAnimations();
    }

    @Override
    public void onClick(View v)
    {
        executarAnimacoes();
    }

    @Override
    public void onAnimationEnd(Animation animation)
    {
        buttonPlay.setEnabled(true);
    }

    @Override
    public void onAnimationRepeat(Animation animation)
    {
    }

    @Override
    public void onAnimationStart(Animation animation)
    {
    }

    private void initAnimations()
    {
        animation = new Animation[spnAnimator.getCount()];
        animation[0] = new AlphaAnimation(1, 0);
        animation[1] = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation[2] = new ScaleAnimation(
                1, 3, 1, 3,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation[3] = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.ABSOLUTE, 200,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.ABSOLUTE, 300);
        AnimationSet set = new AnimationSet(true);
        for (int i = 0; i < animation.length - 2; i++ )
        {
            animation[i].setDuration(DURACAO_ANIMACAO);
            animation[i].setRepeatMode(Animation.REVERSE);
            animation[i].setRepeatCount(1);
            set.addAnimation(animation[i]);
        }
        animation[4] = set;
    }

    private void initInterpolators()
    {
        interpolators = new Interpolator[spnInterpolator.getCount()];
        interpolators[0] = new AccelerateDecelerateInterpolator();
        interpolators[1] = new AccelerateInterpolator();
        interpolators[2] = new AnticipateInterpolator();
        interpolators[3] = new AnticipateOvershootInterpolator();
        interpolators[4] = new BounceInterpolator();
        interpolators[5] = new CycleInterpolator(2);
        interpolators[6] = new DecelerateInterpolator();
        interpolators[7] = new LinearInterpolator();
        interpolators[8] = new OvershootInterpolator();
    }

    private void executarAnimacoes(){
        Interpolator interpolator = interpolators[spnInterpolator.getSelectedItemPosition()];
        Animation animation2 = animation[spnAnimator.getSelectedItemPosition()];
        animation2.setInterpolator(interpolator);
        animation2.setAnimationListener(this);
        img.requestLayout();
        img.setAnimation(animation2);
        animation2.start();
        buttonPlay.setEnabled(false);
    }
}
