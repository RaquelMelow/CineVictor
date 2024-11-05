package com.example.cinevictor.presentation.features.popular.journal.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinevictor.presentation.features.popular.journal.viewmodel.JournalViewModel


// Construccion de la columna
@Composable
fun JournalScreen() {

    val viewModel = JournalViewModel()

    val items by viewModel.sceneListsItems.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            //horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(10.dp)

        ) {
            items(items) { item ->

                Image(
                    painter = painterResource(item.scene),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .size(200.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,

                )

                item.title?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Left,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Normal
                    )
                }

                item.abstract?.let {
                    Text(
                        text = it,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Normal
                    )
                }

                Spacer(Modifier.height(15.dp))

            }
        }
    }
}

@Preview(
    name = "Pixel 5",
    device = "spec:shape=Normal,width=1080,height=2400,unit=px,dpi=480"
)
@Composable
fun PreviewJournalScreen() {
    Surface {
        JournalScreen()
    }
}