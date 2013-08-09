package com.ameron32.mlvt;

import java.util.ArrayList;
import java.util.Arrays;

import com.mobeta.android.dslv.DragSortListView;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	
	DragSortListView dslv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        String[] array = getResources().getStringArray(R.array.jazz_artist_names);
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(array));

        adapter = new ArrayAdapter<String>(this, R.layout.list_item_radio, R.id.text, arrayList);
                
        dslv = (DragSortListView) findViewById(R.id.list);
        
        dslv.setAdapter(adapter);
        
        dslv.setDropListener(onDrop);
        dslv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    private ArrayAdapter<String> adapter;

    private DragSortListView.DropListener onDrop =
        new DragSortListView.DropListener() {
            @Override
            public void drop(int from, int to) {
                if (from != to) {
                    String item = adapter.getItem(from);
                    adapter.remove(item);
                    adapter.insert(item, to);
                    dslv.moveCheckState(from, to);
                    Log.d("DSLV", "Selected item is " + dslv.getCheckedItemPosition());
                }
            }
        };

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.checkable_main);
//        
//        String[] array = getResources().getStringArray(R.array.jazz_artist_names);
//        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(array));
//
//        adapter = new ArrayAdapter<String>(this, R.layout.list_item_radio, R.id.text, arrayList);
//        
//        setListAdapter(adapter);
//        
//        DragSortListView list = getListView();
//        list.setDropListener(onDrop);
//        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//    }

//    @Override
//    public DragSortListView getListView() {
//        return (DragSortListView) super.getListView();
//    }

}
