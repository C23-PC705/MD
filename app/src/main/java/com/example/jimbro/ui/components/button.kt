package com.example.jimbro.ui.components

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jimbro.ui.screens.Landing
import com.example.jimbro.ui.theme.Error
import com.example.jimbro.ui.theme.primary
import com.example.jimbro.ui.theme.secondary
import com.example.jimbro.ui.theme.white

@Composable
fun yellowbutton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .width(100.dp)
            ,
        colors = ButtonDefaults.buttonColors(backgroundColor = primary),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = text,
            modifier = Modifier.align(Alignment.CenterVertically), color = secondary)
    }


}
@Composable
fun redbutton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .width(300.dp).height(70.dp)
        ,
        colors = ButtonDefaults.buttonColors(backgroundColor = Error),
        shape = RoundedCornerShape(30.dp)
    ) {
        Text(text = text,
            modifier = Modifier.align(Alignment.CenterVertically), color = white)
    }


}
@Composable
fun bluebutton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .width(100.dp)
        ,
        colors = ButtonDefaults.buttonColors(backgroundColor = secondary),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = text,
            modifier = Modifier.align(Alignment.CenterVertically), color = primary)
    }


}

@Preview
@Composable
fun LoginScreenPreview() {
    redbutton(text = "Logout", onClick = {})
}