package com.example.cinevictor.presentation.features.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinevictor.R
import com.example.cinevictor.domain.model.User

@Composable
fun UserItem(user: User) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = user.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(80.dp)
                .border(1.dp, Color(0xFFEAEBF1), CircleShape)
                .clip(CircleShape)
        )

        Text(
            text = user.name,
            modifier = Modifier.padding(top = 2.dp),
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}

@Preview
@Composable
fun PreviewUserItem() {
    UserItem(user = User(id = "u0", name = "Amanda", image = R.drawable.perfilamanda))
}