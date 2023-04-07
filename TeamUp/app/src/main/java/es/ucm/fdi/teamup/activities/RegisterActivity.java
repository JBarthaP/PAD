package es.ucm.fdi.teamup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import es.ucm.fdi.teamup.R;
import es.ucm.fdi.teamup.database.AppDatabase;
import es.ucm.fdi.teamup.database.daos.DAOUser;
import es.ucm.fdi.teamup.database.entities.User;
import es.ucm.fdi.teamup.database.repositories.UserRepository;
import es.ucm.fdi.teamup.database.repositories.UserRepositoryImp;

public class RegisterActivity extends AppCompatActivity {

    Button insertButton;

    public final static String TAG = RegisterActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());

        DAOUser daoUser = db.daoUser();

        UserRepository userRepo = new UserRepositoryImp(daoUser);


        insertButton = findViewById(R.id.insertButton);

        insertButton.setOnClickListener(view -> {
            User user = new User();
            user.setFirstName("Prueba");
            user.setLastName("Apellido");
            user.setUsername("Test");
            user.setPassword("Password");
            userRepo.insertUser(user);
            Log.d(TAG, "Usuario creado correctamente");

        });


    }
}