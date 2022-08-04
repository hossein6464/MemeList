package diana.soleil.hossein;

import androidx.appcompat.app.AppCompatActivity;
import diana.soleil.hossein.model.Memes;
import diana.soleil.hossein.utilities.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetailActivity extends AppCompatActivity {
    Intent intentFromMainActivity;
    Bundle bundleFromMainActivity;
    Serializable serializableFromMainActivity;
    Memes memeFromMainActivity;
    ImageView imageView;
    TextView nameTextView, idTextView, widthTextView, heightTextView, boxTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initialize();
        getIntentFromMainActivity();
    }

    public void initialize() {
        imageView = findViewById(R.id.imageViewId);
        idTextView = findViewById(R.id.idTextViewId);
        nameTextView = findViewById(R.id.nameTextViewId);
        widthTextView = findViewById(R.id.widthTextViewId);
        heightTextView = findViewById(R.id.heightTextViewId);
        boxTextView = findViewById(R.id.boxCountTextViewId);
    }
    public void getIntentFromMainActivity () {
        intentFromMainActivity = getIntent();
        bundleFromMainActivity = intentFromMainActivity.getBundleExtra(Constants.BUNDLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY);
        serializableFromMainActivity = bundleFromMainActivity.getSerializable(Constants.SERIALIZABLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY);
        memeFromMainActivity = (Memes) serializableFromMainActivity;

        String photo = memeFromMainActivity.getUrl();
        Picasso.get().load(photo).into(imageView);

        idTextView.setText(String.valueOf(memeFromMainActivity.getId()));
        nameTextView.setText(memeFromMainActivity.getName());
        widthTextView.setText(String.valueOf(memeFromMainActivity.getWidth()));
        heightTextView.setText(String.valueOf(memeFromMainActivity.getHeight()));
        boxTextView.setText(String.valueOf(memeFromMainActivity.getBox_count()));
    }
}