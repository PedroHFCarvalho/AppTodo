package com.carvalho.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carvalho.todo.databinding.FragmentFormBinding

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFormBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

}