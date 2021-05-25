package com.sonda.bangkit.fundamentaldua.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sonda.bangkit.fundamentaldua.R
import com.sonda.bangkit.fundamentaldua.model.User
import com.sonda.bangkit.fundamentaldua.adapter.ViewPagerAdapter

class UserDetail : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabs: TabLayout
    companion object {
        const val EXTRA_USER = "extra_user"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val name: TextView = findViewById(R.id.detail_name)
        val avatar: ImageView = findViewById(R.id.detail_avatar)
        val location: TextView = findViewById(R.id.detail_location)
        val company: TextView = findViewById(R.id.detail_company)
        val repository: TextView = findViewById(R.id.detail_repository)
        val following: TextView = findViewById(R.id.detail_following)
        val followers: TextView = findViewById(R.id.detail_followers)
        val username: TextView = findViewById(R.id.detail_username)
       viewPager = findViewById(R.id.view_pager)
       tabs = findViewById(R.id.tabs)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        name.text = user.name
        location.text = user.location
        company.text = user.company
        repository.text = user.repository
        following.text = user.following
        followers.text = user.followers
        username.text = user.username

        Glide.with(this)
            .load(user.avatar)
            .apply(RequestOptions())
            .into(avatar)

        val Back: TextView = findViewById(R.id.detail_back)
        Back.setOnClickListener(this)
        viewPagerSetUp()

    }

    private fun viewPagerSetUp(){
        val viewpagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewpagerAdapter

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.detail_back -> {
                val moveIntent = Intent(this@UserDetail, MainActivity::class.java)
                moveIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

                startActivity(moveIntent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }


}