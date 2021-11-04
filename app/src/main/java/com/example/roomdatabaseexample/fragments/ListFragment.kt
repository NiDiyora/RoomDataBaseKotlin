package com.example.roomdatabaseexample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseexample.Adapters.UserListAdapter
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.Viewmodels.UserViewModel
import com.example.roomdatabaseexample.databinding.FragmentAddBinding
import com.example.roomdatabaseexample.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    var binding: FragmentListBinding? = null
    var userListAdapter: UserListAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding?.floatingActionButton?.setOnClickListener({
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        })
        userListAdapter = UserListAdapter()
        binding?.rllistuser?.layoutManager = LinearLayoutManager(activity)
        binding?.rllistuser?.adapter = userListAdapter
        lifecycle.coroutineScope.launch {
            mUserViewModel.readAlldata.collect {
                userListAdapter?.setuser(it)
            }
        }

        return binding?.root
    }


}