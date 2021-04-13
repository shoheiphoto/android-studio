package com.example.cameraxex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysisConfig;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.OutputStream;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    // 定数
    private final int REQUEST_CODE_PERMISSIONS = 101;
    private final String[] REQUIRED_PERMISSIONS = new String[]{Manifest.permission.CAMERA};
    // UI
    private TextureView textureView;
    private Button captureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI
        this.textureView = findViewById(R.id.texture_view);
        this.captureButton = findViewById(R.id.capture_button);
        // パーミッションのチェック
        if (allPermissionsGranted()) {
            this.textureView.post(() -> startCamera());
        } else {
            ActivityCompat.requestPermissions(this,
                    REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }
    // パーミッション許可のリクエスト結果の取得
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                Toast.makeText(this, "ユーザーから権限が許可されていません。",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    // 全てのパーミッション許可
    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    // カメラの開始
    private void startCamera() {
        // プレビューの表示
        PreviewConfig pConfig = new PreviewConfig.Builder().build();
        Preview preview = new Preview(pConfig);
        preview.setOnPreviewOutputUpdateListener(
                output -> {
                    // SurfaceTextureの更新
                    ViewGroup parent = (ViewGroup)this.textureView.getParent();
                    parent.removeView(this.textureView);
                    parent.addView(this.textureView, 0);

                    // SurfaceTextureをTextureViewに指定
                    this.textureView.setSurfaceTexture(output.getSurfaceTexture());

                    // TextureViewのサイズの調整
                    int w = output.getTextureSize().getWidth();
                    int h = output.getTextureSize().getHeight();
                    int degree = output.getRotationDegrees();
                    if (degree == 90 || degree == 270) {
                        w = output.getTextureSize().getHeight();
                        h = output.getTextureSize().getWidth();
                    }
                    h = h * textureView.getWidth() / w;
                    w = textureView.getWidth();
                    ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(w,h);
                    params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
                    params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
                    params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
                    params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
                    textureView.setLayoutParams(params);;
                });

        // 画像の解析
        ImageAnalysisConfig config = new ImageAnalysisConfig.Builder()
                .setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
                .build();
        ImageAnalysis imageAnalysis = new ImageAnalysis(config);
        imageAnalysis.setAnalyzer(Executors.newSingleThreadExecutor(),
                new ImageAnalysis.Analyzer() {
                    @Override
                    public void analyze(ImageProxy image, int rotationDegrees) {
                        android.util.Log.d("debug",+image.getWidth()+"x"+image.getHeight());
                    }
                });

        // 画像のキャプチャ
        ImageCaptureConfig cConfig = new ImageCaptureConfig.Builder()
                .setTargetRotation(getWindowManager().getDefaultDisplay().getRotation())
                .build();
        ImageCapture imageCapture = new ImageCapture(cConfig);

        // ボタンのイベントリスナー
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 画像のキャプチャ
                File file = new File(getFilesDir(), "captured.jpg");
                imageCapture.takePicture(file, Executors.newSingleThreadExecutor(),
                        new ImageCapture.OnImageSavedListener() {
                            // 成功時に呼ばれる
                            @Override
                            public void onImageSaved(File file) {
                                android.util.Log.d("debug","success");
                            }

                            // エラー時に呼ばれる
                            @Override
                            public void onError(
                                    ImageCapture.ImageCaptureError imageCaptureError,
                                    String message, Throwable cause) {
                                android.util.Log.d("debug","error");
                            }
                        });

                MediaActionSound sound = new MediaActionSound();
                sound.play(MediaActionSound.SHUTTER_CLICK);
            }
        });

        // カメラのライフサイクルのバインド
        CameraX.bindToLifecycle(this, imageCapture, imageAnalysis, preview);

    }


    // キャラリーへの保存
    private void savePhoto(File file) {
        ContentResolver resolver = getApplicationContext().getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "captured.jpg");
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
        Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        try {
            OutputStream fos = resolver.openOutputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
