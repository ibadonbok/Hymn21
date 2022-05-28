package hymn.book.kakotjingrwai01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import hymn.book.kakotjingrwai01.databinding.ActivityUserProfileBinding;

public class userProfile extends AppCompatActivity {
    private ActivityUserProfileBinding userProfileBinding;

    private FirebaseFirestore userdb;
    private FirebaseAuth firebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userProfileBinding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(userProfileBinding.getRoot());

        userdb = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        loadUserData(userdb,firebaseAuth);

        userProfileBinding.profileBackBtn.setOnClickListener(V->{
            onBackPressed();
            finish();
        });
    }

    private void loadUserData(FirebaseFirestore userdb, FirebaseAuth firebaseAuth) {
        Toast.makeText(getApplicationContext(), "user"+firebaseAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();

        userdb.collection("USERS").document(firebaseAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            String name = task.getResult().get("NAME").toString();
                            String age = task.getResult().get("AGE").toString();
                            String gender = task.getResult().get("GENDER").toString();
                            String email = task.getResult().get("EMAIL").toString();
                            userProfileBinding.yourName.setText(name);
                            userProfileBinding.age.setText(age);
                            userProfileBinding.yourEmail.setText(firebaseAuth.getCurrentUser().getEmail());
                            userProfileBinding.gender.setText(gender);
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(userProfile.this, "Access to Data failed"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}