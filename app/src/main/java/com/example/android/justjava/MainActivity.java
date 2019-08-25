/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity=0,price;
    boolean hasWhippedCream,hasChocolate;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText name=(EditText) findViewById(R.id.name_field);
        String Name=name.getText().toString();
        CheckBox whippedCream=(CheckBox) findViewById(R.id.whippedCream_check_box);
        hasWhippedCream=whippedCream.isChecked();
        CheckBox isChocolate=(CheckBox) findViewById(R.id.chocolate_check_box);
        hasChocolate=isChocolate.isChecked();
        Log.v("MainActivity", "Has Whipped Cream? "+hasWhippedCream);
        Log.v("MainActivity", "Has Chocolate "+hasChocolate);
        price=calculatePrice(quantity);
        String priceMessage=createOrderSummary(Name, price, hasWhippedCream, hasChocolate);

        Intent intent=new Intent((Intent.ACTION_SENDTO));
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for "+Name);
        intent.putExtra(intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);}



    }

    public void increment(View view){

        if(quantity>100) {

            Toast.makeText(this,"You cannot have a quantity more than 100", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }
    public void deccrement(View view){

        if(quantity<1){

            Toast.makeText(this,"You cannot have a quantity less than 0", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate){
        String OrderSummary="Name : "+name+"\nHas Whipped Cream? " + hasWhippedCream + "\nHas Chocolate? "+hasChocolate+"\nQuantity : "+quantity+"\nTotal : $"+price+"\nThank You!";
        return OrderSummary;
    }


    private int calculatePrice(int Quantity){

        int Price=5;
        if(hasChocolate)
            Price+=2;
        if(hasWhippedCream)
            Price++;

        return Price*Quantity;
    }
}