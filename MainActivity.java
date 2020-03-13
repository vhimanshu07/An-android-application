package com.himanshu.orderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URI;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    private int quant=0;
    public void submitOrder(View view) {
        CheckBox x=(CheckBox)findViewById(R.id.not);
        boolean y=x.isChecked();
        CheckBox x1=(CheckBox)findViewById(R.id.nt);
                boolean z=x1.isChecked();
      //  String price="";
        EditText dd=(EditText)findViewById(R.id.name);
        String g=dd.getText().toString();

        //jb tk hmare pass language ka option nhi aaya tha toh hmne price string ko display krvaya tha
        //but ab hmare pass language aagyi toh jbhi aisa kia



//        if(y==false) {
//            price = "Name:" +g+"\nHas Chocolate topping"+z+"\nHas Whipped cream:"+y +"\nTotal : $" + (quant * (5+(z==true?2:0)+(y==true?1:0))) + "\n" + "Quantity:" + quant + "\n";
//            price = price + "\nThankYou!";
//        }
//        else
//        {
//             price = "Name:" +g+"\nHas Chocolate topping"+z+"\nHas Whipped cream:"+y + "\nTotal : $" + (quant * (5+(z==true?2:0)+(y==true?1:0))) + "\n" + "Quantity:" + quant + "\n";
//            price = price + "\nThankYou!";
//        }
  //      displayMessage(price);




        // Calculate the price
        int price = calculatePrice(y, z);
   String message = createOrderSummary(g, price, y, z);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, g));
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
displayMessage(message);

    }
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // First calculate the price of one cup of coffee
        int basePrice = 5;

        // If the user wants whipped cream, add $1 per cup
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        // If the user wants chocolate, add $2 per cup
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        // Calculate the total order price by multiplying by the quantity
        return quant * basePrice;
    }







    private String createOrderSummary(String name, int price, boolean addWhippedCream,
                                      boolean addChocolate) {
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, addChocolate);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quant);
        priceMessage += "\n" + getString(R.string.order_summary_price,
                NumberFormat.getCurrencyInstance().format(price));
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }





    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price);
        priceTextView.setText(message);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.ll);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    public void increment(View view) {
        quant+=1;
        if(quant>=100)
            quant=100;
        display(quant);
    }

    public void decrement(View view)
    {
        quant-=1;
        if(quant<=0)
            quant=1;
        display(quant);
    }
}

