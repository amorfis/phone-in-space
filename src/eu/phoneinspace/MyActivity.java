package eu.phoneinspace;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends Activity {

    private Camera camera;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        Button button = (Button) findViewById(R.id.takePictureButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                camera.takePicture(null, null, new Camera.PictureCallback() {
                    public void onPictureTaken(byte[] bytes, Camera camera) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Image taken", 5000);
                        toast.show();
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
    }
}
