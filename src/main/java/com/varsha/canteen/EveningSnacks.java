package com.varsha.canteen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EveningSnacks extends AppCompatActivity {
    ArrayList<MorningSnacksDetails> results = new ArrayList<MorningSnacksDetails>();
    Button btnSubmit;
    Button btnShareOrder;
    String userName;
    String ordermessage = "Order is \n";
    MorningSnackAdaptor morningSnackAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evening_snacks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        userName=intent.getStringExtra("USERNAME");
        TextView txvUserName=(TextView)findViewById(R.id.txvEveningSnackUName);
        txvUserName.setText(userName);
        ordermessage="Order from "+userName+" is \n";
        ArrayList<MorningSnacksDetails> morningSnacksDetails = GetEveningSnacks();
        final ListView lv1 = (ListView) findViewById(R.id.listV_Eveningsnacks);

        morningSnackAdaptor=new MorningSnackAdaptor(EveningSnacks.this, morningSnacksDetails);
        lv1.setAdapter(morningSnackAdaptor);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                MorningSnacksDetails obj_itemDetails = (MorningSnacksDetails) o;
               // Toast.makeText(EveningSnacks.this, "You have chosen : " + " " + obj_itemDetails.getName() + " Quantity " + obj_itemDetails.getQuantity(), Toast.LENGTH_LONG).show();
            }
        });
        btnSubmit = (Button) findViewById(R.id.btnEveningSnackSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderDetails();
            }
        });
        btnShareOrder = (Button) findViewById(R.id.btnEveningSnacksShareOrder);
        btnShareOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderDetails();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, ordermessage);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }
    private ArrayList<MorningSnacksDetails> GetEveningSnacks(){

        MorningSnacksDetails morningSnack1=setSnackParameters("Batata Wada","15.00",1,0);
        MorningSnacksDetails morningSnack2=setSnackParameters("Dabeli", "15.00", 2, 0);
        MorningSnacksDetails morningSnack3=setSnackParameters("Shevpuri","15.00",3,0);


        results.add(morningSnack1);
        results.add(morningSnack2);
        results.add(morningSnack3);



        return results;
    }
    private MorningSnacksDetails setSnackParameters(String name,String price,int imgnumber,int quantity){
        MorningSnacksDetails morningSnack = new MorningSnacksDetails();
        morningSnack.setName(name);
        morningSnack.setPrice(price);
        morningSnack.setImageNumber(imgnumber);
        morningSnack.setQuantity(quantity);
        return morningSnack;
    }
    private void getOrderDetails() {
        int itemcount = 0;
        ordermessage="Order from "+userName+" is \n";
        itemcount = morningSnackAdaptor.getCount();

        for (int j = 0; j < itemcount; j++) {
            MorningSnacksDetails detailInfo = (MorningSnacksDetails) morningSnackAdaptor.getItem(j);
            String name = detailInfo.getName();
            String price = detailInfo.getPrice();
            int quantity = detailInfo.getQuantity();
            if (quantity != 0) {
                ordermessage = ordermessage + " " + name + " " + String.valueOf(quantity) + "\n";
            }
        }
        Toast.makeText(EveningSnacks.this, ordermessage, Toast.LENGTH_SHORT).show();
    }
}
