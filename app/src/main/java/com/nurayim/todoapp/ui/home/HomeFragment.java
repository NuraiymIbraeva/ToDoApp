package com.nurayim.todoapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nurayim.todoapp.R;
import com.nurayim.todoapp.models.Task;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    TodoAdapter todoAdapter;
    RecyclerView recyclerView;
    ArrayList<Task> list = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        new ViewModelProvider(this).get(HomeViewModel.class);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setFragmentListener();
        todoAdapter = new TodoAdapter();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(todoAdapter);
        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskFragment();

            }
        });

    }

    private void setFragmentListener() {
        Toast.makeText(requireContext(), "HomeStart1", Toast.LENGTH_SHORT).show();
        getParentFragmentManager().setFragmentResultListener("rk_form", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String text = result.getString("text");
                Toast.makeText(requireContext(), "HomeStart2", Toast.LENGTH_SHORT).show();
                Log.e("Home", "text" + text);

                list.add(new Task(text));

                todoAdapter.addText(list);
            }
        });
    }

    private void openTaskFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.taskFragment);
    }
}