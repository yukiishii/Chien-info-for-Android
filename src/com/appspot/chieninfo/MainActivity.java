package com.appspot.chieninfo;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String DEFAULT_URL = "http://chien-info.appspot.com/";

    WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.main);
        setActionBar();

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.loadUrl(DEFAULT_URL);
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