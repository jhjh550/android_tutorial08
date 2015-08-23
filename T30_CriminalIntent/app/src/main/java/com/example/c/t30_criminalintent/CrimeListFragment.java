package com.example.c.t30_criminalintent;


import android.os.Bundle;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;

/**
 * Created by c on 2015-08-23.
 */
public class CrimeListFragment extends ListFragment{
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("범죄 리스트");
        mCrimes = CrimeLab.get().getCriems();
    }
}
