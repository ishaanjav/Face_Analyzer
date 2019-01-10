package com.example.anany.emotionrecognition;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.microsoft.projectoxford.face.contract.Face;
import com.microsoft.projectoxford.face.contract.FaceRectangle;

public class ImageHelper {

   public static FaceRectangle calculateFaceRectangle(Bitmap bitmap, FaceRectangle faceRectangle, double faceRectangleEnlargeRatio){
       double sideLength = faceRectangle.width * faceRectangleEnlargeRatio;
       sideLength = Math.min(sideLength, bitmap.getWidth());
       sideLength = Math.min(sideLength, bitmap.getHeight());

       double left = faceRectangle.left - faceRectangle.width*(faceRectangleEnlargeRatio - 1.0)*0.5;
       left = Math.max(left, 0.0);
       left = Math.min(left, bitmap.getWidth() - sideLength);

       double top = faceRectangle.top - faceRectangle.height*(faceRectangleEnlargeRatio-1.0)*0.5;
       top = Math.max(top, 0.0);
       top = Math.min(top, bitmap.getHeight() - sideLength);

       double shiftTop = faceRectangleEnlargeRatio - 1.0;
       shiftTop = Math.max(shiftTop, 0.0);
       shiftTop = Math.min(shiftTop, 1.0);
       top = 0.15*shiftTop*faceRectangle.height;
       top = Math.max(top, 0.0);

       FaceRectangle result = new FaceRectangle();
       result.left = (int) left;
       result.top = (int) top;
       result.width = (int) sideLength;
       result.height = (int) sideLength;
       return  result;

   }

    public static Bitmap generateThumbnail(Bitmap orig, FaceRectangle faceRectangle){
        FaceRectangle face = calculateFaceRectangle(orig, faceRectangle, 1.0);
        return Bitmap.createBitmap(orig, faceRectangle.left, faceRectangle.top- faceRectangle.height, faceRectangle.width, faceRectangle.height);
    }
}
