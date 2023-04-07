package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

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

    Button insertButton;

    Controlador controlador;

    TextInputLayout first_nameTxt;
    TextInputLayout last_nameTxt;
    TextInputLayout usernameTxt;
    TextInputLayout passwordTxt;
    TextInputLayout password2Txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());

        DAOUser daoUser = db.daoUser();

        UserRepository userRepo = new UserRepositoryImp(daoUser);


        insertButton = findViewById(R.id.insertButton);

        insertButton.setOnClickListener(view -> {

            //Comprobar datos del formulario
            first_nameTxt = findViewById(R.id.firstNameTxt);
            String firstName = Utils.getInputValueAsString(first_nameTxt);

            last_nameTxt = findViewById(R.id.lastNameTxt);
            String lastName = Utils.getInputValueAsString(last_nameTxt);

            usernameTxt = findViewById(R.id.userNameTxt);
            String userName = Utils.getInputValueAsString(usernameTxt);

            passwordTxt = findViewById(R.id.passwordTxt);
            String password = Utils.getInputValueAsString(passwordTxt);

            password2Txt = findViewById(R.id.password2Txt);
            String password2 = Utils.getInputValueAsString(password2Txt);


            if (!userName.equals("") && !password.equals("")) {
                //En el caso que son correctos crear usuario
                if (password.equals(password2)) {
                    User user = new User(firstName, lastName, userName, password);
                    userRepo.insertUser(user);
                    Log.d(TAG, firstName + lastName + userName + password + password2);
                    Log.d(TAG, "Usuario creado correctamente");
                }
                else {
                    Log.e(TAG, "Contraseñas no iguales"); //TODO hacer un mensaje de error y intentar hacer algo parecido a required
                }

            } else {
                //En el caso que no mensaje de error con que campo falla
                Log.e(TAG, "El usuario o la contraseña estan vacias");
            }
        });


    }
}