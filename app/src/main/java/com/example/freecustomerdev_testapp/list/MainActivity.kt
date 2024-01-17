package com.example.freecustomerdev_testapp.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.freecustomerdev_testapp.AnnounceState
import com.example.freecustomerdev_testapp.R
import com.example.freecustomerdev_testapp.network.Announce
import com.example.freecustomerdev_testapp.ui.theme.BackgroundColor
import com.example.freecustomerdev_testapp.ui.theme.BlockColor
import com.example.freecustomerdev_testapp.ui.theme.FreeCustomerDev_testAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FreeCustomerDev_testAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(Modifier.background(BackgroundColor)) {
                        UpperBlock()
                        LastAnnounce(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun AnnouncementsList(viewModel: MainViewModel) {
    val announceState by viewModel.announceState.observeAsState(initial = AnnounceState.Loading)
    when (announceState) {
        is AnnounceState.Loading -> {}
        is AnnounceState.Success -> {
            BuildList(announceState)
        }

        is AnnounceState.Error -> {
            val errorMessage = (announceState as AnnounceState.Error).errorMessage
            ShowErrorMessage(errorMessage)
        }
    }
}

@Composable
fun UpperBlock() {
    Row(
        modifier = Modifier
            .background(BlockColor)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Image(
            painterResource(id = R.drawable.news_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = "Bybit list",
            color = Color.White,
            modifier = Modifier
                .padding(top = 20.dp, end = 16.dp)
        )
    }
}

@Composable
fun LastAnnounce(viewModel: MainViewModel) {
    Column(
        modifier = Modifier.background(BackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { viewModel.loadData() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 16.dp
            )
        )
        {
            Text(text = "Load Announces")
        }
        AnnouncementsList(viewModel = viewModel)
    }
}

@Composable
fun BuildList(announceState: AnnounceState) {
    val announce = (announceState as AnnounceState.Success).announce
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(announce.result.list.size) { announcement ->
            BuildCard(announce, announcement)
        }
    }
}

@Composable
fun BuildCard(announce: Announce, id: Int) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text(
            text = announce.result.list[id].title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp),
        )
        Text(
            text = announce.result.list[id].description,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ShowErrorMessage(errorMessage: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorMessage,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}