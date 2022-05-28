package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import hymn.book.kakotjingrwai01.databinding.ActivityDisplayHymnBinding;

public class DisplayHymn extends AppCompatActivity {

    private ActivityDisplayHymnBinding displayHymnBinding;
    private ArrayList<HymnModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayHymnBinding = ActivityDisplayHymnBinding.inflate(getLayoutInflater());
        setContentView(displayHymnBinding.getRoot());

        displayHymnBinding.HBackBtn.setOnClickListener(V->{
            onBackPressed();
            finish();
        });

      displayHymnBinding.Hnumber.setText(getIntent().getStringExtra("ID"));
      displayHymnBinding.Htitle.setText(getIntent().getStringExtra("TITLE"));
      displayHymnBinding.Hauthor.setText(getIntent().getStringExtra("AUTHOR"));
      //displayHymnBinding.Hlyric.setText(getIntent().getStringExtra("LYRIC"));
        String lyrics=getIntent().getStringExtra("LYRIC");
        lyrics=lyrics.replace("_b","\n");
        displayHymnBinding.Hlyric.setText(lyrics);

    }
}