package com.newlogin.mainpage;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.newlogin.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tarun on 5/17/2016.
 */
public class NeedBlood extends Activity implements ListAdapter.customButtonListener {

    private ListView listView;
    ListAdapter adapter;
    ArrayList<String> dataItems = new ArrayList<String>();
    EditText inputSearch;


    public void onButtonClickListner(int position, String value) {
        Toast.makeText(NeedBlood.this, "Button click " + value,
                Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.needblood);
        String[] dataArray = getResources().getStringArray(R.array.listdata);
        List<String> dataTemp = Arrays.asList(dataArray);
        dataItems.addAll(dataTemp);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ListAdapter(NeedBlood.this, dataItems);
        adapter.setCustomButtonListner(NeedBlood.this);
        listView.setAdapter(adapter);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                NeedBlood.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });


    }
}
