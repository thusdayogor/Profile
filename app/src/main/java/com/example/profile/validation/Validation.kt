package com.example.profile.validation
import com.example.profile.validation.const.ValidConst.AT
import com.example.profile.validation.const.ValidConst.DATE_COUNT_POINT
import com.example.profile.validation.const.ValidConst.DAY_MAX
import com.example.profile.validation.const.ValidConst.MAX_AT
import com.example.profile.validation.const.ValidConst.MAX_LENGTH
import com.example.profile.validation.const.ValidConst.MONTH_MAX
import com.example.profile.validation.const.ValidConst.PLUS
import com.example.profile.validation.const.ValidConst.POINT
import java.util.*

class Validation
{

    //check length for input values
    fun lengthValid(string:String):Boolean
    {
        //check maximum length for input value
        if(string.length > MAX_LENGTH)
            return false
        return true
    }

    //check phone number validation
    fun phoneValid(phone:String):Boolean
    {

        //all symbols in phone it's digit or plus symbol
        phone.forEach {
            if(!it.isDigit()&&it!=PLUS)
                return false
        }

        //only one '+' in phone
        val isMoreThanOne = phone.indexOf(PLUS) != phone.lastIndexOf(PLUS)

        if(isMoreThanOne)
            return false

        if(phone.indexOf(PLUS) > 0)
            return false

        return true
    }


    //check email validation
    fun emailValid(email:String):Boolean
    {
        //only one '@' in email
        val isMoreThanOne = email.indexOf(AT) != email.lastIndexOf(AT)

        if(isMoreThanOne)
           return false

        //check '.' in email (.com) and more
        if(!email.contains(POINT))
            return false

        if(email.filter { it == AT }.count()!= MAX_AT)
            return false

        return true
    }

    fun dateValid(date:String):Boolean
    {
        //date optional field
        if (date.isEmpty())
            return true

        //check only digit and point in date field
        date.forEach {
            if(!it.isDigit()&&it!=POINT)
                return false
        }

        //check count point in Date (only 2)
        if(date.filter { it == POINT }.count()!= DATE_COUNT_POINT)
            return false

        //split date (day,month,year)
        val arrayDate = date.split(POINT)

        //31 max int in day
        if(arrayDate[0].toInt() > DAY_MAX)
            return false

        //12 max int month
        if(arrayDate[1].toInt() > MONTH_MAX)
            return false

        //get cur Date
        val calendar = Calendar.getInstance()

        //get cur year
        val curYear = calendar.get(Calendar.YEAR)

        //cur year <= year
        if(arrayDate[2].toInt() > curYear)
            return false

        return true
    }


}