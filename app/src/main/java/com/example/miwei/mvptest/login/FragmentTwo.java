package com.example.miwei.mvptest.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.miwei.mvptest.R;
import com.example.miwei.mvptest.common.view.ScrollableHelper;

public class FragmentTwo extends Fragment implements ScrollableHelper.ScrollableContainer{

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two, container, false);
        listView = view.findViewById(R.id.listview_two);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] strDatas = new String[50];
        for(int i = 0;i<50;i++) {

            strDatas[i] = String.valueOf(i);

        }
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, strDatas));

    }

    @Override
    public View getScrollableView() {
        return listView;
    }
}
