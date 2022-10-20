package com.example.profile.screens.addProfile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.profile.Const.APP
import com.example.profile.R
import com.example.profile.databinding.FragmentAddProfileBinding
import com.example.profile.db.structure.ProfileModel
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream


class AddProfileFragment : Fragment() {


    private var addPhoto:Boolean = false
    private lateinit var binding: FragmentAddProfileBinding
    private lateinit var viewModel:AddProfileViewModel
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init()
    {
        viewModel = ViewModelProvider(this)[AddProfileViewModel::class.java]


        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->
            if(result.resultCode == Activity.RESULT_OK)
            {
                val uri = result.data?.data
                if (uri != null) {
                    Picasso.get().load(uri).fit().into(binding.addImageView)
                    addPhoto = true
                }
            }
        }

        binding.addButton.setOnClickListener {
            add()
        }

        binding.backButton.setOnClickListener {
            APP.navController.navigate(R.id.action_addProfileFragment_to_startFragment)
        }

        binding.addImageView.setOnClickListener {
            showImageGallery()
        }

        binding.deletePhotoButton.setOnClickListener {

            binding.addImageView.setImageDrawable(
                activity?.let { it1 -> ContextCompat.getDrawable(it1, R.drawable.ic_baseline_account_box_200) }
            )
            addPhoto = false
        }
    }


    private fun add()
    {
        val surname = binding.addSurnameEdit.text.toString()
        val name = binding.addNameEdit.text.toString()
        val patronymic = binding.addPatronymicEdit.text.toString()
        val date = binding.addDateEdit.text.toString()
        val email = binding.addEmailEdit.text.toString()
        val phone = binding.addPhoneEdit.text.toString()

        if(!viewModel.validateEmail(email))
        {
            Toast.makeText(APP, getString(R.string.BadEmail), Toast.LENGTH_SHORT).show()
            return
        }

        if(!viewModel.validatePhone(phone))
        {
            Toast.makeText(APP, getString(R.string.BadPhone), Toast.LENGTH_SHORT).show()
            return
        }

        if(!viewModel.validateDate(date))
        {
            Toast.makeText(APP, getString(R.string.BadDate), Toast.LENGTH_SHORT).show()
            return
        }

        var photo:ByteArray? = null

        if(addPhoto) {
            val bitmap = (binding.addImageView.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            photo = stream.toByteArray()
        }

        val profileModel = ProfileModel(surname = surname,name = name, patronymic = patronymic,
            date = date, email = email, phone = phone,photo=photo)

        viewModel.insert(profileModel){}
        APP.navController.navigate(R.id.action_addProfileFragment_to_startFragment)
    }


    private fun showImageGallery()
    {
        val intent = Intent()
        intent.type = APP.getString(R.string.ImageType)
        intent.action = Intent.ACTION_GET_CONTENT
        launcher.launch(intent)
    }


}