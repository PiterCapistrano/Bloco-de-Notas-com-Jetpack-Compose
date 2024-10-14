package com.pitercapistrano.blocodenotascomjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pitercapistrano.blocodenotascomjetpackcompose.ui.theme.Black
import com.pitercapistrano.blocodenotascomjetpackcompose.ui.theme.BlocoDeNotasComJetpackComposeTheme
import com.pitercapistrano.blocodenotascomjetpackcompose.ui.theme.Gold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlocoDeNotasComJetpackComposeTheme {
                BlocoDeNotasComponentes()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlocoDeNotasComponentes(){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Bloco de Notas", color = Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Gold)
                )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BlocoDeNotasComJetpackComposeTheme {
        BlocoDeNotasComponentes()
    }
}