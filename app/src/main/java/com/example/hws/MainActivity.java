package com.example.hws;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner senderSpinner, receiverSpinner;
    private EditText amountEditText;
    private Button transferButton, cancelButton;
    private ListView accountListView;

    private ArrayList<String> accountsList;
    private ArrayAdapter<String> accountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senderSpinner = findViewById(R.id.senderSpinner);
        receiverSpinner = findViewById(R.id.receiverSpinner);
        amountEditText = findViewById(R.id.amountEditText);
        transferButton = findViewById(R.id.transferButton);
        cancelButton = findViewById(R.id.cancelButton);
        accountListView = findViewById(R.id.accountListView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.accounts_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        senderSpinner.setAdapter(adapter);
        receiverSpinner.setAdapter(adapter);

        accountsList = new ArrayList<>();
        accountsList.add("1: $1000");
        accountsList.add("2: $2000");
        accountsList.add("3: $3000");
        accountsList.add("4: $4000");

        accountAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accountsList);
        accountListView.setAdapter(accountAdapter);

        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TransferTask().execute();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Payment canceled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class TransferTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(10000);
                String sender = senderSpinner.getSelectedItem().toString();
                String receiver = receiverSpinner.getSelectedItem().toString();
                String amount = amountEditText.getText().toString();
                accountsList.add(sender + " -> " + receiver + ": $" + amount);
                accountAdapter.notifyDataSetChanged();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "Payment transferred", Toast.LENGTH_SHORT).show();
        }
    }
}