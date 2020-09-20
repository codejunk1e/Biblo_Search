package com.robin.search.internal

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.annotation.AttrRes
import androidx.annotation.RestrictTo
import androidx.appcompat.widget.AppCompatEditText

/**
 * @hide
 */
class SearchEditText : AppCompatEditText {

    var clearFocusOnBackPressed: Boolean = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP && clearFocusOnBackPressed) {
            if (hasFocus()) {
                clearFocus()
                return true
            }
        }
        return super.onKeyPreIme(keyCode, event)
    }

    override fun clearFocus() {
        super.clearFocus()
        text?.clear()
    }

}
