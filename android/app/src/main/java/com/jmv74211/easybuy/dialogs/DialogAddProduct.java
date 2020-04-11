package com.jmv74211.easybuy.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.jmv74211.easybuy.R;

public class DialogAddProduct extends AppCompatDialogFragment {

  private DialogAddProductListener listener;
  private TextView textView, quantityView;
  private Button addButton, deleteButton;
  private int productId;

  // -----------------------------------------------------------------------------------------------

  public DialogAddProduct() {

  }

  // -----------------------------------------------------------------------------------------------

  @SuppressLint("ValidFragment")
  public DialogAddProduct(int productId) {
    this.productId = productId;
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.dialog_add_product, null);

    builder.setView(view).setTitle(getResources().getText(R.string.addProducts))
        .setPositiveButton(getResources().getText(R.string.accept), new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int i) {
            listener.applyData(productId, Integer.valueOf(quantityView.getText().toString()));
          }
        })
        .setNegativeButton(getResources().getText(R.string.cancel), new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int i) {
          }
        });

    textView = view.findViewById(R.id.text);
    quantityView = view.findViewById(R.id.quantity);
    addButton = view.findViewById(R.id.addProduct);
    deleteButton = view.findViewById(R.id.subtractionProduct);

    addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int userSelection = Integer.valueOf(quantityView.getText().toString());

        if (userSelection < 50)
          userSelection += 1;

        quantityView.setText(String.valueOf(userSelection));
      }
    });

    deleteButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int userSelection = Integer.valueOf(quantityView.getText().toString());

        if (userSelection > 1)
          userSelection -= 1;

        quantityView.setText(String.valueOf(userSelection));
      }
    });


    return builder.create();
  }

  // -----------------------------------------------------------------------------------------------

  public interface DialogAddProductListener {
    void applyData(int productId, int quantity);
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    try {
      listener = (DialogAddProductListener) context;
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString() + " must implement DialogAddProductListener");
    }
  }

  // -----------------------------------------------------------------------------------------------

}
