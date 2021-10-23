package com.hamzaa.hamza.ebook.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.BuildConfig;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.hamzaa.hamza.ebook.R;
import com.hamzaa.hamza.ebook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ReviewManager reviewManager;
    ReviewInfo reviewInfo = null;

    int request ;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        /*addReview();
        startReviewFlow();*/

    }

    @SuppressWarnings("LambdaCanBeReplacedWithAnonymous")
    public void addReview(){
        reviewManager = ReviewManagerFactory.create(getApplicationContext());
        Task<ReviewInfo> manager = reviewManager.requestReviewFlow();
        manager.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                reviewInfo = task.getResult();
            } else {
                Toast.makeText(getApplicationContext(), "In App ReviewFlow failed to start", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void startReviewFlow() {
        if (reviewInfo != null) {
            Task<Void> flow = reviewManager.launchReviewFlow(this, reviewInfo);
            flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "In App Rating complete", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "In App Rating failed", Toast.LENGTH_LONG).show();
        }
    }

    public void OpenBio(View view) {
        Intent intent = new Intent(getApplicationContext(),BioActivity.class);
        startActivity(intent);
    }

    public void openpdf(int requestt){
        Intent intent = new Intent(getApplicationContext(), ReadActivity.class);
        request = requestt;
        intent.putExtra("EXTRA_SESSION_ID",request);
        startActivity(intent);
    }

    public void openMalatBook(View view) { openpdf(1); }

    public void openwayToquranBook(View view) {
        openpdf(2);
    }

    public void openRaqaqBook(View view) {
        openpdf(3);
    }

    public void openMaslakeatBook(View view) {
        openpdf(4);
    }

    public void openSultaBook(View view) {
        openpdf(5);
    }

    public void openTawelBook(View view) {
        openpdf(6);
    }

    public void openMagariatBook(View view) {
        openpdf(7);
    }

    public void shareApp(View view) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain").putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String shareMessage = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch(Exception e) {
            Toast.makeText( getBaseContext(), "خلل في مشاركة التطبيق، المرجو الإعادة", Toast.LENGTH_SHORT).show();
        }
    }

}
