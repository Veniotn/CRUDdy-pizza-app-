package com.example.a3_a_cruddypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderHistory extends BasicActivity implements RecyclerViewInterface{

    Button backButton, changeLanguageButton;
    TextView headerText;

    Intent mainMenu;

    SharedPreferenceHelper preferences;

    enum  index{
        BACK_BUTTON,
        LANGUAGE_BUTTON,
        HEADER_TEXT
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        backButton = findViewById(R.id.backButton);
        changeLanguageButton = findViewById(R.id.changeLanguageButton);

        headerText = findViewById(R.id.orderHistoryTextView);

        backButton.setOnClickListener(buttonClicked);
        changeLanguageButton.setOnClickListener(buttonClicked);

        mainMenu = new Intent(getApplicationContext(), MainMenu.class);

        preferences = new SharedPreferenceHelper(this);


    }


    View.OnClickListener buttonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.backButton:
                    startActivity(mainMenu);
                    break;
                case R.id.changeLanguageButton:
                    preferences.onUpdate();
                    updateLanguage();

            }

        }
    };

    @Override
    protected void updateLanguage() {
        String [] array = preferences.isFrench() ? getResources().getStringArray(R.array.orderHistoryFrench)
                                                 : getResources().getStringArray(R.array.orderHistoryEnglish);
        ArrayList<String> textOptions = new ArrayList<>(Arrays.asList(array));

        backButton.setText(textOptions.get(index.BACK_BUTTON.ordinal()));
        changeLanguageButton.setText(textOptions.get(index.LANGUAGE_BUTTON.ordinal()));
        headerText.setText(textOptions.get(index.HEADER_TEXT.ordinal()));

    }

    @Override
    public void orderClicked(int position) {

    }
}