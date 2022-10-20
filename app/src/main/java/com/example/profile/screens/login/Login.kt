package com.example.profile.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.profile.Const.APP
import com.example.profile.R
import com.example.profile.databinding.FragmentLoginBinding


class Login : Fragment() {


    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel:LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init()
    {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.initAdmin()

        binding.loginButton.setOnClickListener {
            login()
        }
    }

    private fun login()
    {

        val email = binding.emailEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()

        if (!viewModel.validDate(email, password))
        {
            Toast.makeText(APP, getString(R.string.BadLengthLogin), Toast.LENGTH_SHORT).show()
            return
        }

        val result = viewModel.authorization(email,password)

        if(result)
        {
            APP.navController.navigate(R.id.action_login_to_startFragment)
        }
        else
        {
            Toast.makeText(APP, APP.getString(R.string.BadLogin), Toast.LENGTH_SHORT).show()
        }
    }

}