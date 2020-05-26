package sg.edu.np.mad.mad_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoViewHolder> {

    ArrayList<String> data;
    private OnItemClickListener onItemClickListener;

    public ToDoAdapter(ArrayList<String> input) {
        data = input;
    }

    public void setOnItemClickListener(OnItemClickListener onItem) {
        this.onItemClickListener = onItem;
    }

    public interface OnItemClickListener{
        void ItemClick(int position);
    }

    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ToDoViewHolder(item, onItemClickListener);

    }

    public void onBindViewHolder(ToDoViewHolder holder, int position) {
        String s = data.get(position);
        holder.txt.setText(s);
    }

    public int getItemCount() {
        return data.size();
    }
}
