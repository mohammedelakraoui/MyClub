package fr.esgi.mymodule.mymodule.myclub.app.Manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import fr.esgi.mymodule.mymodule.myclub.app.R;

/**
 * Created by ISMAEL on 10/08/2014.
 */
public class PicturesManager {

    public static Bitmap getRoundedCornerImage(Bitmap pic) {

        Bitmap resized = Bitmap.createScaledBitmap(pic, 100, 100, true);
        Bitmap output = Bitmap.createBitmap(resized.getWidth(), resized.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, resized.getWidth(), resized.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 100;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(resized, rect, rect, paint);

        return output;


}

   public static Bitmap getPicFromPath(String Path,Context c)
   {
       Bitmap bitmap= BitmapFactory.decodeResource(c.getResources(), R.drawable.user);
        try {
            if(!Path.isEmpty()) {
                if(bitmap!=null)
                {
                    bitmap.recycle();
                    bitmap=null;
                }
                File f=new File(Path);
                bitmap =decodeFile(f);
            }
        }catch (Exception ext)
        {
          return bitmap;
        }
       return  bitmap;
      // return getRoundedCornerImage(bitmap);
   }
  //decodes image and scales it to reduce memory consumption
    private static Bitmap decodeFile(File f){
        try {
            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);

            //The new size we want to scale to
            final int REQUIRED_SIZE=70;

            //Find the correct scale value. It should be the power of 2.
            int scale=1;
            while(o.outWidth/scale/2>=REQUIRED_SIZE && o.outHeight/scale/2>=REQUIRED_SIZE)
                scale*=2;

            //Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }

}
