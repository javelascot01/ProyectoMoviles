package com.javt.proyectojesusvelasco.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityLoginBinding
import com.javt.proyectojesusvelasco.viewModel.UsuarioViewModel

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: UsuarioViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cargar preferencias de tema
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val isDarkModePreferenced = sharedPreferences.getBoolean("pref_checkbox", false)
        if (isDarkModePreferenced) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val valoresPorDefecto = arrayOf(R.string.user_admin.toString(),R.string.password_admin.toString())

        // Botones
        binding.btnInfo.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.information) +" "+getString(R.string.default_values))
                //.setMessage(getString(R.string.default_values))
                .setItems(valoresPorDefecto) { dialog, which ->
                    if (which == 0) {
                        binding.etUserName.setText("admin")
                    } else if (which == 1) {
                        binding.etPassword.setText("admin")
                    }
                }
                .setPositiveButton(R.string.accept, null)
                .show()
        }


        binding.btnLogin.setOnClickListener(){
            val nombreUsuario = binding.etUserName.text.toString()
            val contrasena = binding.etPassword.text.toString()
            Log.d("Login", "Nombre de usuario: $nombreUsuario, Contraseña: $contrasena")

            viewModel.loginUsuario(nombreUsuario, contrasena) { loginExitoso, mensaje, key ->
                if (loginExitoso) {
                    Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, PantallaPrincipal::class.java)
                    intent.putExtra("nombreUsuario", nombreUsuario)
                    intent.putExtra("idUsuario", key.toString())
                    Log.d("idUsuario", key.toString())
                    startActivity(intent)
                } else {
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btnRegister.setOnClickListener(){
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }




        // Configurar Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        firebaseAuth = FirebaseAuth.getInstance()


        binding.btnLoginGoogle.setOnClickListener {
            signInWithGoogle()
        }
    }

    /**
     * Iniciar sesión con Google
     */
    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    /**
     * Resultado de la actividad de inicio de sesión con Google
     */
    private val googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        Log.d(TAG, "Google sign in result: ${result.resultCode}")
        if (result.resultCode == RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
                Toast.makeText(this, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Google sign in canceled", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Autenticar con Firebase usando Google
     */
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, PantallaPrincipal::class.java)
                    intent.putExtra("nombreUsuario", user?.displayName)
                    startActivity(intent)
                    finish()
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Authentication Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    companion object {
        private const val TAG = "LoginError"
    }
}