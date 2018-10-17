package de.clever.healthvalue.healthvalue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.clever.healthvalue.util.Range;
import de.clever.healthvalue.util.validators.ValidatorEditTextNumberRange;
import de.clever.healthvalue.dao.TemperatureDao;

public class TemperatureActivity extends AppCompatActivity {

    public static final String LOG_TAG = TemperatureActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);


        final EditText editTemperature = findViewById(R.id.editTemperature);
        final Button bAddTemperature = findViewById(R.id.bAddTemperature);

        final ValidatorEditTextNumberRange validatorEditTextNumberRange = new ValidatorEditTextNumberRange(editTemperature, new Range(35.0, 42.0));

        final TemperatureDao temperatureDao = new TemperatureDao(this);

        bAddTemperature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (validatorEditTextNumberRange.isValid()) {

                    Double dTemperature = Double.parseDouble(editTemperature.getText().toString());
                    try {
                        // TODO write to database.
                        temperatureDao.open();


                    } catch (Exception ex) {
                        // TODO Implement exception handling
                    }
                } else {
                    // TODO Write validation errors to gui.
                }

            }

        });
    }


}
