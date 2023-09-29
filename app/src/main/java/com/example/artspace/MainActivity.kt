package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme


val listResources: List<Triple<Int, Int, Int>> = listOf(
    Triple(R.drawable.drunkinthemorning, R.string.lukas_Graham_DITM, R.string.lukas_Graham_DITM_year),
    Triple(R.drawable.lake, R.string.lake, R.string.lake_year),
    Triple(R.drawable.lasagna, R.string.lasagna, R.string.lasagna_year),
    Triple(R.drawable.pizzapant, R.string.pizaa_pant, R.string.pizaa_pant_year),
    Triple(R.drawable.burguer, R.string.burguer, R.string.burguer_year),
    Triple(R.drawable.lukasgrahamblue, R.string.lukas_Graham_blue, R.string.lukas_Graham_blue_year),
    Triple(R.drawable.moonstar, R.string.moonstar, R.string.moonstar_year),
    Triple(R.drawable.lukasgrahampink, R.string.lukas_Graham_pink, R.string.lukas_Graham_pink_year),
    Triple(R.drawable.sky, R.string.sky, R.string.sky_year),
    Triple(R.drawable.sevenyears, R.string.lukas_Graham_seven, R.string.lukas_Graham_seven_year),
    )


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen(listResources = listResources)
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier, listResources: List<Triple<Int, Int, Int>>) {

    var counter by remember { mutableStateOf(0) }
    var currentArtwork by remember { mutableStateOf(listResources[counter].first) }
    var title by remember { mutableStateOf(listResources[counter].second) }
    var year by remember { mutableStateOf(listResources[counter].third) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Spacer(
            modifier = modifier.size(16.dp)
        )
        Text(
            text = "Hi, I'm Santiago Casanas (202025301), and this is my gallery:",
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = modifier.size(16.dp)
        )
        ArtworkImage(
            currentArtwork = currentArtwork
        )
        Spacer(
            modifier = modifier.size(16.dp)
        )
        ArtworkTitle(
            title = title,
            year = year,
        )
        Spacer(
            modifier = modifier.size(25.dp)
        )
        Row(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (counter==0){
                        counter=listResources.size-1
                    }else{
                        counter--
                    }
                    currentArtwork = listResources[counter].first
                    title = listResources[counter].second
                    year = listResources[counter].third
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.red)
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp,
                )
            ) {
                Text(
                    text = stringResource(R.string.previous_buttom),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }
            Button(
                onClick = {
                    counter = 0
                    currentArtwork = listResources[counter].first
                    title = listResources[counter].second
                    year = listResources[counter].third
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue_100)
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp,
                )
            ) {
                Text(
                    text = stringResource(R.string.restart_buttom),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }
            Button(
                onClick = {
                    counter = (counter + 1) % listResources.size
                    currentArtwork = listResources[counter].first
                    title = listResources[counter].second
                    year = listResources[counter].third
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green)
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                ),
            ) {
                Text(
                    text = stringResource(R.string.next_buttom),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}

@Composable
fun ArtworkImage(
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(currentArtwork),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(16.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        )
    }
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int,
    @StringRes year: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = year),
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray_300),
            fontSize = 20.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen(listResources = listResources)
    }
}

