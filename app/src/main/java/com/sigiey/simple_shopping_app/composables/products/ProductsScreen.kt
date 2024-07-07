package com.sigiey.simple_shopping_app.composables.products

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sigiey.simple_shopping_app.R
import com.sigiey.simple_shopping_app.composables.checkout.CheckoutViewModel
import com.sigiey.simple_shopping_app.data.Product

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductsScreen(navController: NavController, viewModel: CheckoutViewModel) {
    val products = listOf(
        Product("1", "Product 1", 10.0),
        Product("2", "Product 2", 15.0),
        Product("3", "Product 3", 20.0)
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        ProductList(products = products, navController = navController, viewModel = viewModel)
    }
}

@Composable
fun ProductList(products: List<Product>, navController: NavController, viewModel: CheckoutViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(products) { product ->
                ProductItem(product = product, onClick = {
                    viewModel.addProductToCheckout(product)
                })
            }
        }

        Button(
            onClick = { navController.navigate("checkout") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Proceed to Checkout")
        }
    }
}

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    Text(text = "${product.name} - $${product.price}")
    Button(onClick = onClick) {
        Text(text = "Add to Cart")
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_products),
                    contentDescription = "Products"
                )
            },
            label = { Text("Products") },
            selected = false,
            onClick = { navController.navigate("products") }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_cart),
                    contentDescription = "Checkout"
                )
            },
            label = { Text("Checkout") },
            selected = false,
            onClick = { navController.navigate("checkout") }
        )
    }
}