package com.kilobird.simplenestedscrolllayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TabFragment extends Fragment {
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private RecyclerView mRecyclerView;
    // private TextView mTextView;
    private List<String> mDatas = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // mTextView = (TextView) view.findViewById(R.id.id_info);
        // mTextView.setText(mTitle);
        for (int i = 0; i < 50; i++) {
            mDatas.add(mTitle + " -> " + i);
        }
        mRecyclerView.setAdapter(new SimpleStringAdapter(getActivity(), mDatas));

        return view;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public static TabFragment newInstance(String title) {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

}
