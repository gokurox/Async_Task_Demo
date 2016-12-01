package in.ac.iiitd.gursimran14041.asynctaskdemo_mc_assign5;

import android.os.AsyncTask;
import android.widget.Toast;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

/**
 * Created by Gursimran Singh on 11-11-2016.
 */

public class HTMLParserUsingJsoup extends AsyncTask<String, Void, String> {
    private MainActivity mainActivity;

    public HTMLParserUsingJsoup(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(String... params) {
        Document document;
        try {
            document = Jsoup.connect(params[0]).get();
        } catch (MalformedURLException mue) {
            mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(mainActivity.getApplicationContext(), "Malformed URL", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        } catch (HttpStatusException hse) {
            mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(mainActivity.getApplicationContext(), "Response NOT OK", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        } catch (UnsupportedMimeTypeException umte) {
            mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(mainActivity.getApplicationContext(), "Unsupported MIME Type", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        } catch (SocketTimeoutException ste) {
            mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(mainActivity.getApplicationContext(), "Connection Timed Out", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        } catch (IOException ioe) {
            mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(mainActivity.getApplicationContext(), "IO Exception", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        } catch (RuntimeException re) {
            mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(mainActivity.getApplicationContext(), "RunTime Exception", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }

        return document.toString();

        /*StringBuffer stringBuffer = new StringBuffer();

        // Get document (HTML page) title
        String title = document.title();
        stringBuffer.append("Title: " + title + "\r\n");

        // Get meta info
        Elements metaElems = document.select("meta");
        stringBuffer.append("META DATA\r\n");
        for (Element metaElem : metaElems) {
            String name = metaElem.attr("name");
            String content = metaElem.attr("content");
            stringBuffer.append("name [" + name + "] - content [" + content + "] \r\n");
        }

        return stringBuffer.toString();*/
    }

    @Override
    protected void onPostExecute(String htmlResponse) {
        super.onPostExecute(htmlResponse);
        mainActivity.asyncHTMLParserUsingJsoupCallback(htmlResponse);
    }
}
