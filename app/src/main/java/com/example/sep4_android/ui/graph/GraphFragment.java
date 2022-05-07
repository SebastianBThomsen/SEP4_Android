package com.example.sep4_android.ui.graph;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sep4_android.R;
import com.example.sep4_android.databinding.FragmentGraphBinding;

public class GraphFragment extends Fragment implements View.OnClickListener {

    private GraphViewModelImpl mViewModel;

    private TextView tv_test;
    private Button btn_edit;

    private FragmentGraphBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(GraphViewModelImpl.class);
        binding = FragmentGraphBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding();
        tv_test.setText("Hej");
        return root;


    }
//Fixme; Vi skal finde ud af hvordan vi får ID på en binding
    @Override
    public void onClick(View view) {
        Log.i("ClickTestOnGraph", "onClick:- before if ");
if (binding.button.callOnClick())
    Log.i("ClickTestOnGraph", "onClick: - in if  ");
        tv_test.setText("Ole er sej");
}








    private void binding() {
        tv_test = binding.graphTv;
        btn_edit= binding.button;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}