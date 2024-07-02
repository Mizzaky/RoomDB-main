package com.example.roomdb.fragment.update

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdb.MainActivity
import com.example.roomdb.R
import com.example.roomdb.data.User
import com.example.roomdb.data.UserViewModel
import com.example.roomdb.databinding.FragmentUpdateBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class UpdateFragment:Fragment()  {
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var FirstName: EditText
    private lateinit var LastName: EditText
    private lateinit var Age: EditText
    private lateinit var updatebtn:Button
    private var id:Int=-1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        val binding=FragmentUpdateBinding.inflate(inflater, container, false)


        // Enable options menu in the fragment

        setHasOptionsMenu(true)
        id= arguments?.getInt("Id")?:-1
        val first_name= arguments?.getString("firstName",null)
        val last_name= arguments?.getString("lastName",null)
        val age= arguments?.getInt("Age",0)
        Log.d("hello world", "onCreateView: $id $first_name")


        FirstName=binding.updateFirstName
        LastName=binding.updateLastName
        Age=binding.updateAge
        updatebtn=binding.updateButton

        FirstName.setText(first_name)
        LastName.setText(last_name)
        Age.setText(age.toString())

        mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)

        try {
            updatebtn.setOnClickListener{
                if (id != -1) {
                    updateItem(id)
                }
            }
        }catch (e : Exception){

        }



        return binding.root
    }


    private fun updateItem(id : Int) {
        val firstName=FirstName.text.toString()
        val lastName=LastName.text.toString()
        val age=Age.text.toString().toInt()
        try {
            if(inputCheck(firstName,lastName)){
                //Create a User Object
                val updateUser= User(firstName,lastName,age)
                //Update Current User
                updateUser.id = id
                mUserViewModel.updateData(requireContext(),updateUser)
                Toast.makeText(requireContext(),"Successfully updated!", Toast.LENGTH_SHORT).show()
                //Navigate Back
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }else{
                Toast.makeText(requireContext(),"Please fll out all fields", Toast.LENGTH_LONG).show()
            }
        }catch (e : Exception){
            Log.d("hello", "updateItem: ${e.message}")
        }


    }

    private fun inputCheck(firstName:String,lastName:String):Boolean{
        return!(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) )
    }


}

