package com.example.tempratureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Spinner spUnit;
    EditText edtValue;
    Button btnConvert;
    TextView txtResult;

    private DecimalFormat _formatRes = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialize();
    }

    // Initialize components.
    private void Initialize() {
        edtValue =  findViewById(R.id.edtTop);
        btnConvert = findViewById(R.id.btnConvert);
        spUnit = findViewById(R.id.spnrUnts);
        txtResult = findViewById(R.id.txtResult);
    }

    public void Convert(View view) {
        if (edtValue.getText().length() == 0) {
            // Display warning.
            ShowAlert();
            return;
        }

        float val = Float.parseFloat(edtValue.getText().toString());
        float res = 0;
        String resUnit = "";

        switch (spUnit.getSelectedItemPosition()) {
            case 0:
                res = (val * 9 / 5) + 32;
                resUnit = "°F";
                break;

            case 1:
                res = (val - 32) * 5/9;
                resUnit = "°C";
                break;
        }

        txtResult.setText(_formatRes.format(res) + resUnit);
        HideKeyBoard(view);
    }

    private void HideKeyBoard (View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // Warning message.
    private void ShowAlert() {
        // Display dialog alert.
        DescDialog dialog = new DescDialog();
        dialog.show(getSupportFragmentManager(), "description");
    }
}
