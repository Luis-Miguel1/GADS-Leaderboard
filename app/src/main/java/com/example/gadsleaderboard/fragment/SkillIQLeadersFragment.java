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
import com.example.gadsleaderboard.model.LeadersScore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillIQLeadersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillIQLeadersFragment extends Fragment {
    private RecyclerView rcv;
    private List<LeadersScore> mylist;
    private SkillIQLeadersFragment.LeaderScoreAdapter myAdapter;
    ProgressBar mprogressBar;
    private String BASE_URL = "https://gadsapi.herokuapp.com";




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
        mprogressBar = view.findViewById(R.id.progress);

        init();

        return  view;
    }
    private void request() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        api.listLeaderSkilliq().enqueue(new Callback<List<LeadersScore>>() {
            @Override
            public void onResponse(Call<List<LeadersScore>> call, Response<List<LeadersScore>> response) {
                if(response.isSuccessful()){
                    List<LeadersScore> list= response.body();
                    if(list!=null){
                        myAdapter.mylList.clear();
                        myAdapter.mylList.addAll(list);
                        myAdapter.notifyDataSetChanged();
                        hideRemoveProgressBar();


                    }
                }
            }

            @Override
            public void onFailure(Call<List<LeadersScore>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_LONG).show();
                Log.e("teste", "erro"+t.toString());
                hideRemoveProgressBar();
            }
        });
    }

    private void init() {
        mprogressBar.setVisibility(View.VISIBLE);
        mylist = new ArrayList<>();
        myAdapter = new SkillIQLeadersFragment.LeaderScoreAdapter(getContext(), mylist);
        rcv.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(mLayoutManager);
        rcv.setItemAnimator(new DefaultItemAnimator());
        rcv.setAdapter(myAdapter);
        request();
    }

    static class LeaderScoreAdapter extends RecyclerView.Adapter< SkillIQLeadersFragment.LeaderScoreAdapter.LeaderScoreViewHolder> {
        private Context context;
        private List<LeadersScore> mylList;


        public class LeaderScoreViewHolder extends RecyclerView.ViewHolder {
            public TextView name,score,skillCountry;
            public ImageView photo;
            ProgressBar mprogressBar;
            int pos;


            public  LeaderScoreViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.skillName);
                score = view.findViewById(R.id.skillScore);
                skillCountry = view.findViewById(R.id.skillCountry);
                photo = view.findViewById(R.id.skillImg2);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });


            }


        }


        public  LeaderScoreAdapter(Context context, List<LeadersScore> mylList) {
            this.context = context;
            this.mylList = mylList;

        }

        @Override
        public SkillIQLeadersFragment.LeaderScoreAdapter.LeaderScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_leaders_skill, parent, false);

            return new SkillIQLeadersFragment.LeaderScoreAdapter.LeaderScoreViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull SkillIQLeadersFragment.LeaderScoreAdapter.LeaderScoreViewHolder holder, int position) {

            LeadersScore item = mylList.get(position);
            holder.name.setText(item.getName());
            holder.skillCountry.setText(item.getCountry());
            holder.score.setText(item.getScore()+ " Skill IQ Score");
            Picasso.get().load(item.getBadgeUrl()).into(holder.photo);


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