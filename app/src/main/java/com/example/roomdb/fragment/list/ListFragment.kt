package com.example.roomdb.fragment.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.RvAdapter
import com.example.roomdb.data.UserViewModel
import com.example.roomdb.databinding.FragmentListBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class ListFragment : Fragment(),MenuProvider {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentListBinding
    private lateinit var rvAdapter: RvAdapter
    lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {


        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        // setupMenu()

        binding.floatingActionButton.setOnClickListener {
            navController.navigate(R.id.action_listFragment_to_addFragment)
        }

        rvAdapter = RvAdapter(requireContext(), navController)
        binding.recyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.adapter = rvAdapter

        mUserViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        try {
            mUserViewModel.readAllData(requireContext()).observe(requireActivity(), Observer {
                rvAdapter.setData(it)
            })
        } catch (e: Exception) {
            Log.d("roomDatabase error", "onCreateView: ${e.message}")
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)

        return binding.root
    }


    //Delete by swiping right to left
    private val itemTouchHelperCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = rvAdapter.getUserAt(position)
                val bottomSheet: BottomSheetDialog =
                    BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
                bottomSheet.setContentView(R.layout.delete_dialog)
                val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
                val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

                textViewYes?.setOnClickListener {
                    mUserViewModel.deleteData(requireContext(), note)
                    Toast.makeText(requireContext(), "Successfully deleted", Toast.LENGTH_SHORT)
                        .show()
                    bottomSheet.dismiss()
//                findNavController().popBackStack()
                }
                textViewNo?.setOnClickListener {
                    rvAdapter.notifyItemChanged(position)
                    bottomSheet.dismiss()

                }

                bottomSheet.show()
            }
        }
}


