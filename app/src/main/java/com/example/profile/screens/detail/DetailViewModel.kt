package com.example.profile.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profile.Const.REP
import com.example.profile.db.structure.ProfileModel
import com.example.profile.validation.Validation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel:ViewModel() {


    private var validation = Validation()

    fun delete(profileModel: ProfileModel, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REP.deleteProfile(profileModel) {
                onSuccess()
            }
        }

    fun update(profileModel: ProfileModel, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REP.updateProfile(profileModel) {
                onSuccess()
            }
        }

    fun validateEmail(email:String):Boolean
    {
        if(validation.lengthValid(email) && validation.emailValid(email))
        {
            return true
        }

        return false
    }

    fun validatePhone(phone:String):Boolean
    {
        if(validation.lengthValid(phone) && validation.phoneValid(phone))
        {
            return true
        }

        return false
    }


    fun validateDate(date:String):Boolean
    {
        if(validation.lengthValid(date) && validation.dateValid(date))
        {
            return true
        }

        return false
    }
}