package fr.esgi.mymodule.mymodule.myclub.app.Manager;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.Adherents;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.AjouterAdherent;
import fr.esgi.mymodule.mymodule.myclub.app.R;

public class CamTestActivity extends Activity implements SurfaceHolder.Callback {

    private static final String TAG = "CamTestActivity";
    Preview preview;
    Button buttonClick;
    Camera camera;
    Activity act;
    Context ctx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        act = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_camera);

        preview = new Preview(this, (SurfaceView)findViewById(R.id.surfaceView));
        preview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        ((FrameLayout) findViewById(R.id.layout)).addView(preview);
        preview.setKeepScreenOn(true);

       // preview.setOnClickListener(new OnClickListener() {

         //   @Override
          //  public void onClick(View arg0) {
            //    Toast.makeText(ctx, "Click", Toast.LENGTH_LONG).show();
             //   camera.takePicture(shutterCallback, rawCallback, jpegCallback);
           // }
        //});

      //  Toast.makeText(ctx, getString(R.string.take_photo_help), Toast.LENGTH_LONG).show();

		buttonClick = (Button) findViewById(R.id.btnCapture);
		buttonClick.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				//preview.mCamera.takePicture(shutterCallback, rawCallback, jpegCallback);
				camera.takePicture(shutterCallback, rawCallback, jpegCallback);
                Toast.makeText(ctx, "Photo à été bien enrigestrée", Toast.LENGTH_LONG).show();
			}
		});

		buttonClick.setOnLongClickListener(new OnLongClickListener(){
			@Override
			public boolean onLongClick(View arg0) {
				camera.autoFocus(new Camera.AutoFocusCallback(){
					@Override
					public void onAutoFocus(boolean arg0, Camera arg1) {

						camera.takePicture(shutterCallback, rawCallback, jpegCallback);
                        Toast.makeText(ctx, "Photo à été bien enrigestrée", Toast.LENGTH_LONG).show();
					}
				});
				return true;
			}
		});
    }

    @Override
    protected void onResume() {
        super.onResume();
//		preview.camera = Camera.open();
        camera = Camera.open();
        camera.startPreview();
        preview.setCamera(camera);
    }

    @Override
    protected void onPause() {
        if(camera != null) {
            camera.stopPreview();
            preview.setCamera(null);
            camera.release();
            camera = null;
        }
        super.onPause();
    }

    private void resetCam() {
        camera.startPreview();
        preview.setCamera(camera);
    }

    private void refreshGallery(File file) {
        Intent mediaScanIntent = new Intent( Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri.fromFile(file));
        sendBroadcast(mediaScanIntent);
    }

    ShutterCallback shutterCallback = new ShutterCallback() {
        public void onShutter() {
//			 Log.d(TAG, "onShutter'd");
        }
    };

    PictureCallback rawCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
//			 Log.d(TAG, "onPictureTaken - raw");
        }
    };

    PictureCallback jpegCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            new SaveImageTask().execute(data);
            resetCam();
            Log.d(TAG, "onPictureTaken - jpeg");
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    private class SaveImageTask extends AsyncTask<byte[], Void, Void> {

        @Override
        protected Void doInBackground(byte[]... data) {
            FileOutputStream outStream = null;

            // Write to SD Card
            try {
                File sdCard = Environment.getExternalStorageDirectory();
                File dir = new File (sdCard.getAbsolutePath() + "/cam");
                dir.mkdirs();

                String fileName = String.format("%d.jpg", System.currentTimeMillis());
                File outFile = new File(dir, fileName);

                outStream = new FileOutputStream(outFile);
                outStream.write(data[0]);
                outStream.flush();
                outStream.close();

               // Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length + " to " + outFile.getAbsolutePath());

                refreshGallery(outFile);


                Intent intAdh = new Intent(CamTestActivity.this,AjouterAdherent.class);
                intAdh.putExtra("path",outFile.getAbsolutePath());
                finish();
                startActivity(intAdh);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
            return null;
        }

    }
}
