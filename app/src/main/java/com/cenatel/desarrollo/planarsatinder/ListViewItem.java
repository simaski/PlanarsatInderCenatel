package com.cenatel.desarrollo.planarsatinder;

import android.graphics.drawable.Drawable;

/**
 * Created by sancasimiro on 19/10/15.
 */
public class ListViewItem {
    //public final Drawable icon;       // the drawable for the ListView item ImageView
    public final String title;        // the text for the ListView item title
    public final String description;  // the text for the ListView item description

    public ListViewItem(String title, String description) {
        //this.icon = icon;
        this.title = title;
        this.description = description;
    }
}