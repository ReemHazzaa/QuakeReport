package com.example.reem.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by reemHazzaa on 11/12/2018.
 *
 * {@link EarthquakeAdapter} is an {@link ArrayAdapter} that can provide
 * the layout of the earthquakes list based on data source, which is
 * a list of Earthquake objects
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the list.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param earthquakes A List of AndroidFlavor objects to display in a list
     */
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView with the ID quakeMagnitude
        TextView quakeMagnitudeTextView = convertView.findViewById(R.id.quakeMagnitude);
        // Get the quake magnitude from the current Earthquake object and Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getEarthquakeMagnitude());
        // Set this text on the quakeMagnitude TextView
        quakeMagnitudeTextView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) quakeMagnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getEarthquakeMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Find the TextView in the earthquake_list_item.xml layout with the ID quakeLocationOffset
        TextView quakeLocationOffsetTextView = convertView.findViewById(R.id.quakeLocationOffset);
        // Get the quake location from the current Earthquake object and set this text on the quakeLocation TextView
        quakeLocationOffsetTextView.setText(currentEarthquake.getEarthquakeLocation());

        // Find the TextView in the earthquake_list_item.xml layout with the ID quakeLocationOffset
        TextView quakeLocationPrimaryTextView = convertView.findViewById(R.id.quakeLocationPrimary);
        // Get the quake location from the current Earthquake object and set this text on the quakeLocation TextView
        quakeLocationPrimaryTextView.setText(currentEarthquake.getEarthquakeLocation());

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView in the earthquake_list_item.xml layout with the ID quakeDate
        TextView quakeDateTextView = convertView.findViewById(R.id.quakeDate);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        quakeDateTextView.setText(formattedDate);

        // Find the TextView in the earthquake_list_item.xml layout with the ID quakeTime
        TextView quakeTimeTextView = convertView.findViewById(R.id.quakeTime);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        quakeTimeTextView.setText(formattedTime);

        // Location formatting(Splitting into 2 strings)
        String originalLocation = currentEarthquake.getEarthquakeLocation();
        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        // Once we have the 2 separate Strings, we can display them in the 2 TextViews in the list item layout.
        TextView primaryLocationView = convertView.findViewById(R.id.quakeLocationPrimary);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = convertView.findViewById(R.id.quakeLocationOffset);
        locationOffsetView.setText(locationOffset);


        // Return the whole list item layout (containing 4 TextViews)
        // so that it can be shown in the ListView
        return convertView;
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     *
     * @param magnitude is the current Earthquake magnitude.
     * @return the integer value that will determine the background circle color of
     * the magnitude TextView.
     */
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        // Use the Math class to take the “floor” of the decimal magnitude value.
        // This means finding the closest integer less than the decimal value.
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        // Once we find the right color resource ID, convert it into an actual color value.
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted time string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

}
