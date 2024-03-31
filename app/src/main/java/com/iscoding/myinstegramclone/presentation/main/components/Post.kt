package com.iscoding.myinstegramclone.presentation.main.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.common.io.Files.append
import com.iscoding.myinstegramclone.R

@Composable
fun PostItem() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Profile Pic",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "User Name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text ="User Location",
                        fontSize = 14.sp
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.three_dots_options),
                    contentDescription = "More Options",
                    modifier =  Modifier.size(20.dp)
                )
            }
        }
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Post Picture",
            contentScale = ContentScale.FillWidth
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(R.drawable.love_icon),
                        contentDescription = "Like Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(25.dp)
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(R.drawable.comment_ic),
                        contentDescription = "Like Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(R.drawable.dm_icon),
                        contentDescription = "Like Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.save_post),
                    contentDescription = "Like Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = "200 likes"
            )
            Text(
                text = buildAnnotatedString {
                    append(
                        AnnotatedString(
                            text = "User Name  ",
                            spanStyle = SpanStyle(fontWeight = FontWeight.Bold)
                        )
                    )
                    append("User Caption")
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "View all 20 comments",
                color = Color.DarkGray,
                fontSize = 13.sp
            )
        }
    }
}

