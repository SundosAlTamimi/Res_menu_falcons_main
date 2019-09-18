package com.falconssoft.menurestaurant;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.falconssoft.menurestaurant.models.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.falconssoft.menurestaurant.MainSetting.usersList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageView settingOfSystem, logoImage;
    private EditText username, password;
    private TextView english, arabic;
    private CheckBox checkBox;
    private Button login;
    private Bitmap imageBitmap = null;
    //    private List<Users> users = new ArrayList<>();
    private MenuPresenter presenter;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);// hide keyboard at the begaining

        databaseHandler = new DatabaseHandler(this);
        presenter = new MenuPresenter(databaseHandler, this);
        presenter.getCategoriesAndItems();

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        english = findViewById(R.id.login_language_english);
        arabic = findViewById(R.id.login_language_arabic);
        login = findViewById(R.id.login_button);
        settingOfSystem = findViewById(R.id.setting);
        checkBox = findViewById(R.id.login_checkBox);

//        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        login.setOnClickListener(this);
        english.setOnClickListener(this);
        arabic.setOnClickListener(this);
        settingOfSystem.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(this);

        Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);

        Animation slide_left = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_from_left);

        username.startAnimation(slide_left);
        password.startAnimation(slide_left);
        login.startAnimation(slide_down);
    }

    public void animateView(View view) { // vibration animation
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        view.startAnimation(shake);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_button:
//                users = databaseHandler.getAllUSER();
                boolean found = false;
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                if (!usernameText.equals("")) {
                    if (!passwordText.equals("")) {
                        for (int i = 0; i < usersList.size(); i++)
                            if (usernameText.equals(usersList.get(i).getUserName()))
                                if (passwordText.equals(usersList.get(i).getUserPassword())) {
                                    found = true;
                                    Intent categoryIntent = new Intent(LoginActivity.this, CategoryActivity.class);
                                    categoryIntent.putExtra("userName", usernameText);
                                    startActivity(categoryIntent);
                                }

                        if (found == false) {
                            if (usernameText.equals("admin")) {
                                if (passwordText.equals("admin")) {
                                    Intent categoryIntent = new Intent(LoginActivity.this, CategoryActivity.class);
                                    categoryIntent.putExtra("userName", usernameText);
                                    startActivity(categoryIntent);
                                } else {
                                    animateView(password);
                                    Toast.makeText(this, getText(R.string.wrong_password), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                animateView(username);
                                Toast.makeText(this, getText(R.string.wrong_username), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        password.setError(getText(R.string.required_field));
                        animateView(password);
                    }
                } else {
                    username.setError(getText(R.string.required_field));
                    animateView(username);
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
                SettingPassword();
                break;

        }

    }

    void SettingDialog() {

        final Dialog settingDialog = new Dialog(LoginActivity.this);
        settingDialog.setCancelable(false);
        settingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        settingDialog.setCanceledOnTouchOutside(false);
        settingDialog.setContentView(R.layout.setting_dialog);

        final EditText ipServer, restName;
        Button save, exit;

        ipServer = (EditText) settingDialog.findViewById(R.id.ip_edit);
        restName = (EditText) settingDialog.findViewById(R.id.restName);
        logoImage = (ImageView) settingDialog.findViewById(R.id.logo);

        List<Setting> set = new ArrayList<>();

        set = databaseHandler.getAllSetting();

        if (set.size() != 0) {
            ipServer.setText(set.get(0).getIpConnection());
            restName.setText(set.get(0).getRestName());
            logoImage.setImageBitmap(set.get(0).getLogoRest());
        } else {
            Toast.makeText(this, "not data ...", Toast.LENGTH_SHORT).show();
        }

        save = (Button) settingDialog.findViewById(R.id.save);
        exit = (Button) settingDialog.findViewById(R.id.exit);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ipServer.getText().toString().equals("") && !restName.getText().toString().equals("")) {

                    databaseHandler.deleteAllSetting();
                    Setting set = new Setting(ipServer.getText().toString(), restName.getText().toString(), imageBitmap);
                    databaseHandler.addSetting(set);

                    Toast.makeText(LoginActivity.this, "Save", Toast.LENGTH_SHORT).show();
                    settingDialog.dismiss();

                } else {
                    Toast.makeText(LoginActivity.this, "Please fill all Data ", Toast.LENGTH_SHORT).show();
                    animateView(ipServer);
                    animateView(restName);

                }

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingDialog.dismiss();
            }
        });

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

    void SettingPassword() {

        final Dialog passwordDialog = new Dialog(LoginActivity.this);
        passwordDialog.setCancelable(false);
        passwordDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        passwordDialog.setCanceledOnTouchOutside(false);
        passwordDialog.setContentView(R.layout.setting_password);

        final EditText password = (EditText) passwordDialog.findViewById(R.id.pass_setting);

        Button save, exit;

        save = (Button) passwordDialog.findViewById(R.id.save);
        exit = (Button) passwordDialog.findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordDialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!password.getText().toString().equals("")) {
                    if (password.getText().toString().equals("admin30")) {
                        SettingDialog();
                        passwordDialog.dismiss();
                    } else {
                        Toast.makeText(LoginActivity.this, "The Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "please enter the password ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        passwordDialog.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked == true)
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        else
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);


    }
}
