package in.ac.iiitd.gursimran14041.asynctaskdemo_mc_assign5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String TEXT_TAG = "mOutputValue";
    private EditText mInput;
    private TextView mOutput;
    private Button mSubmitButton;
    private MainActivity selfReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selfReference = this;

        mInput = (EditText) findViewById(R.id.website_url);
        mOutput = (TextView) findViewById(R.id.output);
        mSubmitButton = (Button) findViewById(R.id.input_submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mInput.getText().toString().trim();
//                new HTMLDownloader(selfReference).execute(url);
                new HTMLParserUsingJsoup(selfReference).execute(url);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(TEXT_TAG, mOutput.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        String outText = savedInstanceState.getString(TEXT_TAG);
        mOutput.setText(outText);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void asyncHTMLDownloaderCallback(String htmlResponse) {
        if (htmlResponse == null)
            return;

        Log.i("HTML CONTENT:", htmlResponse);
        mOutput.setText(htmlResponse);
    }

    public void asyncHTMLParserUsingJsoupCallback(String htmlResponse) {
        if (htmlResponse == null)
            return;

        Log.i("HTML CONTENT:", htmlResponse);
        mOutput.setText(htmlResponse);
    }

    public void makeToast(String toastMessage) {
        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }
}
