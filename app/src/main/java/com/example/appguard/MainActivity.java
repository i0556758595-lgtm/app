package com.example.appguard;
import android.app.Activity; import android.content.Intent; import android.os.Bundle; import android.provider.Settings; import android.text.InputType; import android.view.Gravity; import android.widget.*;
public class MainActivity extends Activity {
 public static final String PREFS="guard", PASSWORD="password", ENABLED="enabled"; private static final String DEFAULT_PASSWORD="1234"; private TextView state; private EditText password;
 @Override public void onCreate(Bundle b){super.onCreate(b); build();}
 private void build(){ LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(40,50,40,40); root.setGravity(Gravity.TOP);
  TextView title=new TextView(this); title.setText("מנהל הגנה"); title.setTextSize(30); root.addView(title);
  TextView info=new TextView(this); info.setText("מצב ההגנה חוסם את מתקין החבילות ומסכי מערכת שנבחרו."); info.setTextSize(18); root.addView(info);
  password=new EditText(this); password.setHint("סיסמת מנהל"); password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD); root.addView(password);
  state=new TextView(this); state.setTextSize(20); root.addView(state);
  Button toggle=new Button(this); toggle.setText("הפעל / כבה הגנה"); root.addView(toggle);
  Button acc=new Button(this); acc.setText("פתיחת הגדרות נגישות"); root.addView(acc); updateState();
  toggle.setOnClickListener(v->{if(!password.getText().toString().equals(getPassword())){Toast.makeText(this,"סיסמה שגויה",Toast.LENGTH_SHORT).show();return;} boolean n=!isEnabled();getSharedPreferences(PREFS,0).edit().putBoolean(ENABLED,n).apply();updateState();});
  acc.setOnClickListener(v->startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))); setContentView(root); }
 private String getPassword(){return getSharedPreferences(PREFS,0).getString(PASSWORD,DEFAULT_PASSWORD);} private boolean isEnabled(){return getSharedPreferences(PREFS,0).getBoolean(ENABLED,false);} private void updateState(){state.setText(isEnabled()?"מצב הגנה: מופעל":"מצב הגנה: כבוי");}
}
