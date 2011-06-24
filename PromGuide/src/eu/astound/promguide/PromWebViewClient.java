package eu.astound.promguide;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PromWebViewClient extends WebViewClient {
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url){
		view.loadUrl(url);
		return true;
	}

}
