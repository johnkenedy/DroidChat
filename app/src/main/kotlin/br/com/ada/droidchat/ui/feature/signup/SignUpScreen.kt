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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.ada.droidchat.R
import br.com.ada.droidchat.ui.components.PrimaryButton
import br.com.ada.droidchat.ui.components.ProfilePictureOptionsModalBottomSheet
import br.com.ada.droidchat.ui.components.ProfilePictureSelector
import br.com.ada.droidchat.ui.components.SecondaryTextField
import br.com.ada.droidchat.ui.feature.signup.SignUpFormEvent
import br.com.ada.droidchat.ui.feature.signup.SignUpFormState
import br.com.ada.droidchat.ui.feature.signup.SignUpFormValidator
import br.com.ada.droidchat.ui.feature.signup.SignUpViewModel
import br.com.ada.droidchat.ui.theme.BackgroundGradient
import br.com.ada.droidchat.ui.theme.DroidChatTheme
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel = viewModel {
        SignUpViewModel(SignUpFormValidator())
    }
) {
    val formState = viewModel.formState
    SignUpUpScreen(
        formState = formState,
        onFormEvent = viewModel::onFormEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpUpScreen(
    formState: SignUpFormState,
    onFormEvent: (SignUpFormEvent) -> Unit
) {
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
                        imageUri = formState.profilePictureUri,
                        modifier = Modifier
                            .clickable {
                                onFormEvent(SignUpFormEvent.OpenProfilePictureModal)
                            }
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_first_name),
                        value = formState.firstName,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.FirstNameChanged(it))
                        },
                        errorText = formState.firstNameError?.let {
                            stringResource(
                                id = it,
                                stringResource(R.string.feature_sign_up_first_name)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_last_name),
                        value = formState.lastName,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.LastNameChanged(it))
                        },
                        errorText = formState.lastNameError?.let {
                            stringResource(
                                id = it,
                                stringResource(R.string.feature_sign_up_last_name)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_email),
                        value = formState.email,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.EmailChanged(it))
                        },
                        keyboardType = KeyboardType.Email,
                        errorText = formState.emailError?.let { stringResource(id = it) }
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_password),
                        value = formState.password,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.PasswordChanged(it))
                        },
                        keyboardType = KeyboardType.Password,
                        extraText = formState.passwordExtraText?.let { stringResource(id = it) },
                        errorText = formState.passwordError?.let { stringResource(id = it) },
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_password_confirmation),
                        value = formState.confirmPassword,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.ConfirmPasswordChanged(it))
                        },
                        keyboardType = KeyboardType.Password,
                        extraText = formState.passwordExtraText?.let { stringResource(id = it) },
                        imeAction = ImeAction.Done,
                        errorText = formState.confirmPasswordError?.let { stringResource(id = it) }
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_extra_large)))

                    PrimaryButton(
                        text = stringResource(id = R.string.feature_sign_up_button),
                        onClick = {
                            onFormEvent(SignUpFormEvent.Submit)
                        }
                    )
                }
            }

            val sheetState = rememberModalBottomSheetState()
            val scope = rememberCoroutineScope()

            if (formState.isProfilePictureModalOpen) {
                ProfilePictureOptionsModalBottomSheet(
                    onPictureSelected = { uri ->
                        onFormEvent(SignUpFormEvent.ProfilePhotoUriChanged(uri))
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onFormEvent(SignUpFormEvent.CloseProfilePictureModal)
                            }
                        }
                    },
                    onDismissRequest = { onFormEvent(SignUpFormEvent.CloseProfilePictureModal) },
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
        SignUpUpScreen(
            formState = SignUpFormState(),
            onFormEvent = {}
        )
    }
}