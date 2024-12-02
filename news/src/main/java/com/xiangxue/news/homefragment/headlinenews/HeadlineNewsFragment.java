package com.xiangxue.news.homefragment.headlinenews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;
import com.xiangxue.news.R;
import com.xiangxue.news.databinding.FragmentHomeBinding;

import java.util.List;

public class HeadlineNewsFragment extends Fragment {
    public HeadlineNewsFragmentAdapter adapter;
    FragmentHomeBinding viewDataBinding;
    HeadlineNewsViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        adapter = new HeadlineNewsFragmentAdapter(getChildFragmentManager());
        viewDataBinding.tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewDataBinding.viewpager.setAdapter(adapter);
        viewDataBinding.tablayout.setupWithViewPager(viewDataBinding.viewpager);
        viewDataBinding.viewpager.setOffscreenPageLimit(1);
        viewModel = new ViewModelProvider(getActivity())
                .get(HeadlineNewsViewModel.class);
        viewModel.dataList.observe(this, new Observer<List<ChannelsModel.Channel>>() {
            @Override
            public void onChanged(List<ChannelsModel.Channel> channels) {
                adapter.setChannels(channels);
                viewDataBinding.viewpager.getAdapter().notifyDataSetChanged();
            }
        });

        return viewDataBinding.getRoot();
    }

}
