package com.example.hws;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout wishlistLayout;
    private EditText totalPriceEditText;
    private ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wishlistLayout = findViewById(R.id.wishlist_layout);
        totalPriceEditText = findViewById(R.id.total_price_edit_text);

        items = new ArrayList<>();

        // Пример предметов
        addItem(new Item("Мебель на кухню", 5000));
        addItem(new Item("Телевизор в спальню", 500));
        addItem(new Item("Кровать в спальню", 1000));
    }

    private void addItem(Item item) {
        items.add(item);

        View itemView = getLayoutInflater().inflate(R.layout.item_layout, null);

        TextView itemNameTextView = itemView.findViewById(R.id.item_name_text_view);
        TextView itemPriceTextView = itemView.findViewById(R.id.item_price_text_view);
        CheckBox itemCheckBox = itemView.findViewById(R.id.item_check_box);

        itemNameTextView.setText(item.getName());
        itemPriceTextView.setText("$" + item.getPrice());

        itemCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setChecked(isChecked);
            calculateTotalPrice();
        });

        wishlistLayout.addView(itemView);

        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        int totalPrice = 0;
        for (Item item : items) {
            if (item.isChecked()) {
                totalPrice += item.getPrice();
            }
        }
        totalPriceEditText.setText("$" + totalPrice);
    }
}