package com.zombie_desk.zombiedesk;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatArrayEvaluator;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class AnimationPropertyActivity extends AppCompatActivity
{

    private FrameLayout mFrame;
    private ImageView mImg;
    private boolean mReverterEscala;
    private boolean mReverterAlpha;
    private Animacoes mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_property);

        mFrame = (FrameLayout) findViewById(R.id.frame);
        mImg = (ImageView) findViewById(R.id.imageView);
        mImg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                executarAnimacao();
            }
        });
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.HONEYCOMB_MR1)
        {
            mAnimation = new Animacao();
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR1)
        {
            mAnimation = new AnimacaoPlus();
        } else
        {
            Toast.makeText(this, "Animações não irão funcionar!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void executarAnimacao()
    {
        int numeroAnimacoes = 6;
        int animacao = (int) (Math.random() * numeroAnimacoes);
        switch (animacao)
        {
            case 0:
                mAnimation.girar();
                break;
            case 1:
                mAnimation.girarEmx();
                break;
            case 2:
                mAnimation.girarEmy();
                break;
            case 3:
                mAnimation.escala();
                break;
            case 4:
                mAnimation.opacidade();
                break;
            case 5:
                mAnimation.movimentar();
                break;
        }
    }

    private AnimatorListenerAdapter mListener = new AnimatorListenerAdapter()
    {
        @Override
        public void onAnimationStart(Animator animation)
        {
            super.onAnimationStart(animation);
            mImg.setEnabled(false);
        }

        @Override
        public void onAnimationEnd(Animator animation)
        {
            super.onAnimationEnd(animation);
            mImg.setEnabled(true);

        }
    };

    interface Animacoes
    {
        void girar();

        void girarEmx();

        void girarEmy();

        void escala();

        void opacidade();

        void movimentar();
    }

    class Animacao implements Animacoes
    {
        @Override
        public void girar()
        {
            ObjectAnimator animator =
                    ObjectAnimator.ofFloat(mImg, "rotation", 0, 360);
            animator.addListener(mListener);
            animator.start();
        }

        @Override
        public void girarEmx()
        {
            ObjectAnimator animator =
                    ObjectAnimator.ofFloat(mImg, "rotationX", 0, 360);
            animator.addListener(mListener);
            animator.start();
        }

        @Override
        public void girarEmy()
        {
            ObjectAnimator animator =
                    ObjectAnimator.ofFloat(mImg, "rotationY", 0, 360);
            animator.addListener(mListener);
            animator.start();
        }

        @Override
        public void escala()
        {
            float novoX = mReverterEscala ? 1.0f : 1.5f;
            float novoY = mReverterEscala ? 1.0f : 1.5f;

            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(mImg, "scaleX", novoX),
                    ObjectAnimator.ofFloat(mImg, "scaleY", novoY));
            set.addListener(mListener);
            mReverterEscala = !mReverterEscala;
        }

        @Override
        public void opacidade()
        {
            ObjectAnimator animator =
                    ObjectAnimator.ofFloat(mImg, "alpha", mReverterAlpha ? 1.0f : 0.5f);
            animator.addListener(mListener);
            animator.start();
            mReverterAlpha = !mReverterAlpha;
        }

        @Override
        public void movimentar()
        {
            float novoX = (float) (Math.random() * (mFrame.getWidth() - mImg.getWidth()));
            float novoY = (float) (Math.random() * (mFrame.getHeight() - mImg.getHeight()));

            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(mImg, "x", novoX),
                    ObjectAnimator.ofFloat(mImg, "y", novoY));
            set.addListener(mListener);
            set.start();
        }
    }

    class AnimacaoPlus implements Animacoes
    {
        private ViewPropertyAnimator getAnimator()
        {
            return mImg.animate().setListener(mListener);
        }

        @Override
        public void girar()
        {
            getAnimator().rotationBy(360);
        }

        @Override
        public void girarEmx()
        {
            getAnimator().rotationXBy(360);
        }

        @Override
        public void girarEmy()
        {
            getAnimator().rotationYBy(360);
        }

        @Override
        public void opacidade()
        {
            getAnimator().alpha(mReverterAlpha ? 1.0f : 0.5f);
            mReverterAlpha = !mReverterAlpha;
        }

        @Override
        public void escala()
        {
            float noxoX = mReverterEscala ? 1.0f : 1.5f;
            float noxoY = mReverterEscala ? 1.0f : 1.5f;

            getAnimator().scaleX(noxoX).scaleY(noxoY);
            mReverterEscala = !mReverterEscala;
        }

        @Override
        public void movimentar()
        {
            float novoX = (float) (Math.random()*(mFrame.getWidth()-mImg.getWidth()));
            float novoY = (float) (Math.random()*(mFrame.getHeight()-mImg.getHeight()));

            getAnimator().x(novoX).y(novoY);
        }
    }
}
