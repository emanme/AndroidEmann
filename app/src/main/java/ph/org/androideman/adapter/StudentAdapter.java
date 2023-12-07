package ph.org.androideman.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ph.org.androideman.R;
import ph.org.androideman.helper.DatabaseHelper;
import ph.org.androideman.helper.HashConverter;
import ph.org.androideman.model.Student;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>  {

    private List<Student> studentList;
    private Context context;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Student student);
    }

    public StudentAdapter(Context context, OnItemClickListener itemClickListener) {
        this.context = context;
        this.studentList = getAllStudents();
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView, nameTextView, courseTextView;
        ImageView avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.idTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            courseTextView = itemView.findViewById(R.id.courseTextView);
            avatar = itemView.findViewById(R.id.imageViewAvatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Student student = studentList.get(position);
                        itemClickListener.onItemClick(student);
                    }
                }
            });
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
        getAvatarResource(student.getId(), holder.avatar);

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    private List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_STUDENTS,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                students.add(new Student(cursor));
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return students;
    }
    public static void getAvatarResource(String studentId, ImageView avatar) {
        int id = Integer.parseInt(studentId);
        String hash = HashConverter.sha256(id+"");
        String imageUrl = "https://robohash.org/"+hash+"?set=set5&bgset=&size=100x100";

        // Use Picasso to load the image into the ImageView
        Picasso.get().load(imageUrl).into(avatar);
    }
}

