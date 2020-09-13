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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillIQLeadersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillIQLeadersFragment extends Fragment {
    private RecyclerView rcv;
    private List<LeadersHour> mylist;
    private LearningLeadersFragment.LeaderHourAdapter myAdapter;
    ProgressBar mprogressBar;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SkillIQLeadersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SkillIQLeadersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SkillIQLeadersFragment newInstance(String param1, String param2) {
        SkillIQLeadersFragment fragment = new SkillIQLeadersFragment();
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

        View view = inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);
        rcv = view.findViewById(R.id.rcv);
        //   mprogressBar = view.findViewById(R.id.progressBarRcv);

        init();
        return  view;
    }

    private void init() {
        Log.e("HomeFragment", "initCasaMas");
        mylist = new ArrayList<>();
        myAdapter = new LearningLeadersFragment.LeaderHourAdapter(getContext(), mylist,mprogressBar);
        rcv.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(mLayoutManager);
        rcv.setItemAnimator(new DefaultItemAnimator());
        rcv.setAdapter(myAdapter);
    }

    static class LeaderScoreAdapter extends RecyclerView.Adapter< SkillIQLeadersFragment.LeaderScoreAdapter.LeaderScoreViewHolder> {
        private Context context;
        private List<LeadersHour> mylList;
        ProgressBar mprogressBar;


        public class LeaderScoreViewHolder extends RecyclerView.ViewHolder {
            public TextView name,description;
            public ImageView photo;
            ProgressBar mprogressBar;
            int pos;


            public  LeaderScoreViewHolder(View view) {
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


        public  LeaderScoreAdapter(Context context, List<LeadersHour> mylList, ProgressBar progressBar) {
            this.context = context;
            this.mylList = mylList;
            this.mprogressBar = progressBar;
        }

        @Override
        public SkillIQLeadersFragment.LeaderScoreAdapter.LeaderScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_leaders_hour, parent, false);

            return new SkillIQLeadersFragment.LeaderScoreAdapter.LeaderScoreViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull SkillIQLeadersFragment.LeaderScoreAdapter.LeaderScoreViewHolder holder, int position) {

            LeadersHour item = mylList.get(position);
            //holder.photo.
            holder.name.setText(item.getName());
            String country=item.getCountry();
            String score=item.getHours();
            holder.description.setText(score+"learning hour ,"+country);

        }

        @Override
        public int getItemCount() {
            return mylList.size();
        }



    }
}