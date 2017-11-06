package com.varsha.canteen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class partyOrder extends AppCompatActivity {
    private LinkedHashMap<String, HeaderInfo> myCatogories = new LinkedHashMap<String, HeaderInfo>();
    private ArrayList<HeaderInfo> catogoryList = new ArrayList<HeaderInfo>();

    private LunchAdaptor listAdapter;
    private ExpandableListView myList;
    Context context;
    Button btnSubmit;
    Button btnShareOrder;
    String userName;
    String ordermessage = "Order is \n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_order);
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
        TextView txvUserName=(TextView)findViewById(R.id.txvPartyOrderUName);
        txvUserName.setText(userName);
        ordermessage="Order from "+userName+" is \n";

        loadData();

        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.myListPartyOrder);
        //create the adapter by passing your ArrayList data
        listAdapter = new LunchAdaptor(partyOrder.this, catogoryList);
        //attach the adapter to the list
        myList.setAdapter(listAdapter);

        //expand all Groups
        expandAll();

        //listener for child row click
        myList.setOnChildClickListener(myListItemClicked);
        //listener for group heading click
        myList.setOnGroupClickListener(myListGroupClicked);
        btnSubmit = (Button) findViewById(R.id.btnPartyOrdersubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderDetails();
            }
        });
        btnShareOrder = (Button) findViewById(R.id.btnPartyOrderShareOrder);
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
    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void loadData() {
        addProduct("Veg Lunch", "Chapati", "5.00", 1);
        addProduct("Veg Lunch", "Roti", "10.00", 16);
        addProduct("Veg Lunch", "Paneer Tikka Masala", "40.00",13);
        addProduct("Veg Lunch", "Veg Korma", "40.00",14);
        addProduct("Veg Lunch", "Veg Makhanwala", "40.00",15);
        addProduct("Veg Lunch", "Gajar Halawa", "40.00",12);
        addProduct("Non Veg Lunch", "Chapati", "5.00", 1);
        addProduct("Non Veg Lunch", "Roti", "10.00", 16);
        addProduct("Non Veg Lunch", "Chicken Malvani", "40.00",8);
        addProduct("Non Veg Lunch", "Chicken Tikka Masala", "40.00",10);
        addProduct("Non Veg Lunch", "Chicken Hyderabadi", "40.00",11);
        addProduct("Non Veg Lunch", "Chicken Popcorn", "40.00",12);
    }

    private ExpandableListView.OnChildClickListener myListItemClicked = new ExpandableListView.OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {

            //get the group header
            HeaderInfo headerInfo = catogoryList.get(groupPosition);
            //get the child info
            MorningSnacksDetails itemDetails = headerInfo.getProductList().get(childPosition);
            //display it or do something with it
            /*Toast.makeText(partyOrder.this, "Clicked on Detail " + headerInfo.getName()
                    + "/" + itemDetails.getName() + "with Quantity " + itemDetails.getQuantity(), Toast.LENGTH_LONG).show();*/
            return false;
        }

    };
    //our group listener
    private ExpandableListView.OnGroupClickListener myListGroupClicked = new ExpandableListView.OnGroupClickListener() {

        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {

            //get the group header
            HeaderInfo headerInfo = catogoryList.get(groupPosition);
            //display it or do something with it
            /*Toast.makeText(Lunch.this, "Child on Header " + headerInfo.getName(),
                    Toast.LENGTH_LONG).show();*/

            return false;
        }

    };

    private int addProduct(String department, String product, String Price, int imageNo) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        HeaderInfo headerInfo = myCatogories.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new HeaderInfo();
            headerInfo.setName(department);
            myCatogories.put(department, headerInfo); //hashmap
            catogoryList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<MorningSnacksDetails> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        MorningSnacksDetails detailInfo = new MorningSnacksDetails();
        detailInfo.setName(product);
        detailInfo.setPrice(Price);
        detailInfo.setImageNumber(imageNo);
        detailInfo.setQuantity(0);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = catogoryList.indexOf(headerInfo);
        return groupPosition;
    }

    private void getOrderDetails() {
        int headercount = 0;
        ordermessage="Order from "+userName+" is \n";
        headercount = listAdapter.getGroupCount();
        for (int i = 0; i < headercount; i++) {

            HeaderInfo headerInfo = (HeaderInfo) listAdapter.getGroup(i);
            //get the children for the group
            ArrayList<MorningSnacksDetails> productList = headerInfo.getProductList();
            //size of the children list
            int listSize = productList.size();
            for (int j = 0; j < listSize; j++) {
                MorningSnacksDetails detailInfo = (MorningSnacksDetails) listAdapter.getChild(i, j);
                String name = detailInfo.getName();
                String price = detailInfo.getPrice();
                int quantity = detailInfo.getQuantity();
                if (quantity != 0) {
                    ordermessage = ordermessage + " " + name + " " + String.valueOf(quantity) + "\n";
                }
            }
        }
        Toast.makeText(partyOrder.this, ordermessage, Toast.LENGTH_SHORT).show();
    }
}

