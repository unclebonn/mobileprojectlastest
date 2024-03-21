package com.devshiv.paypaltestjava.ui.detail;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.devshiv.paypaltestjava.R;
import com.devshiv.paypaltestjava.api.CartDetail.CartDetailCreate;
import com.devshiv.paypaltestjava.api.CartDetail.RequestCartDetailModel;
import com.devshiv.paypaltestjava.api.Controllers.CartDetailController;
import com.devshiv.paypaltestjava.api.Product.Product;


public class DetailActivity extends AppCompatActivity {
    Button buttonPlus, buttonMinus , buttonAddToCart;
    TextView textViewNumber;
    Product product;
    int imageId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize views
        buttonAddToCart = findViewById(R.id.detail_addtocart);
        buttonPlus = findViewById(R.id.plus);
        buttonMinus = findViewById(R.id.minus);
        textViewNumber = findViewById(R.id.text_view_number);

        // Get the product data from the intent
        product = (Product) getIntent().getSerializableExtra("product");
        imageId = getIntent().getIntExtra("imageId", 0);

        // Display product details
        if (product != null) {
            displayProductDetails();
        }

        // get store value from sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        String cart = sharedPreferences.getString("cart","");

        // add product to cart
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartDetailController cartDetailController = new CartDetailController();
                RequestCartDetailModel requestCartDetailModel = new RequestCartDetailModel(product.getId(),1,cart);

                cartDetailController.callApiCreateCartDetail(token, requestCartDetailModel, new CartDetailController.CreateCartDetailCallBack() {
                    @Override
                    public void onCreateCartDetailSuccess(CartDetailCreate cartDetailCreate) {
                        if(cartDetailCreate != null){
                            Toast.makeText(DetailActivity.this, "Add to cart successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onGetCartDetailFailed(String msgFailed) {

                    }
                });
            }
        });


        // Set onClickListeners for buttons
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plus(v);
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minus(v);
            }
        });


    }

    private void displayProductDetails() {
        TextView title,price,description;
        ImageView imageView;

        imageView = findViewById(R.id.detail_image);
        title = findViewById(R.id.detail_title);
        price = findViewById(R.id.detail_price);
        description = findViewById(R.id.detail_description);

        title.setText(product.getName());
        price.setText("Price: $" + product.getPrice());
        description.setText(product.getDescription());
        if (imageId != 0) {
            imageView.setImageResource(imageId);
        } else {
            Log.d("DetailActivity", "Image ID is 0");
        }
    }

    public void plus(View view) {
        int currentValue = Integer.parseInt(textViewNumber.getText().toString());
        currentValue++;
        textViewNumber.setText(String.valueOf(currentValue));
    }

    public void minus(View view) {
        int currentValue = Integer.parseInt(textViewNumber.getText().toString());
        if (currentValue > 1) {
            currentValue--;
            textViewNumber.setText(String.valueOf(currentValue));
        }
    }
}
