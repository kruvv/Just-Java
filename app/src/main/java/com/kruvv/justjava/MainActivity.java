package com.kruvv.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    EditText editName;
    boolean hasWhippedCream;
    boolean hasChocolate;


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

        String priceMessage = "Name: " + editName.getText() +
                "\nAdd whipped creame? " + hasWhippedCream +
                "\nAdd chocolate? " + hasChocolate +
                 "\nQuantity: " + quantity +
                    "\nTotal $" + calculatePrice(quantity) +
                        "\nThank you!";
        displayMessage(priceMessage);
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
        if(quantity >= 0){
            quantity++;
            displayQuantity(quantity);
        }

    }

    /**
     * This method decrements the value of the variable on the screen.
     */
    public void decrement(View view) {
        if(quantity >= 2){
            --quantity;
            displayQuantity(quantity);
        }

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
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
    public void createOrderSummary(boolean hasWhippedCream, boolean hasChocolate) {

    }
}
