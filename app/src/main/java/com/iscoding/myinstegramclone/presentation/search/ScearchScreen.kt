package com.iscoding.myinstegramclone.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.iscoding.myinstegramclone.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var text by remember {
        mutableStateOf("")
    }
    var isActive by remember {
        mutableStateOf(false)
    }
    val constraint = ConstraintSet {
        val searchBar = createRefFor("search")
        val grid = createRefFor("grid")
        val leftLine = createGuidelineFromEnd(10.dp)
        val rightLine = createGuidelineFromStart(10.dp)
        val verticalLine = createGuidelineFromTop(0.3f)


        constrain(searchBar) {
            top.linkTo(parent.top,20.dp)
            start.linkTo(parent.start, margin = 20.dp)
            end.linkTo(parent.end, margin = 20.dp)
//            bottom.linkTo(verticalLine)
            width = Dimension.fillToConstraints


        }
        constrain(grid) {
            bottom.linkTo(parent.bottom)
            top.linkTo(verticalLine)
            start.linkTo(searchBar.start)
            end.linkTo(searchBar.end)
            height = Dimension.wrapContent

        }
    }

    ConstraintLayout(constraint, modifier = Modifier.fillMaxSize()) {

        SearchBar(
            query = text,
            onQueryChange = {},
            onSearch = {},
            active = isActive,
            onActiveChange = { isActive = !isActive },
            modifier = Modifier.layoutId("search"),
            placeholder = { Text(text = "Search") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            trailingIcon = {
                if (isActive) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            } else {
                                isActive = false
                            }
                        })
                }
            }) {
            Row(modifier = Modifier.padding(10.dp)) {
                Icon(imageVector = Icons.Default.History, contentDescription = "")
                Text(text = "Suiiii")
            }
            Row(modifier = Modifier.padding(10.dp)) {
                Icon(imageVector = Icons.Default.History, contentDescription = "")
                Text(text = "Islammm")
            }


        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("grid")
        ) {
            items(150) {
                Image(
                    painter = painterResource(id = R.drawable.mine),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .border(width = 1.dp, color = Color.White)
                )
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun ShowPreview() {
    SearchScreen()
}
