package eu.astound.promguide;

import java.net.URL;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class AsyncImageDownloader extends AsyncTask<URL,Void, Bitmap>  {
protected ImageDownloader bgTask;
public AsyncImageDownloader (){
	this.bgTask = new ImageDownloader();
}

@Override
protected Bitmap doInBackground(URL... arg0) {
	URL u = arg0[0];
	Bitmap b = this.bgTask.fetchImage(u);
    return b;
}


}
