package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import hymn.book.kakotjingrwai01.databinding.ActivityFuneralBinding;

public class Funeral extends AppCompatActivity {

        ActivityFuneralBinding funeralBinding;
        FirebaseFirestore funeralDB = FirebaseFirestore.getInstance();
        private static ArrayList<HymnModel> hymnModelArrayList= new ArrayList<>();
        private HymnAdapter hymnAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        funeralBinding=ActivityFuneralBinding.inflate(getLayoutInflater());
        setContentView(funeralBinding.getRoot());

        funeralBinding.funeralBackBtn.setOnClickListener(V-> onBackPressed());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        funeralBinding.funeralRecyclerView.setLayoutManager(linearLayoutManager);

        funeralDB.collection("Funeral").addSnapshotListener((value,error)-> {
            hymnModelArrayList.clear();
            for(DocumentSnapshot snapshot: value) {

                hymnModelArrayList.add(new HymnModel(
                        snapshot.getString("id"),
                        snapshot.getString("title"),
                        snapshot.getString("author"),
                        snapshot.getString("lyric")));
            }
            hymnAdapter= new HymnAdapter(hymnModelArrayList,getApplicationContext());
            funeralBinding.funeralRecyclerView.setAdapter(hymnAdapter);
            hymnAdapter.notifyDataSetChanged();
        });
    }
}