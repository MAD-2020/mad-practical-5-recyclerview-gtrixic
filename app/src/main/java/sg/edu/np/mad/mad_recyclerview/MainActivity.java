package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String TAG = "To-Do List";
    ArrayList<String> data = new ArrayList<>();
    EditText editText;
    Button Add;
    ToDoAdapter tdAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.writeTask);
        Add = findViewById(R.id.addTaskButton);
        recyclerView = findViewById(R.id.recyclerView);

        data.add("Buy milk");
        data.add("Send postage");
        data.add("Buy Android development book");

        tdAdapter = new ToDoAdapter(data);
        LinearLayoutManager tdLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(tdLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tdAdapter);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask(v);
            }
        });

        tdAdapter.setOnItemClickListener(new ToDoAdapter.OnItemClickListener() {
            @Override
            public void ItemClick(int position) {
                deleteTask(position);
            }
        });

    }


    private void addTask(View v) {
        String newEntry = editText.getText().toString();
        data.add(newEntry);
        tdAdapter.notifyDataSetChanged();
        showNewEntry(recyclerView, data);
        editText.getText().clear();
    }

    private void deleteTask(final int position) {

        String delete = data.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete " + delete + "?").setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG, "Delete ");
                data.remove(position);
                tdAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG, "Do not delete.");
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Delete");
        alert.show();
    }



    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param data ArrayList that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }
}

