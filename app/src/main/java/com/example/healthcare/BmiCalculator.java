package com.example.healthcare;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;


public class BmiCalculator extends AppCompatActivity {

    private EditText weight, height;
    private TextView result;
    private Button calculate;
    Spinner spinnerWeight, spinnerHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        weight = (EditText)findViewById(R.id.etWeight);
        height = (EditText)findViewById(R.id.etHeight);
        calculate = (Button)findViewById(R.id.btnCalculate);
        result = (TextView)findViewById(R.id.tvResult);
//        spinnerWeight = (Spinner)findViewById(R.id.spinner_weight);
//        spinnerHeight = (Spinner)findViewById(R.id.spinner_height);
//
//        List<String> list1 = new ArrayList<String>();
//        list1.add("Kg");
//        list1.add("lbs");
//
//        List<String> list2 = new ArrayList<String>();
//        list2.add("cm");
//        list2.add("inch");
//
//        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list1);
//        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerWeight.setAdapter(arrayAdapter1);
//        spinnerWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String weight = parent.getItemAtPosition(position).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list2);
//        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerHeight.setAdapter(arrayAdapter2);
//        spinnerHeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                spinnerHeight.setSelection(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmiCalculator();
            }
        });

    }

    private void bmiCalculator(){
        float wei, hei, bmi;
        wei = Float.parseFloat(weight.getText().toString()); // weight is in kg
        hei = Float.parseFloat(height.getText().toString())/100; // Converted cm to meter
        bmi = wei / (hei * hei);
        result.setText(""+bmi);

        if(bmi<18.8){
            Toast.makeText(this, "You are Underweight", Toast.LENGTH_SHORT).show();
        }else if((bmi>=18.8) && (bmi<25)){
            Toast.makeText(this, "You are Normal", Toast.LENGTH_SHORT).show();
        }else if(bmi>=25){
            Toast.makeText(this, "You are Overweight", Toast.LENGTH_SHORT).show();
        }
    }
}
