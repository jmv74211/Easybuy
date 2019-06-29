package com.jmv74211.easybuy.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.jmv74211.easybuy.DBInfo.UserDBInfo;
import com.jmv74211.easybuy.Data.CurrentUserInfo;
import com.jmv74211.easybuy.POJO.User;
import com.jmv74211.easybuy.R;

import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    private TextView registerText;
    private EditText emailText, passwordText;
    private Button signInButton;
    private ProgressDialog progress;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TUTORIAL_PREF = "tutorialSettings";

    private static UserDBInfo userDBInfo = UserDBInfo.getInstance();
    private static CurrentUserInfo currentUserInfo;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userCollectionReference = db.collection(userDBInfo.getCollectionName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        this.emailText = (EditText) findViewById(R.id.emailText);
        this.passwordText = (EditText) findViewById(R.id.passwordText);
        this.signInButton = (Button) findViewById(R.id.buttonSignIn);
        this.registerText = (TextView) findViewById(R.id.registerText);

        currentUserInfo = CurrentUserInfo.getInstance(this);

        this.registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        this.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (userIsLogged()) {
            goToHomeActivity();
        }
    }


    private void login() {

        String email = this.emailText.getText().toString().trim();
        String password = this.passwordText.getText().toString().trim();
        boolean errorFlag = false;

        if (email.isEmpty()) {
            this.emailText.setError(getResources().getString(R.string.fieldRequired));
            this.emailText.requestFocus();
            errorFlag = true;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.emailText.setError(getResources().getString(R.string.emailIncorrect));
            this.emailText.requestFocus();
            errorFlag = true;
        }

        if (password.isEmpty()) {
            this.passwordText.setError(getResources().getString(R.string.fieldRequired));
            this.passwordText.requestFocus();
            errorFlag = true;
        }

        if (errorFlag) {
            return;
        }

        this.progress = ProgressDialog.show(this, getResources().getString(R.string.inProcess)
                , getResources().getString(R.string.conecting), true);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progress.dismiss();
                if (task.isSuccessful()) {
                    // Next method redirects to homeActivity too
                    writeUserInformation();

                } else {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.loginError), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void writeUserInformation() {

        final String userEmail = this.mAuth.getCurrentUser().getEmail();

        userCollectionReference.whereEqualTo(userDBInfo.getKeyEmail(), userEmail).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Map<String, Object> data = queryDocumentSnapshots.getDocuments().get(0).getData();

                String username = data.get(userDBInfo.getKeyUsername()).toString();
                String name = data.get(userDBInfo.getKeyName()).toString();
                String firstName = data.get(userDBInfo.getKeyFirstName()).toString();
                String email = data.get(userDBInfo.getKeyEmail()).toString();
                String secondName= data.get(userDBInfo.getKeySecondName()).toString();

                User user = new User(username, email, name, firstName, secondName);
                currentUserInfo.writeData(user);

                goToHomeActivity();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplication(), getResources().getText(R.string.errorReadingUserInfo), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private boolean userIsLogged() {
        if (this.mAuth.getCurrentUser() != null) {
            return true;
        } else
            return false;
    }

    private void goToHomeActivity() {
        finish();
        Intent i = new Intent(this, HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


}