package com.dev.thriftswap.presentation.screens.register

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dev.thriftswap.R
import com.dev.thriftswap.presentation.components.EmailInput
import com.dev.thriftswap.presentation.components.PasswordInput
import com.dev.thriftswap.presentation.components.ThriftAppBar
import kotlinx.coroutines.launch



@Composable
fun RegisterScreen(navController: NavController) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { ThriftAppBar(
            title = "Register",
            navIcon = Icons.AutoMirrored.Filled.ArrowBack,
        ) { navController.popBackStack() } }
    ) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            UserForm(
                onCheckBoxState = {/*Handle checkbox state*/},
                onDone = { email, password ->
                    // Handle registration logic here
                    Log.d("Registering", "RegisterScreen: Registering $email with password $password")
                }
            )
        }
    }

}

@Composable
fun UserForm(
    onCheckBoxState: (Boolean) -> Unit = {},
    onDone: (String, String) -> Unit = { _, _ -> }
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val confirmPassword = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }

    val passwordFocusRequest = remember { FocusRequester() }
    val confirmFocusRequest = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    val receiveOffers = rememberSaveable { mutableStateOf(false) }

    val valid = remember(email.value, password.value, confirmPassword.value) {
        email.value.trim().isNotEmpty() &&
                password.value.trim().isNotEmpty() &&
                confirmPassword.value.trim().isNotEmpty() &&
                password.value == confirmPassword.value
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "Join Thrift Swap",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight.W500,
                    fontSize = 32.sp
                ))
            Spacer(modifier = Modifier.height(48.dp))

            EmailInput(
                emailState = email,
                enabled = true,
                onAction = KeyboardActions {
                    coroutineScope.launch {
                        passwordFocusRequest.requestFocus()
                    }
                }
            )

            PasswordInput(
                modifier = Modifier.focusRequester(passwordFocusRequest),
                passwordState = password,
                labelId = "Password",
                enabled = true,
                passwordVisibility = passwordVisibility,
                onAction = KeyboardActions {
                    coroutineScope.launch { confirmFocusRequest.requestFocus() }
                }
            )

            PasswordInput(
                modifier = Modifier.focusRequester(confirmFocusRequest),
                passwordState = confirmPassword,
                labelId = "Confirm Password",
                enabled = true,
                passwordVisibility = passwordVisibility,
                onAction = KeyboardActions {
                    if (!valid) return@KeyboardActions
                    onDone(email.value.trim(), password.value.trim())
                    keyboardController?.hide()
                }
            )

            if (confirmPassword.value.isNotEmpty() && password.value != confirmPassword.value) {
                Text(
                    text = "Passwords do not match",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = receiveOffers.value,
                    onCheckedChange = {
                        receiveOffers.value = it
                        onCheckBoxState(it)
                                      },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF5B8E7D),
                        uncheckedColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "I would like to receive personalized offers and be the first to know about offer",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp

                    )
                )
            }
        }

        // Submit button at bottom
        SubmitButton(
            textId = "Register",
            validInputs = valid
        ) {
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButton(
    textId: String,
    validInputs: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        enabled = validInputs,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF5B8E7D)
        )
    ) {
         Text(text = textId, modifier = Modifier.padding(5.dp))
    }
}
