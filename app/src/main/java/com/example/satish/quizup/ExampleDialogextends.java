package com.example.satish.quizup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class ExampleDialogextends extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder bulider = new AlertDialog.Builder(getActivity());
        bulider.setTitle("Message")
                .setMessage("You have Successfully Signed out")
                .setPositiveButton("Signin Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface Dialog, int i) {

                        // Closing Dialog screen
                        getActivity().finish();
                    }
                });
        return bulider.create();

    }

}
