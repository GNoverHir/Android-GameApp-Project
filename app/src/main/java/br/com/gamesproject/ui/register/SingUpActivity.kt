package br.com.gamesproject.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.gamesproject.R
import br.com.gamesproject.data.api.RetrofitClient
import br.com.gamesproject.data.api.User
import br.com.gamesproject.ui.login.SignInActivity
import retrofit2.Call

class SingUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nicknameEditText = findViewById<EditText>(R.id.nicknameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordSignUpEditText)
        val emailEditText = findViewById<EditText>(R.id.emailSignUpEditText)
        val button = findViewById<Button>(R.id.buttonSignUp)
        val signInLinkTextView = findViewById<TextView>(R.id.signInLinkTextView)

        signInLinkTextView.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{
            val nickname = nicknameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()
            registerUser(nickname, email, password)

        }


    }

    private fun registerUser(nickname: String,email: String, password: String) {
        val user = User(nickname, email, password, null)

        RetrofitClient.instance.register(user).enqueue(object : retrofit2.Callback<User> {
            override fun onResponse(call: Call<User>, response: retrofit2.Response<User>) {
                if (response.isSuccessful) {
                    val intent = Intent(this@SingUpActivity, SignInActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SingUpActivity, "Falha no registro", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@SingUpActivity, "Falha na comunicação", Toast.LENGTH_SHORT).show()
            }
        })
    }

}