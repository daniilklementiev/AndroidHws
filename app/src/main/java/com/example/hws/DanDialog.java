package com.example.hws;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DanDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder ratingdialog = new AlertDialog.Builder(getActivity());

        ratingdialog.setIcon(android.R.drawable.btn_star_big_on);
        ratingdialog.setTitle("Нравится ли Вам это приложение?");

        // https://android.jlelse.eu/centering-views-in-android-layouts-547930621de7
        View ll = getActivity().getLayoutInflater().inflate(R.layout.my_item, null);
        ratingdialog.setView(ll);

        final RatingBar rating = ll.findViewById(R.id.rb1);

        ratingdialog.setPositiveButton("ДА!",
                        (dialog, which) -> Toast.makeText(getActivity(), rating.getRating() + "", Toast.LENGTH_SHORT).show())

                .setNegativeButton("НЕТ...",
                        (dialog, id) -> dialog.cancel());

        return ratingdialog.create();
    }
}