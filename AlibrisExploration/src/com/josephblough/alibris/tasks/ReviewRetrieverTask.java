package com.josephblough.alibris.tasks;

import org.json.JSONObject;

import com.josephblough.alibris.transport.DataRetriever;

import android.os.AsyncTask;
import android.util.Log;

public class ReviewRetrieverTask extends
	AsyncTask<Integer, Void, JSONObject> {

    private static final String TAG = "ReviewRetrieverTask";
    private DataReceiver receiver;

    public ReviewRetrieverTask(final DataReceiver receiver) {
	this.receiver = receiver;
    }
    
    @Override
    protected JSONObject doInBackground(Integer... params) {
	Log.d(TAG, "Retrieving reviews for " + params[0]);
	return DataRetriever.getReviews(params[0]);
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        //Log.d(TAG, "onPostExecute");
        //Log.d(TAG, result.toString());
        
        if (result == null) {
            receiver.error("There was an error retrieving reviews");
        }
        else {
            receiver.dataReceived(result);
        }
    }
}
