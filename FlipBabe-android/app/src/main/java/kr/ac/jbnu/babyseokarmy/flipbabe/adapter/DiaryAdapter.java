package kr.ac.jbnu.babyseokarmy.flipbabe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.ac.jbnu.babyseokarmy.flipbabe.R;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder> {

    private List<String> list;

    public DiaryAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {
        String text = list.get(position);
        //holder.
        //holder.
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_item, parent, false);
        return new DiaryViewHolder(view);
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
