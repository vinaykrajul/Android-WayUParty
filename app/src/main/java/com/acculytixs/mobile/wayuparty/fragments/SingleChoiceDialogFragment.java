package com.acculytixs.mobile.wayuparty.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.dtos.GetServiceCategoryList;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class SingleChoiceDialogFragment extends DialogFragment  {

    int position = 0; //default selected position
    GetServiceCategoryList getServiceCategoryList;

    public SingleChoiceDialogFragment(GetServiceCategoryList getServiceCategoryList) {
        this.position = position;
        this.getServiceCategoryList = getServiceCategoryList;
    }

    public interface SingleChoiceListener {
        void onPositiveButtonClicked(GetServiceCategoryList getServiceCategoryList, int position);

        void onNegativeButtonClicked();
    }

    SingleChoiceListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (SingleChoiceListener) context;
        } catch (Exception e) {
            throw new ClassCastException(getActivity().toString() + " SingleChoiceListener must implemented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final String[] list = new String[getServiceCategoryList.getData().size()];
        ArrayList<String > list1 = new ArrayList<>();
        for(int i=0;i<getServiceCategoryList.getData().size();i++){
            list1.add(getServiceCategoryList.getData().get(i).getCategoryName());
            list[i]= getServiceCategoryList.getData().get(i).getCategoryName();


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
                        mListener.onPositiveButtonClicked(getServiceCategoryList, position);
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
