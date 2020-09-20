package com.robin.biblosearch.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.robin.biblosearch.R


val GoogleBooksScopeURL  get() = "https://www.googleapis.com/auth/books"
val RC_SIGN_IN  get() = 1000
lateinit var mGoogleSignInClient : GoogleSignInClient
private const val TAG = "LoginActivity"
private lateinit var button: SignInButton

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(Scope(GoogleBooksScopeURL))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        button = findViewById(R.id.signInButton)
        button.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onStart() {
        super.onStart()
        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        if (isAppSignedIn(account)) {
            goToMainActivity()
        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>?) {
        Log.d(TAG, "handleSignInResult:" + task?.isSuccessful )
        try {
            val account = task?.getResult<ApiException>(ApiException::class.java)
            if (isAppSignedIn(account)){ goToMainActivity() }
        } catch (e: ApiException) { Log.w(TAG, "handleSignInResult:error", e) }
    }


    private fun  isAppSignedIn(account: GoogleSignInAccount?) : Boolean{
        return account != null && GoogleSignIn.hasPermissions(account, Scope(GoogleBooksScopeURL))
    }
}