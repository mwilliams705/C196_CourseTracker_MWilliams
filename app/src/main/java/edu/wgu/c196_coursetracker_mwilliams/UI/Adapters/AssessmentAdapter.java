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

import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentEntity;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.AssessmentActivities.AssessmentDetailActivity;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
    private final LayoutInflater inflater;
    private final Context context;
    private List<AssessmentEntity> assessments = new ArrayList<>();

    public AssessmentAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = inflater.inflate(R.layout.assessment_list_item,parent,false);
       return new AssessmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        if (assessments!=null){
            final AssessmentEntity current = assessments.get(position);
            holder.assessmentTitle.setText(current.getAssessment_title());
            holder.assessmentDate.setText(current.getAssessment_date());
            if (current.isOA()){
                holder.assessmentType.setText("OA");
            }else holder.assessmentType.setText("PA");
        }
    }

    public void setAssessments(List<AssessmentEntity> assessments){
        this.assessments = assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(assessments!=null){
            return assessments.size();
        }
        else return 0;
    }



    class AssessmentViewHolder extends RecyclerView.ViewHolder{

        private final TextView assessmentTitle;
        private final TextView assessmentType;
        private final TextView assessmentDate;

        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assessmentTitle = itemView.findViewById(R.id.instructorName);
            assessmentDate = itemView.findViewById(R.id.instructorPhoneListItemTextView);
            assessmentType = itemView.findViewById(R.id.instructorEmailListItemTextView);
            itemView.setOnClickListener(v->{
                int position = getAdapterPosition();
                final AssessmentEntity current = assessments.get(position);
                Intent intent = new Intent(context, AssessmentDetailActivity.class);
                intent.putExtra("assessmentID",current.getId());
                context.startActivity(intent);
            });
        }


    }
}
