package com.example.videoplayinviewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.URLUtil;

import com.example.videoplayinviewpager2.Adapter.Adapter;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    String [] videos ={"https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-3gp-file.3gp",
                        "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-3gp-file.3gp",
                        "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-3gp-file.3gp"};
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager= findViewById(R.id.vpHorizontal);

        for(int i=0;i<videos.length;i++){
           Uri  v= getMedia(videos[i]);
           adapter = new Adapter(v,videos,savedInstanceState);
           viewPager.setAdapter(adapter);

        }

    }

    //getMedia URi
    private Uri getMedia(String mediaName) {
        if (URLUtil.isValidUrl(mediaName)) {
            // Media name is an external URL.
            return Uri.parse(mediaName);
        } else {

            // you can also put a video file in raw package and get file from there as shown below

            return Uri.parse("android.resource://" + getPackageName() +
                    "/raw/" + mediaName);


        }
    }
}