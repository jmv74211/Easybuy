package com.jmv74211.easybuy.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.jmv74211.easybuy.DBInfo.ShoppingListDBInfo;
import com.jmv74211.easybuy.DBInfo.UserDBInfo;
import com.jmv74211.easybuy.Data.CurrentUserInfo;
import com.jmv74211.easybuy.POJO.CartProduct;
import com.jmv74211.easybuy.POJO.User;
import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.Tools.Date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CreateShoppingListActivity extends AppCompatActivity {

    private Toolbar appbar;

    private final int maxParticipants = 10;
    ArrayList<String> users;

    private EditText nameList;
    private EditText[] participantsEditText;
    private FloatingActionButton fab;
    private ProgressDialog progress;

    ShoppingListDBInfo shoppingListDBInfo = ShoppingListDBInfo.getInstance();
    UserDBInfo userDBInfo = UserDBInfo.getInstance();

    private ArrayAdapter<String> adapter;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection(shoppingListDBInfo.getCollectionName());
    private CollectionReference userCollectionReference = db.collection(userDBInfo.getCollectionName());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shopping_list);

        appbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(appbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.createList));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        users = new ArrayList<>();

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


        userCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    User user = doc.getDocument().toObject(User.class);
                    users.add(user.getUsername());
                    adapter.notifyDataSetChanged();

                }
            }
        });

        AutoCompleteTextView[] participants = new AutoCompleteTextView[maxParticipants];
        participants[0] = findViewById(R.id.participant1);
        participants[1] = findViewById(R.id.participant2);
        participants[2] = findViewById(R.id.participant3);
        participants[3] = findViewById(R.id.participant4);
        participants[4] = findViewById(R.id.participant5);
        participants[5] = findViewById(R.id.participant6);
        participants[6] = findViewById(R.id.participant7);
        participants[7] = findViewById(R.id.participant8);
        participants[8] = findViewById(R.id.participant9);
        participants[9] = findViewById(R.id.participant10);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users);

        for (int i = 0; i < participants.length; i++) {
            participants[i].setAdapter(adapter);
        }

    }

    public void createShoppingList() {


        String nameList = this.nameList.getText().toString().trim();
        String date = Date.getInstance().getDate();
        String userCreator = CurrentUserInfo.getInstance(this).getCurrentUserInfo().getUsername();
        String time = Date.getInstance().getTime();
        float price = 0;
        ArrayList<CartProduct> cartProducts = new ArrayList<>();

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

        // check if username participants exist
        int counter = 0;
        for (String item : participants) {

            boolean exist = false;

            for (String username : users) {
                if (username == item || username.equals(item)) {
                    exist = true;
                }
            }

            if (!exist) {
                errorFlag = true;          // counter -1 because the first is the user creator.
                this.participantsEditText[counter - 1].setError(getResources().getString(R.string.userDoesNotExist));
                this.participantsEditText[counter - 1].requestFocus();
            }

            counter++;
        }

        // check if user is already register in the shoppingList
        boolean duplicated = false;
        for (int i = 0; i < participants.size() && !duplicated; i++) {
            for (int j = i + 1; j < participants.size() && !duplicated; j++) {
                if (participants.get(i).equals(participants.get(j))) {
                    this.participantsEditText[j-1].setError(getResources().getString(R.string.errorAddUserToShoppingList));
                    this.participantsEditText[j-1].requestFocus();
                    duplicated = true;
                    errorFlag = true;
                }
            }
        }


        if (errorFlag) {
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
        data.put(shoppingListDBInfo.getKEY_USER_CREATOR(), userCreator);
        data.put(shoppingListDBInfo.getKEY_CART_PRODUCTS(), cartProducts);


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
