package edu.wgu.c196_coursetracker_mwilliams.UI.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermEntity;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermDetailActivity;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {


    private final LayoutInflater inflater;
    private final Context context;
    private List<TermEntity> terms = new ArrayList<>();

    public TermAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.term_list_item,parent,false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {

        if (terms != null){
            final TermEntity current = terms.get(position);
            holder.termTitle.setText(current.getTerm_title());
            holder.termStart.setText(current.getTerm_start());
            holder.termEnd.setText(current.getTerm_end());

        }

    }

    @Override
    public int getItemCount() {
        if (terms != null){
            return terms.size();
        }
        else return 0;
    }

    public void setTerms(List<TermEntity> terms){
        this.terms = terms;
        notifyDataSetChanged();
    }


    public TermEntity getTermAtPosition(int position){
        return terms.get(position);
    }


    class TermViewHolder extends RecyclerView.ViewHolder{

        private final TextView termTitle;
        private final TextView termStart;
        private final TextView termEnd;


        public TermViewHolder(@NonNull View itemView) {
            super(itemView);
            termTitle = itemView.findViewById(R.id.instructorName);
            termStart  = itemView.findViewById(R.id.instructorPhoneListItemTextView);
            termEnd = itemView.findViewById(R.id.instructorEmailListItemTextView);
            itemView.setOnClickListener( v -> {
                 int position = getAdapterPosition();
                 final TermEntity current = terms.get(position);
                Intent intent = new Intent(context, TermDetailActivity.class);

                intent.putExtra("termTitle",current.getTerm_title());
                intent.putExtra("termStart",current.getTerm_start());
                intent.putExtra("termEnd",current.getTerm_end());
                intent.putExtra("termID",current.getTerm_id());
                context.startActivity(intent);

            });
        }
    }

}
