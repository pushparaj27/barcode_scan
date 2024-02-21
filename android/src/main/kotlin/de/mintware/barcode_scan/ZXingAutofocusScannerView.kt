//package de.mintware.barcode_scan
//
//import android.content.Context
//import android.hardware.Camera
//import android.util.Log
//import me.dm7.barcodescanner.core.CameraWrapper
//import me.dm7.barcodescanner.zxing.ZXingScannerView
//
//class ZXingAutofocusScannerView(context: Context) : ZXingScannerView(context) {
//
//    private var callbackFocus = false
//    private var autofocusPresence = true
//
//    override fun setupCameraPreview(cameraWrapper: CameraWrapper?) {
//        android.util.Log.d("TAG", "setupCameraPreview: ${cameraWrapper?.mCamera?.parameters?.maxZoom}")
//        cameraWrapper?.mCamera?.parameters?.let { parameters ->
//            try {
//                // Setting fixed zoom
//                if (parameters.isZoomSupported) {
//                    parameters.zoom = 50
//                }
//                autofocusPresence = parameters.supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO);
//                parameters.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
//
//                cameraWrapper.mCamera.parameters = parameters
//            } catch (ex: Exception) {
//                callbackFocus = true
//            }
//        }
//        super.setupCameraPreview(cameraWrapper)
//    }
//
//    override fun setAutoFocus(state: Boolean) {
//        //Fix to avoid crash on devices without autofocus (Issue #226)
//        if(autofocusPresence){
//            super.setAutoFocus(callbackFocus)
//        }
//    }
//}

//package de.mintware.barcode_scan
//
//import android.content.Context
//import android.hardware.Camera
//import android.util.Log
//import me.dm7.barcodescanner.core.CameraWrapper
//import me.dm7.barcodescanner.zxing.ZXingScannerView
//
//class ZXingAutofocusScannerView(context: Context) : ZXingScannerView(context) {
//
//    private var callbackFocus = false
//    private var autofocusPresence = true
//    private var zoomLevelSet = false
//
//    private var onPreviewStartedCallback: (() -> Unit)? = null
//
//    private var internalCameraWrapper: CameraWrapper? = null
//
//    fun setOnPreviewStartedCallback(callback: () -> Unit) {
//        onPreviewStartedCallback = callback
//    }
//
//    override fun setupCameraPreview(cameraWrapper: CameraWrapper?) {
//        internalCameraWrapper = cameraWrapper // Store cameraWrapper internally
//        super.setupCameraPreview(cameraWrapper)
//        cameraWrapper?.mCamera?.parameters?.let { parameters ->
//            try {
//                autofocusPresence = parameters.supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)
//                parameters.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
//                cameraWrapper.mCamera.parameters = parameters
//            } catch (ex: Exception) {
//                callbackFocus = true
//            }
//        }
//    }
//
//    override fun setAutoFocus(state: Boolean) {
//        //Fix to avoid crash on devices without autofocus (Issue #226)
//        if (autofocusPresence) {
//            super.setAutoFocus(callbackFocus)
//        }
//    }
//
//    fun notifyPreviewStarted() {
//        if (!zoomLevelSet && internalCameraWrapper != null) {
//            setZoomLevel(internalCameraWrapper!!)
//        }
//    }
//
//    private fun setZoomLevel(cameraWrapper: CameraWrapper) {
//        val parameters = cameraWrapper.mCamera.parameters
//        if (parameters.isZoomSupported) {
//            val maxZoom = parameters.maxZoom
//            val desiredZoom = maxZoom / 2 // Set the desired zoom level here
//            if (desiredZoom >= 0 && desiredZoom <= maxZoom) {
//                parameters.zoom = desiredZoom
//                cameraWrapper.mCamera.parameters = parameters
//                zoomLevelSet = true
//            } else {
//                Log.e("ZXingAutofocusScannerView", "Desired zoom level is not within the valid range.")
//            }
//        } else {
//            Log.e("ZXingAutofocusScannerView", "Zoom is not supported on this device.")
//        }
//    }
//
//    override fun onDetachedFromWindow() {
//        super.onDetachedFromWindow()
//        releaseCamera()
//    }
//
//    private fun releaseCamera() {
//        internalCameraWrapper?.mCamera?.release()
//        internalCameraWrapper = null
//    }
//}

//package de.mintware.barcode_scan
//
//import android.content.Context
//import android.hardware.Camera
//import android.util.Log
//import me.dm7.barcodescanner.core.CameraWrapper
//import me.dm7.barcodescanner.zxing.ZXingScannerView
//
//class ZXingAutofocusScannerView(context: Context) : ZXingScannerView(context) {
//
//    private var callbackFocus = false
//    private var autofocusPresence = true
//    private var zoomLevelSet = false
//
//    private var onPreviewStartedCallback: (() -> Unit)? = null
//
//    private var internalCameraWrapper: CameraWrapper? = null
//
//    fun setOnPreviewStartedCallback(callback: () -> Unit) {
//        onPreviewStartedCallback = callback
//    }
//
//    override fun setupCameraPreview(cameraWrapper: CameraWrapper?) {
//        internalCameraWrapper = cameraWrapper // Store cameraWrapper internally
//        super.setupCameraPreview(cameraWrapper)
//        cameraWrapper?.mCamera?.parameters?.let { parameters ->
//            try {
//                autofocusPresence = parameters.supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)
//                parameters.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
//
//                // Set auto-zoom
//                if (parameters.isZoomSupported) {
//                    parameters.zoom = parameters.maxZoom / 2 // Adjust as needed
//                }
//
//                cameraWrapper.mCamera.parameters = parameters
//            } catch (ex: Exception) {
//                callbackFocus = true
//            }
//        }
//    }
//
//    override fun setAutoFocus(state: Boolean) {
//        //Fix to avoid crash on devices without autofocus (Issue #226)
//        if (autofocusPresence) {
//            super.setAutoFocus(callbackFocus)
//        }
//    }
//
//    override fun onDetachedFromWindow() {
//        super.onDetachedFromWindow()
//        releaseCamera()
//    }
//
//    private fun releaseCamera() {
//        internalCameraWrapper?.mCamera?.release()
//        internalCameraWrapper = null
//    }
//}

//package de.mintware.barcode_scan
//
//import android.content.Context
//import android.hardware.Camera
//import android.os.Handler
//import android.util.Log
//import me.dm7.barcodescanner.core.CameraWrapper
//import me.dm7.barcodescanner.zxing.ZXingScannerView
//
//class ZXingAutofocusScannerView(context: Context) : ZXingScannerView(context) {
//
//    private var callbackFocus = false
//    private var autofocusPresence = true
//    private var zoomLevelSet = false
//    private var currentZoomLevel = 0
//
//    private var onPreviewStartedCallback: (() -> Unit)? = null
//
//    private var internalCameraWrapper: CameraWrapper? = null
//
//    fun setOnPreviewStartedCallback(callback: () -> Unit) {
//        onPreviewStartedCallback = callback
//    }
//
//    override fun setupCameraPreview(cameraWrapper: CameraWrapper?) {
//        internalCameraWrapper = cameraWrapper // Store cameraWrapper internally
//        super.setupCameraPreview(cameraWrapper)
//        cameraWrapper?.mCamera?.parameters?.let { parameters ->
//            try {
//                autofocusPresence = parameters.supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)
//                parameters.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
//
//                // Set initial auto-zoom
//                if (parameters.isZoomSupported) {
//                    parameters.zoom = 0
//                    currentZoomLevel = 0
//                    cameraWrapper.mCamera.parameters = parameters
//                    zoomLevelSet = true
//
//                    // Start zooming loop
//                    startZoomingLoop(cameraWrapper)
//                }
//
//                cameraWrapper.mCamera.parameters = parameters
//            } catch (ex: Exception) {
//                callbackFocus = true
//            }
//        }
//    }
//
//    override fun setAutoFocus(state: Boolean) {
//        //Fix to avoid crash on devices without autofocus (Issue #226)
//        if (autofocusPresence) {
//            super.setAutoFocus(callbackFocus)
//        }
//    }
//
//    override fun onDetachedFromWindow() {
//        super.onDetachedFromWindow()
//        releaseCamera()
//    }
//
//    private fun releaseCamera() {
//        internalCameraWrapper?.mCamera?.release()
//        internalCameraWrapper = null
//    }
//
//    private fun startZoomingLoop(cameraWrapper: CameraWrapper) {
//        val handler = Handler()
//        handler.postDelayed(object : Runnable {
//            override fun run() {
//                increaseZoomLevel(cameraWrapper)
//                handler.postDelayed(this, 3000) // Repeat every 3 seconds
//            }
//        }, 3000) // Initial delay 3 seconds
//    }
//
//    private fun increaseZoomLevel(cameraWrapper: CameraWrapper) {
//        val parameters = cameraWrapper.mCamera.parameters
//        if (parameters.isZoomSupported) {
//            val maxZoom = 40
//            if (currentZoomLevel + 10 <= maxZoom) {
//                parameters.zoom = currentZoomLevel + 10
//                cameraWrapper.mCamera.parameters = parameters
//                currentZoomLevel += 10
//            } else {
//                Log.d("ZXingAutofocusScannerView", "Reached maximum zoom level")
//            }
//        } else {
//            Log.d("ZXingAutofocusScannerView", "Zoom is not supported on this device")
//        }
//    }
//}

package de.mintware.barcode_scan

import CustomViewFinderView
import android.content.Context
import android.hardware.Camera
import android.os.Handler
import android.util.Log
import android.graphics.Color
import android.graphics.Point
import me.dm7.barcodescanner.core.CameraWrapper
import me.dm7.barcodescanner.zxing.ZXingScannerView
import android.os.Build

class ZXingAutofocusScannerView(context: Context) : ZXingScannerView(context) {

    private var callbackFocus = false
    private var autofocusPresence = true
    private var zoomLevelSet = false
    private var currentZoomLevel = 0

    private var onPreviewStartedCallback: (() -> Unit)? = null

    private var internalCameraWrapper: CameraWrapper? = null
    private var cameraReleased = false

    fun setOnPreviewStartedCallback(callback: () -> Unit) {
        onPreviewStartedCallback = callback
    }

    override fun createViewFinderView(context: Context): CustomViewFinderView {
        return CustomViewFinderView(context)
    }

    override fun setupCameraPreview(cameraWrapper: CameraWrapper?) {
        internalCameraWrapper = cameraWrapper // Store cameraWrapper internally
        super.setupCameraPreview(cameraWrapper)
        cameraWrapper?.mCamera?.parameters?.let { parameters ->
            try {
                autofocusPresence = parameters.supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)
                parameters.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE

                setLaserEnabled(false)
                setIsBorderCornerRounded(true)
                setBorderCornerRadius(24)
                setBorderStrokeWidth(12)
//                setMaskColor(Color.TRANSPARENT);
//                setSquareViewFinder(false)
//                setBackgroundColor(Color.TRANSPARENT);
//                setBorderLineLength(10)
                setBorderColor(Color.RED)
                setShouldScaleToFill(true)
//                getFramingRectInPreview(100,100)
//                setPadding(0,0,0,100)



                // Set initial auto-zoom
            if(Build.VERSION.SDK_INT > 29){//Greater than android 10 only zoom func work!!!
                if (parameters.isZoomSupported) {
                    parameters.zoom = 0
                    currentZoomLevel = 0
                    cameraWrapper.mCamera.parameters = parameters
                    zoomLevelSet = true

                    // Start zooming loop
                    startZoomingLoop(cameraWrapper)
                }
            }
                cameraWrapper.mCamera.parameters = parameters
            } catch (ex: Exception) {
                callbackFocus = true
            }
        }
    }

    override fun setAutoFocus(state: Boolean) {
        //Fix to avoid crash on devices without autofocus (Issue #226)
        if (autofocusPresence) {
            super.setAutoFocus(callbackFocus)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        releaseCamera()
    }

    private fun releaseCamera() {
        internalCameraWrapper?.mCamera?.release()
        internalCameraWrapper = null
        cameraReleased = true
    }

    private fun startZoomingLoop(cameraWrapper: CameraWrapper) {
        val handler = Handler()
        val camera = cameraWrapper.mCamera
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (camera != null && !cameraReleased) {
                    increaseZoomLevel(cameraWrapper)
                    handler.postDelayed(this, 3000) // Repeat every 3 seconds
                }
            }
        }, 3000) // Initial delay 3 seconds
    }

private fun increaseZoomLevel(cameraWrapper: CameraWrapper) {
        val camera = cameraWrapper.mCamera
        if (camera != null && !cameraReleased) { // Check if the camera is valid and not released
            val parameters = camera.parameters
            if (parameters.isZoomSupported) {
//                val maxZoom = 40
                val maxZoom = parameters.maxZoom
                if (currentZoomLevel + 10 <= maxZoom) {
                    parameters.zoom = currentZoomLevel + 10
                    camera.parameters = parameters
                    currentZoomLevel += 10
                } else {
                    Log.d("ZXingAutofocusScanner", "Reached maximum zoom level")
                }
            } else {
                Log.d("ZXingAutofocusScanner", "Zoom is not supported on this device")
            }
        } else {
            Log.d("ZXingAutofocusScanner", "Camera is not available or already released")
        }
    }


}
