package com.sigiey.simple_shopping_app.composables.checkout

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.sigiey.simple_shopping_app.composables.products.BottomNavigationBar
import com.sigiey.simple_shopping_app.data.Product

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CheckoutScreen(navController: NavController, viewModel: CheckoutViewModel) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        val checkoutItems by viewModel.checkoutItems.observeAsState(initial = emptyList())

        CheckoutList(checkoutItems = checkoutItems, onRemoveItem = { product ->
            viewModel.removeProductFromCheckout(product)
        })
        Button(onClick = { navController.navigate("order_successful") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 200.dp)
        )  {
            Text(text = "Proceed to Order")
        }
    }
}

@Composable
fun CheckoutList(checkoutItems: List<Product>, onRemoveItem: (Product) -> Unit) {
    LazyColumn {
        items(checkoutItems) { product ->
            CheckoutItem(product = product, onRemoveItem = onRemoveItem)
        }
    }
}

@Composable
fun CheckoutItem(product: Product, onRemoveItem: (Product) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = "${product.name} - $${product.price}")
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { onRemoveItem(product) }) {
            Text(text = "Remove")
        }
    }
}

class CheckoutViewModel : ViewModel() {
    private val _checkoutItems = MutableLiveData<List<Product>>(emptyList())
    val checkoutItems: LiveData<List<Product>> = _checkoutItems

    fun addProductToCheckout(product: Product) {
        _checkoutItems.value = _checkoutItems.value?.plus(product)
    }

    fun removeProductFromCheckout(product: Product) {
        _checkoutItems.value = _checkoutItems.value?.minus(product)
    }
}