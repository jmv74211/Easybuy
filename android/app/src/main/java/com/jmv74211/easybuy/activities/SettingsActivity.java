package com.jmv74211.easybuy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.widget.Toolbar;

import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.data.Data;
import com.jmv74211.easybuy.data.SettingsData;
import com.jmv74211.easybuy.data.ThemeData;
import com.jmv74211.easybuy.dialogs.DialogSelectTheme;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        DialogSelectTheme.DialogSelectThemeListener{

  private Toolbar appbar;
  private Spinner spinner;
  private Button buttonApplyChanges, buttonTheme;
  private String currency, language, theme;
  private RadioGroup radioGroup;
  private RadioButton spanishButton, englishButton;

  // -----------------------------------------------------------------------------------------------

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    theme = SettingsData.getInstance(this).getTheme();
    setUpViews();
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    currency = adapterView.getItemAtPosition(i).toString();
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public void onNothingSelected(AdapterView<?> adapterView) {

  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public void applyData(String themeName) {
    theme = themeName;
    setLayoutTheme();
  }

  // -----------------------------------------------------------------------------------------------

  private void setUpAppBar() {
    appbar = findViewById(R.id.app_bar);

    setSupportActionBar(appbar);

    getSupportActionBar().setTitle(R.string.settings);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(
            ThemeData.getInstance(getApplicationContext()).getTheme(theme).getColorPrimary())));

    if (Build.VERSION.SDK_INT >= 21) {
      getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      getWindow().setStatusBarColor(Color.parseColor(ThemeData.getInstance(getApplicationContext()).
              getTheme(theme).getColorPrimaryDark()));
    }
  }

  // -----------------------------------------------------------------------------------------------

  private void setUpSpinner() {
    spinner = findViewById(R.id.spinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.currency, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(this);
  }

  // -----------------------------------------------------------------------------------------------

  private void setUpButtons() {
    radioGroup = findViewById(R.id.radiogroup);
    buttonApplyChanges = findViewById(R.id.buttonApply);
    spanishButton = findViewById(R.id.spanish);
    englishButton = findViewById(R.id.english);
    buttonTheme = findViewById(R.id.buttonTheme);

    int colorAccent = Color.parseColor(
            ThemeData.getInstance(getApplicationContext()).getTheme(theme).getColorAccent());
    int colorPrimary =  Color.parseColor(
            ThemeData.getInstance(getApplicationContext()).getTheme(theme).getColorPrimary());

    buttonApplyChanges.setBackgroundColor(colorAccent);
    buttonTheme.setBackgroundColor(colorPrimary);
    spanishButton.setButtonTintList(ColorStateList.valueOf(colorAccent));
    englishButton.setButtonTintList(ColorStateList.valueOf(colorAccent));

    buttonApplyChanges.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        SettingsData.getInstance(getApplicationContext()).saveData(language, currency, theme);
        Data.getInstance(getApplicationContext()).reloadData();
        goToCartActivity();
      }
    });

    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {

        RadioButton languageSelect = findViewById(checkedId);

        if (languageSelect.getId() == spanishButton.getId())
          language = "es";
        else if (languageSelect.getId() == englishButton.getId())
          language = "en";
      }
    });

    buttonTheme.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        openDialogSelectTheme(theme);
      }
    });
  }

  // -----------------------------------------------------------------------------------------------

  private void setUpViews(){
    setUpAppBar();
    setUpSpinner();
    setUpButtons();
    updateViews();
  }

  // -----------------------------------------------------------------------------------------------

  private void updateViews() {

    language = SettingsData.getInstance(this).getLanguage();
    currency = SettingsData.getInstance(this).getCurrency();

    if (language.equals("es")) {
      spanishButton.setChecked(true);
    } else if (language.equals("en")) {
      englishButton.setChecked(true);
    }
  }

  // -----------------------------------------------------------------------------------------------

  private void openDialogSelectTheme(String themeName) {
    DialogSelectTheme dialog = new DialogSelectTheme(themeName);
    dialog.show(getSupportFragmentManager(), "dialogSelectTheme");
  }

  // -----------------------------------------------------------------------------------------------

  private void setLayoutTheme(){
    setUpAppBar();
    setUpButtons();
  }

  // -----------------------------------------------------------------------------------------------

  private void goToCartActivity(){
    Intent intent = new Intent(this, CartActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

  // -----------------------------------------------------------------------------------------------

}
