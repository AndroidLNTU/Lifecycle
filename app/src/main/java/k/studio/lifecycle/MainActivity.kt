package k.studio.lifecycle

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import k.studio.lifecycle.ui.theme.LifecycleTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        "MainActivity onCreate".logD()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            LifecycleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = "Greeting",
                        Modifier.padding(innerPadding)
                    ) {
                        composable<Greeting> {
                            Greeting(
                                name = "Android",
                                navigateToHome = {
                                    navController.navigate(route = Home)
                                }
                            )
                        }
                        composable<Home> {
                            Home(back = {
                                navController.navigateUp()
                            })
                        }
                    }

                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        "MainActivity onStart".logD()
    }

    override fun onResume() {
        super.onResume()
        "MainActivity onResume".logD()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        "MainActivity onConfigurationChanged".logD()
    }

    override fun onPause() {
        super.onPause()
        "MainActivity onPause".logD()
    }

    override fun onStop() {
        super.onStop()
        "MainActivity onStop".logD()
    }

    override fun onDestroy() {
        super.onDestroy()
        "MainActivity onDestroy".logD()
    }
}

fun String.logD() {
    Log.d("Example", this)
}

@Serializable
object Greeting

@Serializable
object Home

@Composable
fun Greeting(
    name: String, modifier: Modifier = Modifier,
    viewModel: GreetingViewModel = viewModel(), navigateToHome: () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "Hello $name!"
        )
        Button(onClick = navigateToHome) {
            Text(text = "Home")
        }
    }
}

@Composable
fun Home(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    back: () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "Home Screen"
        )
        Button(onClick = back) {
            Text(text = "Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LifecycleTheme {
        Greeting("Android", navigateToHome = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    LifecycleTheme {
        Home(back = {})
    }
}

class GreetingViewModel : ViewModel() {
    init {
        "GreetingViewModel init".logD()
    }

    override fun onCleared() {
        super.onCleared()
        "GreetingViewModel onCleared".logD()
    }
}

class HomeViewModel : ViewModel() {
    init {
        "HomeViewModel init".logD()
    }

    override fun onCleared() {
        super.onCleared()
        "HomeViewModel onCleared".logD()
    }
}