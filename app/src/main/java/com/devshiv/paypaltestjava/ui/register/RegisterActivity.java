package com.devshiv.paypaltestjava.ui.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.devshiv.paypaltestjava.R;
import com.devshiv.paypaltestjava.api.Cart.Cart;
import com.devshiv.paypaltestjava.api.Cart.RequestCartModel;
import com.devshiv.paypaltestjava.api.Controllers.CartController;
import com.devshiv.paypaltestjava.api.Controllers.CustomerController;
import com.devshiv.paypaltestjava.api.Customer.Customer;
import com.devshiv.paypaltestjava.api.Customer.RequestCustomerModel;
import com.devshiv.paypaltestjava.ui.login.LoginActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword, editTextConfirmPassword, editUsername, editPhoneNumber;
    Button buttonReg;
    FirebaseAuth mAuth;
    TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance(); // Initialize mAuth here
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editUsername = findViewById(R.id.username);
        editPhoneNumber = findViewById(R.id.phoneNumber);
        editTextConfirmPassword = findViewById(R.id.confirm_password);
        buttonReg = findViewById(R.id.btn_register);
        textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password, confirmPassword, username, phoneNumber;
                username = String.valueOf(editUsername.getText());
                phoneNumber = String.valueOf(editPhoneNumber.getText());
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                confirmPassword = String.valueOf(editTextConfirmPassword.getText());

                // Check if all fields are filled
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Enter confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if passwords match
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(RegisterActivity.this, "Enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(RegisterActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {


                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Log.d("thisiduid", "onComplete: " + user.getUid());

                                    // create new customer in nodejs database
                                    CustomerController customerController = new CustomerController();
                                    RequestCustomerModel customer = new RequestCustomerModel(user.getUid(), email, username, phoneNumber);

                                    Log.d("customereere", "onComplete: " + customer);
                                    customerController.callApiCreateNewCustomer(customer, new CustomerController.CustomerCallBack() {
                                        @Override
                                        public void onCreateCustomerSuccess(Customer customer) {
                                            String customerId = customer.getId();

                                            // create a new cart for customer
                                            CartController cartController = new CartController();
                                            RequestCartModel requestCartModel = new RequestCartModel(customerId);

                                            Log.d("requestCartModel", "onCreateCustomerSuccess: " + requestCartModel);
                                            cartController.callApiCreateCart(requestCartModel, new CartController.CartDetailCreateCallBack() {
                                                @Override
                                                public void onSuccessCreateCart(Cart cart) {
                                                    Toast.makeText(RegisterActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }

                                                @Override
                                                public void onFailedCreateCart(String msgFailed) {

                                                }
                                            });




                                        }

                                        @Override
                                        public void onCreateCustomerFailed(String msgFailed) {

                                        }
                                    });

                                    // Redirect to Login activity upon successful registration

                                } else {
                                    // If registration fails, display a message to the user.
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}