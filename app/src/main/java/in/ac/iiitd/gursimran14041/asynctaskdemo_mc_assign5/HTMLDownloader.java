package in.ac.iiitd.gursimran14041.asynctaskdemo_mc_assign5;

import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Gursimran Singh on 10-11-2016.
 */

// Assignment Done Using HttpClient, HttpGet and HttpResponse (DEPRECATED)
public class HTMLDownloader extends AsyncTask<String, Void, String> {
    private HttpClient httpClient;
    private HttpGet request;
    private MainActivity mainActivity;

    public HTMLDownloader(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(String... params) {
        httpClient = new DefaultHttpClient();
        HttpResponse httpResponse;

        try {
            request = new HttpGet(params[0]);
            httpResponse = httpClient.execute(request);
        } catch (ClientProtocolException cpe) {
            mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(mainActivity.getApplicationContext(), "ClientProtocolException: Invalid URL", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        } catch (IOException ioe) {
            mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(mainActivity.getApplicationContext(), "IOException: Invalid URL", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        } catch (RuntimeException re) {
            mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(mainActivity.getApplicationContext(), "RunTime Exception: Invalid URL", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }

        String htmlContent = null;
        InputStream in;
        BufferedReader reader;
        StringBuilder str = new StringBuilder();

        try {
            in = httpResponse.getEntity().getContent();
            reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                str.append(line);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            str = null;
        }

        if (str == null)
            return null;

        htmlContent = str.toString();
        return htmlContent;
    }

    @Override
    protected void onPostExecute(String htmlResponse) {
        mainActivity.asyncHTMLDownloaderCallback(htmlResponse);
    }
}
