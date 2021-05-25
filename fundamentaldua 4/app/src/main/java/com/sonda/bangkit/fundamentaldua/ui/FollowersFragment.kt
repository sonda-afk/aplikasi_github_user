package com.sonda.bangkit.fundamentaldua.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sonda.bangkit.fundamentaldua.R
import com.sonda.bangkit.fundamentaldua.adapter.FollowersAdapter
import com.sonda.bangkit.fundamentaldua.model.User
import com.sonda.bangkit.fundamentaldua.ui.viewModel.FollowersVM


class FollowersFragment : Fragment() {
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var rvShowFollowers: RecyclerView
    private lateinit var progressBarFollowers: ProgressBar
    private lateinit var followersAdapter: FollowersAdapter
    private lateinit var viewModel: FollowersVM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_followers, container, false)

        rvShowFollowers = view.findViewById(R.id.recycleShowFollowers)
        progressBarFollowers = view.findViewById(R.id.progressBarFollowers)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followersAdapter = FollowersAdapter()
        showRecylcerConfig()
        showFollowersViewModelConfig()

        val dataUser = activity!!.intent.getParcelableExtra<User>(EXTRA_USER) as User
        fetchData(dataUser.username.toString())

    }

    private fun showFollowersViewModelConfig() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowersVM::class.java)
    }

    private fun fetchData(username: String) {
        viewModel.getFollowers(username)
        activity?.let {
            viewModel.getUser().observe(it, Observer { data->

                if(data != null){
                    progressBarFollowers.visibility = View.GONE
                    followersAdapter.listUser = data
                }
            })
        }
    }

    private fun showRecylcerConfig() {
        rvShowFollowers.setHasFixedSize(true)
        rvShowFollowers.layoutManager = LinearLayoutManager(activity)
        rvShowFollowers.adapter = followersAdapter
    }

}