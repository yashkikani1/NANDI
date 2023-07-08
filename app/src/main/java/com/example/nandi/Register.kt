package com.example.nandi
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignup: Button
    private lateinit var textViewLogin: TextView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonSignup = findViewById(R.id.buttonSignup)
        textViewLogin = findViewById(R.id.textViewLogin)

        buttonSignup.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            // Call the register method
            register(email, password)
        }

        textViewLogin.setOnClickListener {
            // Handle the click event for "Already have an account? Login" option
            navigateToLoginActivity()
        }
    }

    private fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration success, display a toast and navigate to the login activity
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    navigateToLoginActivity()
                } else {
                    // Registration failed, display a toast with the error message
                    val errorMessage = task.exception?.message
                    Toast.makeText(this, "Registration failed: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Optional: Finish the current activity to prevent returning to the registration activity
    }
}
