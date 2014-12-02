package com.slem.whatscrasher;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.slem.whatscrasher.R;


public class MainActivity extends ActionBarActivity {

    EditText text;
    EditText header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.editText);
        header = (EditText) findViewById(R.id.header);

        ActionBar bar = getSupportActionBar();

        bar.setDisplayShowHomeEnabled(true);
        bar.setIcon(R.drawable.ic_launcher);
        bar.setHomeButtonEnabled(true);
        bar.setSubtitle("Crash any conversation");
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void Copy(View v) {/*
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setPackage("com.whatsapp");
        String crasher="";

        //crasher = readTextFile(getResources().openRawResource(R.raw.crasher));
        crasher = text.getText().toString();
        System.out.println(crasher);

        sendIntent.putExtra(Intent.EXTRA_TEXT, crasher);
        sendIntent.setType("text/plain");
        try {
            startActivity(sendIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Whatsapp have not been installed.", Toast.LENGTH_LONG).show();
        }*/

        String headerString= header.getText().toString();
        if(headerString.isEmpty()){
            headerString="-";
        }
        while(headerString.length()<100){
            headerString+=" ";
        }

        String crasher = text.getText().toString();
        String message = headerString+"\n\r" +
                ""+crasher;

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", message);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this, "Text copied.", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Paste this text into the conversation to crash it", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showInfo(){
        View messageView = getLayoutInflater().inflate(R.layout.about, null, false);

        // When linking text, force to always use default color. This works
        // around a pressed color state bug.
        TextView textView = (TextView) messageView.findViewById(R.id.about_credits);
        TextView textView2 = (TextView) messageView.findViewById(R.id.about_descript);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle(R.string.app_name);
        builder.setView(messageView);
        builder.create();
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.action_about:
            showInfo();
            default:
                break;
        }
        return true;
    }


}
