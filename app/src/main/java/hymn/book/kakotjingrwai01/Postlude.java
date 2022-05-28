package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import hymn.book.kakotjingrwai01.databinding.ActivityPostludeBinding;
import hymn.book.kakotjingrwai01.databinding.ActivityYouthBinding;

public class Postlude extends AppCompatActivity {
    ActivityPostludeBinding postludeBinding;
    private FirebaseFirestore hymnDb = FirebaseFirestore.getInstance();
    private HymnAdapter adapter;
    private static ArrayList<HymnModel> hymn_numberArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postludeBinding = ActivityPostludeBinding.inflate(getLayoutInflater());
        setContentView(postludeBinding.getRoot());

        postludeBinding.postBackBtn.setOnClickListener(V->{
            onBackPressed();
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        postludeBinding.postRecyclerView.setLayoutManager(linearLayoutManager);


        hymnDb = FirebaseFirestore.getInstance();


        hymnDb.collection("Postlude").addSnapshotListener((value, error) -> {
            hymn_numberArrayList.clear();
            for (DocumentSnapshot snapshot: value) {
                hymn_numberArrayList.add(new HymnModel(
                        snapshot.getString("id"),
                        snapshot.getString("title"),
                        snapshot.getString("author"),
                        snapshot.getString("lyric")));
            }
            adapter = new HymnAdapter(hymn_numberArrayList,getApplicationContext());
            postludeBinding.postRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });
    }
}