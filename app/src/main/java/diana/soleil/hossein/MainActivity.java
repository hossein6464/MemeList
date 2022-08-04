package diana.soleil.hossein;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import diana.soleil.hossein.adapter.MemesRecyclerAdapter;
import diana.soleil.hossein.db.DBManager;
import diana.soleil.hossein.db.DatabaseDemo;
import diana.soleil.hossein.model.Memes;
import diana.soleil.hossein.service.Download;
import diana.soleil.hossein.utilities.Constants;
import diana.soleil.hossein.utilities.JsonParser;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MemesRecyclerAdapter.OnMemeListener, MemesRecyclerAdapter.OnMemeLongClickListener {
    ArrayList<Memes> memesArrayList;
    RecyclerView memesRecyclerView;
    MemesRecyclerAdapter memesAdapter;
    ArrayList<Memes> memesArrayListFromDB;
    DBManager dbManager;
    DatabaseDemo databaseDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        memesRecyclerView = findViewById(R.id.memesRecyclerView);
        dbManager = (DBManager) getApplication();
        databaseDemo  = new DatabaseDemo(dbManager, memesArrayList);

        if (memesArrayList == null && memesArrayListFromDB == null) {
            memesArrayList = downloadedArrayList();
            databaseDemo.insertDummyDataToDB(memesArrayList);
        }
        memesArrayListFromDB = dbManager.cursorToArrayList(databaseDemo.readFromDB());

        memesAdapter = new MemesRecyclerAdapter(this, memesArrayListFromDB, this, this);
        memesRecyclerView.setAdapter(memesAdapter);

        LinearLayoutManager linearLayoutManagerPopularMovies = new LinearLayoutManager(this);
        linearLayoutManagerPopularMovies.setOrientation(LinearLayoutManager.VERTICAL);

        memesRecyclerView.setLayoutManager(linearLayoutManagerPopularMovies);
    }

    public ArrayList<Memes> downloadedArrayList () {
        ArrayList<Memes> memesFromDownloadArrayList = new ArrayList<>();
        try {
            Download downloadMemes = new Download();
            String stringOfMemes = downloadMemes.execute(Constants.API_URL).get();
            JsonParser jsonParserMemes = new JsonParser();
            memesFromDownloadArrayList = jsonParserMemes.processJSONData(stringOfMemes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return memesFromDownloadArrayList;
    }

    @Override
    public void onMemeClick(int position, int parentPosition) {
        Intent intentToSendToDetailActivity = new Intent(MainActivity.this, DetailActivity.class);
        Bundle bundleToSendToDetailActivity = new Bundle();
        bundleToSendToDetailActivity.putSerializable(Constants.SERIALIZABLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, memesArrayListFromDB.get(position));
        intentToSendToDetailActivity.putExtra(Constants.BUNDLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY, bundleToSendToDetailActivity);
        startActivity(intentToSendToDetailActivity);
    }

    @Override
    public void onMemeLongClick(int position, int parentPosition) {
        createAlertDialog(position);
    }
    public void createAlertDialog(int positionItemToBeRemoved) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete!");
        alertDialogBuilder.setMessage(" Are You sure you want to delete " + memesArrayList.get(positionItemToBeRemoved).getName());
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseDemo.deleteFromDB(memesArrayListFromDB.get(positionItemToBeRemoved).getId());
                memesArrayListFromDB.remove(positionItemToBeRemoved);
                memesAdapter.notifyDataSetChanged();
            }
        });
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }
}