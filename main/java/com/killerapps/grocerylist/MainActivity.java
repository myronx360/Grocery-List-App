package com.killerapps.grocerylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> groceryList = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    String item;
    String quantity;
    String price;
    String coupon;
    double total = 0.00;
    double Sales_Tax = .02;

    // TODO: calculate and update the totsl cost after tax
    private double updateTotal(double amount){
        total = total + amount;
        TextView totalText = (TextView) findViewById(R.id.lblTotal);
        totalText.setText("$ " + Double.toString(total));
        return total;
    }

    // TODO: calculate tax total
    private double getTax(double amount){
        return amount * Sales_Tax;
    }

    // TODO: Calculate the amount of an item by multiplying the
    // price and quantity then subtract any coupons
    private double subTotal(String q, String p){
        double amount = 0.00;
        q =  quantity;
        p = price;


        EditText couponText = (EditText) findViewById(R.id.txtCoupon);
        coupon = couponText.getText().toString();

        if(coupon.isEmpty())
            coupon = "0";

        amount = (Double.parseDouble(q) * Double.parseDouble(p)) - Double.parseDouble(coupon);

        return amount;
    }

    /** Called when the user clicks the Add Item button */
    //TODO: Add the quantity, name, and price to list. Then update total
    public void addItem(View view) {
        String itemSummary = "";

        EditText quantityText = (EditText) findViewById(R.id.txtQuantity);
        quantity = quantityText.getText().toString();

        EditText itemText = (EditText) findViewById(R.id.txtItem);
        item = itemText.getText().toString();

        EditText priceText = (EditText) findViewById(R.id.txtPrice);
        price = priceText.getText().toString();

        double subTotal = subTotal(quantity, price);
        updateTotal(getTax(subTotal) + subTotal);


        itemSummary = quantity + "\t\t" + item + "\t\t" + "$ " + price;

        groceryList.add(itemSummary);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, groceryList);
        listView.setAdapter(adapter);
    }

    /** Called when the user clicks the Save List button */
    public void saveList(View view) {
//        groceryList.set(0,"pk");
//        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, groceryList);
//        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listItems);
    }
}
