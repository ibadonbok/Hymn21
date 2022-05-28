package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

import hymn.book.kakotjingrwai01.databinding.ActivityChorusBinding;

public class Chorus extends AppCompatActivity {
    private static final String TAG = "Chorus";

    ActivityChorusBinding chorusBinding;
    FirebaseFirestore chorusDB=FirebaseFirestore.getInstance();
    private  static ArrayList<ChorusModel> chorusModelArrayList = new ArrayList<>();
    private ChorusAdapter chorusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        chorusBinding=ActivityChorusBinding.inflate(getLayoutInflater());
        setContentView(chorusBinding.getRoot());

        chorusBinding.chorusBackBtn.setOnClickListener(V->{onBackPressed();});

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        chorusBinding.chorusRecyclerView.setLayoutManager(linearLayoutManager);

        chorusDB.collection("Chorus").addSnapshotListener((value,error)-> {
            chorusModelArrayList.clear();
            for(DocumentSnapshot snapshot: value) {

                chorusModelArrayList.add(new ChorusModel(
                        snapshot.getString("id"),
                        snapshot.getString("lyric")));
            }
            chorusAdapter= new ChorusAdapter(chorusModelArrayList,getApplicationContext());
            chorusBinding.chorusRecyclerView.setAdapter(chorusAdapter);
            chorusAdapter.notifyDataSetChanged();
        });

        //To Search
        chorusBinding.chorusSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(TAG, "afterTextChanged: " + editable.toString());
                if(editable.toString().isEmpty())
                {
                    chorusDB.collection("Chorus").addSnapshotListener((value,error)-> {
                        chorusModelArrayList.clear();
                        for(DocumentSnapshot snapshot: value) {

                            chorusModelArrayList.add(new ChorusModel(
                                    snapshot.getString("id"),
                                    snapshot.getString("lyric")));
                        }
                        chorusAdapter= new ChorusAdapter(chorusModelArrayList,getApplicationContext());
                        chorusBinding.chorusRecyclerView.setAdapter(chorusAdapter);
                        chorusAdapter.notifyDataSetChanged();
                    });
                }
                else
                {
                    chorusDB.collection("Chorus")
                            .whereEqualTo("id",editable.toString())
                            .addSnapshotListener((value,error)-> {
                        chorusModelArrayList.clear();
                        for(DocumentSnapshot snapshot: value) {

                            chorusModelArrayList.add(new ChorusModel(
                                    snapshot.getString("id"),
                                    snapshot.getString("lyric")));
                        }
                                chorusAdapter= new ChorusAdapter(chorusModelArrayList,getApplicationContext());
                                chorusBinding.chorusRecyclerView.setAdapter(chorusAdapter);
                                chorusAdapter.notifyDataSetChanged();

                    });
                }


            }
        });

      /*  //To add Favourite
        public static void  addtoFavourite(Context context,String Id){
            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            FirebaseFirestore chorusDB=FirebaseFirestore.getInstance();
            long timestamp=System.currentTimeMillis();

            //setup data to add in firebase db of  current user for favourite hymn
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("ID" ,"" + Id);
            hashMap.put("timestamp","" + timestamp);

            //Save to dp

            chorusDB.collection("USERS").document("ID").collection("Favourite")
                    .addSnapshotListener((value,error)-> {
                        chorusModelArrayList.clear();
                        for(DocumentSnapshot snapshot: value) {

                            chorusModelArrayList.add(new ChorusModel(
                                    snapshot.getString("id"),
                                    snapshot.getString("lyric")));
                        }
                    });*/

    }
}