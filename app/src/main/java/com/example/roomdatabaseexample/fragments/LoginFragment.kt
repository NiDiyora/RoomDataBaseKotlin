package com.example.roomdatabaseexample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.databinding.FragmentLoginBinding
import androidx.appcompat.app.AppCompatActivity





class LoginFragment : Fragment() {

var binding:FragmentLoginBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        // findNavController().navigate(R.id.action_loginFragment_to_listFragment)
        return binding?.root
    }

}