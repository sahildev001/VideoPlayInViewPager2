package com.example.videoplayinviewpager2.Adapter;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoplayinviewpager2.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Uri video;
    String [] videos;

    Bundle savedInstanceState;
    private static final String PLAYBACK_TIME = "play_time";
    private int mCurrentPosition = 0;


    public Adapter(Uri video,String [] videos,Bundle savedInstanceState){
        this.video = video;
        this.videos = videos;
        this.savedInstanceState=savedInstanceState;

    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.help,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        MediaController controller = new MediaController(holder.videoView.getContext());
        controller.setMediaPlayer(holder.videoView);
       holder.videoView.setMediaController(controller);

        holder.videoView.setVideoURI(video);
       holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           @Override
           public void onPrepared(MediaPlayer mp) {

               // Hide buffering message.
              holder.buffering.setVisibility(VideoView.INVISIBLE);

               // Restore saved position, if available.
               if (mCurrentPosition > 0) {
                  holder.videoView.seekTo(mCurrentPosition);
               } else {
                   // Skipping to 1 shows the first frame of the video.
                   holder.videoView.seekTo(1);
               }

               // Start playing!
               holder.videoView.start();

           }
       });
       holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           public void onCompletion(MediaPlayer mp) {
               Toast.makeText(holder.videoView.getContext(),
                       "R.string.toast_message",
                       Toast.LENGTH_SHORT).show();

               // Return the video position to the start.
               holder.videoView.seekTo(0);
           }
       });



    }

    @Override
    public int getItemCount() {
        return videos.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        TextView buffering;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            buffering = itemView.findViewById(R.id.tvBuffer);

        }
    }



}
