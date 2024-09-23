package br.com.gamesproject.ui.login

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
import br.com.gamesproject.ui.profile.StartApplicationActivity
import br.com.gamesproject.ui.register.SingUpActivity
import retrofit2.Call

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailEditText = findViewById<EditText>(R.id.emailSignInEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordSignInEditText)
        val button = findViewById<Button>(R.id.buttonSignIn)
        val signUpLinkTextView = findViewById<TextView>(R.id.signUpLinkTextView)


        signUpLinkTextView.setOnClickListener{
            val intent = Intent(this, SingUpActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginUser(email, password)
        }



    }
    private fun loginUser(email: String, password: String) {
        val user = User(null, email, password, null)

        RetrofitClient.instance.login(user).enqueue(object : retrofit2.Callback<User> {
            override fun onResponse(call: Call<User>, response: retrofit2.Response<User>) {
                if (response.isSuccessful) {
                    val intent = Intent(this@SignInActivity, StartApplicationActivity::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignInActivity, "Falha no login", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@SignInActivity, "Falha na comunicação", Toast.LENGTH_SHORT).show()
            }
        })
    }
}