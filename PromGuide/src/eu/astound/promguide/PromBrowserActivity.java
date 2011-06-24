package eu.astound.promguide;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

public class PromBrowserActivity extends Activity{
	public static String PROM_URL = "eu.astound.promguide.PromBrowserActivity.PROM_URL";

	WebView wPromBrowser;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.prom_browser);
	    String promURL = getIntent().getStringExtra(PROM_URL);
	    
	    wPromBrowser = (WebView) findViewById(R.id.wPromBrowser);
	    wPromBrowser.getSettings().setJavaScriptEnabled(true);
	    wPromBrowser.loadUrl(promURL);
	    
	    wPromBrowser.setWebViewClient(new PromWebViewClient());
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wPromBrowser.canGoBack()){
			wPromBrowser.goBack();
			return true;
		}
		return super.onKeyDown(keyCode,event);
	}
}
