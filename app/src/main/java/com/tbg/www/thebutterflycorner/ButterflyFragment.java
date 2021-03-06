package com.tbg.www.thebutterflycorner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class ButterflyFragment extends DialogFragment {

    GridView gridView;
    ArrayList<String> gridViewString;
    ArrayList<Integer>gridViewImageId;
    GameActivity gameActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_images,null);
        gameActivity=(GameActivity)getActivity();
        gridView =rootView.findViewById(R.id.gridView);
        getDialog().setTitle("Choose a matching image");

    gridViewString = new ArrayList<>(Arrays.asList("acraea violae","cethosia cyane","danaus chrysippus","graphium agamemnon","hypolimnas bolina","pachliopta aristolochiae","papilio demoleus","papilio memnon","parthenos slyvia","lemon pansy","dryas iulia","papilio polytes"
        ));


        gridViewImageId = new ArrayList<>(Arrays.asList( R.drawable.av1,R.drawable.cc1, R.drawable.dc1, R.drawable.ga1, R.drawable.hb1, R.drawable.pa1, R.drawable.pd1, R.drawable.pm1, R.drawable.ps1, R.drawable.lp1, R.drawable.di1, R.drawable.pp1));
        DialogGridAdapter dialogGridAdapter = new DialogGridAdapter(getContext(),gridViewString,gridViewImageId);
        gridView.setAdapter(dialogGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(),gridViewString.get(position),Toast.LENGTH_SHORT).show();
                Bundle args = getArguments();
                String gameActivityRes = args.getString("name","name");
                String removeQuotes =gameActivityRes.replaceAll("^\"+|\"+$", "");
                String gridViewStrinRes = gridViewString.get(position);
                //gameActivity.getAllImagePaths().get(position).setImage(gridViewImageId.get(position));

                if(gridViewStrinRes.equals(removeQuotes)){
                    gameActivity.updateResult("CORRECT",gridViewImageId.get(position));
                    Toast.makeText(getContext(), "Correct match.You are doing great, keep going...", Toast.LENGTH_SHORT).show();
                    //gameActivity.getAllImagePaths().get(position).setStatus("CORRECT");
                    gameActivity.score++;
                }else{
                    gameActivity.updateResult("WRONG",gridViewImageId.get(position));
                    Toast.makeText(getContext(), "Wrong match, You need to improve...", Toast.LENGTH_SHORT).show();
                }
                gameActivity.counter++;
                ((GameActivity) getActivity()).update();
                getFragmentManager().popBackStackImmediate();


            }
        });
        return rootView;


    }


}
