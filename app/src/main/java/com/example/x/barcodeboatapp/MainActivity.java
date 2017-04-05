package com.example.x.barcodeboatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends Activity implements OnClickListener {


    private Button scanBtn, addData;
    private TextView formatTxt, contentTxt, console;
    private EditText name, brand, unit, quantity;
    private static String EAN;
    private static String artikelName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        addData = (Button)findViewById(R.id.addData);
        console = (TextView)findViewById(R.id.console);

        name = (EditText) findViewById(R.id.editText);
        brand = (EditText) findViewById(R.id.editText2);
        unit = (EditText) findViewById(R.id.editText3);
        quantity = (EditText) findViewById(R.id.editText4);


        scanBtn.setOnClickListener(this);
        addData.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();

        }
        if(v.getId()==R.id.addData){

            {
            String str = name.getText().toString();
            Toast msg = Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG);

            msg.show();
                artikelName = str;
                try {
                    PostgreSQLJDBC.writetoDB(EAN, artikelName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }



    }


    public  void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);

            EAN = scanContent;
            String artikeldaten = "https://www.outpan.com/key?k=" + EAN;
            console.setText(artikeldaten);
        }
            else{
                Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
                toast.show();
        }
        }
    }

