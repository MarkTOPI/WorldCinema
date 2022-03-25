package com.example.worldcinema.buttonBottomNavigation.main;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.worldcinema.R;
import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.MovieHandler;
import com.example.worldcinema.network.adapters.MovieAdapter;
import com.example.worldcinema.network.models.MovieResponse;
import com.example.worldcinema.network.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private ArrayList<MovieResponse> movieResponses;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private LinearLayoutManager linearLayoutManager;
    ApiService serviceMovie = MovieHandler.getInstance().getService();

    public MainFragment() {
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        getMovies();
        return view;
    }

    private void getMovies() {
        AsyncTask.execute(() -> {
            serviceMovie.getMovies().enqueue(new Callback<List<MovieResponse>>() {

                @Override
                public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {
                    if(response.isSuccessful()){
//                        Toast.makeText(getContext(), "Response!"+response.body(), Toast.LENGTH_SHORT).show();
                        movieResponses = new ArrayList<>(response.body());
                        movieAdapter = new MovieAdapter(movieResponses, getContext());
                        recyclerView.setAdapter(movieAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        movieAdapter.notifyDataSetChanged();
                    } else if (response.code() == 400) {
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Toast.makeText(getContext(), serverErrorMessage.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                    Toast.makeText(getContext(),t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}