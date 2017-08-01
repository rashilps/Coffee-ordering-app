/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *

 */
package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.jar.Attributes;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        EditText NameField = (EditText) findViewById(R.id.name_field);
        String name = NameField.getText().toString();
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        CheckBox WippedCreamCheckBox = (CheckBox) findViewById(R.id.wipped_cream_checkbox);
        Boolean hasChocolate = ChocolateCheckBox.isChecked();
        Boolean hasWippedCream = WippedCreamCheckBox.isChecked();

        int price = calculatePrice(hasWippedCream, hasChocolate);

        String priceMessage = createOrderSummary(name, price, hasWippedCream, hasChocolate, quantity);
        
        
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name, name));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }
    public void increment(View view) {

         if(quantity==100)
        {
            Toast.makeText(this,"you cannot have more than 100 cups of coffee",Toast.LENGTH_LONG).show();

            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    public void decrement (View view) {
        if (quantity==1)
        {
            Toast.makeText(this,"you cannot have less than 1 cups of coffee",Toast.LENGTH_LONG).show();

            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        int basePrice=5;
        if (addWhippedCream){
            basePrice=basePrice+1;
        }
        if (addChocolate)
        {
            basePrice=basePrice+2;
        }
        return quantity * basePrice;
    }
    private String  createOrderSummary(String name, int price, Boolean addWippedCream, Boolean addChocolate, int quantity){
        displayQuantity(quantity);
        String priceMessage = "Name: " + name +  "\nAdd wipped cream? " + addWippedCream + "\nAdd Chocolate? " + addChocolate + "\nQuantity = "   + quantity +
                "\nTotal= $" + price  + "\nThank you!";
        return priceMessage;


    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */

}
