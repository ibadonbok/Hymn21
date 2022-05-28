package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import hymn.book.kakotjingrwai01.databinding.ActivityDashBoardBinding;

import hymn.book.kakotjingrwai01.databinding.ActivityUserHomeBinding;

public class user_home extends AppCompatActivity {

    private FirebaseAuth auth;
    private ActivityUserHomeBinding homeBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_home);

        homeBinding = ActivityUserHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        homeBinding.number.setOnClickListener(view -> {
            Intent intent = new Intent(user_home.this, hymn_number.class);
            startActivity(intent);
        });


        homeBinding.khorus.setOnClickListener(view -> {
            Intent khorus = new Intent(user_home.this, Chorus.class);
            startActivity(khorus);

        });

        homeBinding.khynnah.setOnClickListener(view -> {
            Intent khynnah = new Intent(user_home.this, Children.class);
            startActivity(khynnah);

        });

        homeBinding.samla.setOnClickListener(view -> {
            Intent samla = new Intent(user_home.this, Youth.class);
            startActivity(samla);

        });

        homeBinding.revival.setOnClickListener(view -> {
            Intent revival = new Intent(user_home.this, Revival.class);
            startActivity(revival);

        });

        homeBinding.pynwai.setOnClickListener(view -> {
            Intent pynwai = new Intent(user_home.this, Postlude.class);
            startActivity(pynwai);

        });

        homeBinding.kurim.setOnClickListener(view -> {
            Intent kurim = new Intent(user_home.this, Wedding.class);
            startActivity(kurim);

        });

        homeBinding.iap.setOnClickListener(view -> {
            Intent iap = new Intent(user_home.this, Funeral.class);
            startActivity(iap);

        });



        homeBinding.christmas.setOnClickListener(view -> {
            Intent christmas = new Intent(user_home.this, Christmas.class);
            startActivity(christmas);

        });

        homeBinding.notepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent note = new Intent(user_home.this, note_saved.class);
                startActivity(note);
            }
        });

        homeBinding.userProfileButton.setOnClickListener(view -> {
            Intent pro = new Intent(user_home.this, userProfile.class);
            startActivity(pro);

        });

        auth = FirebaseAuth.getInstance();
            homeBinding.logout.setOnClickListener(V -> {
            auth.signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        });


    }
}