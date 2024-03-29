package taffer.statcounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private int p1Color;
    private int p2Color;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.sp = getApplicationContext().getSharedPreferences(getString(R.string.prefKey), Context.MODE_PRIVATE);
        this.editor = sp.edit();
    }

    /**
     * onClick method, selects the color player 1
     * @param v
     */
    public void setP1Color(View v){
        this.p1Color = ((ImageView) v).getImageTintList().getDefaultColor();
        displayToast("Player 1 color selected");
    }

    /**
     * onClick method, selects the color player 2
     * @param v
     */
    public void setP2Color(View v){
        this.p2Color = ((ImageView) v).getImageTintList().getDefaultColor();
        displayToast("Player 2 color selected");
    }

    /**
     * Saves the settings in Shared preferences.
     * @param v
     */
    public void saveSettings(View v){

        Spinner spinner = findViewById(R.id.spinerMode);
        this.editor.putString("def_mode", spinner.getSelectedItem().toString()).commit();

        if(this.p1Color != 0){
            this.editor.putInt("def_p1Color", this.p1Color).commit();
        }

        if(this.p2Color != 0){
            this.editor.putInt("def_p2Color", this.p2Color).commit();
        }

        EditText p1 = findViewById(R.id.edDefaultP1Name);
        EditText p2 = findViewById(R.id.edDefaultP2Name);

        if(!p1.getText().equals("")){
            this.editor.putString("def_p1Name", p1.getText().toString()).commit();
        }

        if(!p2.getText().equals("")){
            this.editor.putString("def_p2Name", p2.getText().toString()).commit();
        }

        displayToast("Settings Saved!");
    }

    private void displayToast(String s){
        if(this.toast != null) {
            this.toast.cancel();
        }
        this.toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        this.toast.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.saveSettings(null);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        this.finish();
    }
}
