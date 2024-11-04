package com.example.cinevictor.presentation.features.popular.journal

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinevictor.presentation.features.popular.lists.model.ListsItemData
import com.example.cinevictor.presentation.features.popular.lists.view.ListsScreen
import com.example.cinevictor.presentation.features.popular.lists.viewmodel.ListsViewModel

// Forma de jerarquización de la información que acompaña a cada
// carrusel de escenas
data class ListsJournalData(
    @DrawableRes val scene: Int,
    val title: String,
    val abstract: String

)

// Construccion de la columna
@Composable
fun ListsJournal(data: ListsJournalData) {
    Column(
        modifier = Modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            data.title.let {
                Text(
                    text = it,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Normal
                )
            }

            data.abstract.let {
                Text(
                    text = it,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Normal
                )
            }

            Spacer(Modifier.weight(1f))
        }
    }
}

















@Preview(
    name = "Pixel 5",
    device = "spec:shape=Normal,width=1080,height=2400,unit=px,dpi=480",
)
@Composable
fun PreviewJournalScreen() {
    Surface {
        ListsScreen()
    }
}