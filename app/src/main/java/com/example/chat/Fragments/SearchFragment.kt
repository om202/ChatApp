package com.example.chat.Fragments


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.AdapterClasses.UserAdapter
import com.example.chat.ModelClasses.Users
import com.example.chat.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private var userAdapter: UserAdapter? = null
    private var mUsers: List<Users>? = null
    private var recyclerView_Search: RecyclerView? = null
    private var searchEditText: EditText? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mUsers = ArrayList()
        retrieveAllUsers()

        recyclerView_Search = search_list_adapter
//        recyclerView!!.setHasFixedSize(true)
//        recyclerView!!.layoutManager = LinearLayoutManager(context)
//        searchEditText = view?.findViewById(R.id.search_list_ET)

//        searchEditText?.addTextChangedListener(object : TextWatcher{
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                searchForUsers(s.toString().toLowerCase())
//
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                TODO("Not yet implemented")
//            }
//
//        })
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    private fun retrieveAllUsers() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
        val refUsers = FirebaseDatabase.getInstance().reference.child("Users")

        refUsers.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                (mUsers as ArrayList<Users>).clear()
//                if (searchEditText!!.text.toString() == "") {
                    for (s in snapshot.children) {
                        val user: Users? = snapshot.getValue(Users::class.java)
//                        if (!(user!!.getUID()).equals(firebaseUser)) {
                        if (user != null) {
                            (mUsers as ArrayList<Users>).add(user)
                        }
//                        }
                    }

//                    Log.d("M_USERS", (mUsers as ArrayList<Users>)[0].toString())
                    userAdapter = UserAdapter(context!!, mUsers!! as ArrayList<Users>, isChatCheck = false)
//                    recyclerView_Search!!.adapter = userAdapter

                }
//            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

//    private fun searchForUsers(str: String) {
//        val firebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
//        val queryUsers =
//            FirebaseDatabase.getInstance().reference.child("Users").orderByChild("search")
//                .startAt(str)
//                .endAt(str + "\uf8ff")
//
//        queryUsers.addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(snapshot_: DataSnapshot) {
//                (mUsers as ArrayList<Users>).clear()
//
//                for (s in snapshot_.children) {
//                    val user: Users? = snapshot_.getValue(Users::class.java)
//                    if (!(user!!.getUID()).equals(firebaseUser)) {
//                        (mUsers as ArrayList<Users>).add(user)
//                    }
//                }
//                userAdapter = UserAdapter(context!!, mUsers!!, isChatCheck = false)
//                recyclerView!!.adapter = userAdapter
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
//    }
}


