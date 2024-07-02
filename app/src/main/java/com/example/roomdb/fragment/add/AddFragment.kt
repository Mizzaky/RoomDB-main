package com.example.roomdb.fragment.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdb.R
import com.example.roomdb.data.User
import com.example.roomdb.data.UserViewModel

class AddFragment : Fragment(){

    private lateinit var mUserViewModel:UserViewModel
    private lateinit var addFirstName:EditText
    private lateinit var addLastName:EditText
    private lateinit var addAge:EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        addFirstName=view.findViewById(R.id.addFirstName)
        addLastName=view.findViewById(R.id.addLastName)
        addAge=view.findViewById(R.id.addAge)

        val addbtn:Button=view.findViewById(R.id.button)
        addbtn.setOnClickListener{
            insertDataToDatabase()
        }



        return view
    }

    private fun insertDataToDatabase() {
        val firstName=addFirstName.text.toString()
        val lastName=addLastName.text.toString()
        val age=addAge.text

        if(inputCheck(firstName,lastName,age)){
            //Create a User Object
            val user= User(firstName,lastName,age.toString().toInt())
            //Add Data to Database
            mUserViewModel.addUser(requireContext(),user)
            Toast.makeText(requireContext(),"Successfully added!",Toast.LENGTH_SHORT).show()
            //Navigate Back

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fll out all fields",Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(firstName:String,lastName:String,age:Editable):Boolean{
        return!(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


}