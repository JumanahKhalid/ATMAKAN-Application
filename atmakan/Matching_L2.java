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

public class Matching_L2 extends AppCompatActivity {
    ImageView que_1, que_2, que_3, que_4, que_5, que_6;
    ImageButton backimage;
    Integer[] cardsArray = {101, 102, 201, 202, 103, 203};
    MediaPlayer clapvoice, wrongvoice;
    int img101, img102, img201, img202, img103, img203;
    int firstCard, secondCard;
    int clickFirst, clickSecond;
    int cardNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_l2);
        clapvoice = MediaPlayer.create(Matching_L2.this, R.raw.clapmat);
        wrongvoice = MediaPlayer.create(Matching_L2.this, R.raw.wronggvoice);

        que_1 = (ImageView) findViewById(R.id.que_1);
        que_2 = (ImageView) findViewById(R.id.que_2);
        que_3 = (ImageView) findViewById(R.id.que_3);
        que_4 = (ImageView) findViewById(R.id.que_4);
        que_5 = (ImageView) findViewById(R.id.que_5);
        que_6 = (ImageView) findViewById(R.id.que_6);


        que_1.setTag("0");
        que_2.setTag("1");
        que_3.setTag("2");
        que_4.setTag("3");
        que_5.setTag("4");
        que_6.setTag("5");
        backimage = (ImageButton) findViewById(R.id.imageButton50);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Matching_L2.this, Entertainment.class);
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
        que_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(que_5, theCard);

            }
        });
        que_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(que_6, theCard);

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
        } else if (cardsArray[card] == 103) {
            que.setImageResource(img103);
        } else if (cardsArray[card] == 203) {
            que.setImageResource(img203);
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
            que_5.setEnabled(false);
            que_6.setEnabled(false);


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
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
                clapvoice.start();
                que_1.setVisibility(View.INVISIBLE);
            } else if (clickFirst == 1) {
                clapvoice.start();
                que_2.setVisibility(View.INVISIBLE);
            } else if (clickFirst == 2) {
                clapvoice.start();
                que_3.setVisibility(View.INVISIBLE);
            } else if (clickFirst == 3) {
                clapvoice.start();
                que_4.setVisibility(View.INVISIBLE);
            } else if (clickFirst == 4) {
                clapvoice.start();
                que_5.setVisibility(View.INVISIBLE);

            } else if (clickFirst == 5) {
                clapvoice.start();
                que_6.setVisibility(View.INVISIBLE);
            }

            if (clickSecond == 0) {
                clapvoice.start();
                que_1.setVisibility(View.INVISIBLE);
            } else if (clickSecond == 1) {
                clapvoice.start();
                que_2.setVisibility(View.INVISIBLE);
            } else if (clickSecond == 2) {
                clapvoice.start();
                que_3.setVisibility(View.INVISIBLE);
            } else if (clickSecond == 3) {
                clapvoice.start();
                que_4.setVisibility(View.INVISIBLE);
            } else if (clickSecond == 4) {
                clapvoice.start();
                que_5.setVisibility(View.INVISIBLE);
            } else if (clickSecond == 5) {
                clapvoice.start();
                que_6.setVisibility(View.INVISIBLE);
            }
        } else {
            wrongvoice.start();
            que_1.setImageResource(R.drawable.qeuu);
            que_2.setImageResource(R.drawable.qeuu);
            que_3.setImageResource(R.drawable.qeuu);
            que_4.setImageResource(R.drawable.qeuu);
            que_5.setImageResource(R.drawable.qeuu);
            que_6.setImageResource(R.drawable.qeuu);

        }
        que_1.setEnabled(true);
        que_2.setEnabled(true);
        que_3.setEnabled(true);
        que_4.setEnabled(true);
        que_5.setEnabled(true);
        que_6.setEnabled(true);

        checkEnd();
    }

    private void checkEnd() {
        if (que_1.getVisibility() == View.INVISIBLE &&
                que_2.getVisibility() == View.INVISIBLE &&
                que_3.getVisibility() == View.INVISIBLE &&
                que_4.getVisibility() == View.INVISIBLE &&
                que_5.getVisibility() == View.INVISIBLE &&
                que_6.getVisibility() == View.INVISIBLE
        ) {
            Intent intent = new
                    Intent(Matching_L2.this, Goodjob.class);
            startActivity(intent);
        }
    }

    private void frontOfCardsResources() {
        img101 = R.drawable.shape101;
        img102 = R.drawable.shape102;
        img201 = R.drawable.shape201;
        img202 = R.drawable.shape202;
        img103 = R.drawable.shape103;
        img203 = R.drawable.shape203;


    }
}
