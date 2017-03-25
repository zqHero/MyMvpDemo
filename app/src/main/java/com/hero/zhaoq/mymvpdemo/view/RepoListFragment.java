package com.hero.zhaoq.mymvpdemo.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hero.zhaoq.mymvpdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主界面 的 Fragment   用于展示 获取到的数据
 */
public class RepoListFragment extends Fragment {

    public RepoListFragment() {}

    @Bind(R.id.fragment_repo_list_view)
    ListView listView;

//    private RepoListPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
