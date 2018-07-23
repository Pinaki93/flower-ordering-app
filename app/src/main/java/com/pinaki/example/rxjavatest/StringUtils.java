package com.pinaki.example.rxjavatest;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.regex.Pattern;

/**
 * Created by pinaki93 on 11/07/18.
 */

public class StringUtils {
    private static final String SPACE = " ";
    static final byte[] HEX_CHAR_TABLE = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    static NumberFormat twoDigitNumber = NumberFormat.getInstance();
    static NumberFormat threeDigitNumber = NumberFormat.getInstance();
    static NumberFormat fourDigitNumber = NumberFormat.getInstance();
    static Pattern numericOrAlphaNumericPattern = Pattern.compile("[0-9][0-9]+");

    public StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null?true:"".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static final String commaToSpace(String l) {
        return l.replaceAll(",", " ");
    }

    public static String getHexString(byte[] raw) throws UnsupportedEncodingException {
        byte[] hex = new byte[2 * raw.length];
        int index = 0;
        byte[] var3 = raw;
        int var4 = raw.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            byte b = var3[var5];
            int v = b & 255;
            hex[index++] = HEX_CHAR_TABLE[v >>> 4];
            hex[index++] = HEX_CHAR_TABLE[v & 15];
        }

        return new String(hex, "ASCII");
    }

    public static String arrayToString(String[] a, String separator) {
        StringBuffer result = new StringBuffer();
        if(a.length > 0) {
            result.append(a[0]);

            for(int i = 1; i < a.length; ++i) {
                result.append(separator);
                result.append(a[i]);
            }
        }

        return result.toString();
    }

    public static String arrayToString(int[] a, String separator) {
        StringBuffer result = new StringBuffer();
        if(a.length > 0) {
            result.append(a[0]);

            for(int i = 1; i < a.length; ++i) {
                result.append(separator);
                result.append(a[i]);
            }
        }

        return result.toString();
    }

    public static String to2Digits(int value) {
        return twoDigitNumber.format((long)value);
    }

    public static String to3Digits(int value) {
        return threeDigitNumber.format((long)value);
    }

    public static String to4Digits(int value) {
        return fourDigitNumber.format((long)value);
    }

    public static String to4Digits(String value, int lowValue, int def) {
        int v;
        try {
            v = isNotEmpty(value)?Integer.parseInt(value):def;
            if(v < lowValue) {
                v = def;
            }
        } catch (Exception var5) {
            v = def;
        }

        return to4Digits(v);
    }

    public static boolean startsWith(String input, String substring) {
        return isNotEmpty(input) && isNotEmpty(substring) && input.startsWith(substring);
    }

    public static String replaceLineBreaks(String input) {
        if(input.contains("\r")) {
            input = input.replaceAll("\\r", " ");
        } else if(input.contains("\n")) {
            input = input.replaceAll("\\n", " ");
        } else if(input.contains("\r\n")) {
            input = input.replaceAll("\\r\\n", " ");
        }

        return input;
    }

    public static int getLevenshteinDistance(String s, String t) {
        if(s != null && t != null) {
            int n = s.length();
            int m = t.length();
            if(n == 0) {
                return m;
            } else if(m == 0) {
                return n;
            } else {
                if(n > m) {
                    String tmp = s;
                    s = t;
                    t = tmp;
                    n = m;
                    m = tmp.length();
                }

                int[] p = new int[n + 1];
                int[] d = new int[n + 1];

                int i;
                for(i = 0; i <= n; p[i] = i++) {
                    ;
                }

                for(int j = 1; j <= m; ++j) {
                    char t_j = t.charAt(j - 1);
                    d[0] = j;

                    for(i = 1; i <= n; ++i) {
                        int cost = s.charAt(i - 1) == t_j?0:1;
                        d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
                    }

                    int[] _d = p;
                    p = d;
                    d = _d;
                }

                return p[n];
            }
        } else {
            throw new IllegalArgumentException("Strings must not be null");
        }
    }

    public static boolean isEmptyOrUnknown(String s) {
        return isEmpty(s) || "unknown".equalsIgnoreCase(s);
    }

    public static String escapeSQL(String s) {
        int length = s.length();
        int newLength = length;
        int i = 0;

        while(i < length) {
            char c = s.charAt(i);
            switch(c) {
                case '\u0000':
                case '"':
                case '\'':
                case '\\':
                    ++newLength;
                default:
                    ++i;
            }
        }

        if(length == newLength) {
            return s;
        } else {
            StringBuilder sb = new StringBuilder(newLength);

            for(int j = 0; j < length; ++j) {
                char c = s.charAt(j);
                switch(c) {
                    case '\u0000':
                        sb.append("\\0");
                        break;
                    case '"':
                        sb.append("\\\"");
                        break;
                    case '\'':
                        sb.append("''");
                        break;
                    case '\\':
                        sb.append("\\\\");
                        break;
                    default:
                        sb.append(c);
                }
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String s = "domino's";
        System.out.println(s + " --> " + escapeSQL(s));
    }

    public static boolean isNumericOrAlphaNumeric(String s) {
        return numericOrAlphaNumericPattern.matcher(s).find();
    }

    public static SpannableString getSpannableDrawableString(String text, Drawable... d) {
        SpannableString ss = new SpannableString(text);
        if(d != null && d.length > 0) {
            String dummy = "012  ";
            int spacesInDummy = 2;
            ImageSpan[] imageSpans = new ImageSpan[d.length];
            int currentSpanCount = 0;
            Drawable[] var7 = d;
            int var8 = d.length;

            int var9;
            for(var9 = 0; var9 < var8; ++var9) {
                Drawable currentDrawable = var7[var9];
                ss = new SpannableString(dummy + ss);
                currentDrawable.setBounds(0, 0, currentDrawable.getIntrinsicWidth(), currentDrawable.getIntrinsicHeight());
                ImageSpan span = new ImageSpan(currentDrawable, 1);
                imageSpans[currentSpanCount] = span;
                ++currentSpanCount;
            }

            currentSpanCount = 0;
            ImageSpan[] var12 = imageSpans;
            var8 = imageSpans.length;

            for(var9 = 0; var9 < var8; ++var9) {
                ImageSpan currentSpan = var12[var9];
                ss.setSpan(currentSpan, currentSpanCount * dummy.length(), currentSpanCount * dummy.length() + (dummy.length() - spacesInDummy), 17);
                ++currentSpanCount;
            }
        }

        return ss;
    }

    public static SpannableString getSpannableDrawableString(String text1, String text2, Drawable drawable) {
        String dummy = "  012  ";
        int spacesInDummy = 2;
        SpannableString ss = new SpannableString(text1 + dummy + text2);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(drawable, 1);
        ss.setSpan(span, text1.length() + spacesInDummy, text1.length() + dummy.length() - spacesInDummy, 17);
        return ss;
    }

    public static SpannableStringBuilder setSubScript(String text, String subscript) {
        return setSubScript(text, subscript, true);
    }

    public static SpannableStringBuilder setSubScript(String text, String subscript, boolean justSize) {
        SpannableStringBuilder cs = new SpannableStringBuilder(text + " " + subscript);
        if(!justSize) {
            cs.setSpan(new SubscriptSpan(), text.length() + " ".length(), text.length() + " ".length() + subscript.length(), 33);
        }

        cs.setSpan(new RelativeSizeSpan(0.75F), text.length() + " ".length(), text.length() + " ".length() + subscript.length(), 33);
        return cs;
    }

    public static SpannableStringBuilder setSuperScript(String text, String superScript) {
        return setSuperScript(text, superScript, true);
    }

    public static SpannableStringBuilder setSuperScript(String text, String superScript, boolean justSize) {
        SpannableStringBuilder cs = new SpannableStringBuilder(text + " " + superScript);
        if(!justSize) {
            cs.setSpan(new SuperscriptSpan(), text.length() + " ".length(), text.length() + " ".length() + superScript.length(), 33);
        }

        cs.setSpan(new RelativeSizeSpan(1.25F), text.length() + " ".length(), text.length() + " ".length() + superScript.length(), 33);
        return cs;
    }

    public static Spannable getColoredSpannable(String text, String coloredText, int color) {
        Spannable wordtoSpan = new SpannableString(text + coloredText);
        wordtoSpan.setSpan(new ForegroundColorSpan(color), text.length(), text.length() + coloredText.length(), 33);
        return wordtoSpan;
    }

    public static Spannable getColoredSpannable(String preText, String coloredText, String postText, int color) {
        Spannable wordtoSpan = new SpannableString(preText + coloredText + postText);
        wordtoSpan.setSpan(new ForegroundColorSpan(color), preText.length(), preText.length() + coloredText.length(), 33);
        wordtoSpan.setSpan(new RelativeSizeSpan(1.1F), preText.length(), preText.length() + coloredText.length(), 33);
        return wordtoSpan;
    }

    public static Spannable getTwoLineHintSpannable(String firstLineHint, String secondLineHint) {
        Spannable wordtoSpan = new SpannableString(firstLineHint + secondLineHint);
        wordtoSpan.setSpan(new RelativeSizeSpan(0.8F), firstLineHint.length(), firstLineHint.length() + secondLineHint.length(), 33);
        return wordtoSpan;
    }

    public static String ordinal(int i) {
        String[] sufixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch(i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];
        }
    }

    public static boolean isNotEmptyUnknownAndNull(String s) {
        return !isEmptyOrUnknown(s) && !s.equalsIgnoreCase("null");
    }

    static {
        twoDigitNumber.setGroupingUsed(false);
        twoDigitNumber.setMinimumIntegerDigits(2);
        threeDigitNumber.setGroupingUsed(false);
        threeDigitNumber.setMinimumIntegerDigits(3);
        fourDigitNumber.setGroupingUsed(false);
        fourDigitNumber.setMinimumIntegerDigits(4);
    }
}
