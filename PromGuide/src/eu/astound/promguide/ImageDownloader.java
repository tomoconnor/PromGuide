package eu.astound.promguide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageDownloader {
	public ImageDownloader() {
		
	}
	public Bitmap fetchImage( URL url )
	{
	    try
	    {
	        HttpURLConnection c = ( HttpURLConnection ) url.openConnection();
	        c.setDoInput( true );
	        c.connect();
	        InputStream is = c.getInputStream();
	        Bitmap img;
	        img = BitmapFactory.decodeStream( is );
	        return img;
	    }
	    catch ( MalformedURLException e )
	    {
	        Log.d( "RemoteImageHandler", "fetchImage passed invalid URL: " + url.toString() );
	    }
	    catch ( IOException e )
	    {
	        Log.d( "RemoteImageHandler", "fetchImage IO exception: " + e );
	    }
	    return null;
	}
}
