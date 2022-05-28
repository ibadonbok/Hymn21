package hymn.book.kakotjingrwai01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class splash_screen extends AppCompatActivity {

    //variables
    Animation top_anima;
    Animation bottom_anima;
    ImageView image;
    TextView logo,slogan;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        firebaseAuth = FirebaseAuth.getInstance();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (firebaseAuth.getCurrentUser()!=null){
                startActivity(new Intent(getApplicationContext(),user_home.class));
            }else{
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
            finish();
        },2000);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

       // Animation
        top_anima= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_anima= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.simage);
        logo = findViewById(R.id.s3);
        slogan = findViewById(R.id.s1);

        image.setAnimation(top_anima);
        logo.setAnimation(bottom_anima);
        slogan.setAnimation(bottom_anima);


    }
}