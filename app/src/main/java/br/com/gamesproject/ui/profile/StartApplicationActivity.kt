package br.com.gamesproject.ui.profile

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.gamesproject.R
import br.com.gamesproject.data.api.RetrofitClient
import br.com.gamesproject.data.api.User
import retrofit2.Call

class StartApplicationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start_application)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getUserProfile(email: String) {
        RetrofitClient.instance.getProfile(email).enqueue(object : retrofit2.Callback<User> {
            override fun onResponse(call: Call<User>, response: retrofit2.Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()

                } else {
                    Toast.makeText(this@StartApplicationActivity, "Erro ao carregar perfil", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@StartApplicationActivity, "Falha na comunicação", Toast.LENGTH_SHORT).show()
            }
        })
    }

}