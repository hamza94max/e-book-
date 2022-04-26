package com.ibrahem.islamic.ebook.app.utill;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;

public class ReviewApp {
    static ReviewManager reviewManager;
    static ReviewInfo reviewInfo = null;

    @SuppressWarnings("LambdaCanBeReplacedWithAnonymous")
    public static void addReview(Context context) {
        reviewManager = ReviewManagerFactory.create(context);
        Task<ReviewInfo> manager = reviewManager.requestReviewFlow();
        manager.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Getting the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();

                Task<Void> flow = reviewManager.launchReviewFlow((Activity) context, reviewInfo);
                flow.addOnCompleteListener(task1 -> {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown.
                });
            }
        });
    }


    public static void startReviewFlow(Activity activity) {
        if (reviewInfo != null) {
            Task<Void> flow = reviewManager.launchReviewFlow(activity, reviewInfo);
            flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(Task<Void> task) {
                    Toast.makeText(activity, "In App Rating complete", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(activity, "In App Rating failed", Toast.LENGTH_LONG).show();
        }
    }

}
