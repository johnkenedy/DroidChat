package br.com.ada.droidchat.ui.feature.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import br.com.ada.droidchat.R
import br.com.ada.droidchat.ui.components.PrimaryButton
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

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacing_extra_large)))

        var email by remember { mutableStateOf("") }

        PrimaryTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.spacing_medium)),
            placeholder = stringResource(R.string.feature_login_email),
            leadingIcon = R.drawable.ic_envelope,
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.spacing_small)))

        var password by remember { mutableStateOf("") }

        PrimaryTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.spacing_medium)),
            placeholder = stringResource(R.string.feature_login_password),
            leadingIcon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_extra_large)))

        var isLoading by remember { mutableStateOf(false) }

        PrimaryButton(
            text = stringResource(R.string.feature_login_button),
            onClick = {
                isLoading = !isLoading
            },
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.spacing_medium)),
            isLoading = isLoading
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