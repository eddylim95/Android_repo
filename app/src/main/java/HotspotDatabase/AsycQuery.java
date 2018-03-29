package HotspotDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eddyl on 25/3/2018.
 */

public class AsycQuery extends AsyncTask<Void, Void, List<Hotspot>> {

    private HotspotDatabase.AppDatabase appDatabase;
    private List<Hotspot> hotspots;
    public AsycQuery(HotspotDatabase.AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    protected List<Hotspot> doInBackground(Void... voids) {
        try {
            List<Hotspot> hotspots = new ArrayList<Hotspot>();
            hotspots = appDatabase.hotspotDao().getAll();
            Log.d("AsyncQuery", hotspots.toString());//Query test
        }
        catch (Exception e){
            Log.e("AsyncQuery", "Async doInBackground Error", e);
        }
        return hotspots;
    }
/*
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        getHotspots();
    }

    protected List<Hotspot> getHotspots(){
        return hotspots;
    }
    */
}
