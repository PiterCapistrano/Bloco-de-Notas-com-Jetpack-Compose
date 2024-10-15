package com.pitercapistrano.blocodenotascomjetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pitercapistrano.blocodenotascomjetpackcompose.datastore.StoreAnotacao
import com.pitercapistrano.blocodenotascomjetpackcompose.ui.theme.Black
import com.pitercapistrano.blocodenotascomjetpackcompose.ui.theme.BlocoDeNotasComJetpackComposeTheme
import com.pitercapistrano.blocodenotascomjetpackcompose.ui.theme.Gold
import com.pitercapistrano.blocodenotascomjetpackcompose.ui.theme.White
import kotlinx.coroutines.launch

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

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val storeAnotacao = StoreAnotacao(context)

    val anotacaoSalva = storeAnotacao.getAnotacao.collectAsState(initial = "")

    var anotacao by remember {
        mutableStateOf("")
    }

    anotacao = anotacaoSalva.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Bloco de Notas", color = Black, fontSize = 22.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Gold)
                )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        storeAnotacao.salvarAnotacao(anotacao)
                        Toast.makeText(context, "Anotação salva com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                },
                containerColor = Gold,
                elevation = FloatingActionButtonDefaults.elevation(
                    8.dp,
                ),
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_save),
                    contentDescription = "Icone de salvar anotação"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            TextField(value = anotacao, onValueChange = {
            anotacao = it
            },
                label = {
                    Text(text = "Digite a sua anotação...")
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedContainerColor = White,
                    cursorColor = Gold,
                    focusedLabelColor = White
                )
            )
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