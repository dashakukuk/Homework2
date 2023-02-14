package com.msaggik.secondlessonmortgagecalculationalgorithm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    float apartmentPrice = 14000;
    int account =1000;
    float wage = 2500;
    int percentFree = 100;
    float percentBank = 5;
    float[] monthlyPayments = new float[120];


    private float apartmentPriceWithContribution() {
        return apartmentPrice - account;
    }


    public float mortgageCosts(float amount, int percent) {
        return (amount*percent)/100;
    }


    public int countMonth(float total, float mortgageCosts, float percentBankYear) {

        float percentBankMonth = percentBankYear / 12;
        int count = 0;

        // алгоритм расчёта ипотеки
        while (total>0) {
            count++;
            monthlyPayments [count -1] = Math.min(total, mortgageCosts);
            total = (total + (total*percentBankMonth)/100) - mortgageCosts;
        }

        return count;
    }


    private TextView countOut;
    private TextView manyMonthOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        countOut = findViewById(R.id.countOut);
        manyMonthOut = findViewById(R.id.manyMonthOut);


        countOut.setText("Телескоп будет выплачиваться " + countMonth(apartmentPriceWithContribution(), mortgageCosts(wage, percentFree),percentBank) + " месяцев");

        String monthlyPaymentsList = "";
        for(float list : monthlyPayments) {
            if (list > 0) {
                monthlyPaymentsList = monthlyPaymentsList + Float.toString(list) + " монет ";
            } else {
                break;
            }
        }

        manyMonthOut.setText("Первоначальный взнос " + account + " монет, ежемесячные выплаты: " + monthlyPaymentsList);
    }
}