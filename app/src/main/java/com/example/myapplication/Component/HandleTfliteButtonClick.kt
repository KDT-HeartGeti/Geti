package com.example.myapplication.Component

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.myapplication.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

fun handleButtonClick(context: Context, bitmap: Bitmap?, imageProcessor: ImageProcessor): String {
    if (bitmap != null) {
        val model = Model.newInstance(context)
        var tensorImage = TensorImage(DataType.UINT8)
        tensorImage.load(bitmap)
        tensorImage = imageProcessor.process(tensorImage)

        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(tensorImage.buffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

        model.close()

        if (outputFeature0.isNotEmpty()) {
            return when {
                outputFeature0[0] in 0.0..0.5 -> "organic"
                outputFeature0[0] == 0.5f -> "organic/recycle"
                outputFeature0[0] in 0.5..1.0 -> "recycle"
                else -> "no-value"
            }
        } else return "no-value"
    }
    return "no-value"
}