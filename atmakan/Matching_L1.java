package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;

public class Matching_L1 extends AppCompatActivity {
    ImageView que_1, que_2, que_3, que_4, que_5, que_6, que_7, que_8;
    ImageButton backimage;
    Integer[] cardsArray = {101, 102, 103, 104, 201, 202, 203, 204};
    MediaPlayer clapvoice, wrongvoice;
    int img101, img102, img103, img104, img201, img202, img203, img204;
    int firstCard, secondCard;
    int clickFirst, clickSecond;
    int cardNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_l1);

        clapvoice = MediaPlayer.create(Matching_L1.this, R.raw.clapmat);
        wrongvoice = MediaPlayer.create(Matching_L1.this, R.raw.wronggvoice);

        backimage = (ImageButton) findViewById(R.id.imageButton51);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Matching_L1.this, Entertainment.class);
                startActivity(intent);
            }
        });

        que_1 = (ImageView) findViewById(R.id.que_1);
        que_2 = (ImageView) findViewById(R.id.que_2);
        que_3 = (ImageView) findViewById(R.id.que_3);
        que_4 = (ImageView) findViewById(R.id.que_4);
        que_5 = (ImageView) findViewById(R.id.que_5);
        que_6 = (ImageView) findViewById(R.id.que_6);
        que_7 = (ImageView) findViewById(R.id.que_7);
        que_8 = (ImageView) findViewById(R.id.que_8);


        que_1.setTag("0");
        que_2.setTag("1");
        que_3.setTag("2");
        que_4.setTag("3");
        que_5.setTag("4");
        que_6.setTag("5");
        que_7.setTag("6");
        que_8.setTag("7");

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
        que_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(que_7, theCard);

            }
        });
        que_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(que_8, theCard);

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
        } else if (cardsArray[card] == 104) {
            que.setImageResource(img104);
        } else if (cardsArray[card] == 203) {
            que.setImageResource(img203);
        } else if (cardsArray[card] == 204) {
            que.setImageResource(img204);
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
            que_7.setEnabled(false);
            que_8.setEnabled(false);


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
            } else if (clickFirst == 6) {
                clapvoice.start();
                que_7.setVisibility(View.INVISIBLE);

            } else if (clickFirst == 7) {
                clapvoice.start();
                que_8.setVisibility(View.INVISIBLE);
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
            } else if (clickSecond == 6) {
                clapvoice.start();
                que_7.setVisibility(View.INVISIBLE);
            } else if (clickSecond == 7) {
                clapvoice.start();
                que_8.setVisibility(View.INVISIBLE);
            }
        } else {
            wrongvoice.start();
            que_1.setImageResource(R.drawable.qeuu);
            que_2.setImageResource(R.drawable.qeuu);
            que_3.setImageResource(R.drawable.qeuu);
            que_4.setImageResource(R.drawable.qeuu);
            que_5.setImageResource(R.drawable.qeuu);
            que_6.setImageResource(R.drawable.qeuu);
            que_7.setImageResource(R.drawable.qeuu);
            que_8.setImageResource(R.drawable.qeuu);

        }
        que_1.setEnabled(true);
        que_2.setEnabled(true);
        que_3.setEnabled(true);
        que_4.setEnabled(true);
        que_5.setEnabled(true);
        que_6.setEnabled(true);
        que_7.setEnabled(true);
        que_8.setEnabled(true);
        //check the game is over
        checkEnd();
    }

    private void checkEnd() {
        if (que_1.getVisibility() == View.INVISIBLE &&
                que_2.getVisibility() == View.INVISIBLE &&
                que_3.getVisibility() == View.INVISIBLE &&
                que_4.getVisibility() == View.INVISIBLE &&
                que_5.getVisibility() == View.INVISIBLE &&
                que_6.getVisibility() == View.INVISIBLE &&
                que_7.getVisibility() == View.INVISIBLE &&
                que_8.getVisibility() == View.INVISIBLE

        ) {
            Intent intent = new
                    Intent(Matching_L1.this, Goodjob.class);
            startActivity(intent);
        }
    }

    private void frontOfCardsResources() {
        img101 = R.drawable.shape101;
        img201 = R.drawable.shape201;
        img102 = R.drawable.shape102;
        img202 = R.drawable.shape202;
        img103 = R.drawable.shape103;
        img203 = R.drawable.shape203;
        img104 = R.drawable.shape104;
        img204 = R.drawable.shape204;


    }
}