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

import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.model.LeadersHour;

import java.util.ArrayList;
import java.util.List;


public class LearningLeadersFragment extends Fragment {
    private RecyclerView rcv;
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
     //   mprogressBar = view.findViewById(R.id.progressBarRcv);

        init();
        return  view;
    }

    private void init() {
        Log.e("HomeFragment", "initCasaMas");
        mylist = new ArrayList<>();
        myAdapter = new LeaderHourAdapter(getContext(), mylist,mprogressBar);
        rcv.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(mLayoutManager);
        rcv.setItemAnimator(new DefaultItemAnimator());
        rcv.setAdapter(myAdapter);
    }

    static class LeaderHourAdapter extends RecyclerView.Adapter< LeaderHourAdapter.LeaderHourViewHolder> {
        private Context context;
        private List<LeadersHour> mylList;
        ProgressBar mprogressBar;


        public class LeaderHourViewHolder extends RecyclerView.ViewHolder {
            public TextView name,description;
            public ImageView photo;
            ProgressBar mprogressBar;
            int pos;


            public  LeaderHourViewHolder(View view) {
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


        public  LeaderHourAdapter(Context context, List<LeadersHour> mylList, ProgressBar progressBar) {
            this.context = context;
            this.mylList = mylList;
            this.mprogressBar = progressBar;
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
            String country=item.getCountry();
            String hour=item.getHours();
            holder.description.setText(hour+"learning hour ,"+country);

        }

        @Override
        public int getItemCount() {
            return mylList.size();
        }



    }
}