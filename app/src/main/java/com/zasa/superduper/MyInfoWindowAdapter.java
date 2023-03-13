package com.zasa.superduper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.zasa.superduper.activities.TrackOperationPlaceActivity;

public class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    Context context;
    public MyInfoWindowAdapter(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        View infoView = LayoutInflater.from(context).inflate(R.layout.custom_info, null);
        TextView title = infoView.findViewById(R.id.title);
        TextView snipp = infoView.findViewById(R.id.snipp);
        title.setText(marker.getTitle());
        snipp.setText(marker.getSnippet());
        return infoView;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        return null;
    }
}
