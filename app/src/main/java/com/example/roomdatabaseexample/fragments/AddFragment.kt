package com.example.roomdatabaseexample.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.Viewmodels.UserViewModel
import com.example.roomdatabaseexample.data.User
import com.example.roomdatabaseexample.databinding.FragmentAddBinding
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import android.provider.MediaStore

import android.net.Uri
import android.util.Patterns


class AddFragment : Fragment() {

    var binding: FragmentAddBinding? = null
    val PICK_IMAGE = 1
    var image: ByteArray? = null
    private lateinit var mUserViewModel: UserViewModel
    var MobilePattern = "[0-9]{10}"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding?.add?.setOnClickListener({
            insertUserdata()
        })
        binding?.circleImageView?.setOnClickListener({
            pickImageToGallary()
        })
        return binding?.root
    }


    private fun pickImageToGallary() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }

    private fun insertUserdata() {
        var firstname = binding?.firstname?.text.toString()
        var lastname = binding?.lastname?.text.toString()
        var mobile = binding?.mobile?.text.toString()
        var age = binding?.age?.text.toString()

        if (inputCheck(firstname, lastname, Integer.parseInt(age), validCellPhone(mobile))) {
            val user = User(
                0,
                image,
                firstname,
                lastname,
                age,
                mobile
            )
            lifecycleScope.launchWhenCreated {
                mUserViewModel.addUser(user)
            }

            Toast.makeText(requireContext(), "successfully send data", Toast.LENGTH_SHORT)
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "please Fill All out Of Filed", Toast.LENGTH_SHORT)
        }

    }

    private fun inputCheck(
        firstname: String,
        lastname: String,
        age: Int,
        mobile: Boolean
    ): Boolean {
        return !(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname) && TextUtils.isEmpty(age.toString()) && mobile == true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            var uri = data.getData();
            val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, uri)
            binding?.circleImageView?.setImageBitmap(bitmap)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            image = stream.toByteArray()
        }
    }

    fun validCellPhone(number: String?): Boolean {
        return Patterns.PHONE.matcher(number).matches()
    }
}