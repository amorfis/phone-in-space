package eu.phoneinspace;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyActivity extends Activity {

    private Camera camera;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);





                // Create our Preview view and set it as the content of our activity.





        Button button = (Button) findViewById(R.id.takePictureButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                camera.takePicture(null, null, new Camera.PictureCallback() {
                    public void onPictureTaken(byte[] bytes, Camera camera) {
                        Toast.makeText(getApplicationContext(), "Image taken", 5000).show();

                        File externalStorage = Environment.getExternalStorageDirectory();
                        System.out.println("Getting pictures dir");
//                        File picturesDir = new File(externalStorage, "Pictures");
                        File picturesDir = externalStorage;

//                        boolean dirCreateRes = picturesDir.mkdir();

                        System.out.println("Creating picture file");
                        File pictureFile = new File(picturesDir, "pic");
                        System.out.println("Pic file created");
                        try {
                            pictureFile.createNewFile();
                            FileOutputStream fos = new FileOutputStream(pictureFile);
                            fos.write(bytes);
                            fos.close();
                            Toast.makeText(getApplicationContext(), "Image saved", 5000).show();
                        } catch (IOException e) {
                            Log.e("a", "Error", e);
                            Toast.makeText(getApplicationContext(), "Image saved", 5000).show();
                        }



//                        File pictureFile = getExternalOutputMediaFile(MEDIA_TYPE_IMAGE);
//                        if (pictureFile == null){
//                            Log.d(TAG, "Error creating media file, check storage permissions: " +
//                                e.getMessage());
//                            return;
//                        }
//
//                        try {
//                            FileOutputStream fos = new FileOutputStream(pictureFile);
//                            fos.write(data);
//                            fos.close();
//                        } catch (FileNotFoundException e) {
//                            Log.d(TAG, "File not found: " + e.getMessage());
//                        } catch (IOException e) {
//                            Log.d(TAG, "Error accessing file: " + e.getMessage());
//                        }


                    }
                });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        camera.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera = Camera.open();

        CameraPreview cameraPreview = new CameraPreview(this, camera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(cameraPreview);

    }
}
