package com.example.themoviedatabase.common.extension

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Insets
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.SpannableString
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowInsets
import android.widget.TextView
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.common.DEFAULT_DEBOUNCE_INTERVAL
import com.example.themoviedatabase.common.DebouncedOnClickListener
import java.util.*
import kotlin.reflect.full.memberProperties


fun View.setOnSafeClick(
    delayBetweenClicks: Long = DEFAULT_DEBOUNCE_INTERVAL,
    click: (view: View) -> Unit
) {
    setOnClickListener(object : DebouncedOnClickListener(delayBetweenClicks) {
        override fun onDebouncedClick(v: View) = click(v)
    })
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun TextView.setImageLeft(left: Drawable?) {
    setCompoundDrawablesWithIntrinsicBounds(left, null, null, null)
}

fun TextView.setImageTop(top: Drawable?) {
    setCompoundDrawablesWithIntrinsicBounds(null, top, null, null)
}

fun TextView.setImageRight(right: Drawable?) {
    setCompoundDrawablesWithIntrinsicBounds(null, null, right, null)
}

fun TextView.setImageBottom(bottom: Drawable?) {
    setCompoundDrawablesWithIntrinsicBounds(null, null, null, bottom)
}

fun TextView.setImageLeftRight(left: Drawable?, right: Drawable?) {
    setCompoundDrawablesWithIntrinsicBounds(left, null, right, null)
}

fun TextView.clearImage() {
    setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
}

fun getAppString(
    @StringRes stringId: Int,
    context: Context? = getApplication()
): String {
    return context?.getString(stringId) ?: ""
}

fun getAppString(
    @StringRes stringId: Int,
    vararg params: Any,
    context: Context? = getApplication()
): String {
    return context?.getString(stringId, *params) ?: ""
}

fun getAppSpannableString(
    @StringRes stringId: Int,
    context: Context? = getApplication()
): SpannableString {
    return SpannableString(context?.getString(stringId))
}

fun getAppFont(
    @FontRes fontId: Int,
    context: Context? = getApplication()
): Typeface? {
    return context?.let {
        ResourcesCompat.getFont(it, fontId)
    }
}

fun getAppDrawable(
    @DrawableRes drawableId: Int,
    context: Context? = getApplication()
): Drawable? {
    return context?.let {
        ContextCompat.getDrawable(it, drawableId)
    }
}

fun getAppColor(
    @ColorRes colorRes: Int,
    context: Context? = getApplication()
): Int {
    return context?.let {
        ContextCompat.getColor(it, colorRes)
    } ?: Color.TRANSPARENT
}

fun getAppDimension(
    @DimenRes dimenId: Int,
    context: Context? = getApplication()
): Float {
    return context?.resources?.getDimension(dimenId) ?: -1f
}

fun Activity.getScreenHeight(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets: Insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        windowMetrics.bounds.height() - insets.top - insets.bottom
    } else {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.heightPixels
    }
}

fun String.addToken(token: String?): String {
    return if (token != null) {
        this + token
    } else {
        STRING_DEFAULT
    }
}

fun String.allow(): String {
    return this + AppConfig.PERMISSION_ALLOW
}

fun String.deny(): String {
    return this + AppConfig.PERMISSION_DENY
}

fun String.domainLinkImage(): String {
    return AppConfig.DOMAIN_LINK_IMAGE + this
}

fun String.getFirstString(): String {
    return this.first().toString().uppercase(Locale.getDefault())
}

@Throws(IllegalAccessException::class, ClassCastException::class)
inline fun <reified T> Any.getField(fieldName: String): T? {
    this::class.memberProperties.forEach { kCallable ->
        if (fieldName == kCallable.name) {
            return kCallable.getter.call(this) as T?
        }
    }
    return null
}
