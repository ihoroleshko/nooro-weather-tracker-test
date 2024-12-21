package com.test.nooro.weathertracker.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.nooro.weathertracker.R
import com.test.nooro.weathertracker.ui.theme.Black
import com.test.nooro.weathertracker.ui.theme.Gray
import com.test.nooro.weathertracker.ui.theme.Teal

@Composable
fun Home() {
    var city by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
            singleLine = true,
            textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(400)),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Teal,
                focusedContainerColor = Teal,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = city,
            onValueChange = {
                city = it
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_location),
                    fontSize = 15.sp,
                    color = Gray,
                    fontWeight = FontWeight(400)
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "search",
                    tint = Gray
                )
            })
        NoCitySelected()
    }
}

@Composable
fun NoCitySelected() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_city_selected),
            fontSize = 30.sp,
            color = Black,
            lineHeight = 45.sp,
            fontWeight = FontWeight(600)
        )
        Text(
            text = stringResource(R.string.please_search_for_a_city),
            fontSize = 15.sp,
            color = Black,
            lineHeight = 22.sp,
            fontWeight = FontWeight(400)
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    Home()
}