package com.devshiv.paypaltestjava.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devshiv.paypaltestjava.R;
import com.devshiv.paypaltestjava.api.Controllers.ProductController;
import com.devshiv.paypaltestjava.api.Product.Product;
import com.devshiv.paypaltestjava.databinding.FragmentHomeBinding;
import com.devshiv.paypaltestjava.ui.detail.DetailActivity;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView rv1;
    private RecyclerView rv2;
    private LinearLayoutManager linearLayoutManager1;
    private LinearLayoutManager linearLayoutManager2;
    private MyRvAdapter myRvAdapter1;
    private MyRvAdapter myRvAdapter2;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // RecyclerView 1
        rv1 = binding.horizontalRecyclerView;
        linearLayoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        int imageIds[] = {R.drawable.cream, R.drawable.lipstick, R.drawable.facewash, R.drawable.serum, R.drawable.toner};
        myRvAdapter1 = new MyRvAdapter(new ArrayList<>(), imageIds, context);
        rv1.setLayoutManager(linearLayoutManager1);
        rv1.setAdapter(myRvAdapter1);

        // RecyclerView 2
        rv2 = binding.horizontalRecyclerView2;
        linearLayoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        myRvAdapter2 = new MyRvAdapter(new ArrayList<>(), imageIds, context);
        rv2.setLayoutManager(linearLayoutManager2);
        rv2.setAdapter(myRvAdapter2);

        // Call API to fetch products
        fetchProducts();

        return root;
    }

    private void fetchProducts() {
        ProductController productController = new ProductController();
        productController.callApiGetProducts(new ProductController.ProductsCallBack() {
            @Override
            public void onGetProductSuccess(List<Product> listProducts) {
                updateRecyclerView(listProducts);
            }

            @Override
            public void onGetProductFailed() {
                handleFetchProductsFailure();
            }
        });
    }

    private void updateRecyclerView(List<Product> listProducts) {
        myRvAdapter1.setProductList(new ArrayList<>(listProducts));
        myRvAdapter2.setProductList(new ArrayList<>(listProducts));
        myRvAdapter1.notifyDataSetChanged();
        myRvAdapter2.notifyDataSetChanged();
    }

    private void handleFetchProductsFailure() {
        // Handle failure to fetch products
        // For example, display a toast message
        Toast.makeText(requireContext(), "Failed to fetch products", Toast.LENGTH_SHORT).show();
    }

    class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {

        private ArrayList<Product> productList;
        private int[] imageIds;
        private Context context;

        public MyRvAdapter(ArrayList<Product> productList, int[] imageIds, Context context) {
            this.productList = productList;
            this.imageIds = imageIds;
            this.context = context;
        }

        public void setProductList(ArrayList<Product> productList) {
            this.productList = productList;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            Product product = productList.get(position);
            holder.tvTitle.setText(product.getName());
            holder.lvImage.setImageResource(imageIds[position % imageIds.length]);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductController productController = new ProductController();
                    productController.callApiGetProductDetail(product.getId(), new ProductController.ProductDetailCallBack() {
                        @Override
                        public void onGetProductSuccess(Product product) {
                            Intent intent = new Intent(context, DetailActivity.class);
                            intent.putExtra("product", product);
                            intent.putExtra("imageResourceId", imageIds);
                            context.startActivity(intent);
                        }

                        @Override
                        public void onGetProductFailed() {

                        }
                    });


//
                }
            });
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }


        class MyHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;
            ImageView lvImage;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvTitle);
                lvImage = itemView.findViewById(R.id.lvImage);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
