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

public class RegisterActivity extends AppCompatActivity {


    public final static String TAG = RegisterActivity.class.getSimpleName();

    ImageView insertButton;

    Controlador controlador;

    TextInputLayout usernameTxt;
    TextInputLayout passwordTxt;
    TextInputLayout password2Txt;

    private TextView error_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        error_message = findViewById(R.id.error_message);

        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());

        DAOUser daoUser = db.daoUser();

        UserRepository userRepo = new UserRepositoryImp(daoUser);


        insertButton = findViewById(R.id.insertButton);

        insertButton.setOnClickListener(view -> {

            usernameTxt = findViewById(R.id.userNameTxt);
            String userName = Utils.getInputValueAsString(usernameTxt);

            passwordTxt = findViewById(R.id.passwordTxt);
            String password = Utils.getInputValueAsString(passwordTxt);

            password2Txt = findViewById(R.id.password2Txt);
            String password2 = Utils.getInputValueAsString(password2Txt);

            controlador = (Controlador) getApplication();


            if (!userName.equals("") && !password.equals("")) {
                //En el caso que son correctos crear usuario
                if (password.equals(password2)) {
                    User user = new User(userName, password);
                    userRepo.insertUser(user);
                    Log.d(TAG, userName + password + password2);
                    Log.d(TAG, "Usuario creado correctamente");

                    controlador.setUserLogged(user);
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.e(TAG, "Contraseñas no iguales");
                    error_message.setText("Contraseñas no iguales");
                }

            } else {
                //En el caso que no mensaje de error con que campo falla
                Log.e(TAG, "El usuario o la contraseña estan vacias");
                error_message.setText("El usuario o la contraseña estan vacias");
            }
        });


    }
}