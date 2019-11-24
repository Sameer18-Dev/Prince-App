package com.example.chmarax.logregform;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class SalesOrderForm extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private static final String TAG = "SalesOrderForm";

    private CheckBox check;
    private Button submit;

    private TextView mDisplayDate1, mDisplayDate2, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22;
    private DatePickerDialog.OnDateSetListener mDateSetListener1, mDateSetListener2;

    private String bookingdate, deliverydate, fullname, fatherhusbandname, financialinstitute, dealer, address, city, province, cnic, ntn, strn, cell, telephone, vehicle, model, color, advanceamount, paychkno, slipno, bank, totalpay, balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order_form);


        mDisplayDate1 = (TextView) findViewById(R.id.BookingDate);
        mDisplayDate2 = (TextView) findViewById(R.id.DeliveryDate);

        v2 = (TextView) findViewById(R.id.FullName);
        v3 = (TextView) findViewById(R.id.FatherHusbandName);
        v4 = (TextView) findViewById(R.id.FinancialInstitute);
        v5 = (TextView) findViewById(R.id.Dealer);
        v6 = (TextView) findViewById(R.id.Address);
        v7 = (TextView) findViewById(R.id.City);
        v8 = (TextView) findViewById(R.id.Province);
        v9 = (TextView) findViewById(R.id.Cnic);
        v10 = (TextView) findViewById(R.id.Ntn);
        v11 = (TextView) findViewById(R.id.Strn);
        v12 = (TextView) findViewById(R.id.Cell);
        v13 = (TextView) findViewById(R.id.Telephone);
        v14 = (TextView) findViewById(R.id.Vehicle);
        v15 = (TextView) findViewById(R.id.Model);
        v16 = (TextView) findViewById(R.id.Color);
        v17 = (TextView) findViewById(R.id.AdvanceAmount);
        v18 = (TextView) findViewById(R.id.PayChkNo);
        v19 = (TextView) findViewById(R.id.SlipNo);
        v20 = (TextView) findViewById(R.id.Bank);
        v21 = (TextView) findViewById(R.id.Payment);
        v22 = (TextView) findViewById(R.id.Balance);

        check = (CheckBox)findViewById(R.id.checkBox);
        submit = (Button) findViewById(R.id.btnSubmit);



        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //         Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //         toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    //        Log.d(TAG, "onAuthStateChanged:signed_out");
                    //        toastMessage("Successfully signed out.");
                }
                // ...
            }
        };



        mDisplayDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SalesOrderForm.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDisplayDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SalesOrderForm.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });



        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate1.setText(date);
                bookingdate = date;
            }
        };

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate2.setText(date);
                deliverydate = date;
            }
        };



            submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

//                if(validateInput())

                    if(check.isChecked()){

                    fullname = v2.getText().toString();
                    fatherhusbandname = v3.getText().toString();
                    financialinstitute = v4.getText().toString();
                    dealer = v5.getText().toString();
                    address = v6.getText().toString();
                    city = v7.getText().toString();
                    province = v8.getText().toString();
                    cnic = v9.getText().toString();
                    ntn = v10.getText().toString();
                    strn = v11.getText().toString();
                    cell = v12.getText().toString();
                    telephone = v13.getText().toString();
                    vehicle = v14.getText().toString();
                    model = v15.getText().toString();
                    color = v16.getText().toString();
                    advanceamount = v17.getText().toString();
                    paychkno = v18.getText().toString();
                    slipno = v19.getText().toString();
                    bank = v20.getText().toString();
                    totalpay = v21.getText().toString();
                    balance = v22.getText().toString();


                    // Read from the database
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            Object value = dataSnapshot.getValue();
                            Log.d(TAG, "Value is: " + value);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w(TAG, "Failed to read value.", error.toException());
                        }
                    });


                            Log.d(TAG, "onClick: Attempting to add object to database.");

                                FirebaseUser user = mAuth.getCurrentUser();

                                String userID = user.getUid();


                    myRef.child("users").child(userID).child("Full_Name").child(fullname).setValue("true");
                    myRef.child("users").child(userID).child("Father_Husband_Name").child(fatherhusbandname).setValue("true");
                    myRef.child("users").child(userID).child("Financial_Institute").child(financialinstitute).setValue("true");
                    myRef.child("users").child(userID).child("Dealer").child(dealer).setValue("true");
                    myRef.child("users").child(userID).child("Address").child(address).setValue("true");
                    myRef.child("users").child(userID).child("City").child(city).setValue("true");
                    myRef.child("users").child(userID).child("Province").child(province).setValue("true");
                    myRef.child("users").child(userID).child("CNIC").child(cnic).setValue("true");
                    myRef.child("users").child(userID).child("NTN").child(ntn).setValue("true");
                    myRef.child("users").child(userID).child("STRN").child(strn).setValue("true");
                    myRef.child("users").child(userID).child("Cell").child(cell).setValue("true");
                    myRef.child("users").child(userID).child("Telephone").child(telephone).setValue("true");
                    myRef.child("users").child(userID).child("Vehicle").child(vehicle).setValue("true");
                    myRef.child("users").child(userID).child("Model").child(model).setValue("true");
                    myRef.child("users").child(userID).child("Color").child(color).setValue("true");
                    myRef.child("users").child(userID).child("Advance_Amount").child(advanceamount).setValue("true");
                    myRef.child("users").child(userID).child("Pay_Check_NO").child(paychkno).setValue("true");
                    myRef.child("users").child(userID).child("Slip_NO").child(slipno).setValue("true");
                    myRef.child("users").child(userID).child("Bank").child(bank).setValue("true");
                    myRef.child("users").child(userID).child("Total_Pay").child(totalpay).setValue("true");
                    myRef.child("users").child(userID).child("Balance_Amount").child(balance).setValue("true");

                    myRef.child("users").child(userID).child("Booking_Date").child(bookingdate).setValue("true");
                    myRef.child("users").child(userID).child("Delivery_Date").child(deliverydate).setValue("true");


                 //   toastMessage("Adding " + newFood + " to database...");

                    startActivity(new Intent(SalesOrderForm.this,MainActivity.class));

                }else{
                    Toast.makeText(SalesOrderForm.this,"Accept Our Terms and Conditions!",Toast.LENGTH_LONG).show();

                }


            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



    public boolean validateInput(){

        if(bookingdate.isEmpty()){
            mDisplayDate1.setError("Field is empty.");
            return false;
        }
        if(deliverydate.isEmpty()){
            mDisplayDate2.setError("Field is empty.");
            return false;
        }
        if(fullname.isEmpty()){
            v2.setError("Field is empty.");
            return false;
        }
        if(fatherhusbandname.isEmpty()){
            v3.setError("Field is empty.");
            return false;
        }
        if(financialinstitute.isEmpty()){
            v4.setError("Field is empty.");
            return false;
        }
        if(dealer.isEmpty()){
            v5.setError("Field is empty.");
            return false;
        }
        if(address.isEmpty()){
            v6.setError("Password is empty.");
            return false;
        }
        if(city.isEmpty()){
            v7.setError("Field is empty.");
            return false;
        }
        if(province.isEmpty()){
            v8.setError("Field is empty.");
            return false;
        }
        if(cnic.isEmpty()){
            v9.setError("Field is empty.");
            return false;
        }
        if(ntn.isEmpty()){
            v10.setError("Field is empty.");
            return false;
        }
        if(strn.isEmpty()){
            v11.setError("Field is empty.");
            return false;
        }
        if(cell.isEmpty()){
            v12.setError("Field is empty.");
            return false;
        }
        if(telephone.isEmpty()){
            v13.setError("Field is empty.");
            return false;
        }
        if(vehicle.isEmpty()){
            v14.setError("Field is empty.");
            return false;
        }
        if(model.isEmpty()){
            v15.setError("Field is empty.");
            return false;
        }
        if(color.isEmpty()){
            v16.setError("Field is empty.");
            return false;
        }
        if(advanceamount.isEmpty()){
            v17.setError("Field is empty.");
            return false;
        }
        if(paychkno.isEmpty()){
            v18.setError("Password is empty.");
            return false;
        }
        if(slipno.isEmpty()){
            v19.setError("Field is empty.");
            return false;
        }
        if(bank.isEmpty()){
            v20.setError("Field is empty.");
            return false;
        }
        if(totalpay.isEmpty()){
            v21.setError("Field is empty.");
            return false;
        }
        if(balance.isEmpty()){
            v22.setError("Field is empty.");
            return false;
        }

        return true;
    }

}
