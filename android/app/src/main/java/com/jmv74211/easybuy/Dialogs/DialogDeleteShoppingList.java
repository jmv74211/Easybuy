package com.jmv74211.easybuy.Dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

import com.jmv74211.easybuy.R;

public class DialogDeleteShoppingList extends AppCompatDialogFragment {

    private DialogDeleteShoppingListListener listener;

    private int position;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public DialogDeleteShoppingList(){

    }


    @SuppressLint("ValidFragment")
    public DialogDeleteShoppingList(int position) {
        this.position = position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getResources().getText(R.string.deleteShoppingList)).
                setMessage(getResources().getText(R.string.confirmDeleteShoppingList)).
                setPositiveButton(getResources().getText(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getActivity(),"Se ha pulsado SI", Toast.LENGTH_SHORT).show();
                        listener.onClickYes(position);
                    }
                }).setNegativeButton(getResources().getText(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(getActivity(),"Se ha pulsado NO", Toast.LENGTH_SHORT).show();
                listener.onClickNo();
            }
        });

        return builder.create();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public interface DialogDeleteShoppingListListener {
        void onClickYes(int position);

        void onClickNo();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogDeleteShoppingListListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement  DialogDeleteShoppingList");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}
