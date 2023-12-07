package ph.org.androideman.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import ph.org.androideman.R;
import ph.org.androideman.model.Student;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView, nameTextView, courseTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.idTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            courseTextView = itemView.findViewById(R.id.courseTextView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.idTextView.setText("ID: " + student.getId());
        holder.nameTextView.setText("Name: " + student.getName());
        holder.courseTextView.setText("Course: " + student.getCourse());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
