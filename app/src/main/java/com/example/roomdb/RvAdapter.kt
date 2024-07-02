package com.example.roomdb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.data.User
import com.example.roomdb.data.UserViewModel
import com.example.roomdb.fragment.update.UpdateFragment

class RvAdapter(private val context: Context,val navController: NavController):RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    private var userData:List<User> = listOf()
//    val userViewModel=ViewModelProvider(context as FragmentActivity).get(UserViewModel::class.java)
//    userViewModel.deleteData(context,userData[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val first_name: TextView =itemView.findViewById(R.id.first_Name)
        val last_name: TextView =itemView.findViewById(R.id.last_name)
        val age: TextView =itemView.findViewById(R.id.age)
        val id:TextView=itemView.findViewById(R.id.serial_no)
        val row_layout:LinearLayout=itemView.findViewById(R.id.row_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_row,parent,false))

    }

    override fun onBindViewHolder(holder: RvAdapter.ViewHolder, position: Int) {
        val user:User=userData[position]
        holder.first_name.text=user.firstName
        holder.last_name.text=user.lastName
        holder.age.text=user.age.toString()
        holder.id.text=user.id.toString()

        holder.row_layout.setOnClickListener{
            val args = Bundle()
            user.id?.let { it1 -> args.putInt("Id", it1) }
            args.putString("firstName",user.firstName)
            args.putString("lastName",user.lastName)
            args.putInt("Age",user.age)
            navController.navigate(R.id.action_listFragment_to_updateFragment,args)
        }
    }

    override fun getItemCount(): Int {
        return userData.size
    }
    fun setData(data : List<User>){
        userData = data
        notifyDataSetChanged()
    }
    fun getUserAt(position: Int): User{
        return userData[position]
    }


}