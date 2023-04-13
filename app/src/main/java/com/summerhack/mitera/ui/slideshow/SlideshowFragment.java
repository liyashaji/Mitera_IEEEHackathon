package com.summerhack.mitera.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.summerhack.mitera.Adapters.SelfAdapter;
import com.summerhack.mitera.Model.SC;
import com.summerhack.mitera.databinding.FragmentSlideshowBinding;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private List<SC> items;
    SelfAdapter selfAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        items = new ArrayList<>();
        items.add(new SC("Meditation"," Meditation is a practice that involves focusing the mind and calming the body to reduce stress and promote relaxation. It can help improve mental clarity, reduce anxiety, and enhance overall well-being."));
        items.add(new SC("Exercise Routine","Regular exercise is a physical activity that promotes physical fitness and improves mental health. It can include activities such as walking, jogging, swimming, or yoga, and can help reduce stress, improve mood, and boost energy levels."));
        selfAdapter = new SelfAdapter(items,getContext());
        binding.rvselfcare.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvselfcare.setAdapter(selfAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}