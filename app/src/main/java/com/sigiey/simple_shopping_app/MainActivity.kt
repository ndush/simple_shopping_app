package com.sigiey.simple_shopping_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sigiey.simple_shopping_app.composables.checkout.CheckoutScreen
import com.sigiey.simple_shopping_app.composables.checkout.CheckoutViewModel
import com.sigiey.simple_shopping_app.composables.order.OrderSuccessfulScreen
import com.sigiey.simple_shopping_app.composables.products.ProductsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleShoppingApp()
        }
    }
}

@Composable
fun SimpleShoppingApp() {
    val navController = rememberNavController()
    val viewModel: CheckoutViewModel = viewModel()

    Surface(color = Color.White) {
        NavHost(navController, startDestination = "products") {
            composable("products") {
                ProductsScreen(navController = navController, viewModel = viewModel)
            }
            composable("checkout") {
                CheckoutScreen(navController = navController, viewModel = viewModel)
            }
            composable("order_successful") {
                OrderSuccessfulScreen(navController = navController)
            }
        }
    }
}