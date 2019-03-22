package com.liuxiaobei.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;


/**
 *@author xiaobei 472887082@qq.com
 * 圆角
 * 边框
 * 背景色(narmal、press、false)
 * 文本色(narmal、press、false)
 */
public class CustomButton extends Button {

    public static int[] mNormalState = new int[]{};
    public static int[] mPressState = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
    public static int[] mFalseState = new int[]{-android.R.attr.state_enabled};

    private int customButtonRadius;//圆角
    private int customButtonBackgroundColor = 0xFFFFFFFF;//背景颜色
    private int customButtonBackgroundColorPress;//按压时背景颜色(默认为设置背景颜色透明度50%)
    private int customButtonBackgroundColorFalse;//Enable为false时背景颜色(默认CCCCCC)
    private int customButtonBackgroundTextColor = 0xFF000000;//text颜色
    private int customButtonBackgroundTextColorPress;//按压时text颜色(默认为设置text颜色透明度50%)
    private int customButtonBackgroundTextColorFalse;//Enable为false时text颜色(默认为设置按压时text颜色)
    private int customButtonBorder;//边线
    private int customButtonBorderColor;//边线颜色
    private int customButtonBorderColorPress;//边线按压时颜色(默认为设置边线颜色透明度50%)
    private int customButtonBorderColorFalse;//Enable为false时颜色(默认为设置边线按压时颜色)

    public CustomButton(Context context) {
        this(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        @SuppressLint("Recycle")
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);
        customButtonRadius = typedArray.getDimensionPixelSize(R.styleable.CustomButton_cb_radius, customButtonRadius);
        customButtonBackgroundColor = typedArray.getColor(R.styleable.CustomButton_cb_background_color, customButtonBackgroundColor);
        customButtonBackgroundColorPress = typedArray.getColor(R.styleable.CustomButton_cb_background_color_press, customButtonBackgroundColorPress);
        customButtonBackgroundColorFalse = typedArray.getColor(R.styleable.CustomButton_cb_background_color_false, customButtonBackgroundColorFalse);
        customButtonBackgroundTextColor = typedArray.getColor(R.styleable.CustomButton_cb_background_text_color, customButtonBackgroundTextColor);
        customButtonBackgroundTextColorPress = typedArray.getColor(R.styleable.CustomButton_cb_background_text_color_press, customButtonBackgroundTextColorPress);
        customButtonBackgroundTextColorFalse = typedArray.getColor(R.styleable.CustomButton_cb_background_text_color_false, customButtonBackgroundTextColorFalse);
        customButtonBorder = typedArray.getDimensionPixelSize(R.styleable.CustomButton_cb_background_border, customButtonBorder);
        customButtonBorderColor = typedArray.getColor(R.styleable.CustomButton_cb_background_border_color, customButtonBorderColor);
        customButtonBorderColorPress = typedArray.getColor(R.styleable.CustomButton_cb_background_border_color_press, customButtonBorderColorPress);
        customButtonBorderColorFalse = typedArray.getColor(R.styleable.CustomButton_cb_background_border_color_false, customButtonBorderColorFalse);
        typedArray.recycle();
        setView();
    }

    public void setView() {
        setGravity(Gravity.CENTER);
        //设置背景
        setCBBackground();
        //设置文字颜色
        setCBTextColor();
    }

    /**
     * 设置背景
     */
    private void setCBBackground() {
        StateListDrawable drawable = new StateListDrawable();
        //背景颜色
        if (customButtonBackgroundColorPress == 0) {
            customButtonBackgroundColorPress = Color.argb(127,
                    Color.red(customButtonBackgroundColor),
                    Color.green(customButtonBackgroundColor),
                    Color.blue(customButtonBackgroundColor));
        }
        if (customButtonBackgroundColorFalse == 0) {
            customButtonBackgroundColorFalse = customButtonBackgroundColorPress;
        }
        //边线颜色
        if (customButtonBorderColor == 0) {
            customButtonBorderColor = customButtonBackgroundColor;
        }
        if (customButtonBorderColorPress == 0) {
            customButtonBorderColorPress = Color.argb(127,
                    Color.red(customButtonBorderColor),
                    Color.green(customButtonBorderColor),
                    Color.blue(customButtonBorderColor));
        }
        if (customButtonBorderColorFalse == 0) {
            customButtonBorderColorFalse = customButtonBorderColorPress;
        }
        //文字颜色
        if (customButtonBackgroundTextColorPress == 0) {
            customButtonBackgroundTextColorPress = customButtonBackgroundTextColor;
        }
        if (customButtonBackgroundTextColorFalse == 0) {
            customButtonBackgroundTextColorFalse = customButtonBackgroundTextColorPress;
        }

        //设置我们按钮背景的颜色
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setCornerRadius(customButtonRadius);
        pressedDrawable.setColor(customButtonBackgroundColorPress);
        pressedDrawable.setStroke(customButtonBorder, customButtonBorderColorPress);
        drawable.addState(mPressState, pressedDrawable);

        GradientDrawable falseDrawable = new GradientDrawable();
        falseDrawable.setCornerRadius(customButtonRadius);
        falseDrawable.setColor(customButtonBackgroundColorFalse);
        falseDrawable.setStroke(customButtonBorder, customButtonBorderColorFalse);
        drawable.addState(mFalseState, falseDrawable);

        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setCornerRadius(customButtonRadius);
        normalDrawable.setColor(customButtonBackgroundColor);
        normalDrawable.setStroke(customButtonBorder, customButtonBorderColor);
        drawable.addState(mNormalState, normalDrawable);

        setBackground(drawable);
    }

    /**
     * 设置文字颜色
     */
    private void setCBTextColor() {
        ColorStateList colorStateList = new ColorStateList(new int[][]{mPressState, mFalseState, mNormalState},
                new int[]{customButtonBackgroundTextColorPress,
                        customButtonBackgroundTextColorFalse,
                        customButtonBackgroundTextColor});
        setTextColor(colorStateList);
    }

    /**
     * 设置按钮背景颜色(normal)
     *
     * @param customButtonBackgroundColor
     */
    public void setCustomButtonBackgroundColor(int customButtonBackgroundColor) {
        this.customButtonBackgroundColor = customButtonBackgroundColor;
        setCBBackground();
    }

    /**
     * 设置按钮背景颜色(false)
     *
     * @param customButtonBackgroundColorFalse
     */
    public void setCustomButtonBackgroundColorFalse(int customButtonBackgroundColorFalse) {
        this.customButtonBackgroundColorFalse = customButtonBackgroundColorFalse;
        setCBBackground();
    }

    /**
     * 设置按钮背景颜色(press)
     *
     * @param customButtonBackgroundColorPress
     */
    public void setCustomButtonBackgroundColorPress(int customButtonBackgroundColorPress) {
        this.customButtonBackgroundColorPress = customButtonBackgroundColorPress;
        setCBBackground();
    }

    /**
     * 设置Text颜色(normal)
     *
     * @param customButtonBackgroundTextColor
     */
    public void setCustomButtonBackgroundTextColor(int customButtonBackgroundTextColor) {
        this.customButtonBackgroundTextColor = customButtonBackgroundTextColor;
        setCBTextColor();
    }

    /**
     * 设置Text颜色(press)
     *
     * @param customButtonBackgroundTextColorPress
     */
    public void setCustomButtonBackgroundTextColorPress(int customButtonBackgroundTextColorPress) {
        this.customButtonBackgroundTextColorPress = customButtonBackgroundTextColorPress;
        setCBTextColor();
    }

    /**
     * 设置Text颜色(false)
     *
     * @param customButtonBackgroundTextColorFalse
     */
    public void setCustomButtonBackgroundTextColorFalse(int customButtonBackgroundTextColorFalse) {
        this.customButtonBackgroundTextColorFalse = customButtonBackgroundTextColorFalse;
        setCBTextColor();
    }

    /**
     * 设置按钮圆角
     *
     * @param customButtonRadius
     */
    public void setCustomButtonRadius(int customButtonRadius) {
        this.customButtonRadius = customButtonRadius;
        setCBBackground();
    }

    /**
     * 设置按钮边框
     *
     * @param customButtonBorder
     */
    public void setCustomButtonBorder(int customButtonBorder) {
        this.customButtonBorder = customButtonBorder;
        setCBBackground();
    }

    /**
     * 设置按钮边框颜色(normal)
     *
     * @param customButtonBorderColor
     */
    public void setCustomButtonBorderColor(int customButtonBorderColor) {
        this.customButtonBorderColor = customButtonBorderColor;
        setCBBackground();
    }

    /**
     * 设置按钮边框颜色(false)
     *
     * @param customButtonBorderColorFalse
     */
    public void setCustomButtonBorderColorFalse(int customButtonBorderColorFalse) {
        this.customButtonBorderColorFalse = customButtonBorderColorFalse;
        setCBBackground();
    }

    /**
     * 设置按钮边框颜色(press)
     *
     * @param customButtonBorderColorPress
     */
    public void setCustomButtonBorderColorPress(int customButtonBorderColorPress) {
        this.customButtonBorderColorPress = customButtonBorderColorPress;
        setCBBackground();
    }
}