package com.mertozan.membox.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.mertozan.membox.login.databinding.LoginScreenBinding

@Composable
fun LoginScreen() {
    AndroidViewBinding(factory = LoginScreenBinding::inflate){

    }
}

@Preview
@Composable
fun PreviewLogin() {
    AndroidViewBinding(factory = LoginScreenBinding::inflate, modifier = Modifier.fillMaxSize()){

    }
}