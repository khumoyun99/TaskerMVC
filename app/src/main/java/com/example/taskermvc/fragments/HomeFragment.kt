package com.example.taskermvc.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskermvc.R
import com.example.taskermvc.adapter.TaskAdapter
import com.example.taskermvc.adapter.TypeAdapter
import com.example.taskermvc.databinding.CustomListDialogBinding
import com.example.taskermvc.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var typeAdapter: TypeAdapter

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.fab.setOnClickListener {
            binding.fab.setImageResource(R.drawable.ic_close)
            binding.fab.imageTintList = ColorStateList.valueOf(R.color.black)

            val popupMenu = PopupMenu(requireContext(),it)
            popupMenu.inflate(R.menu.menu)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                popupMenu.setForceShowIcon(true)
            }

            popupMenu.setOnMenuItemClickListener { item->
                when(item.itemId){
                    R.id.task->{
                        Toast.makeText(requireContext(), "task", Toast.LENGTH_SHORT).show()
                        binding.fab.setImageResource(R.drawable.ic_add_24)
                        binding.fab.imageTintList = ColorStateList.valueOf(R.color.fab_close_color)

                        findNavController().navigate(R.id.taskFragment)
                    }
                    R.id.list->{
                        Toast.makeText(requireContext(), "list", Toast.LENGTH_SHORT).show()
                        val alertDialog = AlertDialog.Builder(requireContext())
                        val customListDialogBinding = CustomListDialogBinding.inflate(layoutInflater)
                        alertDialog.setView(customListDialogBinding.root)
                        alertDialog.setCancelable(true)
                        val builder = alertDialog.create()

                        customListDialogBinding.addBtn.setOnClickListener {
                            val typeText = customListDialogBinding.typeEt.text.toString()
                            if(typeText.isNotEmpty()){
                                Toast.makeText(requireContext(), typeText, Toast.LENGTH_SHORT).show()
                                builder.dismiss()
                                binding.fab.setImageResource(R.drawable.ic_add_24)
                                binding.fab.imageTintList = ColorStateList.valueOf(R.color.fab_close_color)
                            }else{
                                Toast.makeText(
                                    requireContext(),
                                    "Please write type!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        customListDialogBinding.cancelBtn.setOnClickListener {
                            builder.dismiss()
                            binding.fab.setImageResource(R.drawable.ic_add_24)
                            binding.fab.imageTintList = ColorStateList.valueOf(R.color.fab_close_color)
                        }

                        builder.show()


                    }

                }
                true
            }
            popupMenu.show()
        }








        return binding.root
    }


}