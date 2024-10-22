package com.example.decinevictor000.presentation.features.lists.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cinevictor.R


@Composable
fun TestScreen () {
    Column(
        Modifier.fillMaxWidth()
            .padding(16.dp)) {

        Text ("Nuevas de los amiguitos",
            modifier = Modifier.padding (bottom = 8.dp) )

        Row (horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

        }

        Row{
            Text ("Y si hubiera mujeres directoras")

            Text ("¡Ay Vanessa!")

            Image (painterResource(R.drawable.vanesamartin),
                contentDescription = null


            )
    }

    }
}

