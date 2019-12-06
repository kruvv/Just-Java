package com.kruvv.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

    int quantity = 0;
    int whippedCream = 1;
    int chocolate = 2;
    EditText editName;
    boolean hasWhippedCream;
    boolean hasChocolate;
    String priceMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        editName = findViewById(R.id.edit_name_edit_text);

        priceMessage = getString(R.string.name) + editName.getText();
        priceMessage += "\n" + getString(R.string.add_whipped_cream) + hasWhippedCream;
        priceMessage += "\n" + getString(R.string.add_chocolate) + hasChocolate;
        priceMessage += "\n" + getString(R.string.quantity_two) + quantity;
        priceMessage += "\n" + getString(R.string.total) + createOrderSummary(hasWhippedCream, hasChocolate);
        priceMessage += "\n" + getString(R.string.thank_you);

        //displayMessage(priceMessage);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method increments the value of the variable on the screen.
     */
    public void increment(View view) {
        if(quantity >= 0 & quantity < 100){
            quantity++;
            displayQuantity(quantity);
            int summary = createOrderSummary(hasWhippedCream, hasChocolate);
            displayMessage(summary);
        }else if (quantity == 100) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.more_then, Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    /**
     * This method decrements the value of the variable on the screen.
     */
    public void decrement(View view) {
        if(quantity >= 2) {
            --quantity;
            displayQuantity(quantity);
            int summary = createOrderSummary(hasWhippedCream, hasChocolate);
            displayMessage(summary);
        }else if (quantity == 1){
            Toast toast = Toast.makeText(getApplicationContext(), R.string.less_then, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(int summary) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText("$" + summary);

    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(int quantity) {
        int price = quantity * 5;
        return price;
    }

    public void onCheckboxClicked(View view) {

        switch (view.getId()) {
            case R.id.checkbox_toppings:
                hasWhippedCream = ((CheckBox) view).isChecked();
                break;

            case R.id.checkbox_chocolate:
                hasChocolate = ((CheckBox) view).isChecked();
                break;
        }
        createOrderSummary(hasChocolate, hasChocolate);
    }

    /**
     * This method crate order
     */
    public int createOrderSummary(boolean hasWhippedCream, boolean hasChocolate) {

        int sum = 0;

        if (hasWhippedCream & !hasChocolate ) {
             sum = calculatePrice(quantity) + whippedCream * quantity;
        }else if (!hasWhippedCream & hasChocolate) {
            sum = calculatePrice(quantity) + chocolate * quantity;
        }else if (hasWhippedCream & hasChocolate) {
            sum = calculatePrice(quantity) + (chocolate + whippedCream) * quantity;
        }else {
            sum = calculatePrice(quantity);
        }
        return sum;

    }
}
