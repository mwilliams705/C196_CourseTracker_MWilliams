package edu.wgu.c196_coursetracker_mwilliams.UI.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorEntity;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.InstructorActivities.InstructorDetailActivity;

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.InstructorViewHolder>{
    private final LayoutInflater inflater;
    private final Context context;
    private List<InstructorEntity> instructors = new ArrayList<>();

    public InstructorAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public InstructorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.instructor_list_item,parent,false);
        return new InstructorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructorViewHolder holder, int position) {
            if (instructors!=null) {
                final InstructorEntity current = instructors.get(position);
                holder.instructorName.setText(current.getInstructor_name());
                holder.instructorPhone.setText(current.getInstructor_phone());
                holder.instructorEmail.setText(current.getInstructor_email());
            }
    }

    @Override
    public int getItemCount() {
        if (instructors != null){
            return instructors.size();
        } else return 0;
    }

    public void setInstructors(List<InstructorEntity> instructors) {
        this.instructors = instructors;
        notifyDataSetChanged();
    }

    class InstructorViewHolder extends RecyclerView.ViewHolder{

        private final TextView instructorName;
        private final TextView instructorPhone;
        private final TextView instructorEmail;

        public InstructorViewHolder(@NonNull View itemView) {
            super(itemView);
            instructorName = itemView.findViewById(R.id.instructorName);
            instructorPhone = itemView.findViewById(R.id.instructorPhoneListItemTextView);
            instructorEmail = itemView.findViewById(R.id.instructorEmailListItemTextView);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final InstructorEntity current = instructors.get(position);
                Intent intent = new Intent(context, InstructorDetailActivity.class);
                intent.putExtra("instructorID",current.getId());
                context.startActivity(intent);
            });
        }
    }

}
