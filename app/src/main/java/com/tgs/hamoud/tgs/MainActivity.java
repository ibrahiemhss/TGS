package com.tgs.hamoud.tgs;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.tgs.hamoud.tgs.data.Contract;
import com.tgs.hamoud.tgs.util.TypewriterView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edit_add_link)
    protected MaterialEditText mMaterialEditText;
    @BindView(R.id.btn_add_link)
    protected Button mAddBtn;
    @BindView(R.id.typing_txt1)
    protected TypewriterView mTypewriterView1;
    /* @BindView(R.id.typing_txt2)
     protected TypewriterView mTypewriterView2;
     @BindView(R.id.typing_txt3)
     protected TypewriterView mTypewriterView3;
     @BindView(R.id.typing_txt4)
     protected TypewriterView mTypewriterView4;
     @BindView(R.id.typing_txt5)
     protected TypewriterView mTypewriterView5;
     @BindView(R.id.typing_txt6)
     protected TypewriterView mTypewriterView6;
     @BindView(R.id.typing_txt7)
     protected TypewriterView mTypewriterView7;
     @BindView(R.id.typing_txt8)
     protected TypewriterView mTypewriterView8;
     @BindView(R.id.typing_txt9)
     protected TypewriterView mTypewriterView9;
     @BindView(R.id.typing_txt10)
     protected TypewriterView mTypewriterView10;
     @BindView(R.id.typing_txt11)
     protected TypewriterView mTypewriterView11;*/
    @BindView(R.id.pb)
    protected ProgressBar mProgressBar;
    @BindView(R.id.tv)
    protected TextView mProgresstxt;

    @BindView(R.id.chronometer)
    protected Chronometer mChronometer;

    private boolean isClicked;
    private int progressStatus = 0;
    private final Handler handler = new Handler();


    @SuppressLint({"SetTextI18n", "ResourceType"})
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(v -> showDialog());


        mChronometer.setBase(SystemClock.elapsedRealtime());

        clearAll();
        Log.d("valueOfClick", String.valueOf(isClicked));

        mAddBtn.setOnClickListener(this);
        mProgresstxt.setText(progressStatus + Contract.PERCENT);

        addEditText();

    }

    private void addEditText() {

        if (mMaterialEditText.getText() != null) {
            mMaterialEditText.setText(mMaterialEditText.getText());
            Selection.setSelection(mMaterialEditText.getText(), mMaterialEditText.getText().length());
        }

        mMaterialEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().contains(Contract.PART_OF_URL)) {
                    mMaterialEditText.setText(Contract.PART_OF_URL);
                    Selection.setSelection(mMaterialEditText.getText(), mMaterialEditText.getText().length());

                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_add_link:
                String mText = Objects.requireNonNull(mMaterialEditText.getText()).toString();
                int length = Contract.PART_OF_URL.length();
                //noinspection EqualsBetweenInconvertibleTypes
                if (mMaterialEditText.getText().equals("") || !mText.contains(Contract.PART_OF_URL)) {
                    Toast.makeText(MainActivity.this, R.string.toast_add_link, Toast.LENGTH_LONG).show();
                } else if (length == mText.length()) {
                    Toast.makeText(MainActivity.this, R.string.toast_add_full_link, Toast.LENGTH_LONG).show();

                } else {
                    addText1();
                    break;
                }

        }
    }

    //TODO ------------------------------------------------//

    @SuppressLint("SetTextI18n")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void addText1() {
        if (!isClicked) {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgresstxt.setVisibility(View.VISIBLE);
            setProgress();
            clearAll();
            mAddBtn.setText(getString(R.string.loadingbtn));
            mAddBtn.setClickable(false);
            mMaterialEditText.setEnabled(false);
            mAddBtn.setBackground(getResources().getDrawable(R.drawable.gray_light_back));
            isClicked = true;
            mChronometer.setBase(SystemClock.elapsedRealtime());
            Log.d("valueOfClick1", String.valueOf(isClicked));

            mChronometer.start();
            mTypewriterView1
                    .type(getResources().getString(R.string.text1)).pause()
                    .delete(getResources().getString(R.string.loading), 2000).pause()
                    .type(getResources().getString(R.string.loading), 2000).pause()
                    .delete(getResources().getString(R.string.loading), 2000).pause()
                    .type(getResources().getString(R.string.loading), 2000).pause()
                    .delete(getResources().getString(R.string.loading), 2000).pause()
                    .type(getResources().getString(R.string.loading) + "\n", 1000).pause()

                    .type(getResources().getString(R.string.text2)).pause()
                    .delete(getResources().getString(R.string.loading), 500).pause()
                    .type(getResources().getString(R.string.loading), 500).pause()
                    .delete(getResources().getString(R.string.loading), 500).pause()
                    .type(getResources().getString(R.string.loading), 500).pause()
                    .delete(getResources().getString(R.string.loading), 300).pause()
                    .type(getResources().getString(R.string.loading), 300).pause()
                    .delete(getResources().getString(R.string.loading), 200).pause()
                    .type(getResources().getString(R.string.loading), 200).pause()
                    .delete(getResources().getString(R.string.loading), 200).pause()
                    .type(getResources().getString(R.string.loading) + "\n", 200).pause()

                    .type(getResources().getString(R.string.text3)).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading) + "\n", 1000).pause()


                    .type(getResources().getString(R.string.text4)).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading) + "\n", 4000).pause()

                    .type(getResources().getString(R.string.text5)).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 800).pause()
                    .type(getResources().getString(R.string.loading), 800).pause()
                    .delete(getResources().getString(R.string.loading), 800).pause()
                    .type(getResources().getString(R.string.loading) + "\n", 800).pause()


                    .type(getResources().getString(R.string.text6)).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading), 4000).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 800).pause()
                    .type(getResources().getString(R.string.loading) + "\n", 800).pause()


                    .type(getResources().getString(R.string.text7)).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 800).pause()
                    .type(getResources().getString(R.string.loading) + "\n", 800).pause()


                    .type(getResources().getString(R.string.text8)).pause()
                    .delete(getResources().getString(R.string.loading2), 5000).pause()
                    .type(getResources().getString(R.string.loading2), 5000).pause()
                    .delete(getResources().getString(R.string.loading2), 5000).pause()
                    .type(getResources().getString(R.string.loading2), 5000).pause()
                    .delete(getResources().getString(R.string.loading2), 5000).pause()
                    .type(getResources().getString(R.string.loading2), 5000).pause()
                    .delete(getResources().getString(R.string.loading), 5000).pause()
                    .type(getResources().getString(R.string.loading2), 5000).pause()
                    .delete(getResources().getString(R.string.loading2), 5000).pause()
                    .type(getResources().getString(R.string.loading), 5000).pause()
                    .delete(getResources().getString(R.string.loading2), 5000).pause()
                    .type(getResources().getString(R.string.loading2), 5000).pause()
                    .delete(getResources().getString(R.string.loading2), 5000).pause()
                    .type(getResources().getString(R.string.loading2), 5000).pause()
                    .delete(getResources().getString(R.string.loading2), 5000).pause()
                    .type(getResources().getString(R.string.loading2), 5000).pause()
                    .delete(getResources().getString(R.string.loading2), 5000).pause()
                    .type(getResources().getString(R.string.loading2), 5000).pause()
                    .delete(getResources().getString(R.string.loading2), 5000).pause()
                    .type(getResources().getString(R.string.loading2), 5000).pause()
                    .delete(getResources().getString(R.string.loading2), 5000).pause()
                    .type(getResources().getString(R.string.loading2), 5000).pause()
                    .delete(getResources().getString(R.string.loading2), 5000).pause()
                    .type(getResources().getString(R.string.loading2) + "\n", 4000).pause()


                    .type(getResources().getString(R.string.text9)).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 800).pause()
                    .type(getResources().getString(R.string.loading), 800).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 800).pause()
                    .type(getResources().getString(R.string.loading), 800).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 600).pause()
                    .type(getResources().getString(R.string.loading), 600).pause()
                    .delete(getResources().getString(R.string.loading), 600).pause()
                    .type(getResources().getString(R.string.loading) + "\n", 600).pause()

                    .type(getResources().getString(R.string.text10)).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 1000).pause()
                    .type(getResources().getString(R.string.loading), 1000).pause()
                    .delete(getResources().getString(R.string.loading), 600).pause()
                    .type(getResources().getString(R.string.loading), 400).pause()
                    .delete(getResources().getString(R.string.loading), 400).pause()
                    .type(getResources().getString(R.string.loading), 400).pause()
                    .delete(getResources().getString(R.string.loading), 400).pause()
                    .type(getResources().getString(R.string.loading) + "\n", 400).pause()


                    .type(getResources().getString(R.string.text11)).pause()
                    .delete(getResources().getString(R.string.loading), 500).pause()
                    .type(getResources().getString(R.string.loading), 500).pause()
                    .delete(getResources().getString(R.string.loading), 300).pause()
                    .type(getResources().getString(R.string.loading), 300).pause()
                    .delete(getResources().getString(R.string.loading), 200).pause()
                    .type(getResources().getString(R.string.loading), 200).pause()
                    .delete(getResources().getString(R.string.loading), 200).pause()
                    .type(getResources().getString(R.string.loading) + "\n", 200).pause()

                    .run(() -> {

                        mTypewriterView1.setEnabled(false);
                        if (mTypewriterView1.hasPaused) {

                            mProgresstxt.setText(progressStatus + Contract.PERCENT);
                            mMaterialEditText.setEnabled(true);
                            mChronometer.stop();
                            mAddBtn.setBackground(getDrawable(R.drawable.light_blue_back));
                            mAddBtn.setClickable(true);
                            Log.d("valueOfClick10", String.valueOf(isClicked));
                            isClicked = false;
                            mAddBtn.setText(R.string.reconnect);
                            Log.d("valueOfClick10", String.valueOf(isClicked));
                            mProgressBar.setVisibility(View.GONE);
                            mProgresstxt.setVisibility(View.GONE);
                            progressStatus = 0;

                        }
                    });

        }


    }

   /* private void addText2() {

        mTypewriterView2
                .type(getResources().getString(R.string.text2)).pause()

               .delete(getResources().getString(R.string.loading),500).pause()
                .type(getResources().getString(R.string.loading),500).pause()
                .delete(getResources().getString(R.string.loading),500).pause()
                .type(getResources().getString(R.string.loading),500).pause()
                .delete(getResources().getString(R.string.loading),300).pause()
                .type(getResources().getString(R.string.loading),300).pause()
                .delete(getResources().getString(R.string.loading),200).pause()
                .type(getResources().getString(R.string.loading),200).pause()
                .delete(getResources().getString(R.string.loading),200).pause()
                .type(getResources().getString(R.string.loading),200).pause()

                .run(() -> {

                    mTypewriterView2.setEnabled(false);
                    if (mTypewriterView1.hasPaused) {
                        progressStatus=progressStatus+10;

                        mProgressBar.setProgress(progressStatus);
                        // Show the progress on TextView
                        mProgresstxt.setText(progressStatus + "%");
                        addText3();

                    }
                });


    }
    private void addText3() {
        if(mTypingProgress==3){
            mTypewriterView3.setText("");
        }
        mTypingProgress=3;
        mTypewriterView3
                .type(getResources().getString(R.string.text3)).pause()

                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()
                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()
                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()

                .run(() -> {

                    mTypewriterView3.setEnabled(false);
                    if (mTypewriterView3.hasPaused) {
                        progressStatus=progressStatus+5;

                        mProgressBar.setProgress(progressStatus);
                        // Show the progress on TextView
                        mProgresstxt.setText(progressStatus + "%");
                        addText4();
                    }
                });


    }
    private void addText4() {

        mTypewriterView4
                .type(getResources().getString(R.string.text4)).pause()

               .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()
                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()

                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()
                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()

                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()
                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()

                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()
                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),4000).pause()

                .run(() -> {

                    mTypewriterView4.setEnabled(false);
                    if (mTypewriterView4.hasPaused) {
                        progressStatus=progressStatus+5;

                        mProgressBar.setProgress(progressStatus);
                        // Show the progress on TextView
                        mProgresstxt.setText(progressStatus + "%");
                        addText5();
                    }
                });


    }
    private void addText5() {

        mTypewriterView5
               .type(getResources().getString(R.string.text5)).pause()

                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()
                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()
                .delete(getResources().getString(R.string.loading),800).pause()
                .type(getResources().getString(R.string.loading),800).pause()
                .delete(getResources().getString(R.string.loading),800).pause()
                .type(getResources().getString(R.string.loading),800).pause()

                .run(() -> {

                    mTypewriterView5.setEnabled(false);
                    if (mTypewriterView5.hasPaused) {
                        progressStatus=progressStatus+10;

                        mProgressBar.setProgress(progressStatus);
                        // Show the progress on TextView
                        mProgresstxt.setText(progressStatus + "%");
                        addText6();
                    }
                });


    }
    private void addText6() {


        mTypewriterView6
                .type(getResources().getString(R.string.text6)).pause()

                 .delete(getResources().getString(R.string.loading),5000).pause()
                  .type(getResources().getString(R.string.loading),5000).pause()
                  .delete(getResources().getString(R.string.loading),5000).pause()
                  .type(getResources().getString(R.string.loading),5000).pause()

                  .delete(getResources().getString(R.string.loading),5000).pause()
                  .type(getResources().getString(R.string.loading),5000).pause()
                  .delete(getResources().getString(R.string.loading),5000).pause()
                  .type(getResources().getString(R.string.loading),5000).pause()

                  .delete(getResources().getString(R.string.loading),5000).pause()
                  .type(getResources().getString(R.string.loading),5000).pause()
                  .delete(getResources().getString(R.string.loading),5000).pause()
                  .type(getResources().getString(R.string.loading),5000).pause()

                  .delete(getResources().getString(R.string.loading),5000).pause()
                  .type(getResources().getString(R.string.loading),5000).pause()
                  .delete(getResources().getString(R.string.loading),5000).pause()
                  .type(getResources().getString(R.string.loading),5000).pause()

                  .delete(getResources().getString(R.string.loading),5000).pause()
                  .type(getResources().getString(R.string.loading),5000).pause()
                  .delete(getResources().getString(R.string.loading),5000).pause()
                  .type(getResources().getString(R.string.loading),5000).pause()

                  .delete(getResources().getString(R.string.loading),5000).pause()
                  .type(getResources().getString(R.string.loading),5000).pause()
                  .delete(getResources().getString(R.string.loading),5000).pause()
                  .type(getResources().getString(R.string.loading),4000).pause()

                  .delete(getResources().getString(R.string.loading),1000).pause()
                  .type(getResources().getString(R.string.loading),1000).pause()
                  .delete(getResources().getString(R.string.loading),1000).pause()
                  .type(getResources().getString(R.string.loading),1000).pause()
                  .delete(getResources().getString(R.string.loading),800).pause()
                  .type(getResources().getString(R.string.loading),800).pause()

                .run(() -> {

                    mTypewriterView6.setEnabled(false);
                    if (mTypewriterView1.hasPaused) {
                        progressStatus=progressStatus+10;

                        mProgressBar.setProgress(progressStatus);
                        // Show the progress on TextView
                        mProgresstxt.setText(progressStatus + "%");
                        addText7();
                    }
                });


    }
    private void addText7() {

        mTypewriterView7
                .type(getResources().getString(R.string.text7)).pause()

                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()
                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()
                .delete(getResources().getString(R.string.loading),800).pause()
                .type(getResources().getString(R.string.loading),800).pause()

                .run(() -> {

                    mTypewriterView7.setEnabled(false);
                    if (mTypewriterView7.hasPaused) {
                        progressStatus=progressStatus+10;

                        mProgressBar.setProgress(progressStatus);
                        // Show the progress on TextView
                        mProgresstxt.setText(progressStatus + "%");
                        addText8();
                    }
                });


    }
    private void addText8() {

        mTypewriterView8
                .type(getResources().getString(R.string.text8)).pause()

                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()
                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()

                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()
                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()

                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()
                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()

                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()
                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()

                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()
                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()

                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),5000).pause()
                .delete(getResources().getString(R.string.loading),5000).pause()
                .type(getResources().getString(R.string.loading),4000).pause()

                .run(() -> {

                    mTypewriterView8.setEnabled(false);
                    if (mTypewriterView8.hasPaused) {
                        progressStatus=progressStatus+10;

                        mProgressBar.setProgress(progressStatus);
                        // Show the progress on TextView
                        mProgresstxt.setText(progressStatus + "%");

                        addText9();


                    }
                });


    }

    private void addText9() {

        mTypewriterView9
                .type(getResources().getString(R.string.text9)).pause()

               .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()
                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()
                .delete(getResources().getString(R.string.loading),800).pause()
                .type(getResources().getString(R.string.loading),800).pause()

                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()
                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()
                .delete(getResources().getString(R.string.loading),800).pause()
                .type(getResources().getString(R.string.loading),800).pause()


                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()

                .delete(getResources().getString(R.string.loading),600).pause()
                .type(getResources().getString(R.string.loading),600).pause()
                .delete(getResources().getString(R.string.loading),600).pause()
                .type(getResources().getString(R.string.loading),600).pause()

                .run(() -> {

                    mTypewriterView9.setEnabled(false);
                    if (mTypewriterView9.hasPaused) {
                        progressStatus=progressStatus+10;

                        mProgressBar.setProgress(progressStatus);
                        // Show the progress on TextView
                        mProgresstxt.setText(progressStatus + "%");
                        addText10();

                    }
                });


    }
    private void addText10() {

        mTypewriterView10
                .type(getResources().getString(R.string.text10)).pause()

                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()

                .delete(getResources().getString(R.string.loading),1000).pause()
                .type(getResources().getString(R.string.loading),1000).pause()

                .delete(getResources().getString(R.string.loading),600).pause()
                .type(getResources().getString(R.string.loading),400).pause()
                .delete(getResources().getString(R.string.loading),400).pause()
                .type(getResources().getString(R.string.loading),400).pause()
                .delete(getResources().getString(R.string.loading),400).pause()
                .type(getResources().getString(R.string.loading),400).pause()
                .run(() -> {

                    mTypewriterView10.setEnabled(false);
                    if (mTypewriterView8.hasPaused) {
                        progressStatus=progressStatus+10;

                        mProgressBar.setProgress(progressStatus);
                        // Show the progress on TextView
                        mProgresstxt.setText(progressStatus + "%");

                        addText11();
                    }
                });


    }
    private void addText11() {

        mTypewriterView11.type(getResources().getString(R.string.text11)).pause()

                .delete(getResources().getString(R.string.loading),500).pause()
                .type(getResources().getString(R.string.loading),500).pause()
                .delete(getResources().getString(R.string.loading),300).pause()
                .type(getResources().getString(R.string.loading),300).pause()
                .delete(getResources().getString(R.string.loading),200).pause()
                .type(getResources().getString(R.string.loading),200).pause()
                .delete(getResources().getString(R.string.loading),200).pause()
                .type(getResources().getString(R.string.loading),200).pause()
                .run(() -> {

                    mTypewriterView11.setEnabled(false);
                    if (mTypewriterView11.hasPaused) {
                        progressStatus=progressStatus+10;

                        mProgressBar.setProgress(progressStatus);
                        // Show the progress on TextView
                        mProgresstxt.setText(progressStatus + "%");
                        mMaterialEditText.setEnabled(true);
                        mChronometer.stop();
                        mAddBtn.setBackground(getDrawable(R.drawable.light_blue_back));
                        mAddBtn.setClickable(true);
                        Log.d("valueOfClick10",String.valueOf(isClicked));
                        isClicked=false;
                        mAddBtn.setText(R.string.reconnect);
                        Log.d("valueOfClick10",String.valueOf(isClicked));
                        mProgressBar.setVisibility(View.GONE);
                        mProgresstxt.setVisibility(View.GONE);
                        progressStatus=0;

                    }
                });


    }
*/
    //TODO ------------------------------------------------//


    public void showDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.exit_dialog, (dialog, which) -> finish())
                .setNegativeButton(android.R.string.no, (dialog, which) -> {
                    // do nothing
                })
                .setIcon(android.R.drawable.ic_dialog_alert
                )
                .show();

    }


    public void clearAll() {
        mTypewriterView1.setEnabled(false);
       /* mTypewriterView2.setEnabled(false);
        mTypewriterView3.setEnabled(false);
        mTypewriterView4.setEnabled(false);
        mTypewriterView5.setEnabled(false);
        mTypewriterView6.setEnabled(false);
        mTypewriterView7.setEnabled(false);
        mTypewriterView8.setEnabled(false);
        mTypewriterView9.setEnabled(false);
        mTypewriterView10.setEnabled(false);
        mTypewriterView11.setEnabled(false);
*/
        mTypewriterView1.setText("");
      /*  mTypewriterView2.setText("");
        mTypewriterView3.setText("");
        mTypewriterView4.setText("");
        mTypewriterView5.setText("");
        mTypewriterView6.setText("");
        mTypewriterView7.setText("");
        mTypewriterView8.setText("");
        mTypewriterView9.setText("");
        mTypewriterView10.setText("");
        mTypewriterView11.setText("");*/
    }

    @SuppressLint("SetTextI18n")
    private void setProgress() {
        new Thread(() -> {
            while (progressStatus < 100) {
                // Update the progress status
                progressStatus += 1;

                // Try to sleep the thread for 20 milliseconds
                try {
                    Thread.sleep(13000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Update the progress bar
                handler.post(() -> {
                    mProgressBar.setProgress(progressStatus);
                    // Show the progress on TextView
                    mProgresstxt.setText(progressStatus + Contract.PERCENT);
                });
            }
        }).start(); // Start the operation
    }

}
