package com.varsha.canteen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.varsha.canteen.R;

/**
 * Created by Varsha on 12/2/2015.
 */
public class CanteenTwo extends Fragment{
    ListView listView ;
    Context context;
    private View rootView;
    public String userName;
    String[] values = new String[] {
            "Morning Snacks",
            "Lunch",
            "Evening Snacks",
            "Dinner",
            "Party Order",
            "Fast Food/Punjabi",
    };
    public CanteenTwo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_two, container, false);
        rootView = inflater.inflate(R.layout.fragment_main_list, container, false);
        context = rootView.getContext(); // Assign your rootView to context

        Intent intent = CanteenTwo.this.getActivity().getIntent();
        userName=intent.getStringExtra(Login.EXTRA_MESSAGE);
        TextView txvUserName=(TextView)rootView.findViewById(R.id.txvcanteenOneUName);
        txvUserName.setText(userName);

        // Get ListView object from xml
        listView = (ListView) rootView.findViewById(R.id.listV_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CanteenTwo.this.getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, values);
// Assign adapter to ListView

        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
               /* Toast.makeText(getActivity(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();*/
                switch(itemPosition){
                    case 0:
                    {
                        Intent i = new Intent(context, MorningSnacks.class);
                        i.putExtra("USERNAME",userName);
                        startActivity(i);
                        break;
                    }
                    case 1:
                    {
                        Intent i = new Intent(CanteenTwo.this.getContext(), Lunch.class);
                        i.putExtra("USERNAME",userName);
                        startActivity(i);
                        break;
                    }
                    case 2:
                    {
                        Intent i = new Intent(CanteenTwo.this.getContext(), EveningSnacks.class);
                        i.putExtra("USERNAME",userName);
                        startActivity(i);
                        break;
                    }
                    case 3:
                    {
                        Intent i = new Intent(CanteenTwo.this.getContext(), Dinner.class);
                        i.putExtra("USERNAME",userName);
                        startActivity(i);
                        break;
                    }
                    case 4:
                    {
                        Intent i = new Intent(CanteenTwo.this.getContext(), partyOrder.class);
                        i.putExtra("USERNAME",userName);
                        startActivity(i);
                        break;
                    }
                    case 5:
                    {
                        Intent i = new Intent(CanteenTwo.this.getContext(), FastFoodPunjabi.class);
                        i.putExtra("USERNAME",userName);
                        startActivity(i);
                        break;
                    }
                }

            }

        });

        return rootView;

    }
}
