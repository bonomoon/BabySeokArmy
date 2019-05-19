package kr.ac.jbnu.babyseokarmy.flipbabe.view.home;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import kr.ac.jbnu.babyseokarmy.flipbabe.R;
import kr.ac.jbnu.babyseokarmy.flipbabe.adapter.DiaryAdapter;
import kr.ac.jbnu.babyseokarmy.flipbabe.model.BabyTotal;
import kr.ac.jbnu.babyseokarmy.flipbabe.view.base.BaseFragment;

public class HomeDiaryFragment extends BaseFragment {

    private RecyclerView recyclerView;

    private FirestoreRecyclerAdapter<BabyTotal, DiaryViewHolder> adapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String USER = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public HomeDiaryFragment() {
        super(R.layout.fragment_home_diary);
    }

    public static HomeDiaryFragment newInstance() {
        HomeDiaryFragment fragment = new HomeDiaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onCreateView(LayoutInflater inflate, View v) {
        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        Query query = rootRef.collection("users").document(USER).collection("babyDiary")
                .orderBy("date", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<BabyTotal> options = new FirestoreRecyclerOptions.Builder<BabyTotal>()
                .setQuery(query, BabyTotal.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<BabyTotal, DiaryViewHolder>(options) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void onBindViewHolder(@NonNull DiaryViewHolder diaryViewHolder, int i, @NonNull BabyTotal babyTotal) {
                diaryViewHolder.date.setText(babyTotal.getDate());
                diaryViewHolder.pee.setText(babyTotal.getPeeTotal());
                diaryViewHolder.poo.setText(babyTotal.getPooTotal());g
                diaryViewHolder.flip.setText(babyTotal.getFlipTotal() + "회");
            }

            @NonNull
            @Override
            public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_item, parent, false);
                return new DiaryViewHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }

    public class DiaryViewHolder extends RecyclerView.ViewHolder {
        TextView date, pee, poo, flip;

        DiaryViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.date_tv);
            pee = view.findViewById(R.id.peecnt_tv);
            poo = view.findViewById(R.id.poocnt_tv);
            flip = view.findViewById(R.id.flipcnt_tv);
        }
    }
}
