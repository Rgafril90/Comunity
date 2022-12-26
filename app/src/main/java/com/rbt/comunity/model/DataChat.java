package com.rbt.comunity.model;

import com.rbt.comunity.R;

import java.util.ArrayList;

public class DataChat {
    public static String [][] data = new String[][] {
            {String.valueOf(R.drawable.logobrt), "TPWS", "Senggol Dong"},
            {String.valueOf(R.drawable.logobrt), "TPWS", "Senggol Dong"},
            {String.valueOf(R.drawable.logobrt), "TPWS", "Senggol Dong"},
    };

    public static ArrayList<ModelChat> getData() {
        ArrayList<ModelChat> dataCard = new ArrayList<>();
        for (String[] varData : data) {
            ModelChat model = new ModelChat();
            model.setProfil(Integer.parseInt(varData[0]));
            model.setNama(varData[1]);
            model.setisichat(varData[2]);
            dataCard.add(model);
        }
        return dataCard;
    }
}
