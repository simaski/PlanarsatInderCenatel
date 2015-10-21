package com.cenatel.desarrollo.planarsatinder;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sancasimiro on 19/10/15.
 */
public class ListViewDemoFragment extends ListFragment {
    private List<ListViewItem> mItems;        // ListView items list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<ListViewItem>();
        Resources resources = getResources();

        mItems.add(new ListViewItem(getString(R.string.op_embalse), getString(R.string.titulo_cenatel)));
        mItems.add(new ListViewItem(getString(R.string.op_derivacion), getString(R.string.titulo_cenatel)));
        mItems.add(new ListViewItem(getString(R.string.op_laguna), getString(R.string.titulo_cenatel)));
        mItems.add(new ListViewItem(getString(R.string.op_pozo), getString(R.string.titulo_cenatel)));
        mItems.add(new ListViewItem(getString(R.string.op_vialidad), getString(R.string.titulo_cenatel)));

        // initialize and set the list adapter
        setListAdapter(new ListViewDemoAdapter(getActivity(), mItems));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        ListViewItem item = mItems.get(position);
        if(position == 4) {
            // do something
            Toast.makeText(getActivity(), "VIALIDAD", Toast.LENGTH_SHORT).show();
        }
    }
}