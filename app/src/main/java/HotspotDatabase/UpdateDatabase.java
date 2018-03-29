package HotspotDatabase;

import android.content.Context;
import android.util.Log;

public class UpdateDatabase {

    private static UpdateDatabase mInstance = new UpdateDatabase();

    private UpdateDatabase(){
    }

    public static synchronized UpdateDatabase getInstance() {
        if (mInstance == null) {
            mInstance = new UpdateDatabase();
        }
        return mInstance;
    }

    public void refreshDatabase(Context context){
        if (InternetConnection.getInternetStatus().getInternet() == true) {
            try {
                Log.d("MainActivity", "Internet connected");
                new AsyncStoreSQL(AppDatabase.getInstance(context), context).execute();
            } catch (Exception e) {
                Log.e("MainActivity", "Error, skipping data update", e);
            }
        } else {
            Log.d("MainActivity", "No Internet Connection, skipping Json retrieval");
        }
    }
}

