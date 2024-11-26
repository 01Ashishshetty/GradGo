package com.example.yourapp

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.gradgo.Profile
import com.example.gradgo.R
import de.hdodenhof.circleimageview.CircleImageView

class MenuActivity : AppCompatActivity() {

    data class UserProfile(
        val name: String,
        val title: String,
        val imageUrl: String
    )

    private val dummyUser = UserProfile(
        name = "Sharan kumar",
        title = "associate software engineer",
        imageUrl = "https://example.com/profile.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        setupUserProfile()
        setupNavigationListeners()
    }

    private fun setupUserProfile() {
        findViewById<TextView>(R.id.userName).text = dummyUser.name
        findViewById<TextView>(R.id.userTitle).text = dummyUser.title

        val profileImage = findViewById<CircleImageView>(R.id.profileImage)
        Glide.with(this)
            .load(dummyUser.imageUrl)
            .placeholder(R.drawable.profile_placeholder)
            .error(R.drawable.profile_placeholder)
            .into(profileImage)
    }

    private fun setupNavigationListeners() {
        findViewById<LinearLayout>(R.id.profileSection).setOnClickListener {
            navigateTo(Profile::class.java)
        }

        findViewById<LinearLayout>(R.id.jobPostsSection).setOnClickListener {
            navigateTo(Profile::class.java)
        }

        findViewById<LinearLayout>(R.id.appliedSection).setOnClickListener {
            navigateTo(Profile::class.java)
        }

        findViewById<LinearLayout>(R.id.otherSection).setOnClickListener {
            navigateTo(Profile::class.java)
        }
    }

    private fun navigateTo(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}