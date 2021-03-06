package com.example.cathay_pacific_20210813.Model.Uill


import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import android.graphics.*
import com.squareup.picasso.Transformation

//對應Picasso用的圖片切圓
class CircleTransform : Transformation {

    private var x: Int = 0
    private var y: Int = 0

    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)
        x = (source.width - size) / 2
        y = (source.height - size) / 2
        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap !== source) source.recycle()
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val r = size / 2f

        paint.shader = shader
        paint.isAntiAlias = true
        canvas.drawCircle(r, r, r, paint)
        squaredBitmap.recycle()

        return bitmap
    }
    override fun key() = "circle(x=$x,y=$y)"
}
