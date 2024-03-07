package com.Assignment_2.ecommercestore

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class CheckoutActivity : AppCompatActivity() {

    private lateinit  var checkoutName : EditText
    private lateinit  var checkoutEmail : EditText
    private lateinit  var checkoutNumber : EditText
    private lateinit  var checkoutExpiryDate : EditText
    private lateinit  var checkoutCode : EditText
    private lateinit  var btnCheckout : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        checkoutName = findViewById(R.id.checkoutCardHolderName)
        checkoutEmail = findViewById(R.id.checkoutEmail)
        checkoutNumber = findViewById(R.id.checkoutCardNumber)
        checkoutExpiryDate = findViewById(R.id.checkoutExpiry)
        checkoutCode = findViewById(R.id.checkoutCode)
        btnCheckout = findViewById(R.id.btnCheckout)

        btnCheckout.setOnClickListener {
            if (validateCardDetails()) {
//                showToast("Your order has been placed successfully. Thank you for shopping with us.")
                val i = Intent(this, Order::class.java)
                startActivity(i)
            }
        }
    }

    private fun validateCardDetails(): Boolean {
        val email = checkoutEmail.text.toString()
        val cardNumber = checkoutNumber.text.toString().trim()
        val cardHolderName = checkoutName.text.toString().trim()
        val expiryDate = checkoutExpiryDate.text.toString().trim()
        val cvv = checkoutCode.text.toString().trim()

        if (email.isEmpty() || !isValidEmail(email)) {
            showError(checkoutEmail, "Enter a valid email address")
            return false
        }

        if (cardNumber.isEmpty() || cardNumber.length < 16) {
            showError(checkoutNumber, "Enter a valid 16-digit card number")
            return false
        }

        if (cardHolderName.isEmpty()) {
            showError(checkoutName, "Enter the cardholder's name")
            return false
        }

        if (expiryDate.isEmpty() || expiryDate.length != 5 || !isValidExpiryDate(expiryDate)) {
            showError(checkoutExpiryDate, "Enter a valid expiry date (MM/YY)")
            return false
        }

        if (cvv.isEmpty() || cvv.length < 3) {
            showError(checkoutCode, "Enter a valid CVV")
            return false
        }

        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidExpiryDate(expiryDate: String): Boolean {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
        val enteredYear = expiryDate.substring(3).toInt()
        val enteredMonth = expiryDate.substring(0, 2).toInt()

        return enteredYear > currentYear || (enteredYear == currentYear && enteredMonth >= currentMonth)
    }

    private fun showError(editText: EditText, error: String) {
        editText.error = error
        editText.requestFocus()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}