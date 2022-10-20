package com.example.profile.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.profile.Const.APP
import com.example.profile.R
import com.example.profile.adapter.ProfileAdapter
import com.example.profile.databinding.FragmentStartBinding
import com.example.profile.db.structure.ProfileModel


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter:ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStartBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init()
    {
        val viewModel = ViewModelProvider(this)[StartViewModel::class.java]
        viewModel.initDB()
        recyclerView = binding.rvProfile
        adapter = ProfileAdapter()
        recyclerView.adapter = adapter

        viewModel.getAllProfiles().observe(viewLifecycleOwner) { listProfiles ->
            adapter.setList(listProfiles.asReversed())
        }

        binding.btnNext.setOnClickListener{
            APP.navController.navigate(R.id.action_startFragment_to_addProfileFragment)
        }
    }


    companion object {

        fun clickProfile(profileModel: ProfileModel)
        {
            val bundle = Bundle()
            bundle.putSerializable("profile",profileModel)
            APP.navController.navigate(R.id.action_startFragment_to_detailFragment,bundle)
        }
    }


}