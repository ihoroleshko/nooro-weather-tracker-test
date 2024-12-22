package com.test.nooro.weathertracker.ui.screens.home

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import coil.compose.rememberAsyncImagePainter
import com.test.nooro.domain.model.DataState
import com.test.nooro.domain.model.Weather
import com.test.nooro.weathertracker.R
import com.test.nooro.weathertracker.ui.theme.Black
import com.test.nooro.weathertracker.ui.theme.Gray
import com.test.nooro.weathertracker.ui.theme.Teal
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@Composable
fun Home(
    viewModel: HomeViewModel = koinViewModel()
) {
    var city by rememberSaveable { mutableStateOf("") }
    val weather by viewModel.weather.collectAsState()

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
                viewModel.getWeather(it)
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
        when (weather) {
            is DataState.Idle -> {
                NoCitySelected()
            }

            is DataState.Loading -> {
                // handle loading here, no design provided
            }

            is DataState.Success -> {
                val weather = weather as DataState.Success
                SearchWeatherCard(weather.data)
            }

            is DataState.Error -> {
                // handle error here, no design provided
            }
        }
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

@Composable
fun SearchWeatherCard(weather: Weather) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 30.dp)
            .background(color = Teal, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 30.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = weather.city,
                fontSize = 20.sp,
                color = Black,
                lineHeight = 30.sp,
                fontWeight = FontWeight(600)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = weather.temperature.roundToInt().toString(),
                fontSize = 50.sp,
                color = Black,
                lineHeight = 50.sp,
                fontWeight = FontWeight(600)
            )
        }
        Image(
            painter = rememberAsyncImagePainter("https://" + weather.weatherIconUrl),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    Home()
}