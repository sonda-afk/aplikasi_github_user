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
import com.sonda.bangkit.fundamentaldua.adapter.FollowingAdapter
import com.sonda.bangkit.fundamentaldua.model.User
import com.sonda.bangkit.fundamentaldua.ui.viewModel.FollowingVM


class FollowingFragment : Fragment() {
    companion object {
        const val EXTRA_USER = "extra_user"

    }

    private lateinit var rvShowFollowing: RecyclerView
    private lateinit var progressBarFollowing: ProgressBar
    private lateinit var followingAdapter: FollowingAdapter
    private lateinit var viewModel: FollowingVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_following, container, false)

        rvShowFollowing = view.findViewById(R.id.recycleShowFollowing)
        progressBarFollowing = view.findViewById(R.id.progressBarFollowing)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followingAdapter = FollowingAdapter()
        showRecylcerConfig()
        showFollowingViewModelConfig()

        val user = activity!!.intent.getParcelableExtra<User>(EXTRA_USER) as User
        fetchData(user.username.toString())

    }

    private fun fetchData(username: String) {
        viewModel.getFollowing(username)
        activity?.let {
            viewModel.getUser().observe(it, Observer { data->

                if(data != null){
                    progressBarFollowing.visibility = View.GONE
                    followingAdapter.listUser = data
                }
            })
        }
    }

    private fun showFollowingViewModelConfig() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowingVM::class.java)
    }


    private fun showRecylcerConfig() {
        rvShowFollowing.setHasFixedSize(true)
        rvShowFollowing.layoutManager = LinearLayoutManager(activity)
        rvShowFollowing.adapter = followingAdapter


    }

}