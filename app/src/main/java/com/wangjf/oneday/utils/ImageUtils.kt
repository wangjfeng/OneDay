package com.wangjf.kotlinframwork.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

/**
 * 图形/图像处理类，专门实现图片图像的处理。 例如 拉伸、缩小、裁剪、倒影等效果
 */
object ImageUtils {

    @JvmOverloads fun getBitmapByPath(filePath: String,
                                      opts: BitmapFactory.Options? = null): Bitmap? {
        var fis: FileInputStream? = null
        var bitmap: Bitmap? = null
        try {
            val file = File(filePath)
            fis = FileInputStream(file)
            bitmap = BitmapFactory.decodeStream(fis, null, opts)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
        } finally {
            try {
                fis!!.close()
            } catch (e: Exception) {
            }

        }
        return bitmap
    }

    fun loadImgThumbnail(filePath: String): Bitmap? {
        return getBitmapByPath(filePath)
    }

    /**
     * 放大缩小图片

     * @param bitmap
     * *
     * @param w
     * *
     * @param h
     * *
     * @return
     */
    fun zoomBitmap(bitmap: Bitmap?, w: Int, h: Int): Bitmap? {
        var newbmp: Bitmap? = null
        if (bitmap != null) {
            val width = bitmap.width
            val height = bitmap.height
            val matrix = Matrix()
            val scaleWidht = w.toFloat() / width
            val scaleHeight = h.toFloat() / height
            matrix.postScale(scaleWidht, scaleHeight)
            newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
                    true)
        }
        return newbmp
    }

    /**
     * 缩放图片

     * @param bitmap
     * *
     * @param newWidth
     * *
     * @return
     */
    fun zoomBitmap(bitmap: Bitmap?, newWidth: Int): Bitmap? {
        if (bitmap == null) {
            return null
        }
        val width = bitmap.width
        val height = bitmap.height

        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = height * scaleWidth / height
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
    }

    /**
     * 缩放图片

     * @param drawable
     * *
     * @param newWidth
     * *
     * @return
     */
    fun zoomDrawable(res: Resources, drawable: Drawable?,
                     newWidth: Int): Drawable? {
        if (drawable == null) {
            return null
        }
        val bitmap = drawableToBitmap(drawable)
        return BitmapDrawable(res, zoomBitmap(bitmap, newWidth))
    }

    // 创建倒影Bitmap
    fun createReflectedDrawable(res: Resources,
                                originalDrawable: Drawable): Drawable {
        val originalBitmap = drawableToBitmap(originalDrawable)
        val reflectionGap = 1
        val width = originalBitmap.width
        val height = originalBitmap.height

        val matrix = Matrix()
        matrix.preScale(1f, -1f)

        val reflectionImage = Bitmap.createBitmap(originalBitmap, 0,
                height / 2, width, height / 2, matrix, false)

        val bitmapWithReflection = Bitmap.createBitmap(width, height
                + height / 4 + reflectionGap, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmapWithReflection)

        canvas.drawBitmap(originalBitmap, 0f, 0f, null)

        val deafaultPaint = Paint()
        deafaultPaint.color = Color.WHITE
        canvas.drawRect(0f, height.toFloat(), width.toFloat(), (height + reflectionGap).toFloat(), deafaultPaint)

        canvas.drawBitmap(reflectionImage, 0f, (height + reflectionGap).toFloat(), null)

        val paint = Paint()
        val shader = LinearGradient(0f,
                originalBitmap.height.toFloat(), 0f, (bitmapWithReflection.height + reflectionGap).toFloat(), 0x30ffffff, 0x00ffffff, Shader.TileMode.CLAMP)

        paint.shader = shader

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)

        canvas.drawRect(0f, height.toFloat(), width.toFloat(), (bitmapWithReflection.height + reflectionGap).toFloat(), paint)
        reflectionImage.recycle()
        return BitmapDrawable(res, bitmapWithReflection)
    }

    /**
     * Drawable 转化Bitmap

     * @param drawable
     * *
     * @return
     */
    fun drawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap
                .createBitmap(
                        Math.abs(drawable.intrinsicWidth),
                        Math.abs(drawable.intrinsicHeight),
                        if (drawable.opacity != PixelFormat.OPAQUE)
                            Bitmap.Config.ARGB_8888
                        else
                            Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth,
                drawable.intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    }

    /**
     * 图片旋转
     */
    fun adjustPhotoRotation(bm: Bitmap,
                            orientationDegree: Int): Bitmap {
        // 定义矩阵对象
        val matrix = Matrix()
        // 参数为正则向右旋转
        matrix.postRotate(orientationDegree.toFloat())
        // bmp.getWidth(), 500分别表示重绘后的位图宽高
        val dstbmp = Bitmap.createBitmap(bm, 0, 0, bm.width,
                bm.height, matrix, true)

        return dstbmp
    }

    /**
     * bitmap to bytes

     * @param bm
     * *
     * @return
     */
    fun bitmap2Bytes(bm: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }

    /**
     * 计算图片的缩放值

     * @param options
     * *
     * @param reqWidth
     * *
     * @param reqHeight
     * *
     * @return
     */
    fun calculateInSampleSize(options: BitmapFactory.Options,
                              reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
            val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
            inSampleSize = if (heightRatio < widthRatio) widthRatio else heightRatio
            if (inSampleSize % 2 == 1) {
                inSampleSize++
            }
        }
        return inSampleSize
    }

    /**
     * 根据路径获得突破并压缩返回bitmap用于显示

     * @param filePath
     * *
     * @return
     */
    @SuppressLint("NewApi")
    fun getSmallBitmap(filePath: String): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, options)

        options.inSampleSize = calculateInSampleSize(options, 480, 800)
        options.inJustDecodeBounds = false
        // 重新读出图片
        val bimap = BitmapFactory.decodeFile(filePath, options)
        val sizeRow = bimap.rowBytes.toFloat() / 1024
        val sizeCount = (bimap.byteCount / 1024).toFloat()// 3168
        DLog.e("sizeRow:" + sizeRow, "sizeCount:" + sizeCount)

        return bimap
    }


    // 这里把bitmap图片截取出来pr是指截取比例
    fun getRoundedCornerBitmap(bit: Bitmap, pr: Float): Bitmap {
        val bitmap = Bitmap.createBitmap(bit.width, bit.height,
                Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawARGB(0, 0, 0, 0)
        val paint = Paint()
        paint.isAntiAlias = true
        canvas.clipRect(0f, bit.height.toFloat(), bit.width.toFloat(), bit.height - pr * bit.height / 100)
        canvas.drawBitmap(bit, 0f, 0f, paint)
        return bitmap

    }
}
/**
 * 获取bitmap

 * @param filePath
 * *
 * @return
 */