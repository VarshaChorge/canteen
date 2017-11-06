package com.varsha.canteen;

/**
 * Created by Varsha on 4/3/2016.
 */
import java.util.ArrayList;


import android.content.Context;
import android.location.GpsStatus;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.List;

public class MorningSnackAdaptor extends BaseAdapter{
    private static ArrayList<MorningSnacksDetails> itemDetailsrrayList;
    Context contextgl;
    CustomOnItemSelectedListener listener;

    private Integer[] imgid = {
            R.drawable.batatawada,//1
            R.drawable.dabeli,//2
            R.drawable.shevpuri,//3
            R.drawable.dosa,
            R.drawable.idli,
            R.drawable.poha,
            R.drawable.upma,
            R.drawable.friedrice,//8
            R.drawable.paneer_tikka_masala,//9
            R.drawable.chicken_tikka_masala,//10
            R.drawable.veg_korma,//11
            R.drawable.chicken_popcorn,//12
            R.drawable.veg_makhanwala//13
    };

    private LayoutInflater l_Inflater;



    public MorningSnackAdaptor(Context context, ArrayList<MorningSnacksDetails> results) {
        itemDetailsrrayList = results;
        l_Inflater = LayoutInflater.from(context);
        contextgl=context;
    }

    public int getCount() {
        return itemDetailsrrayList.size();
    }

    public Object getItem(int position) {
        return itemDetailsrrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.morningsnackview, null);
            holder = new ViewHolder();
            holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
            holder.txt_itemPrice = (TextView) convertView.findViewById(R.id.price);
            holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);
            holder.spn_itemQuantity=(Spinner)convertView.findViewById(R.id.quantity);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_itemName.setText(itemDetailsrrayList.get(position).getName());
        holder.txt_itemPrice.setText(itemDetailsrrayList.get(position).getPrice());
        holder.itemImage.setImageResource(imgid[itemDetailsrrayList.get(position).getImageNumber() - 1]);

        if(itemDetailsrrayList.get(position).getQuantity()<=0)
        {
            //Display spinner contents for quantity
            // Spinner Drop down elements
            List<String> quantity = new ArrayList<String>();
            for (int i=0;i<=31;i++) {
                quantity.add(String.valueOf(i));
            }
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(contextgl, android.R.layout.simple_spinner_item, quantity);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            // attaching data adapter to spinner
            holder.spn_itemQuantity.setAdapter(dataAdapter);
            listener=new CustomOnItemSelectedListener();
            listener.setProductDetailParamenter(itemDetailsrrayList.get(position));
            //holder.spn_itemQuantity.setOnItemSelectedListener(new CustomOnItemSelectedListener());
            holder.spn_itemQuantity.setOnItemSelectedListener(listener);
            String selection=(String)holder.spn_itemQuantity.getSelectedItem();
            itemDetailsrrayList.get(position).setQuantity(Integer.parseInt((String)holder.spn_itemQuantity.getSelectedItem()));
            //itemDetailsrrayList.get(position).setQuantity(Integer.parseInt(listener.returnSelection()));
        }
        else{
            holder.spn_itemQuantity.setSelection(itemDetailsrrayList.get(position).getQuantity());
            itemDetailsrrayList.get(position).setQuantity(Integer.parseInt((String) holder.spn_itemQuantity.getSelectedItem()));
        }




        return convertView;
    }

    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }



    static class ViewHolder {
        TextView txt_itemName;
        TextView txt_itemPrice;
        Spinner spn_itemQuantity;
        ImageView itemImage;
    }


}
