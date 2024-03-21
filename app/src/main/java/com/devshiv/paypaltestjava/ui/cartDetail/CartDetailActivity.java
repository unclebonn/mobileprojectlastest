package com.devshiv.paypaltestjava.ui.cartDetail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.devshiv.paypaltestjava.MainActivity;
import com.devshiv.paypaltestjava.R;
import com.devshiv.paypaltestjava.api.CartDetail.CartDetail;
import com.devshiv.paypaltestjava.api.Controllers.CartDetailController;
import com.devshiv.paypaltestjava.api.Controllers.TransactionController;
import com.devshiv.paypaltestjava.api.Transaction.TransactionRequestModel;
import com.paypal.checkout.approve.Approval;
import com.paypal.checkout.approve.OnApprove;
import com.paypal.checkout.createorder.CreateOrder;
import com.paypal.checkout.createorder.CreateOrderActions;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.OrderIntent;
import com.paypal.checkout.createorder.UserAction;
import com.paypal.checkout.order.Amount;
import com.paypal.checkout.order.AppContext;
import com.paypal.checkout.order.CaptureOrderResult;
import com.paypal.checkout.order.OnCaptureComplete;
import com.paypal.checkout.order.OrderRequest;
import com.paypal.checkout.order.PurchaseUnit;
import com.paypal.checkout.paymentbutton.PaymentButtonContainer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class CartDetailActivity extends AppCompatActivity {

    ListView cartDetailLv;

    CartDetailAdapter adapter;

    ArrayList<CartDetail> cartDetailArrayList;

    String token;
    String customerId;
    String cartId;
    PaymentButtonContainer paymentButtonContainer;

    double totalAmount = 0;


    public interface CartDetailCallback {
        void onGetTotalAmount(double totalAmount);

        void onGetCartDetailFailed(String errorMsg);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartdetail);


        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        customerId = sharedPreferences.getString("customerId", "");
        cartId = sharedPreferences.getString("cart", "");

        //get button payment
        paymentButtonContainer = findViewById(R.id.payment_button_container);

        //get list view of cart detail
        cartDetailLv = (ListView) findViewById(R.id.horizontal_recycler_view_cart_detail);
        cartDetailArrayList = new ArrayList<>();
        adapter = new CartDetailAdapter(CartDetailActivity.this, R.layout.rvcart_item, cartDetailArrayList);
        cartDetailLv.setAdapter(adapter);

        getCartDetailFromCustomer(new CartDetailCallback() {
            @Override
            public void onGetTotalAmount(double amount) {


                totalAmount = amount;

                Log.d("totalAmount", "onGetTotalAmount: " + totalAmount);
            }

            @Override
            public void onGetCartDetailFailed(String errorMsg) {

            }
        });


        //paypal payment
        paymentButtonContainer.setup(
                new CreateOrder() {
                    @Override
                    public void create(@NotNull CreateOrderActions createOrderActions) {
                        ArrayList<PurchaseUnit> purchaseUnits = new ArrayList<>();
                        purchaseUnits.add(
                                new PurchaseUnit.Builder()
                                        .amount(
                                                new Amount.Builder()
                                                        .currencyCode(CurrencyCode.USD)
                                                        .value(String.valueOf(totalAmount))
                                                        .build()
                                        )
                                        .build()
                        );
                        OrderRequest order = new OrderRequest(
                                OrderIntent.CAPTURE,
                                new AppContext.Builder()
                                        .userAction(UserAction.PAY_NOW)
                                        .build(),
                                purchaseUnits
                        );
                        createOrderActions.create(order, (CreateOrderActions.OnOrderCreated) null);
                    }
                },
                new OnApprove() {
                    @Override
                    public void onApprove(@NotNull Approval approval) {
                        approval.getOrderActions().capture(new OnCaptureComplete() {
                            @Override
                            public void onCaptureComplete(@NotNull CaptureOrderResult result) {
                                TransactionController transactionController = new TransactionController();
                                TransactionRequestModel transactionRequestModel = new TransactionRequestModel(cartId,totalAmount);
                                transactionController.callApiTransaction(token, transactionRequestModel, new TransactionController.TransactionCallBack() {
                                    @Override
                                    public void onTransacSuccess(String message) {
                                        Intent intent = new Intent(CartDetailActivity.this, MainActivity.class);
                                        Toast.makeText(CartDetailActivity.this, "Thanks for buying our products", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                    }
                                });
                                Log.i("CaptureOrder", String.format("CaptureOrderResult: %s", result));
                            }
                        });
                    }
                }
        );

    }


    public void getCartDetailFromCustomer(final CartDetailCallback callback) {
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);

        final double[] totalAmount = {0};
        CartDetailController cartDetailController = new CartDetailController();
        cartDetailController.callApiGetCartDetailById(token, customerId, new CartDetailController.CartDetailCallBack() {
            @Override
            public void onGetCartDetailSuccess(List<CartDetail> cartDetail) {
                Toast.makeText(CartDetailActivity.this, "Get cart detail", Toast.LENGTH_SHORT).show();
                for (CartDetail detail : cartDetail) {
                    Log.d("priceeeeeee", "onGetCartDetailSuccess: " + detail.getProduct().getPrice());
                    totalAmount[0] += (double) detail.getProduct().getPrice();
                    cartDetailArrayList.add(detail);
                }

                adapter.notifyDataSetChanged();
                callback.onGetTotalAmount(totalAmount[0]);

            }

            @Override
            public void onGetCartDetailFailed(String msgFailed) {

            }
        });


    }


}
