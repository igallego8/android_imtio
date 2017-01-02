package com.agora.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.text.TextUtils;

import com.agora.app.AppConfig;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Ivan on 16/09/15.
 */
public final class UtilProcess {

    private static int previousRandom=-1;
    private static List<BackgroundItemColor> backgroundColors= new ArrayList<>();

    static{

        backgroundColors.add(new BackgroundItemColor(Color.parseColor("#E57373"),Color.parseColor("#EF5350")));
        backgroundColors.add(new BackgroundItemColor(Color.parseColor("#BA68C8"),Color.parseColor("#AB47BC")));
        backgroundColors.add(new BackgroundItemColor(Color.parseColor("#9575CD"),Color.parseColor("#7E57C2")));
        backgroundColors.add(new BackgroundItemColor(Color.parseColor("#7986CB"),Color.parseColor("#5C6BC0")));
        backgroundColors.add(new BackgroundItemColor(Color.parseColor("#64B5F6"),Color.parseColor("#42A5F5")));
        backgroundColors.add(new BackgroundItemColor(Color.parseColor("#4DD0E1"),Color.parseColor("#26C6DA")));
        backgroundColors.add(new BackgroundItemColor(Color.parseColor("#81C784"),Color.parseColor("#66BB6A")));
        backgroundColors.add(new BackgroundItemColor(Color.parseColor("#AED581"),Color.parseColor("#9CCC65")));
        backgroundColors.add(new BackgroundItemColor(Color.parseColor("#FFD54F"),Color.parseColor("#FFCA28")));
        backgroundColors.add(new BackgroundItemColor(Color.parseColor("#FF8A65"),Color.parseColor("#FF7043")));


    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum=0;
        do {
             randomNum = rand.nextInt((max - min) + 1) + min;
        }while(previousRandom==randomNum);
        previousRandom=randomNum;

        return randomNum;
    }


    public static ShapeDrawable getRoundColorDrawable(){
        int color= backgroundColors.get(UtilProcess.randInt(0, backgroundColors.size()-1)).getColor1();
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setStyle(Paint.Style.FILL); //See more paint style for border circle etc. like STROKE
        drawable.getPaint().setAntiAlias(true);
        drawable.getPaint().setColor(color);
        drawable.setIntrinsicHeight(80); //converting dp to px, you can just put any integer instead of dp2px method
        drawable.setIntrinsicWidth(80);
        drawable.getPaint().setTextAlign(Paint.Align.CENTER);
        return drawable;
    }

    public static BackgroundItemColor getRandomColor(){
        return backgroundColors.get(UtilProcess.randInt(0, backgroundColors.size()-1));
    }

    public static ShapeDrawable getSquareColorDrawable(){
        int color= backgroundColors.get(UtilProcess.randInt(0, backgroundColors.size()-1)).getColor1();
        ShapeDrawable drawable = new ShapeDrawable(new RectShape());
        drawable.getPaint().setStyle(Paint.Style.FILL); //See more paint style for border circle etc. like STROKE
        drawable.getPaint().setAntiAlias(true);
        drawable.getPaint().setColor(color);
        drawable.setIntrinsicHeight(200); //converting dp to px, you can just put any integer instead of dp2px method
        drawable.setIntrinsicWidth(200);
        drawable.getPaint().setTextAlign(Paint.Align.CENTER);
        return drawable;
    }

    public static String readFully(InputStream inputStream) throws IOException {

        if(inputStream == null) {
            return "";
        }

        BufferedInputStream bufferedInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            byteArrayOutputStream = new ByteArrayOutputStream();

            final byte[] buffer = new byte[1024];
            int available = 0;

            while ((available = bufferedInputStream.read(buffer)) >= 0) {
                byteArrayOutputStream.write(buffer, 0, available);
            }

            return byteArrayOutputStream.toString();

        } finally {
            if(bufferedInputStream != null) {
                bufferedInputStream.close();
            }
        }
    }

    public static int daysToExpirate(Date date){
        Date currentDate= new Date (System.currentTimeMillis());
        return (int)( (currentDate.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));

    }

    public static Bitmap createThumbnail(Bitmap image){
        try
        {
            Bitmap imageBitmap = Bitmap.createScaledBitmap(image, AppConfig.THUMBNAIL_SIZE, AppConfig.THUMBNAIL_SIZE, false);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
           return imageBitmap;
        }
        catch(Exception ex) {

        }
        return null;
    }


    public static String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
    }

    public static Date getDateTime(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date dt=null;
        try {
           return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static Date getDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy", Locale.getDefault());
        Date dt=null;
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy", Locale.getDefault());
        return dateFormat.format(date);
    }


    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        Bitmap output;
        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight()/8, bitmap.getHeight()/8, Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth()/8, bitmap.getWidth()/8, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth()/8, bitmap.getHeight()/8);
        float r = 0;
        if (bitmap.getWidth()/8 > bitmap.getHeight()/8) {
            r = bitmap.getHeight() / 16;
        } else {
            r = bitmap.getWidth() / 16;
        }
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static String[] convertToArrayString(List<Integer> params){
        String[] values= new String[params.size()];
        for (int index=0; index<params.size();index++){
            values[index]=params.get(index).toString();
        }
        return values;
    }

    public static String[] convertLongToArrayString(List<Long> params){
        String[] values= new String[params.size()];
        for (int index=0; index<params.size();index++){
            values[index]=params.get(index).toString();
        }
        return values;
    }


    public static String quote(String string) {
        if (string == null || string.length() == 0) {
            return "\"\"";
        }

        char         c = 0;
        int          i;
        int          len = string.length();
        StringBuilder sb = new StringBuilder(len + 4);
        String       t;

        sb.append('"');
        for (i = 0; i < len; i += 1) {
            c = string.charAt(i);
            switch (c) {
                case '\\':
                case '"':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '/':
                    //                if (b == '<') {
                    sb.append('\\');
                    //                }
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ') {
                        t = "000" + Integer.toHexString(c);
                        sb.append("\\u" + t.substring(t.length() - 4));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
        return sb.toString();
    }


    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
