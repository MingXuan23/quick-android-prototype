package com.example.quick_android

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quick_android.Api.str
import com.example.quick_android.ui.theme.QuickandroidTheme
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var result by remember { mutableStateOf("No response yet") }
            val scope = rememberCoroutineScope()

            Column (modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(
                rememberScrollState()
            )) {
                Button( onClick = {
                    scope.launch {
                        try {
                            result = Api.get("http://10.0.2.2:5112/api/data").toString(2)
                        }catch (e: Exception){
                            result = "Error: ${e.message}"
                        }
                    }
                }, modifier = Modifier.fillMaxWidth()) {

                    Text("Get")
                }
                Spacer(modifier = Modifier.height((8.dp)))

                Button( onClick = {
                    scope.launch {
                        try {
                            val req = JSONObject().put("message","post message")
                            val data = Api.post("http://10.0.2.2:5112/api/data", req);
                            result = data.str("value","default value")
                        }catch (e: Exception){
                            result = "Error: ${e.message}"
                        }
                    }
                }, modifier = Modifier.fillMaxWidth()) {

                    Text("Post")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(result)
            }

        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    QuickandroidTheme {
//        Greeting("Android")
//    }
//}