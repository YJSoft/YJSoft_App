package kr.pe.yjsoft.offcialapp;

import kr.pe.yjsoft.offcialapp.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;


public class WebViewActivity extends Activity {
	public boolean doubleBackToExitPressedOnce;
    private WebView webView;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

		webView = (WebView) findViewById(R.id.webView1);
        webView.setWebViewClient(new MyWebViewClient());

                	String url = getString(R.string.url);

                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.loadUrl(url);
            
  
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
    
    
    public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 1, 0, R.string.menu_go_back).setIcon(android.R.drawable.ic_menu_revert);
		menu.add(0, 2, 0, R.string.menu_about).setIcon(android.R.drawable.ic_menu_info_details);
		SubMenu subMenu = menu.addSubMenu(R.string.menu_family).setIcon(android.R.drawable.ic_menu_add);
		subMenu.add(1, 4, Menu.NONE, R.string.yjsoft_off);
		subMenu.add(1, 5, Menu.NONE, R.string.yjsoft_blog);
		subMenu.add(1, 6, Menu.NONE, R.string.kmc_blog);
		subMenu.add(1, 7, Menu.NONE, R.string.indexbot);
		menu.add(0, 3, 0, R.string.menu_quit).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
		
		return true;
	}

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			if (webView.canGoBack()) webView.goBack();
			return true;
		case 3:
			//this.finish();
			AlertDialog.Builder alert_confirm = new AlertDialog.Builder(WebViewActivity.this);
			alert_confirm.setMessage(getString(R.string.quit_confirm)).setCancelable(true).setPositiveButton(getString(R.string.btn_ok),
			new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			    	android.os.Process.killProcess(android.os.Process.myPid());
			    }
			}).setNegativeButton(getString(R.string.btn_cancel),
			new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			    return;
			    }
			});
			AlertDialog alert_con = alert_confirm.create();
			alert_con.show();
			return true;
		case 2:
			AlertDialog.Builder alert = new AlertDialog.Builder(WebViewActivity.this);
			alert.setNegativeButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			    dialog.dismiss();     //´Ý±â
			    }
			});
			alert.setMessage(R.string.made_by);
			alert.show();
			return true;
		case 4:
			webView.getSettings().setJavaScriptEnabled(true);
			webView.loadUrl(getString(R.string.url));
			return true;
		case 5:
			webView.getSettings().setJavaScriptEnabled(true);
			webView.loadUrl(getString(R.string.yjsoft_blog_url));
			return true;
		case 6:
			webView.getSettings().setJavaScriptEnabled(true);
			webView.loadUrl(getString(R.string.kmc_blog_url));
			return true;
		case 7:
			webView.getSettings().setJavaScriptEnabled(true);
			webView.loadUrl(getString(R.string.indexbot_url));
			return true;
		}
		return false;
	}
    
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.back_to_exit), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
             doubleBackToExitPressedOnce=false;   

            }
        }, 2000);
    } 
    
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}