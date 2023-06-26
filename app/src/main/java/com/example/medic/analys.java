package com.example.medic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class analys extends Fragment {

    RecyclerView viewNews, viewCatalog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.analys, container, false);
        viewNews = rootView.findViewById(R.id.viewNews);
        viewCatalog = rootView.findViewById(R.id.viewCatalog);

        viewNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        viewCatalog.setLayoutManager(new LinearLayoutManager(getContext()));

        getnews();
        getcatalog();
        return rootView;
    }

    private void getnews() {
        API_smart_lab apiSmartlab = API_smart_lab.retrofit.create(API_smart_lab.class);
        Call<List<news>> call = apiSmartlab.getNews();
        call.enqueue(new Callback<List<news>>() {
            @Override
            public void onResponse(Call<List<news>> call, Response<List<news>> response) {
                List<news> news = response.body();
                if (response.isSuccessful()) {
                    assert news != null;
                    new_adap adapter = new new_adap(getContext(), news);
                    viewNews.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<news>> call, Throwable t) {
            }
        });
    }
    private void getcatalog() {
        API_smart_lab apiSmartlab = API_smart_lab.retrofit.create(API_smart_lab.class);
        Call<List<catalog>> call = apiSmartlab.getCatalog();
        call.enqueue(new Callback<List<catalog>>() {
            @Override
            public void onResponse(Call<List<catalog>> call, Response<List<catalog>> response) {
                List<catalog> catalog = response.body();
                if (response.isSuccessful()) {
                    assert catalog != null;
                    catalog_adap adapter = new catalog_adap(getContext(), catalog);
                    viewCatalog.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<catalog>> call, Throwable t) {
            }
        });
    }
}