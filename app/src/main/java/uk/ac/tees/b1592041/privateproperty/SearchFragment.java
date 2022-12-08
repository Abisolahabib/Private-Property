package uk.ac.tees.b1592041.privateproperty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;


public class SearchFragment extends Fragment {

    TextView time, date;
    Button dateBtn, timeBtn;
    String timeText, dateText;

    private View layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Button dateBtn = view.findViewById(R.id.date_button);
        Button timeBtn = view.findViewById(R.id.time_button);
        TextView date = view.findViewById(R.id.date_selected);
        TextView time = view.findViewById(R.id.time_selected);


        MaterialDatePicker picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Time")
                .build();

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.show(getActivity().getSupportFragmentManager(), "Material_Date_Picker");
                picker.addOnPositiveButtonClickListener(selection -> {
                    date.setText(picker.getHeaderText());
                    dateText = picker.getHeaderText();
                });
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.show(getActivity().getSupportFragmentManager(), "Material_Time_Picker");
                timePicker.addOnPositiveButtonClickListener(selection -> {
                    time.setText(String.format("%dh:%dmin", timePicker.getHour(), timePicker.getMinute()));
                    timeText = String.format("%dh:%dmin", timePicker.getHour(), timePicker.getMinute());
                });
            }
        });
        return view;
    }
}








