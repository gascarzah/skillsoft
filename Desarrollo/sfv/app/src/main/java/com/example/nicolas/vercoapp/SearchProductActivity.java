package com.example.nicolas.vercoapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchProductActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    public static final int COLUMNS_NUMBER = 2;
    SearchView searchView;
    AlertDialog alertDialog;
    String queryEntered="";
    TextView selectedTypeTextView;
    LinearLayout filtersLinearLayout;
    RecyclerView productRecyclerView;
    TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productRecyclerView = (RecyclerView) findViewById(R.id.productsRecyclerView);
        emptyView = (TextView) findViewById(R.id.empty_view);
        final CharSequence[] values = {"Hombre","Mujer","No filtrar"};

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        selectedTypeTextView = (TextView) findViewById(R.id.selectedTypeTextView);
        filtersLinearLayout = (LinearLayout) findViewById(R.id.filtersLinearLayout);
        filtersLinearLayout.setVisibility(View.GONE);

        Button filterButton = (Button) findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(SearchProductActivity.this);
                builder.setTitle("Filtrar resultados");
                builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        //Filtro de acuerdo al género
                        ProductService productService=new ProductService();
                        String filterType=productService.getNameFilterType(item);
                        selectedTypeTextView.setText(filterType.toUpperCase());
                        doQuery(queryEntered, filterType);
                        dialog.cancel();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        queryEntered = query;
        selectedTypeTextView.setText("MOSTRAR TODOS");
        doQuery(queryEntered, "mostrar todo");

        return false;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {

        if(TextUtils.isEmpty(newText)){
            productRecyclerView.setVisibility(View.GONE);
            filtersLinearLayout.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
        }
        return false;
    }

    private void doQuery(String queryEntered, String selected){

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, COLUMNS_NUMBER);
        productRecyclerView.setLayoutManager(gridLayoutManager);
        final ArrayList<Product> productList = new ArrayList<>();
        emptyView.setVisibility(View.GONE);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Product");
        query.whereContains("labels", queryEntered);
        if(!"mostrar todo".equalsIgnoreCase(selected)) {
            query.whereEqualTo("sex", selected);
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        filtersLinearLayout.setVisibility(View.VISIBLE);
                        productRecyclerView.setVisibility(View.VISIBLE);
                        for (final ParseObject product : objects) {
                            final String trademark = product.getString("trademark");
                            final String model = product.getString("model");
                            final Double price = product.getDouble("price");
                            final Double discount = product.getDouble("discount");
                            final String sex = product.getString("sex");
                            final String type = product.getString("type");
                            final String labels = product.getString("labels");
                            //**********get bitmap from parse file
                            ParseFile photo = product.getParseFile("image");
                            if (photo == null) {
                                return;
                            }
                            photo.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e == null) {
                                        if (data.length == 0) {
                                            return;
                                        }
                                        Bitmap photoBmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        productList.add(new Product(trademark, model, price, discount, sex, type, labels, photoBmp));
                                        ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(), productList);
                                        productRecyclerView.setAdapter(productAdapter);
                                        productAdapter.setOnProductClickListener(new ProductAdapter.OnProductClickListener() {
                                            @Override
                                            public void onProductList(Product product) {
                                                Intent intent = new Intent(SearchProductActivity.this, ProductDetailActivity.class);
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    } else {
                        productRecyclerView.setVisibility(View.GONE);
                        filtersLinearLayout.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService((INPUT_METHOD_SERVICE));
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                } else {
                    Toast.makeText(SearchProductActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
