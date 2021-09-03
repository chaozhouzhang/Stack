package android.stack.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.NinePatch;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class BitmapUtils {

    public static boolean isNinePatch(Bitmap bitmap) {
        byte[] np = bitmap.getNinePatchChunk();
        final boolean isNinePatch = np != null && NinePatch.isNinePatchChunk(np);
        return isNinePatch;
    }


    /**
     * 质量压缩方法，这里quality=100表示不压缩，把压缩后的数据存放到字节数组输出流中
     *
     * @param bitmap
     * @param quality
     * @return
     */
    public static Bitmap compressQuality(Bitmap bitmap, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Bitmap bmp = BitmapFactory.decodeStream(byteArrayInputStream);
        return bmp;
    }


    /**
     * 采样率压缩方法
     *
     * @param resources
     * @param drawableId
     * @param inSampleSize
     * @return
     */
    public static Bitmap compressInSampleSize(Resources resources, int drawableId, int inSampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;
        Bitmap bmp = BitmapFactory.decodeResource(resources, drawableId, options);//或其他方式解码
        return bmp;
    }


    /**
     * 缩放法
     *
     * @param bitmap
     * @param scaleX
     * @param scaleY
     * @return
     */
    public static Bitmap compressScale(Bitmap bitmap, float scaleX, float scaleY) {
        Matrix matrix = new Matrix();
        matrix.setScale(scaleX, scaleY);
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bmp;
    }


    /**
     * 颜色模式：RGB_565
     *
     * @param resources
     * @param drawableId
     * @return
     */
    public static Bitmap compressARGB(Resources resources, int drawableId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bmp = BitmapFactory.decodeResource(resources, drawableId, options);//或其他方式解码
        return bmp;
    }


    /**
     * 拷贝
     *
     * @param bitmap
     * @return
     */
    public static Bitmap compressCopy(Bitmap bitmap) {
        return bitmap.copy(Bitmap.Config.RGB_565, true);
    }
}
