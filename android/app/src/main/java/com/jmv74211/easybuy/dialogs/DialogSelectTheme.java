package com.jmv74211.easybuy.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.jmv74211.easybuy.R;
import com.jmv74211.easybuy.data.ThemeData;

import java.util.ArrayList;

public class DialogSelectTheme extends AppCompatDialogFragment {

  private DialogSelectThemeListener listener;
  private ArrayList<Button> themeButtons;
  private String themeNameSelected;

  // -----------------------------------------------------------------------------------------------

  public DialogSelectTheme() {
    themeButtons = new ArrayList<>();
  }

  // -----------------------------------------------------------------------------------------------

  @SuppressLint("ValidFragment")
  public DialogSelectTheme(String themeName) {
    this.themeNameSelected = themeName;
    this.themeButtons = new ArrayList<>();
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.dialog_change_theme, null);

    builder.setView(view).setTitle(getResources().getText(R.string.chooseTheme))
        .setPositiveButton(getResources().getText(R.string.accept), new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int i) {
            listener.applyData(themeNameSelected);
          }
        })
        .setNegativeButton(getResources().getText(R.string.cancel), new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int i) {
          }
        });

    loadThemeView(view);

    return builder.create();
  }

  // -----------------------------------------------------------------------------------------------


  private void loadThemeView(View view){
    for(int i = 0; i < ThemeData.getInstance(getContext()).getThemes().size(); i++){
      int layoutId = getResources().getIdentifier("b" + i, "id", getContext().getPackageName());
      setUpThemeButton(view, layoutId, i);
    }
  }

  // -----------------------------------------------------------------------------------------------

  private void setUpThemeButton(final View view, int layoutId, final int position){
    final Button button = (Button) view.findViewById(layoutId);

    button.setBackgroundColor(Color.parseColor(
            ThemeData.getInstance(getContext()).getThemes().get(position).getColorPrimary()));

    addBorderButton(button, ThemeData.getInstance(getContext()).getThemes().get(position).getColorPrimary(),
           ThemeData.getInstance(getContext()).getThemes().get(position).getColorPrimaryDark(),
           ThemeData.getInstance(getContext()).getThemeName(position));

    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View viewButton) {
        // Set new theme
        themeNameSelected = ThemeData.getInstance(getContext()).getThemeName(position);

        addBorderButton(button, ThemeData.getInstance(getContext()).getThemes().get(position).getColorPrimary(),
                ThemeData.getInstance(getContext()).getThemes().get(position).getColorPrimaryDark(),
                themeNameSelected);

        loadThemeView(view);

        Toast.makeText(getContext(), ThemeData.getInstance(getContext()).getThemeName(position),
                Toast.LENGTH_SHORT).show();
      }
    });

    themeButtons.add(button);
  }

  // -----------------------------------------------------------------------------------------------

  private void addBorderButton(Button button, String colorPrimary, String colorDark, String theme){
    GradientDrawable border = new GradientDrawable();
    border.setColor(Color.parseColor(colorPrimary));
    border.setStroke(10, (Color.parseColor(colorDark)));
    if(theme.equals(themeNameSelected)) {
      border.setStroke(20, Color.parseColor("#000000"));
    }
    else {
      border.setStroke(10, (Color.parseColor(colorDark)));
    }

    button.setBackground(border);
  }


  // -----------------------------------------------------------------------------------------------

  public interface DialogSelectThemeListener {
    void applyData(String themeName);
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    try {
      listener = (DialogSelectThemeListener) context;
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString() + " must implement DialogSelectThemeListener");
    }
  }

  // -----------------------------------------------------------------------------------------------

}
