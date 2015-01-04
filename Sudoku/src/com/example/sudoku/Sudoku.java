package com.example.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.MenuInflater;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;


public class Sudoku extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Set up click listeners for all the buttons
        View continueButton = findViewById(R.id.continue_button);
        continueButton.setOnClickListener(this);
        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
        
        // If the activity is restarted, do a continue next time
        // getIntent().putExtra(Game.KEY_DIFFICULTY, Game.DIFFICULTY_CONTINUE);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main, menu);
    	return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	switch (item.getItemId()) {
    	case R.id.settings:
    	startActivity(new Intent(this, Prefs.class));
    	return true;
    	// More items go here (if any) ...
    	}
    	return false;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.about_button:
				Intent i = new Intent(this, About.class);
				startActivity(i);
				break;
			case R.id.new_button:
				openNewGameDialog();
				break;
			case R.id.exit_button:
				finish();
				break;
			case R.id.continue_button:
				startGame(Game.DIFFICULTY_CONTINUE);
				break;
			// More buttons go here (if any) ...
		}
	}

	private static final String TAG = "Sudoku" ;

	private void openNewGameDialog() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this)
			.setTitle(R.string.new_game_title)
			.setItems(R.array.difficulty,
				new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialoginterface,
										int i) {
										startGame(i);
										}
				})
		.show();
	}
	
	private void startGame(int i) {
		Log.d(TAG, "clicked on " + i);
		Intent intent = new Intent(Sudoku.this, Game.class);
		intent.putExtra(Game.KEY_DIFFICULTY, i);
		startActivity(intent);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Music.play(this, R.raw.main);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Music.stop(this);
	}
}
