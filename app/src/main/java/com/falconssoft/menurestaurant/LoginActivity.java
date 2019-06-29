package com.falconssoft.menurestaurant;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.falconssoft.menurestaurant.Models.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username, password;
    private TextView english, arabic;
    private Button login;
    private DatabaseHandler databaseHandler;
    private List<Users> users = new ArrayList<>();
    private MenuPresenter presenter;
    ImageView settingOfSystem , logoImage;
    Bitmap imageBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHandler = new DatabaseHandler(this);
        presenter = new MenuPresenter(this);
        presenter.getUsersData();

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        english = findViewById(R.id.login_language_english);
        arabic = findViewById(R.id.login_language_arabic);
        login = findViewById(R.id.login_button);
        settingOfSystem=findViewById(R.id.setting);

        login.setOnClickListener(this);
        english.setOnClickListener(this);
        arabic.setOnClickListener(this);
        settingOfSystem.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_button:
                users = databaseHandler.getAllUSER();
                boolean found = false;
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                if (usernameText != null && passwordText != null) {
                    for (int i = 0; i < users.size(); i++)
                        if (usernameText .equals( users.get(i).getUserName()))
                            if (passwordText.equals(users.get(i).getUserPassword())) {
                                found = true;
                                Intent categoryIntent = new Intent(LoginActivity.this, CategoryActivity.class);
                                categoryIntent.putExtra("userName",usernameText);
                                startActivity(categoryIntent);
                            }

                    if (found == false) {
                        if (usernameText.equals("admin")){
                            if (passwordText.equals("admin")) {
                                Intent categoryIntent = new Intent(LoginActivity.this, CategoryActivity.class);
                                categoryIntent.putExtra("userName", usernameText);
                                startActivity(categoryIntent);
                            }else { Toast.makeText(this, "Wrong in username or password!", Toast.LENGTH_SHORT).show();}
                        }else{
                        Toast.makeText(this, "Wrong in username or password!", Toast.LENGTH_SHORT).show();}
                    }
                } else {
                    username.setError("Required field!");
                    password.setError("Required field!");
                }
                break;
            case R.id.login_language_english:
                LocaleAppUtils.setLocale(new Locale("en"));
                LocaleAppUtils.setConfigChange(this);
                finish();
                startActivity(getIntent());
                break;
            case R.id.login_language_arabic:
                LocaleAppUtils.setLocale(new Locale("ar"));
                LocaleAppUtils.setConfigChange(this);
                finish();
                startActivity(getIntent());
                break;

            case R.id.setting:

                SettingDialog();

                break;

        }

    }

    void SettingDialog(){

        Dialog settingDialog= new Dialog(LoginActivity.this);
        settingDialog.setCancelable(false);
        settingDialog.setCanceledOnTouchOutside(false);
        settingDialog.setContentView(R.layout.setting_dialog);

        EditText ipServer,RestName;

        ipServer=(EditText)settingDialog.findViewById(R.id.ip_edit);
        RestName=(EditText)settingDialog.findViewById(R.id.restName);
        logoImage=(ImageView)settingDialog.findViewById(R.id.logo);

        logoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.putExtra("crop", "false");
//                intent.putExtra("scale", true);
                intent.putExtra("outputX", 456);
                intent.putExtra("outputY", 256);
//                intent.putExtra("aspectX", 1);
//                intent.putExtra("aspectY", 1);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, 1);
            }
        });

        settingDialog.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                imageBitmap = extras.getParcelable("data");
                logoImage.setImageDrawable(new BitmapDrawable(getResources(), imageBitmap));
            }
        }
    }

}
