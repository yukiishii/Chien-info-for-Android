package com.appspot.chieninfo;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Picture;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebView.PictureListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String DEFAULT_URL = "http://chien-info.appspot.com/";
	
	private static final String PROGRESS_DIALOG_MSG = "Now Loading ...";
    WebView mWebView;

    ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.main);
        setActionBar();

        dialog = new ProgressDialog(this);
        dialog.setMessage(PROGRESS_DIALOG_MSG);
		dialog.show();
        
        Map<String, String> httpHeaders = new HashMap<String, String>();
        httpHeaders.put("Accept-Encoding", "gzip");

        WebChromeClient chrome = new WebChromeClient() {
        	public void onProgressChanged(WebView view, int progress) {
        		dialog.setProgress(progress);
        	}
        };
        PictureListener picture = new PictureListener(){
        	@Override
        	public void onNewPicture (WebView view, Picture picture){
        		if (dialog.isShowing()) {
        			dialog.dismiss();
        		}
        	}
        };
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setPictureListener(picture);
        mWebView.setWebChromeClient(chrome);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setAppCacheMaxSize(8 * 1024 * 1024);
        mWebView.loadUrl(DEFAULT_URL, httpHeaders);
    }

    private void setActionBar() {
    	getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.action_bar);

    	ImageView titleIcon = (ImageView) findViewById(R.id.title_icon);
    	titleIcon.setImageResource(R.drawable.icon);

    	TextView title = (TextView) findViewById(R.id.title_text);
    	title.setText(R.string.app_name);
    	
    	ImageView infoBtn = (ImageView) findViewById(R.id.share_btn);
    	infoBtn.setImageResource(android.R.drawable.ic_menu_info_details);
    	infoBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Dialog dialog = new Dialog(v.getContext());
				dialog.setTitle(R.string.app_name);
				dialog.setContentView(R.layout.about);
				TextView tv = (TextView)dialog.findViewById(R.id.about_textview);
				tv.setMovementMethod(LinkMovementMethod.getInstance());
				dialog.show();
			}
    	});
    }
}