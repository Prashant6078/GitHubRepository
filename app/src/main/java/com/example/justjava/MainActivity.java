package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        EditText name1=(EditText)findViewById(R.id.name);
        String name= name1.getText().toString();


        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.WhippedCreamCheckBox);
        boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();


        CheckBox  ChocklateCheckBox = (CheckBox) findViewById(R.id.ChocklateCheckBox);
        boolean haschocklate = ChocklateCheckBox.isChecked();

        int price = calculatePrice(haschocklate,hasWhippedCream);

        String priceMessage = createOrderSunnary(price,hasWhippedCream,name,haschocklate);

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL,"emailaddress@emailaddress.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"ORDER SUMMARY..");
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager())!= null) {
            startActivity(intent);
        }

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);

    }

    /*private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }*/

    public void increment(View view) {
        if (quantity>99){
            Toast.makeText(this,"Prashant",Toast.LENGTH_LONG).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {

        if (quantity <1) {
            Toast.makeText(this,"Prashant",Toast.LENGTH_LONG).show();

            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);


    }

    private int calculatePrice( boolean haschocklate,boolean hasCWhippedCream) {
            int baseprice=5;

            if (hasCWhippedCream){
                baseprice= baseprice+1;
            }

            if (haschocklate){
                baseprice=baseprice+2;
            }
       return quantity*baseprice;
    }

    private String createOrderSunnary(int price,boolean hasWhippedCream,String name,boolean haschocklate) {

        String priceMessage = "Name:"+name+
                "\nQuantity: " + quantity +
                "\nTotal$:" + price +
                "\nHas Whipped Cream ? " + hasWhippedCream +
                "\nHas Chocklate ? " + haschocklate +
                "\nThank You..!";
             return priceMessage;


        }
    }


