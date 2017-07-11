package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DIPANSH KHANDELWAL on 08-05-2017.
 */

public class EarthquakeDataAdapter extends ArrayAdapter<EarthquakeData>{
    public EarthquakeDataAdapter(Context context, int resource, List<EarthquakeData> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.earthquake_data_layout, parent ,false);
        }

        TextView scale = (TextView) convertView.findViewById(R.id.scale_xml);
        TextView place = (TextView) convertView.findViewById(R.id.place_xml);
        TextView date = (TextView) convertView.findViewById(R.id.date_xml);
        TextView time = (TextView) convertView.findViewById(R.id.time_xml);

        EarthquakeData ed = getItem(position);

        GradientDrawable magnitudeCircle = (GradientDrawable) scale.getBackground();
        int magnitudeColor = getMagnitudeColor(ed.getScale());
        magnitudeCircle.setColor(magnitudeColor);

        scale.setText(ed.getScale().toString());
        place.setText(ed.getPlace());

        long timeInMilliseconds = ed.getDate();
        Date dateObject = new Date(timeInMilliseconds);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);

        date.setText(dateToDisplay);

        SimpleDateFormat dateFormatter2 = new SimpleDateFormat("h:mm a");
        String dateToDisplay2 = dateFormatter2.format(dateObject);

        time.setText(dateToDisplay2);



        return convertView;
    }

    private int getMagnitudeColor(Double magnitude) {
        int magCRId;
        int magFloor = (int) Math.floor(magnitude);

        switch (magFloor) {
            case 0:
            case 1:
                magCRId = R.color.magnitude1;
                break;
            case 2:
                magCRId = R.color.magnitude2;
                break;
            case 3:
                magCRId = R.color.magnitude3;
                break;
            case 4:
                magCRId = R.color.magnitude4;
                break;
            case 5:
                magCRId = R.color.magnitude5;
                break;
            case 6:
                magCRId = R.color.magnitude6;
                break;
            case 7:
                magCRId = R.color.magnitude7;
                break;
            case 8:
                magCRId = R.color.magnitude8;
                break;
            case 9:
                magCRId = R.color.magnitude9;
                break;
            default:
                magCRId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magCRId);


    }
}
