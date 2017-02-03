package com.example.ramraj.datecarousal;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ramraj on 2/3/2017.
 */

public class DateCarousal extends HorizontalScrollView {

    private HorizontalScrollView dateCarousal;
    private LinearLayout dateCarousalContainer;
    private ArrayList<CarousalClickListener> clickListeners = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    public DateCarousal(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = mLayoutInflater.inflate(R.layout.date_carousal, this, true);

        dateCarousal = (HorizontalScrollView) v.findViewById(R.id.dateCarousal);
        dateCarousalContainer = (LinearLayout)v.findViewById(R.id.dateCarousalContainer);
        setupDateCarousal();
    }

    public void addClickListener(CarousalClickListener listener){
        this.clickListeners.add(listener);
    }

    private void setupDateCarousal() {
//        dt = new LocalDate();
        int daysExtraToShow = 3;
//        LocalDate dateToStartFrom = dt.plusDays(daysExtraToShow);

        for (int i = 20; i >= 0; i--) {
            View itemDateCarousalLayout = mLayoutInflater.inflate(R.layout.item_date_carousal, null);
            itemDateCarousalLayout.setPadding(
                    getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin), 0,
                    getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin), 0);

            final TextView daysOfMonthTV = (TextView) itemDateCarousalLayout.findViewById(R.id.dayOfMonthTV);
            final TextView monthOfYearTV = (TextView) itemDateCarousalLayout.findViewById(R.id.monthOfYearTV);

            daysOfMonthTV.setText(String.valueOf(i));
            daysOfMonthTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            monthOfYearTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            daysOfMonthTV.setTypeface(null, Typeface.NORMAL);
            monthOfYearTV.setTypeface(null, Typeface.NORMAL);

            dateCarousalContainer.addView(itemDateCarousalLayout);

            if (i == daysExtraToShow) {
                focusOnView(dateCarousalContainer, itemDateCarousalLayout);
            }
            if (i < daysExtraToShow) {
                fadeOutView(itemDateCarousalLayout);
            } else if (i >= daysExtraToShow) {
                itemDateCarousalLayout.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        focusOnView(dateCarousalContainer, v);

//                        DateTimeFormatter formatterForDisplay = DateTimeFormatter.forPattern("dd/MM/YYYY");
//
//                        DateTime formattedDate = formatterForDisplay.parseDateTime(
//                                daysOfMonthTV.getText().toString()
//                                        + "/"
//                                        + monthOfYearTV.getText().toString()
//                                        + "/"
//                                        + dt.year().getAsText()
//                        );

//                        String finalDesiredDate = Helpers.prepareDateForAPIRequest(formattedDate.toLocalDate());
//
//                        String todaysDateFormatted = dt.getYear() + "-" + dt.getMonthOfYear() + "-" + dt.getDayOfMonth();

                        for (CarousalClickListener listener : clickListeners) {
                            listener.handleClick("somedate");
                        }
                    }
                });
            }
        }
    }

    private void focusOnView(final LinearLayout dateCarousalContainer, final View view) {
        TextView dayOfMonthTV;
        TextView monthOfYearTV;
        LinearLayout dateEntryLayout;

        for (int i = 0; i < dateCarousalContainer.getChildCount(); i++) {
            dateEntryLayout = (LinearLayout) dateCarousalContainer.getChildAt(i).findViewById(R.id.dateEntryLayout);
            dayOfMonthTV = (TextView) dateCarousalContainer.getChildAt(i).findViewById(R.id.dayOfMonthTV);
            monthOfYearTV = (TextView) dateCarousalContainer.getChildAt(i).findViewById(R.id.monthOfYearTV);

            dateEntryLayout.setBackgroundColor(0);
            dayOfMonthTV.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            monthOfYearTV.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            dayOfMonthTV.setTypeface(null,Typeface.NORMAL);
            monthOfYearTV.setTypeface(null,Typeface.NORMAL);

        }

        dateEntryLayout = (LinearLayout) view.findViewById(R.id.dateEntryLayout);
        dayOfMonthTV = (TextView) view.findViewById(R.id.dayOfMonthTV);
        monthOfYearTV = (TextView) view.findViewById(R.id.monthOfYearTV);

        dateEntryLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        dayOfMonthTV.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        monthOfYearTV.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        dayOfMonthTV.setTypeface(null,Typeface.BOLD);
        monthOfYearTV.setTypeface(null,Typeface.BOLD);
    }

    private void fadeOutView(View view){
        view.setAlpha(0.5f);
    }

    public interface CarousalClickListener{
        void handleClick(String desiredDate);
    }
}
