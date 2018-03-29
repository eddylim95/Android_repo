package HotspotDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import HotspotDatabase.AppDatabase;
import HotspotDatabase.Hotspot;
import HotspotDatabase.OneMapJsonHandler;

/**
 * Created by eddyl on 25/3/2018.
 */

public class AsyncStoreSQL extends AsyncTask<Void, Void, Void> {

    private HotspotDatabase.AppDatabase appDatabase;
    private Context context;
    private HotspotDatabase.Hotspot[] hotspots;

    public AsyncStoreSQL(HotspotDatabase.AppDatabase appDatabase, Context context){
        this.appDatabase = appDatabase;
        this.context = context;
    }

    protected Void doInBackground(Void... voids) {
        try {
            OneMapJsonHandler oneMapJsonHandler = new HotspotDatabase.OneMapJsonHandler();
            hotspots = oneMapJsonHandler.getHotspots(context);
            if (hotspots == null){
                Log.d("AsyncStoreSQL", "hotspot value is null");
                return null;
            }
            storeInSQL(hotspots);
            Log.d("AsyncStoreSQL", "Store in SQL done");
            String test = appDatabase.hotspotDao().findByPostcode(470719).getNAME(); //Query debug test
            Log.d("AsyncStoreSQL", test);
        }
        catch (Exception e){
            Log.e("AsyncStoreSQL", "Async doInBackground Error", e);
        }
        return null;
    }

    private void storeInSQL(HotspotDatabase.Hotspot[] hotspots){
        try {
            appDatabase.hotspotDao().dropTable();
            appDatabase.hotspotDao().insertAll(hotspots);
        }
        catch (Exception e){
            Log.e("Database", "storeInSQL Error", e);
        }
    }
}
