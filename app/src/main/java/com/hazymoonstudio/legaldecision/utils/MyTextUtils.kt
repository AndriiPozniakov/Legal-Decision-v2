package com.hazymoonstudio.legaldecision.utils

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextUtils
import android.text.style.ClickableSpan
import android.view.View
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.random.Random


class MyTextUtils {
    companion object {
        fun highlightText(
            text: String,
            startTag: String,
            endTag: String,
            listener: HighlightsTextClickHandler
        ): SpannableString {
            var spannableText = text

            val lStart = startTag.length
            val lEnd = endTag.length
            val lSum = lStart + lEnd

            val subText: MutableList<String> = ArrayList()

            val mStart: Matcher = Pattern.compile("(?=($startTag))").matcher(spannableText)
            val posStart: MutableList<Int> = ArrayList()
            while (mStart.find()) {
                posStart.add(mStart.start())
            }

            val mEnd: Matcher = Pattern.compile("(?=($endTag))").matcher(spannableText)
            val posEnd: MutableList<Int> = ArrayList()
            while (mEnd.find()) {
                posEnd.add(mEnd.start())
            }

            spannableText = spannableText.replace(startTag, "")
            spannableText = spannableText.replace(endTag, "")

            for (i in 0 until posStart.size) {
                posStart[i] = posStart[i] - (lSum * i)
                posEnd[i] = posEnd[i] - (lSum * i) - lStart
                subText.add(spannableText.substring(posStart[i], posEnd[i]))
            }

            var spannableString = SpannableString(spannableText)
            for(i in 0 until posStart.size) {
                val clickableSpan: ClickableSpan = object : ClickableSpan() {
                    override fun onClick(textView: View) {
                        listener.onTextClick(subText[i])
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)

                    }
                }

                //val bss = StyleSpan(Typeface.BOLD)
                spannableString.setSpan(clickableSpan, posStart[i], posEnd[i], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                //spannableString.setSpan(bss, posStart[i] - ((startTagLength + endTagLength) * i), posEnd[i] - ((startTagLength + endTagLength) * i) - startTagLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            return spannableString
        }

        fun getSafeSubstring(s: String, maxLength: Int): String {
            if (!TextUtils.isEmpty(s)) {
                if (s.length >= maxLength) {
                    return s.substring(0, maxLength)
                }
            }
            return s
        }

        fun getSafeRandomSubstring(s: String, maxLength: Int = 50): String {
            if (!TextUtils.isEmpty(s)) {
                if (s.length >= maxLength) {
                    return s.substring(0, Random.nextInt(maxLength)) + "..."
                }
            }
            return s
        }

        fun getSafeSubstringWords(s: String, maxWords: Int): String {
            if (!TextUtils.isEmpty(s)) {
                val words = s.split(" ")
                if (words.size >= maxWords) {
                    return words.joinToString(separator = " ", limit = maxWords)
                }
            }
            return s
        }
    }
}