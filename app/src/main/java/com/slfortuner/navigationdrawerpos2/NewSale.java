package com.slfortuner.navigationdrawerpos2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.slfortuner.navigationdrawerpos2.adapters.ProductAdapter;
import com.slfortuner.navigationdrawerpos2.models.Products;

public class NewSale extends AppCompatActivity {

    private ListView productListView;
    ImageButton toCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_sale );
        initWidgets();
        loadFromDBTomMemory();
        setProductAdapter();

/////////////////////////////toolbar work/////////////////////////////////////////////////////////////////////
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar6 );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
////where the listview values pass to CartAdditem activity////////////////////////////////////////////////////
        productListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Products selectedProducts = (Products) productListView.getItemAtPosition( i );
                Intent addProductIntent = new Intent(getApplicationContext(), CartAddItems.class);
                addProductIntent.putExtra( Products.PRODUCT_EDIT_EXTRA, selectedProducts.getId() );
                startActivity( addProductIntent );

            }
        } );
        
  /////////////NewSale to cart activity////////////////////////////////////////////////////////////////////////
        toCart = findViewById( R.id.shoppingCart);
        toCart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NewSale.this, Summary.class);
                startActivity( intent );

            }
        } );


    }

    ////////////////////////////////////////////////productlist extraction from database//////////////////////////
    private void loadFromDBTomMemory(){

        SQLManager sqlManager = SQLManager.instanceOfDatabase( this );
        sqlManager.populateProductListArray();

    }

    private void setProductAdapter()
    {
        ProductAdapter productAdapter = new ProductAdapter( getApplicationContext(), Products.nonDeletedProducts());
        productListView.setAdapter( productAdapter );
    }

    private void initWidgets()
    {
        productListView = findViewById( R.id.listViewProducts2 );
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        setProductAdapter();
    }


}

