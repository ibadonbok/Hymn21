package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import hymn.book.kakotjingrwai01.databinding.ActivityChildrenBinding;
import hymn.book.kakotjingrwai01.databinding.ActivityHymnNumberBinding;

public class Children extends AppCompatActivity {

    ActivityChildrenBinding childrenBinding;
    private FirebaseFirestore hymnDb = FirebaseFirestore.getInstance();
    private HymnAdapter adapter;
    private static ArrayList<HymnModel> hymn_numberArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        childrenBinding = ActivityChildrenBinding.inflate(getLayoutInflater());
        setContentView(childrenBinding.getRoot());

        childrenBinding.childrenBackBtn.setOnClickListener(V->{
            onBackPressed();
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        childrenBinding.childrenRecyclerView.setLayoutManager(linearLayoutManager);


        hymnDb = FirebaseFirestore.getInstance();


        hymnDb.collection("Children").addSnapshotListener((value, error) -> {
            hymn_numberArrayList.clear();
            for (DocumentSnapshot snapshot: value) {
                hymn_numberArrayList.add(new HymnModel(
                        snapshot.getString("id"),
                        snapshot.getString("title"),
                        snapshot.getString("author"),
                        snapshot.getString("lyric")));
            }
            adapter = new HymnAdapter(hymn_numberArrayList,getApplicationContext());
            childrenBinding.childrenRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });

    }
}