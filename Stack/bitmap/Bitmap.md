# Bitmap
位图
1、实现Parcelable接口。
2、创建一个Bitmap对象有那几种方式？
两种：一种是使用BitmapFactory类去加载，另一种是使用Bitmap类加载。


## 压缩

Bitmap 所占内存大小计算方式：
```
像素数 x 像素大小 = 图片长度（单位为像素）x 图片宽度（单位为像素）x 一个像素点占用的字节数
```
可采用的压缩方法：

质量压缩：内存不变，压缩转化后的 bytes.length 减少，适用于传输，png 无效
采样率压缩（Options）：改变宽高，减少像素，采用一定的采样算法
缩放法压缩（Matrix）：改变宽高，减少像素，采用一定的缩放算法（数字图像处理相关）
RGB_565：改变字节数


使用 JPEG 格式的质量压缩：
对一张透明图片（png），内存、宽高不变，bytes.length 减少。图片会失去透明度，透明处变黑。
对一张非透明图片（png、jpg），内存、宽高不变，bytes.length 减少。

使用 PNG 格式的质量压缩：
对一张透明图片（png），没有影响。
对一张非透明图片（png、jpg），没有影响。


 样本大小是在解码位图中对应于单个像素的任一维度上的像素个数。

 如果 inSampleSize = 2，采样后的一个像素在 x 轴上相当于之前的 2 个像素，在 y 轴上也相当于之前的 2 个像素。 即采样后的一个像素相当于之前的 2*2=4 个像素。


 直接设置 RGB_565：

 对于一张透明图片（png），内存、宽高不变，bitmap 也不会失去透明度。
 对于一张非透明图片（png、jpg），宽高不变，内存减小。

 copy 一遍可以减少内存，但生成的 bitmap 会失去透明度，透明处变黑。


## BitmapFactory
位图工厂，解码作用。
从各种源创建位图对象，包括文件、流和字节数组、文件描述符以及资源ID：
```
decodeFile();
decodeByteArray();
decodeStream();
decodeResource();
decodeFileDescriptor();
```
最后所调用的Native方法：
```
nativeDecodeStream();
nativeDecodeFileDescriptor();
nativeDecodeAsset();
nativeDecodeByteArray();
nativeIsSeekable();
```
### BitmapFactory.Options

inJustDecodeBounds
如果设置为true，解码器将返回null（无位图），但是仍将设置out…字段，允许调用者查询位图而不必为其像素分配内存。
如果为true，Bitmap 并不会加载到内存中，但是却可以拿到关于这张图的信息，比如outWidth、outHeight(即图片的宽高)。


inSampleSize
这个属性的作用就是用来设置图片缩放比的，如果设置值为N且大于1，就是原图大小分辨率的1/N，这个值如果小于或者等于1，那么就是原图大小，没有任何缩放。
通常我们会根据设定的目标宽高/原图的宽高来计算这个值。


inMutable
表示该bitmap缓存是否可变，如果设置为true，将可被inBitmap复用。默认是false的，如果我们没有设置这个值为true就去改变Bitmap 的宽高，那一定是会崩溃的。


inPreferredConfig
表示图片解码时使用的颜色模式，一共有四个模式，默认是ARGB_8888。
ALPHA_8: 每个像素用占8位,存储的是图片的透明值,占1个字节。
RGB_565:每个像素用占16位,分别为5-R,6-G,5-B通道,占2个字节。
ARGB-4444:每个像素占16位,即每个通道用4位表示,占2个字节。
ARGB_8888:每个像素占32位,每个通道用8位表示,占4个字节。
其中ARGB_8888 占据的内存最大，如果对图片的清晰度要求不高，使用RGB_565就够用，而且还可以降低内存的使用从而减少OOM的发生。
如果需要图片支持透明度，那就只能ARGB_8888。


inDensity
位图使用的像素密度
Bitmap的像素密度为屏幕默认像素密度，如果这个值为0，系统就会设置一个默认值，这个值在DisplayMetrics 类中的 DENSITY_MEDIUM。


inScreenDensity
正在使用的实际屏幕的像素密度。


inTargetDensity
设备的屏幕密度。


判断Bitmap是不是.9图片。
```
    public static boolean isNinePatch(Bitmap bitmap) {
        byte[] np = bitmap.getNinePatchChunk();
        final boolean isNinePatch = np != null && NinePatch.isNinePatchChunk(np);
        return isNinePatch;
    }
```

### DisplayMetrics
