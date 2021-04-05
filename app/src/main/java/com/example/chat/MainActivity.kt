  package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.chat.Fragments.ChatsFragment
import com.example.chat.Fragments.SearchFragment
import com.example.chat.Fragments.SettingsFragment
import com.example.chat.ModelClasses.Users
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_message_chat.*

  class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide() // hide top bar

        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val refUser = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser!!.uid)

        // Fragments
        val tabLayout: TabLayout = tab_layout
        val viewPager: ViewPager = view_pager
        val viewPagerAdapter= ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(ChatsFragment(), "Chats")
        viewPagerAdapter.addFragment(SearchFragment(), "Search")
        viewPagerAdapter.addFragment(SettingsFragment(), "Settings")
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        logout_temp.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@MainActivity, Welcome::class.java)
            startActivity(intent)
            finish()
        }

        // display the user name and profile picture on main activity
        refUser!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshotUser: DataSnapshot) {
                if(snapshotUser.exists()){
                    val user: Users? = snapshotUser.getValue(Users::class.java)
                    user_name.text = user!!.getname()
                    Picasso.get().load(user.getprofile()).into(profile_image)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    // class for setting up fragments
    internal class ViewPagerAdapter(fragmentManager: FragmentManager):
            FragmentPagerAdapter(fragmentManager) {
        private val fragments: ArrayList<Fragment> = ArrayList<Fragment>()
        private val titles: ArrayList<String> = ArrayList<String>()

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return  fragments[position]
        }

        fun addFragment(fragment: Fragment, title: String){
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }

}