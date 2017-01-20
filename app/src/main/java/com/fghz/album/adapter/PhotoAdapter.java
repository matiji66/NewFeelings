package com.fghz.album.adapter;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import com.bumptech.glide.Glide;
import com.fghz.album.Config;
import com.fghz.album.R;
import com.fghz.album.entity.PhotoItem;

import static android.media.ThumbnailUtils.extractThumbnail;
import static com.fghz.album.utils.ImagesScaner.getBitmap;

/**
 * 照片栏目的适配器
 * Created by me on 16-12-21.
 */
public class PhotoAdapter extends ArrayAdapter<PhotoItem> {
    private int resourceId;

    List<PhotoItem> mImageList;
    private Context context;

    public PhotoAdapter(Context context, int textViewResourceId,
                        List<PhotoItem> objects) {
        super(context, textViewResourceId, objects);

        this.context = context;
        mImageList = objects;
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PhotoItem photo = getItem(position);
        ImageView myImageView = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
        }

        myImageView = (ImageView) convertView.findViewById(R.id.photo_small);
        String url = photo.getImageId();
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .error(R.drawable.error)
                .crossFade()
                .thumbnail(0.1f).into(myImageView);
        return convertView;
    }


}

