package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import hymn.book.kakotjingrwai01.databinding.ActivityWeddingBinding;

public class Wedding extends AppCompatActivity {

    ActivityWeddingBinding weddingBinding;
    FirebaseFirestore wedDB = FirebaseFirestore.getInstance();
    private static ArrayList<HymnModel> hymnModelArrayList= new ArrayList<>();
    private HymnAdapter hymnAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weddingBinding=ActivityWeddingBinding.inflate(getLayoutInflater());
        setContentView(weddingBinding.getRoot());

        weddingBinding.weddingBackBtn.setOnClickListener(V-> onBackPressed());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        weddingBinding.weddingRecyclerView.setLayoutManager(linearLayoutManager);

        wedDB.collection("Wedding").addSnapshotListener((value,error)-> {
            hymnModelArrayList.clear();
            for(DocumentSnapshot snapshot: value) {

                hymnModelArrayList.add(new HymnModel(
                        snapshot.getString("id"),
                        snapshot.getString("title"),
                        snapshot.getString("author"),
                        snapshot.getString("lyric")));
            }
            hymnAdapter= new HymnAdapter(hymnModelArrayList,getApplicationContext());
            weddingBinding.weddingRecyclerView.setAdapter(hymnAdapter);
            hymnAdapter.notifyDataSetChanged();
        });


    }
}