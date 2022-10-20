package com.example.profile.screens.detail

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.profile.Const.APP
import com.example.profile.R
import com.example.profile.databinding.FragmentDetailBinding
import com.example.profile.db.structure.ProfileModel
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream


class DetailFragment : Fragment() {


    private var updatePhoto:Boolean = false
    private lateinit var binding:FragmentDetailBinding
    private lateinit var curProfileModel: ProfileModel
    private lateinit var viewModel: DetailViewModel
    private lateinit var launcher: ActivityResultLauncher<Intent>

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        curProfileModel = arguments?.getSerializable("profile",
            ProfileModel::class.java) as ProfileModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init()
    {
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->
            if(result.resultCode == RESULT_OK)
            {
                val uri = result.data?.data
                if (uri != null) {
                    Picasso.get().load(uri).fit().into(binding.updateImageView)
                    updatePhoto = true
                }
            }
        }

        binding.tvSurnameDetail.setText(curProfileModel.surname)
        binding.tvNameDetail.setText(curProfileModel.name)
        binding.tvPatronymicDetail.setText(curProfileModel.patronymic)
        binding.tvDateDetail.setText(curProfileModel.date)
        binding.tvEmailDetail.setText(curProfileModel.email)
        binding.tvPhoneDetail.setText(curProfileModel.phone)

        val curPhoto =
            curProfileModel.photo?.let {
                BitmapFactory.decodeByteArray(curProfileModel.photo, 0,
                    it.size)
            }

        if(curProfileModel.photo!=null) {
            updatePhoto = true
            binding.updateImageView.setImageBitmap(curPhoto)
        }


        binding.updateButton.setOnClickListener {
            updateClick()
            APP.navController.navigate(R.id.action_detailFragment_to_startFragment)
        }

        binding.deleteButton.setOnClickListener {
            viewModel.delete(curProfileModel){}
            APP.navController.navigate(R.id.action_detailFragment_to_startFragment)
        }

        binding.backButton.setOnClickListener {
            APP.navController.navigate(R.id.action_detailFragment_to_startFragment)
        }

        binding.updateImageView.setOnClickListener {
            showImageGallery()
        }

        binding.deletePhotoButton.setOnClickListener {
           binding.updateImageView.setImageDrawable(
               activity?.let { it1 -> ContextCompat.getDrawable(it1, R.drawable.ic_baseline_account_box_200) }
           )
           updatePhoto = false
        }
    }


    private fun updateClick()
    {
        val id = curProfileModel.id

        val surname = binding.tvSurnameDetail.text.toString()
        val name = binding.tvNameDetail.text.toString()
        val patronymic = binding.tvPatronymicDetail.text.toString()
        val date = binding.tvDateDetail.text.toString()
        val email = binding.tvEmailDetail.text.toString()
        val phone = binding.tvPhoneDetail.text.toString()

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

        if(updatePhoto) {
            val bitmap = (binding.updateImageView.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            photo = stream.toByteArray()
        }

        val updateProfileModel = ProfileModel(id=id,surname = surname,name = name, patronymic = patronymic,
            date = date, email = email, phone = phone,photo=photo)

        viewModel.update(updateProfileModel){}
    }

    private fun showImageGallery()
    {
        val intent = Intent()
        intent.type = APP.getString(R.string.ImageType)
        intent.action = Intent.ACTION_GET_CONTENT
        launcher.launch(intent)
    }


}