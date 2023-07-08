package com.example.nandi
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var textViewForgotPassword: TextView
    private lateinit var textViewSignup: TextView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword)
        textViewSignup = findViewById(R.id.textViewSignup)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            // Call the login method
            login(email, password)
        }

        textViewForgotPassword.setOnClickListener {
            // Handle the click event for "Forgot Password?" option
            // Add your code here to handle password reset functionality
        }

        textViewSignup.setOnClickListener {
            // Handle the click event for "Don't have an account? Sign up" option
            navigateToSignupActivity()
        }
    }

    private fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login success, display a toast and navigate to the main activity
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    // Add your navigation code here
                } else {
                    // Login failed, display a toast with the error message
                    val errorMessage = task.exception?.message
                    Toast.makeText(this, "Login failed: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToSignupActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
