package com.example.profile.validation

import org.junit.Test
import org.junit.jupiter.api.Assertions
import java.util.*


class ValidationTest
{

    @Test
    fun `lengthValid (should return true for normal string)`()
    {
        val validation = Validation()

        val testString = "hello"

        val expected = true

        val actual = validation.lengthValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `lengthValid (should return true for empty string)`()
    {
        val validation = Validation()

        val testString = ""

        val expected = true

        val actual = validation.lengthValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `lengthValid (should return false for long string)`()
    {
        val validation = Validation()

        val testString = "fkjfeifnefnfeunefiuenfuefnfueinfuefnfuienfiuenuiefnieufnfeinifiufenfieunfiifeiefifenfeifenifeifiefieffiefni"

        val expected = false

        val actual = validation.lengthValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `phoneValid (should return true for normal phone with a plus)`()
    {
        val validation = Validation()

        val testString = "+79218585689"

        val expected = true

        val actual = validation.phoneValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `phoneValid (should return true for normal phone)`()
    {
        val validation = Validation()

        val testString = "79218585689"

        val expected = true

        val actual = validation.phoneValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `phoneValid (should return false for more plus in phone)`()
    {
        val validation = Validation()

        val testString = "+79217+653+834"

        val expected = false

        val actual = validation.phoneValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `phoneValid (should return false for bad symbol in phone)`()
    {
        val validation = Validation()

        val testString = "-a79217653834"

        val expected = false

        val actual = validation.phoneValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `phoneValid (should return false for bad plus position in phone)`()
    {
        val validation = Validation()

        val testString = "7+9217653834"

        val expected = false

        val actual = validation.phoneValid(testString)

        Assertions.assertEquals(expected,actual)
    }



    @Test
    fun `emailValid (should return true for normal email)`()
    {
        val validation = Validation()

        val testString = "kukaibks@icloud.com"

        val expected = true

        val actual = validation.emailValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `emailValid (should return false if email not contains point)`()
    {
        val validation = Validation()

        val testString = "kukaibks@icloudcom"

        val expected = false

        val actual = validation.emailValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `emailValid (should return false if email not contains '@')`()
    {
        val validation = Validation()

        val testString = "kukaibksicloud.com"

        val expected = false

        val actual = validation.emailValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `emailValid (should return false if email contains '@' more one)`()
    {
        val validation = Validation()

        val testString = "kukaibks@@icloud.com"

        val expected = false

        val actual = validation.emailValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `emailValid (should return true if email contains point more one)`()
    {
        val validation = Validation()

        val testString = "kuka.ibks@icloud.com"

        val expected = true

        val actual = validation.emailValid(testString)

        Assertions.assertEquals(expected,actual)
    }



    @Test
    fun `dateValid (should return true for normal date)`()
    {
        val validation = Validation()

        val testString = "30.12.1998"

        val expected = true

        val actual = validation.dateValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `dateValid (should return false if date contains only 1 point)`()
    {
        val validation = Validation()

        val testString = "30.1998"

        val expected = false

        val actual = validation.dateValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `dateValid (should return false if date contains more 2 point)`()
    {
        val validation = Validation()

        val testString = "30.1.998.13"

        val expected = false

        val actual = validation.dateValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `dateValid (should return false if date not contains points)`()
    {
        val validation = Validation()

        val testString = "301998"

        val expected = false

        val actual = validation.dateValid(testString)

        Assertions.assertEquals(expected,actual)
    }

    @Test
    fun `dateValid (should return false if day more 31)`()
    {
        val validation = Validation()

        val testString = "32.12.1998"

        val expected = false

        val actual = validation.dateValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `dateValid (should return false if month more 12)`()
    {
        val validation = Validation()

        val testString = "31.13.1998"

        val expected = false

        val actual = validation.dateValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `dateValid (should return false if year more cur_year)`()
    {
        val validation = Validation()

        //get cur Date
        val calendar = Calendar.getInstance()

        //get cur year
        val moreCurYear = calendar.get(Calendar.YEAR) + 1

        val testString = "31.12.$moreCurYear"

        val expected = false

        val actual = validation.dateValid(testString)

        Assertions.assertEquals(expected,actual)
    }


    @Test
    fun `dateValid (should return false if date contains bad symbols)`()
    {
        val validation = Validation()

        val testString = "-31.13.1998"

        val expected = false

        val actual = validation.dateValid(testString)

        Assertions.assertEquals(expected,actual)
    }

}