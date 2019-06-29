package com.jmv74211.easybuy.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jmv74211.easybuy.DBInfo.UserDBInfo;
import com.jmv74211.easybuy.POJO.User;
import com.jmv74211.easybuy.R;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private Toolbar appbar;
    private ProgressDialog progress;

    private EditText username, email, name, password, passwordConfirm, firstName, secondName;
    private Button registerButton;

    private static UserDBInfo userDBInfo = UserDBInfo.getInstance();

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection(userDBInfo.getCollectionName());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(appbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.signup));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = (EditText) findViewById(R.id.usernameText);
        email = (EditText) findViewById(R.id.emailText);
        name = (EditText) findViewById(R.id.nameText);
        password = (EditText) findViewById(R.id.passwordText);
        passwordConfirm = (EditText) findViewById(R.id.repeatPasswordText);
        firstName = (EditText) findViewById(R.id.firstNameText);
        secondName = (EditText) findViewById(R.id.secondNameText);
        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Usuario logeado
        if (this.mAuth.getCurrentUser() != null) {
            goToHomeActivity();
        }
    }

    private void registerUser() {

        final String username = this.username.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();
        String passwordConfirm = this.passwordConfirm.getText().toString().trim();
        final String name = this.name.getText().toString().trim();
        final String firstName = this.firstName.getText().toString().trim();
        final String secondName = this.secondName.getText().toString().trim();
        boolean errorFlag = false;

        if (username.isEmpty()) {
            this.username.setError(getResources().getString(R.string.fieldRequired));
            this.username.requestFocus();
            errorFlag = true;
        }

        if (username.length() < 4 || username.length() > 10) {
            this.username.setError(getResources().getString(R.string.errorUsernameLength));
            this.username.requestFocus();
            errorFlag = true;
        }

        if (email.isEmpty()) {
            this.email.setError(getResources().getString(R.string.fieldRequired));
            this.email.requestFocus();
            errorFlag = true;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.email.setError(getResources().getString(R.string.emailIncorrect));
            this.email.requestFocus();
            errorFlag = true;
        }

        if (password.isEmpty()) {
            this.password.setError(getResources().getString(R.string.fieldRequired));
            this.password.requestFocus();
            errorFlag = true;
        }

        if (password.length() < 6) {
            this.password.setError(getResources().getString(R.string.passwordLengthError));
            this.password.requestFocus();
            errorFlag = true;
        }

        if (passwordConfirm.isEmpty()) {
            this.passwordConfirm.setError(getResources().getString(R.string.fieldRequired));
            this.passwordConfirm.requestFocus();
            errorFlag = true;
        }

        if (!password.isEmpty() && !passwordConfirm.isEmpty() && !password.equals(passwordConfirm)) {
            this.passwordConfirm.setError(getResources().getString(R.string.passwordsIncorrect));
            this.passwordConfirm.requestFocus();
            errorFlag = true;
        }

        if (name.isEmpty()) {
            this.name.setError(getResources().getString(R.string.fieldRequired));
            this.name.requestFocus();
            errorFlag = true;
        }

        if (firstName.isEmpty()) {
            this.firstName.setError(getResources().getString(R.string.fieldRequired));
            this.firstName.requestFocus();
            errorFlag = true;
        }

        if (secondName.isEmpty()) {
            this.secondName.setError(getResources().getString(R.string.fieldRequired));
            this.secondName.requestFocus();
            errorFlag = true;
        }

        if (errorFlag) {
            return;
        }


        this.progress = ProgressDialog.show(this, getResources().getString(R.string.inProcess)
                , getResources().getString(R.string.creatingUser), true);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(username, email, name, firstName, secondName);

                            Map<String, Object> data = new HashMap<>();

                            data.put(userDBInfo.getKeyUsername(), username);
                            data.put(userDBInfo.getKeyName(), name);
                            data.put(userDBInfo.getKeyFirstName(), firstName);
                            data.put(userDBInfo.getKeyEmail(), email);
                            data.put(userDBInfo.getKeySecondName(), secondName);

                            collectionReference.add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(getApplication(), getResources().getString(R.string.succesfullRegister), Toast.LENGTH_LONG).show();
                                    // Necessary because register add a new auth
                                    mAuth.getInstance().signOut();
                                    goToLoginRegisterActivity();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplication(), getResources().getString(R.string.errorCreateUser), Toast.LENGTH_LONG).show();
                                }
                            });
                            progress.dismiss();

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.emailAlreadyRegistered), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, getResources().getString(R.string.errorRegister), Toast.LENGTH_LONG).show();
                            }
                            progress.dismiss();
                        }

                    }
                }
        );
    }


    public void goToLoginRegisterActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


}
