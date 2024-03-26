package com.senac.appboaviagem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senac.appboaviagem.ui.theme.AppBoaViagemTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppBoaViagemTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {

    var usuario = remember {
        mutableStateOf("")
    }

    var senha = remember {
        mutableStateOf("")
    }

    var senhaVisivel = remember {
        mutableStateOf(false)
    }

    val snackbarHostState = remember { SnackbarHostState() }
    var coroutineScope = rememberCoroutineScope()
    val focus = LocalFocusManager.current

    Scaffold( snackbarHost = { SnackbarHost (snackbarHostState) } )  {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .padding(it)

        ) {
            Image(
                painter = painterResource(id = R.drawable.deserto),
                contentDescription = "Cabana",
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(350.dp)
                    .fillMaxWidth()
                    .padding(top = 0.dp)
            )

            Text(
                text = "Usuário",
                fontSize = 24.sp
            )

            OutlinedTextField(
                value = usuario.value,
                onValueChange = {usuario.value = it},
                label = {
                    Text(text = "Login")
                },
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Senha",
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(top = 15.dp)
            )

            OutlinedTextField(
                value = senha.value,
                onValueChange = {senha.value = it},
                label = {
                    Text(text = "Senha")
                },
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),

                visualTransformation =
                if (senhaVisivel.value)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),

                trailingIcon = {
                    IconButton(onClick = {
                        senhaVisivel.value = !senhaVisivel.value
                    }) {
                        if (senhaVisivel.value)
                            Icon(painterResource(id = R.drawable.eye), contentDescription = "")
                        else
                            Icon(painterResource(id = R.drawable.visible), contentDescription = "")
                    }
                }
            )

            Button(
                onClick = {
                    if (senha.value == "12345" && usuario.value == "Ana")
                        coroutineScope.launch {
                            focus.clearFocus()
                            snackbarHostState.showSnackbar(
                                message = "login Realizado com Sucesso!",
                                withDismissAction = true
                            )
                        } else {
                            coroutineScope.launch {
                                focus.clearFocus()
                                snackbarHostState.showSnackbar(
                                    message = "login não realizado!",
                                    withDismissAction = true
                                )
                            }
                    }
                    },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            ) {
                Text(
                    text = "Login",
                    fontSize = 24.sp
                )
            }
        }
    }
    }

