package com.iscoding.myinstegramclone.presentation.profile.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.iscoding.myinstegramclone.domain.model.TabModel

@Composable
fun TabView(
    modifier: Modifier = Modifier,
    tabModels: List<TabModel>,
    onTabSelected: (index: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = Color.Black,

    ) {

        tabModels.forEachIndexed{index , item ->
        Tab(
            selected = selectedTabIndex==index,
            onClick = {
                selectedTabIndex=index
                onTabSelected(index)

            },
            selectedContentColor = Color.Black,
            unselectedContentColor = Color(0xFF777777),
            text = { Text(text = item.text) }
        )

        }

    }

}