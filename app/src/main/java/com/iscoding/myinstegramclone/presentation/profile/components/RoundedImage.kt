package com.iscoding.myinstegramclone.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun RoundedImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    val borderColor = Color.Gray // Replace with your desired color
    val brush: Brush = SolidColor(borderColor)
    Image(painter = image, contentDescription = "",
        modifier= modifier .aspectRatio(1f,matchHeightConstraintsFirst = true)
            .clip(CircleShape).border(1.dp, brush, CircleShape)

        )

}