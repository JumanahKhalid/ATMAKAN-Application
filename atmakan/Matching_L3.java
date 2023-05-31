package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;

public class Matching_L3 extends AppCompatActivity {
    ImageView que_1, que_2, que_3, que_4;
    ImageButton backimage;
    Integer[] cardsArray = {101, 102, 201, 202};
    MediaPlayer voice, wrongvoice;
    int img101, img102, img201, img202;
    int firstCard, secondCard;
    int clickFirst, clickSecond;
    int cardNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_l3);
        voice = MediaPlayer.create(Matching_L3.this, R.raw.clapmat);
        wrongvoice = MediaPlayer.create(Matching_L3.this, R.raw.wronggvoice);
        que_1 = (ImageView) findViewById(R.id.que_1);
        que_2 = (ImageView) findViewById(R.id.que_2);
        que_3 = (ImageView) findViewById(R.id.que_3);
        que_4 = (ImageView) findViewById(R.id.que_4);

        que_1.setTag("0");
        que_2.setTag("1");
        que_3.setTag("2");
        que_4.setTag("3");
        backimage = (ImageButton) findViewById(R.id.imageButton45);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Matching_L3.this, Entertainment.class);
                startActivity(intent);
            }
        });

        frontOfCardsResources();

        Collections.shuffle(Arrays.asList(cardsArray));

        que_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(que_1, theCard);
            }
        });
        que_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(que_2, theCard);

            }
        });
        que_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(que_3, theCard);

            }
        });
        que_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(que_4, theCard);

            }
        });
    }

    private void doStuff(ImageView que, int card) {
        if (cardsArray[card] == 101) {
            que.setImageResource(img101);

        } else if (cardsArray[card] == 102) {
            que.setImageResource(img102);
        } else if (cardsArray[card] == 201) {
            que.setImageResource(img201);
        } else if (cardsArray[card] == 202) {
            que.setImageResource(img202);
        }

        if (cardNumber == 1) {

            firstCard = cardsArray[card];
            if (firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickFirst = card;
            que.setEnabled(false);

        } else if (cardNumber == 2) {
            secondCard = cardsArray[card];
            if (secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickSecond = card;
            que_1.setEnabled(false);
            que_2.setEnabled(false);
            que_3.setEnabled(false);
            que_4.setEnabled(false);
            Handler ha = new Handler();
            ha.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            }, 1000);
        }
    }

    private void calculate() {
        if (firstCard == secondCard) {
            if (clickFirst == 0) {
                voice.start();
                que_1.setVisibility(View.INVISIBLE);
            } else if (clickFirst == 1) {
                voice.start();
                que_2.setVisibility(View.INVISIBLE);
            } else if (clickFirst == 2) {
                voice.start();
                que_3.setVisibility(View.INVISIBLE);
            } else if (clickFirst == 3) {
                voice.start();
                que_4.setVisibility(View.INVISIBLE);
            }

            if (clickSecond == 0) {
                voice.start();
                que_1.setVisibility(View.INVISIBLE);
            } else if (clickSecond == 1) {
                voice.start();
                que_2.setVisibility(View.INVISIBLE);
            } else if (clickSecond == 2) {
                voice.start();
                que_3.setVisibility(View.INVISIBLE);
            } else if (clickSecond == 3) {
                voice.start();
                que_4.setVisibility(View.INVISIBLE);
            }

        } else {
            wrongvoice.start();
            que_1.setImageResource(R.drawable.qeuu);
            que_2.setImageResource(R.drawable.qeuu);
            que_3.setImageResource(R.drawable.qeuu);
            que_4.setImageResource(R.drawable.qeuu);

        }
        que_1.setEnabled(true);
        que_2.setEnabled(true);
        que_3.setEnabled(true);
        que_4.setEnabled(true);

        checkEnd();
    }

    private void checkEnd() {
        if (que_1.getVisibility() == View.INVISIBLE &&
                que_2.getVisibility() == View.INVISIBLE &&
                que_3.getVisibility() == View.INVISIBLE &&
                que_4.getVisibility() == View.INVISIBLE
        ) {
            Intent intent = new
                    Intent(Matching_L3.this, Goodjob.class);
            startActivity(intent);
        }
    }

    private void frontOfCardsResources() {
        img101 = R.drawable.shape101;
        img102 = R.drawable.shape102;
        img201 = R.drawable.shape201;
        img202 = R.drawable.shape202;
    }
}