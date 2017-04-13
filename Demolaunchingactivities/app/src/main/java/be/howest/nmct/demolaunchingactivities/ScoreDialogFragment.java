package be.howest.nmct.demolaunchingactivities;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by downe on 11/03/2017.
 */

public class ScoreDialogFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //get layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.dialog,null);
        final View resultView = inflater.inflate(R.layout.activity_main,null);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("NMCT scores")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText val = (EditText) dialogView.findViewById(R.id.score);
                        Snackbar snackbar = Snackbar
                                .make(resultView.findViewById(R.id.activity_main), "Jou score: " + val.getText(), Snackbar.LENGTH_LONG);

                        snackbar.show();

                    }
                })
                .setView(dialogView)
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelle d the dialog
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
