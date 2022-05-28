package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import hymn.book.kakotjingrwai01.databinding.ActivityDisplayChorusBinding;

public class DisplayChorus extends AppCompatActivity {
    private ActivityDisplayChorusBinding displayChorusBinding;
    private ArrayList<ChorusModel> chorusModelArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        displayChorusBinding=ActivityDisplayChorusBinding.inflate(getLayoutInflater());
        setContentView(displayChorusBinding.getRoot());

        displayChorusBinding.chorusBackBtn.setOnClickListener(V->{
            onBackPressed();
            finish();
        });

        displayChorusBinding.chorusNumber.setText(getIntent().getStringExtra("ID"));

        String lyric=getIntent().getStringExtra("LYRIC");
        lyric=lyric.replace("_b","\n");
        displayChorusBinding.choruslyric.setText(lyric);
    }
}