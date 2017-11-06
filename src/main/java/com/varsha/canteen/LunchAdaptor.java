package com.varsha.canteen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Varsha on 4/4/2016.
 */
public class LunchAdaptor extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<HeaderInfo> catogoryList;
    CustomOnItemSelectedListener listener;
    //private static ArrayList<ItemDetails> itemDetailsrrayList;
    private Integer[] imgid = {
            R.drawable.chapati,
            R.drawable.dalrice,
            R.drawable.dietmenu,
            R.drawable.friedrice,
            R.drawable.pavbhaji,
            R.drawable.pulav,
            R.drawable.vegthali,
            R.drawable.chicken_malvani,//8
            R.drawable.chicken_popcorn,//9
            R.drawable.chicken_tikka_masala,//10
            R.drawable.chiken_hyderabadi,//11
            R.drawable.gajar_halawa,//12
            R.drawable.paneer_tikka_masala,//13
            R.drawable.veg_korma,//14
            R.drawable.veg_makhanwala,//15
            R.drawable.roti //16
    };

    public LunchAdaptor(Context context, ArrayList<HeaderInfo> catogoryList) {
        this.context = context;
        this.catogoryList = catogoryList;
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<MorningSnacksDetails> itemDetailsrrayList = catogoryList.get(groupPosition).getProductList();
        return itemDetailsrrayList.get(childPosition);

        /*ArrayList<ItemDetails> productList = catogoryList.get(groupPosition).getProductList();
        return productList.get(childPosition);*/
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        MorningSnacksDetails itemDetails = (MorningSnacksDetails) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.morningsnackview, null);
        }

        //TextView sequence = (TextView) convertView.findViewById(R.id.sequence);
        //sequence.setText(itemDetails.getSequence().trim() + ") ");
        TextView childItem = (TextView) convertView.findViewById(R.id.name);
        childItem.setText(itemDetails.getName().trim());
        TextView itemPrice=(TextView)convertView.findViewById(R.id.price);
        itemPrice.setText(itemDetails.getPrice().trim());
        ImageView itemImage=(ImageView)convertView.findViewById(R.id.photo);
        itemImage.setImageResource(imgid[itemDetails.getImageNumber()-1]);
        Spinner spn_itemQuantity=(Spinner)convertView.findViewById(R.id.quantity);

        if(itemDetails.getQuantity()<=0) {
            //Display spinner contents for quantity
            // Spinner Drop down elements
            List<String> quantity = new ArrayList<String>();
            for (int i = 0; i <= 31; i++) {
                quantity.add(String.valueOf(i));
            }
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_item, quantity);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            // attaching data adapter to spinner
            spn_itemQuantity.setAdapter(dataAdapter);

            listener=new CustomOnItemSelectedListener();
            listener.setProductDetailParamenter(itemDetails);
            spn_itemQuantity.setOnItemSelectedListener(listener);
            itemDetails.setQuantity(Integer.parseInt((String) spn_itemQuantity.getSelectedItem()));
        }
        else{
            spn_itemQuantity.setSelection(itemDetails.getQuantity());
            itemDetails.setQuantity(Integer.parseInt((String) spn_itemQuantity.getSelectedItem()));
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<MorningSnacksDetails> itemDetailsrrayList = catogoryList.get(groupPosition).getProductList();
        return itemDetailsrrayList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return catogoryList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return catogoryList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        HeaderInfo headerInfo = (HeaderInfo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.group_heading, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(headerInfo.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
 }

