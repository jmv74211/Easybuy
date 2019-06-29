package com.jmv74211.easybuy.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jmv74211.easybuy.Data.Data;
import com.jmv74211.easybuy.POJO.ShoppingList;
import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.Tools.Date;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateShoppingListActivity extends AppCompatActivity {

    private Toolbar appbar;

    private final int maxParticipants = 10;

    private EditText nameList;
    private EditText[] participantsEditText;
    private FloatingActionButton fab;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shopping_list);

        appbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(appbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.createList));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        fab = findViewById(R.id.fab);
        nameList = (EditText) findViewById(R.id.nameList);
        participantsEditText = new EditText[maxParticipants];

        participantsEditText[0] = (EditText) findViewById(R.id.participant1);
        participantsEditText[1] = (EditText) findViewById(R.id.participant2);
        participantsEditText[2] = (EditText) findViewById(R.id.participant3);
        participantsEditText[3] = (EditText) findViewById(R.id.participant4);
        participantsEditText[4] = (EditText) findViewById(R.id.participant5);
        participantsEditText[5] = (EditText) findViewById(R.id.participant6);
        participantsEditText[6] = (EditText) findViewById(R.id.participant7);
        participantsEditText[7] = (EditText) findViewById(R.id.participant8);
        participantsEditText[8] = (EditText) findViewById(R.id.participant9);
        participantsEditText[9] = (EditText) findViewById(R.id.participant10);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createShoppingList();
            }
        });

    }

    public void createShoppingList() {


        String nameList = this.nameList.getText().toString().trim();
        String date = Date.getInstance().getDate();
        String userCreator = "jmv74211";
        String time = Date.getInstance().getTime();
        ArrayList<String> products = new ArrayList<>();

        ArrayList<String> participants = new ArrayList<>(Arrays.asList(userCreator));


        for (int i = 0; i < participantsEditText.length; i++) {
            String participantUser = participantsEditText[i].getText().toString().trim();

            if (!participantUser.isEmpty() && participantUser.length() != 0 && !participantUser.equals("") && participantUser != null) {
                participants.add(participantsEditText[i].getText().toString().trim());
            }

        }

        boolean errorFlag = false;

        if (nameList.isEmpty()) {
            this.nameList.setError(getResources().getString(R.string.fieldRequired));
            this.nameList.requestFocus();
            errorFlag = true;
        }

        if (errorFlag){
            return;
        }


        this.progress = ProgressDialog.show(this, getResources().getString(R.string.inProcess)
                , getResources().getString(R.string.CreatingShoppingList), true);

        Data.getInstance().addShoppingList(new ShoppingList(nameList, userCreator, date, time, participants, products));
        progress.dismiss();

        for(ShoppingList sh : Data.getInstance().getShoppingList()){
            System.out.println(sh);
        }

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

        Toast.makeText(this, "Cesta creada", Toast.LENGTH_SHORT).show();

    }


}
