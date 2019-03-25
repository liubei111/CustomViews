package com.liuxiaobei.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

    private int[] CTColors;//颜色(start1,end1,color1,start2,length2,color2......)
    private int[] CTSizes;//大小(start1,end1,size1,start2,length2,size2......)
    private int[] CTUnderlines;//下划线(start1,end1,start2,length2......)
    private int[] CTDeleteLines;//删除线(start1,end1,start2,length2......)
    private int[] CTSuperscripts;//上标(start1,end1,start2,length2......)
    private int[] CTSubscripts;//下标(start1,end1,start2,length2......)
    private int[] CTBolds;//加粗(start1,end1,start2,length2......)
    private int[] CTItalic;//倾斜(start1,end1,start2,length2......)
    private int ctLength;
    private SpannableString spannableString;
    private Context mContext;

    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    /**
     * 设置颜色
     */
    private void setColors() {
        if (CTColors.length > 0 && CTColors.length % 3 == 0) {
            for (int i = 0; i+2 < CTColors.length; i += 3) {
                if (CTColors[i] >= 0 && CTColors[i+1] >= CTColors[i] && CTColors[i+1] <= getCTLength() && CTColors[i+2] != 0) {
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(CTColors[i+2]);
                    getSpannableString().setSpan(colorSpan, CTColors[i], CTColors[i+1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            setText(getSpannableString());
        }
    }

    /**
     * 设置大小
     */
    private void setSizes() {
        if (CTSizes.length > 0 && CTSizes.length % 3 == 0) {
            for (int i = 0; i+2 < CTSizes.length; i += 3) {
                if (CTSizes[i] >= 0 && CTSizes[i+1] >= CTSizes[i] && CTSizes[i+1] <= getCTLength() && CTSizes[i+2] > 0) {
                    RelativeSizeSpan sizeSpan = new RelativeSizeSpan((float) ((float) dip2px(CTSizes[i+2])/(float)getTextSize()));
                    getSpannableString().setSpan(sizeSpan, CTSizes[i], CTSizes[i+1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            setText(getSpannableString());
        }
    }

    /**
     * 设置下划线
     */
    private void setUnderlines() {
        if (CTUnderlines.length > 0 && CTUnderlines.length % 2 == 0) {
            for (int i = 0; i+1 < CTUnderlines.length; i += 2) {
                if (CTUnderlines[i] >= 0 && CTUnderlines[i+1] >= CTUnderlines[i] && CTUnderlines[i+1] <= getCTLength()) {
                    UnderlineSpan underlineSpan = new UnderlineSpan();
                    getSpannableString().setSpan(underlineSpan, CTUnderlines[i], CTUnderlines[i+1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE); }
            }
            setText(getSpannableString());
        }
    }

    /**
     * 设置下划线
     */
    private void setDeleteLines() {
        if (CTDeleteLines.length > 0 && CTDeleteLines.length % 2 == 0) {
            for (int i = 0; i+1 < CTDeleteLines.length; i += 2) {
                if (CTDeleteLines[i] >= 0 && CTDeleteLines[i+1] >= CTDeleteLines[i] && CTDeleteLines[i+1] <= getCTLength()) {
                    StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                    getSpannableString().setSpan(strikethroughSpan, CTDeleteLines[i], CTDeleteLines[i+1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            setText(getSpannableString());
        }
    }

    /**
     * 设置上标
     */
    private void setSuperscripts() {
        if (CTSuperscripts.length > 0 && CTSuperscripts.length % 2 == 0) {
            for (int i = 0; i+1 < CTSuperscripts.length; i += 2) {
                if (CTSuperscripts[i] >= 0 && CTSuperscripts[i+1] >= CTSuperscripts[i] && CTSuperscripts[i+1] <= getCTLength()) {
                    SuperscriptSpan superscriptSpan = new SuperscriptSpan();
                    getSpannableString().setSpan(superscriptSpan, CTSuperscripts[i], CTSuperscripts[i+1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            setText(getSpannableString());
        }
    }

    /**
     * 设置下标
     */
    private void setSubscripts() {
        if (CTSubscripts.length > 0 && CTSubscripts.length % 2 == 0) {
            for (int i = 0; i+1 < CTSubscripts.length; i += 2) {
                if (CTSubscripts[i] >= 0 && CTSubscripts[i+1] >= CTSubscripts[i] && CTSubscripts[i+1] <= getCTLength()) {
                    SubscriptSpan subscriptSpan = new SubscriptSpan();
                    getSpannableString().setSpan(subscriptSpan, CTSubscripts[i], CTSubscripts[i+1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            setText(getSpannableString());
        }
    }

    /**
     * 设置加粗
     */
    private void setBolds() {
        if (CTBolds.length > 0 && CTBolds.length % 2 == 0) {
            for (int i = 0; i+1 < CTBolds.length; i += 2) {
                if (CTBolds[i] >= 0 && CTBolds[i+1] >= CTBolds[i] && CTBolds[i+1] <= getCTLength()) {
                    StyleSpan styleSpan  = new StyleSpan(Typeface.BOLD);
                    getSpannableString().setSpan(styleSpan, CTBolds[i], CTBolds[i+1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            setText(getSpannableString());
        }
    }

    /**
     * 设置斜体
     */
    private void setItalic() {
        if (CTItalic.length > 0 && CTItalic.length % 2 == 0) {
            for (int i = 0; i+1 < CTItalic.length; i += 2) {
                if (CTItalic[i] >= 0 && CTItalic[i+1] >= CTItalic[i] && CTItalic[i+1] <= getCTLength()) {
                    StyleSpan styleSpan  = new StyleSpan(Typeface.ITALIC);
                    getSpannableString().setSpan(styleSpan, CTItalic[i], CTItalic[i+1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            setText(getSpannableString());
        }
    }

    /**
     * 设置颜色
     * @param colors
     */
    public void setCTColors(int... colors) {
        this.CTColors = colors;
        setColors();
    }

    /**
     * 设置大小
     * @param sizes
     */
    public void setCTSizes(int... sizes) {
        this.CTSizes = sizes;
        setSizes();
    }

    /**
     * 设置下划线
     * @param underlines
     */
    public void setCTUnderlines(int... underlines){
        this.CTUnderlines = underlines;
        setUnderlines();
    }

    /**
     * 设置删除线
     * @param deleteLines
     */
    public void setCTDeleteLines(int... deleteLines){
        this.CTDeleteLines = deleteLines;
        setDeleteLines();
    }

    /**
     * 设置上标
     * @param superscripts
     */
    public void setCTSuperscripts(int... superscripts){
        this.CTSuperscripts = superscripts;
        setSuperscripts();
    }

    /**
     * 设置下标
     * @param subscripts
     */
    public void setCTSubscripts(int... subscripts){
        this.CTSubscripts = subscripts;
        setSubscripts();
    }

    /**
     * 设置加粗
     * @param bolds
     */
    public void setCTBolds(int... bolds){
        this.CTBolds = bolds;
        setBolds();
    }

    /**
     * 设置斜体
     * @param italics
     */
    public void setCTItalic(int... italics){
        this.CTItalic = italics;
        setItalic();
    }

    /**
     * 设置图片
     * @param start
     * @param end
     * @param drawable
     */
    public void setCTImages(int start,int end,Drawable drawable){
        if (start >= 0 && end >= start && drawable != null) {
            ImageSpan imageSpan = new ImageSpan(drawable);
            getSpannableString().setSpan(imageSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            setText(getSpannableString());
        }
    }

    /**
     * 设置点击
     * @param start
     * @param end
     * @param clickableSpan
     */
    public void setClickable(int start,int end,ClickableSpan clickableSpan){
        if (start >= 0 && end >= start && clickableSpan != null) {
            getSpannableString().setSpan(clickableSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            setMovementMethod(LinkMovementMethod.getInstance());
            setText(getSpannableString());
        }
    }

    /**
     * 获取长度
     * @return
     */
    private int getCTLength(){
        if(ctLength == 0){
            ctLength = getText().toString().length();
        }
        return ctLength;
    }

    /**
     * 获取spannableString
     * @return
     */
    private SpannableString getSpannableString(){
        if(spannableString == null){
            spannableString = new SpannableString(getText().toString());
        }
        return spannableString;
    }

    /**
     * dp转px
     * @param dpValue
     * @return
     */
    public int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }
}
