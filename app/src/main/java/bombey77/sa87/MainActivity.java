package bombey77.sa87;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ProgressBar pb;
    MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tvResult);
        pb = (ProgressBar) findViewById(R.id.pb);
    }

    public void onClick(View v) {
        myTask = new MyTask();
        myTask.execute("file_1", "file_2", "file_3", "file_4", "file_5");
    }

    class MyTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv.setText("Begin");
        }

        @Override
        protected Void doInBackground(String... params) {
            int cnt = 0;
            for (String url : params) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(++cnt);
                try {
                    downloadFile(url);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText("Download " + values[0] + " files");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tv.setText("End");
        }

        private void downloadFile(String url) throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
