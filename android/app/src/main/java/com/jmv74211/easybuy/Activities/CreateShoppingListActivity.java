package com.jmv74211.easybuy.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jmv74211.easybuy.DBInfo.ShoppingListDBInfo;
import com.jmv74211.easybuy.Data.Data;
import com.jmv74211.easybuy.POJO.ShoppingList;
import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.Tools.Date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CreateShoppingListActivity extends AppCompatActivity {

    private Toolbar appbar;

    private final int maxParticipants = 10;

    private EditText nameList;
    private EditText[] participantsEditText;
    private FloatingActionButton fab;
    private ProgressDialog progress;

    ShoppingListDBInfo shoppingListDBInfo = ShoppingListDBInfo.getInstance();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection(shoppingListDBInfo.getCollectionName());


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
        float price = 0;
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


        Map<String, Object> data = new HashMap<>();

        data.put(shoppingListDBInfo.getKEY_NAME(), nameList);
        data.put(shoppingListDBInfo.getKEY_DATE(), date);
        data.put(shoppingListDBInfo.getKEY_TIME(), time);
        data.put(shoppingListDBInfo.getKEY_PARTICIPANTS(), participants);
        data.put(shoppingListDBInfo.getKEY_PRICE(), price);
        data.put(shoppingListDBInfo.getKEY_PRODUCTS(), products);


        collectionReference.add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                progress.dismiss();
                Toast.makeText(getApplication(), getResources().getText(R.string.successCreateShoppingList), Toast.LENGTH_SHORT).show();
                goToHomeActivity();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progress.dismiss();
                Toast.makeText(getApplication(), getResources().getText(R.string.errorCreateShoppingList), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goToHomeActivity() {
        finish();
        Intent i = new Intent(this, HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}
