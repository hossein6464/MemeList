package diana.soleil.hossein.utilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.ArrayList;

import diana.soleil.hossein.model.Memes;

public class JsonParser {
    ArrayList<Memes> memesArrayList = new ArrayList<>();
    private InputStreamReader inputStreamReader, genreInputStream, castInputStream;

    public ArrayList<Memes> processJSONData(String data){

        try {
            JSONObject jsonObject = new JSONObject(data);

            JSONObject jsonObjectOfData = new JSONObject(jsonObject.getString(Constants.DATA));
            JSONArray jsonArray = new JSONArray(jsonObjectOfData.getString(Constants.MEMES));

            for (int i=0; i < jsonArray.length(); i++){

                JSONObject currentJSONMemesObject = jsonArray.getJSONObject(i);
                int id                 = currentJSONMemesObject.getInt(Constants.ID);
                String name                 = currentJSONMemesObject.getString(Constants.NAME);
                String url                = currentJSONMemesObject.getString(Constants.URL);
                int width     = currentJSONMemesObject.getInt(Constants.WIDTH);
                int height        = currentJSONMemesObject.getInt(Constants.HEIGHT);
                int box_count                 = currentJSONMemesObject.getInt(Constants.BOX_COUNT);

                Memes currentMeme = new Memes (id,name,url,width,height,box_count);
                memesArrayList.add(currentMeme);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memesArrayList;
    }
}
