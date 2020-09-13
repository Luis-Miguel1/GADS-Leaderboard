package com.example.gadsleaderboard.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gadsleaderboard.Api;
import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.model.LeadersHour;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LearningLeadersFragment extends Fragment {
    private RecyclerView rcv;
    private String BASE_URL = "https://gadsapi.herokuapp.com";
    private List<LeadersHour> mylist;
    private LeaderHourAdapter myAdapter;
    ProgressBar mprogressBar;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public LearningLeadersFragment() {
        // Required empty public constructor
    }


    public static LearningLeadersFragment newInstance(String param1, String param2) {
        LearningLeadersFragment fragment = new LearningLeadersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_leanning_leaders, container, false);
        rcv = view.findViewById(R.id.rcv);
        mprogressBar = view.findViewById(R.id.progress);

        init();
        return view;
    }

    private void init() {
        Log.e("LeaningLeaderFragment", "init");
        mprogressBar.setVisibility(View.VISIBLE);
        mylist = new ArrayList<>();
        myAdapter = new LeaderHourAdapter(getContext(), mylist);
        rcv.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(mLayoutManager);
        rcv.setItemAnimator(new DefaultItemAnimator());
        rcv.setAdapter(myAdapter);
        request();
    }

    private void request() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        api.listLeadersHour().enqueue(new Callback<List<LeadersHour>>() {
            @Override
            public void onResponse(Call<List<LeadersHour>> call, Response<List<LeadersHour>> response) {
                if (response.isSuccessful()) {
                    List<LeadersHour> list = response.body();
                    if (list != null) {
                        myAdapter.mylList.clear();
                        myAdapter.mylList.addAll(list);
                        myAdapter.notifyDataSetChanged();
                        hideRemoveProgressBar();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<LeadersHour>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_LONG).show();
                Log.e("teste", "erro" + t.toString());
                hideRemoveProgressBar();
            }
        });
    }

    static class LeaderHourAdapter extends RecyclerView.Adapter<LeaderHourAdapter.LeaderHourViewHolder> {
        private Context context;
        private List<LeadersHour> mylList;



        public class LeaderHourViewHolder extends RecyclerView.ViewHolder {
            public TextView name, description;
            public ImageView photo;




            public LeaderHourViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.name);
                description = view.findViewById(R.id.description);
                photo = view.findViewById(R.id.photo);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });


            }


        }


        public LeaderHourAdapter(Context context, List<LeadersHour> mylList) {
            this.context = context;
            this.mylList = mylList;

        }

        @Override
        public LeaderHourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_leaders_hour, parent, false);

            return new LeaderHourViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull LeaderHourViewHolder holder, int position) {

            LeadersHour item = mylList.get(position);
            //holder.photo.
            holder.name.setText(item.getName());
            String country = item.getCountry();
            String hour = item.getHours();
            holder.description.setText(hour + "" + "learning hour ," + country);

        }

        @Override
        public int getItemCount() {
            return mylList.size();
        }


    }
    private void hideRemoveProgressBar() {
        if (mprogressBar.isShown()) {
            mprogressBar.setVisibility(View.GONE);
        }

    }
}