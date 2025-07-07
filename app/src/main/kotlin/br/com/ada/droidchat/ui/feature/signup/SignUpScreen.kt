import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.ada.droidchat.R
import br.com.ada.droidchat.ui.components.PrimaryButton
import br.com.ada.droidchat.ui.components.ProfilePictureOptionsModalBottomSheet
import br.com.ada.droidchat.ui.components.ProfilePictureSelector
import br.com.ada.droidchat.ui.components.SecondaryTextField
import br.com.ada.droidchat.ui.theme.BackgroundGradient
import br.com.ada.droidchat.ui.theme.DroidChatTheme
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute() {
    SignUpUpScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpUpScreen() {
    Box(
        modifier = Modifier
            .background(BackgroundGradient)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_x_extra_large)))

            var profilePictureSelectedUri by remember {
                mutableStateOf<Uri?>(null)
            }

            var openProfilePictureOptionsModalBottomSheet by remember {
                mutableStateOf(false)
            }

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))

            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp)
                ),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    ProfilePictureSelector(
                        imageUri = profilePictureSelectedUri,
                        modifier = Modifier
                            .clickable {
                                openProfilePictureOptionsModalBottomSheet = true
                            }
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_first_name),
                        value = "",
                        onValueChange = {}
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_last_name),
                        value = "",
                        onValueChange = {}
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_email),
                        value = "",
                        onValueChange = {},
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_password),
                        value = "",
                        onValueChange = {},
                        keyboardType = KeyboardType.Password
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_password_confirmation),
                        value = "",
                        onValueChange = {},
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_extra_large)))

                    PrimaryButton(
                        text = stringResource(id = R.string.feature_sign_up_button),
                        onClick = {}
                    )
                }
            }

            val sheetState = rememberModalBottomSheetState()
            val scope = rememberCoroutineScope()

            if (openProfilePictureOptionsModalBottomSheet) {
                ProfilePictureOptionsModalBottomSheet(
                    onPictureSelected = { uri ->
                        profilePictureSelectedUri = uri
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                openProfilePictureOptionsModalBottomSheet = false
                            }
                        }
                    },
                    onDismissRequest = { openProfilePictureOptionsModalBottomSheet = false },
                    sheetState = sheetState
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignUpUpPreview() {
    DroidChatTheme {
        SignUpUpScreen()
    }
}