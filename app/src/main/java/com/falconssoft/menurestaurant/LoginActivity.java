package com.falconssoft.menurestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        login.setOnClickListener(this);
        english.setOnClickListener(this);
        arabic.setOnClickListener(this);

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
                        if (usernameText == users.get(i).getUserName())
                            if (passwordText.equals(users.get(i).getUserPassword())) {
                                found = true;
                                Intent categoryIntent = new Intent(LoginActivity.this, CategoryActivity.class);
                                startActivity(categoryIntent);
                            }

                    if (found == false) {
                        Toast.makeText(this, "Wrong in username or password!", Toast.LENGTH_SHORT).show();
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
        }

    }


}
