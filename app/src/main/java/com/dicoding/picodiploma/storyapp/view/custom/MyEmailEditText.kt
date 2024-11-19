package com.dicoding.picodiploma.storyapp.view.custom

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import androidx.appcompat.widget.AppCompatEditText

class MyEmailEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isValidEmail(s)) {
                    error = null
                } else {
                    setError("Email tidak valid", null)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun isValidEmail(email: CharSequence?): Boolean {
        return !TextUtils.isEmpty(email) && email?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() } == true
    }
}
