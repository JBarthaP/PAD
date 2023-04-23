package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import es.ucm.fdi.teamup.Controlador;
import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.database.AppDatabase;
import es.ucm.fdi.teamup.database.daos.DAOUser;
import es.ucm.fdi.teamup.database.entities.User;
import es.ucm.fdi.teamup.database.repositories.UserRepository;
import es.ucm.fdi.teamup.database.repositories.UserRepositoryImp;
import es.ucm.fdi.teamup.models.Utils;

public class LoginActivity extends AppCompatActivity {

    public final static String TAG = LoginActivity.class.getSimpleName();

    private TextInputLayout usernameTxt;

    private TextInputLayout passwordTxt;

    private ImageView login_check;

    private TextView register_text;

    private TextView error_message;


    private Controlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());

        DAOUser daoUser = db.daoUser();

        UserRepository userRepo = new UserRepositoryImp(daoUser);

        usernameTxt = findViewById(R.id.userNameTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        login_check = findViewById(R.id.login_check);
        register_text = findViewById(R.id.register_text);
        error_message = findViewById(R.id.error_message);
        controlador = (Controlador) getApplication();

        login_check.setOnClickListener(view -> {
            String userName = Utils.getInputValueAsString(usernameTxt);
            String password = Utils.getInputValueAsString(passwordTxt);

            if (!userName.equals("") && !password.equals("")) {
                User userLogged = userRepo.findUserByNameAndPassword(userName, password);
                if (userLogged != null) {
                    controlador.setUserLogged(userLogged);
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Log.e(TAG, "Error en el usuario o contraseña introducidas");
                    error_message.setText(getString(R.string.generic_error_login));
                }

            } else {
                Log.e(TAG, "El usuario o la contraseña estan vacias");
                error_message.setText(getString(R.string.empty_inputs));
            }
        });

        register_text.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}