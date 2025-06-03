package br.com.ada.droidchat.ui.feature.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.ada.droidchat.R
import br.com.ada.droidchat.ui.components.PrimaryTextField
import br.com.ada.droidchat.ui.theme.BackgroundGradient
import br.com.ada.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignInScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradient),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo"
        )

        Spacer(modifier = Modifier.padding(34.dp))

        var email by remember { mutableStateOf("") }

        PrimaryTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(horizontal = 16.dp),
            placeholder = stringResource(R.string.feature_login_email),
            leadingIcon = R.drawable.ic_envelope,
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.padding(8.dp))

        var password by remember { mutableStateOf("") }

        PrimaryTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(horizontal = 16.dp),
            placeholder = stringResource(R.string.feature_login_password),
            leadingIcon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
    }

}

@Preview
@Composable
private fun SignInScreenPreview() {
    DroidChatTheme {
        SignInScreen()
    }
}