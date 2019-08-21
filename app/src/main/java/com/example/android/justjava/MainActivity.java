/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
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
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        price=calculatePrice(quantity);
        String priceMessage=createOrderSummary(price);
        displayMessage(priceMessage);

    }

    public void increment(View view){
        quantity++;
        display(quantity);
    }
    public void deccrement(View view){
        quantity--;
        if(quantity<0)
            quantity=0;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String createOrderSummary(int price){
        String OrderSummary="Name : Sherlock Holmes\nQuantity : "+quantity+"\nTotal : $"+price+"\nThank You!";
        return OrderSummary;
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.orderSummary_text_view);
        orderSummaryTextView.setText(message);
    }

    private int calculatePrice(int num){
        return num*5;
    }
}