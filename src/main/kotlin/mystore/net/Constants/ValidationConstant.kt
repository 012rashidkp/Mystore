package mystore.net.Constants

import java.util.regex.Pattern

fun isEmailValid(email: String): Boolean {
    val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(email)
    return matcher.matches()
}

fun isPhoneNumberValid(phoneNumber: String): Boolean {
    val regex = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}\$"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(phoneNumber)
    return matcher.matches()
}
fun isPasswordLengthAtLeast6(password: String): Boolean {
    return password.length >= 6
}


