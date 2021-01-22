package com.acculytixs.mobile.wayuparty.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.acculytixs.mobile.wayuparty.dtos.Datun;
import com.acculytixs.mobile.wayuparty.dtos.GetServiceCategoryList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class PickDateTimeDialogFragment extends DialogFragment {

    int position = 0; //default selected position
    List<String> datun;
    List<String> formatdata;
    String name;

    public PickDateTimeDialogFragment( List<String> datun,List<String> formatdata,String name) {
        this.position = position;
        this.datun = datun;
        this.formatdata = formatdata;
        this.name = name;
    }

    public interface SingleChoiceListener {
        void onPositiveButtonClicked(List<String> datun,List<String> formatdata, int position,String name);

        void onNegativeButtonClicked();
    }

    PickDateTimeDialogFragment.SingleChoiceListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (PickDateTimeDialogFragment.SingleChoiceListener) context;
        } catch (Exception e) {
            throw new ClassCastException(getActivity().toString() + " SingleChoiceListener must implemented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final String[] list = new String[datun.size()];
        ArrayList<String > list1 = new ArrayList<>();
        for(int i=0;i<datun.size();i++){
            list1.add(datun.get(i));
            list[i]= datun.get(i);


        }

        builder.setTitle("Select your Choice")
                .setSingleChoiceItems(list, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        position = i;
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onPositiveButtonClicked(datun,formatdata, position,name);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onNegativeButtonClicked();
                    }
                });

        return builder.create();
    }
}
