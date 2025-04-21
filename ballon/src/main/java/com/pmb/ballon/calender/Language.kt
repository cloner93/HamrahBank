package com.pmb.ballon.calender


enum class Language(val code: String, val nativeName: String) {
    // The following order is used for language change dialog also
    // Official languages
    FA("fa", "فارسی"),
    IT("it", "Italiano"),
    JA("ja", "日本語"),
    ZH_CN("zh-CN", "中文");

    val inParentheses: String
        get() = when (this) {
            JA, ZH_CN -> "%s（%s）"
            else -> "%s (%s)"
        }

    companion object {

        // See the naming here, https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-type
        val PERSIAN_DIGITS = charArrayOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')
        val ARABIC_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
        val ARABIC_INDIC_DIGITS = charArrayOf('٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩')
        val DEVANAGARI_DIGITS = charArrayOf('०', '१', '२', '३', '४', '५', '६', '७', '८', '९')
    }
}
