package com.agora.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.agora.R;

import java.lang.reflect.Field;

/**
 * Created by Ivan on 11/11/15.
 */
public class ExpandableTextView extends TextView {
    private static final int DEFAULT_TRIM_LENGTH = 100;
    private static final String ELLIPSIS = "...";

    private CharSequence originalText;
    private CharSequence trimmedText;
    private BufferType bufferType;
    private boolean trim = true;
    private int trimLength;

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        this.trimLength = typedArray.getInt(R.styleable.ExpandableTextView_trimLength, DEFAULT_TRIM_LENGTH);
        typedArray.recycle();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trim = !trim;
                setText();
                requestFocusFromTouch();
            }
        });
    }

    private void setText() {
        CharSequence text=getDisplayableText();
        if(text.toString().contains(" ...more > ")) {
            SpannableString spanText = new SpannableString(text);
            spanText.setSpan(new TextAppearanceSpan(getContext(), R.style.text_9), 0, text.length() - 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanText.setSpan(new TextAppearanceSpan(getContext(), R.style.text_8), text.length() - 11, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            super.setText(spanText, bufferType);
        }else if(text.toString().contains("  < less ")) {
            SpannableString spanText = new SpannableString(text);
            spanText.setSpan(new TextAppearanceSpan(getContext(), R.style.text_9), 0, text.length() - 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanText.setSpan(new TextAppearanceSpan(getContext(), R.style.text_8), text.length() - 9, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            super.setText(spanText, bufferType);
        }else{
            super.setText(text, bufferType);
        }
    }

    private CharSequence getDisplayableText() {
        return trim ? trimmedText : originalText;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        trimmedText = getTrimmedText(text);
        bufferType = type;
        setText();
    }

    private CharSequence getTrimmedText(CharSequence text) {
        if (originalText != null && originalText.length() > trimLength) {
            CharSequence c=new SpannableStringBuilder(originalText, 0, trimLength + 1).append(" ...more > ");
            originalText=originalText+"  < less ";
            return c;
        }
            return originalText;

    }

    public CharSequence getOriginalText() {
        return originalText;
    }

    public void setTrimLength(int trimLength) {
        this.trimLength = trimLength;
        trimmedText = getTrimmedText(originalText);
        setText();
    }

    public int getTrimLength() {
        return trimLength;
    }
}
