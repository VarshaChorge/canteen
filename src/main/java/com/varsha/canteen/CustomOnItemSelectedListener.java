package com.varsha.canteen;

/**
 * Created by Varsha on 4/4/2016.
 */
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class CustomOnItemSelectedListener implements OnItemSelectedListener {
    private String selectedItem="";
    private MorningSnacksDetails morningSnacksDetails;
    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        /*Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();*/
        selectedItem=parent.getItemAtPosition(pos).toString();
        morningSnacksDetails.setQuantity(Integer.parseInt(selectedItem));
    }
    public String returnSelection()
    {
        return selectedItem;
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void setProductDetailParamenter(MorningSnacksDetails morningSnacksDetails)
    {

        this.morningSnacksDetails=morningSnacksDetails;
    }

}