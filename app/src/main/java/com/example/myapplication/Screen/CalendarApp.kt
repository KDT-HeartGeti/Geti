package com.example.myapplication.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.CalendarDataSource
import com.example.myapplication.data.CalendarUiModel
import com.example.myapplication.ui.theme.Gray200
import com.example.myapplication.ui.theme.Gray400
import com.example.myapplication.ui.theme.Gray50
import com.example.myapplication.ui.theme.Gray500
import com.example.myapplication.ui.theme.NeonBlue
import com.example.myapplication.ui.theme.NeonRed
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navController: NavController) {
    val dataSource = CalendarDataSource()
    // get CalendarUiModel from CalendarDataSource, and the lastSelectedDate is Today.
    var calendarUiModel by remember {
        mutableStateOf(
            dataSource.getData(lastSelectedDate = dataSource.today)
        )
    }

    var isToggled by remember { mutableStateOf(false) }

    val toggleImage by remember(isToggled) {
        mutableStateOf(if (isToggled) R.drawable.toggle_on_svgrepo_com else R.drawable.toggle_off_svgrepo_com)
    }

    Scaffold(
        Modifier.background(Gray50),
        topBar = {
            Box(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .height(64.dp)
//                    .clip(
//                        RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
//                    )
            ) {
                TopBar(
                    title = "김상은",
                    navigationIcon = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.heartbeat_logo),
                                contentDescription = "프로필 사진"
                            )
                        }
                    },
                    actionIcon = {
                        IconButton(
                            onClick = { isToggled = !isToggled }
                        ) {
                            Icon(
                                ImageVector.vectorResource(id = toggleImage),
                                contentDescription = "토글 아이콘",
                                tint = if (isToggled) Gray400 else NeonBlue
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .background(NeonRed)
                    .fillMaxWidth()
                    .height(70.dp)
//                    .padding(start = 20.dp, end = 20.dp)
            ) {
                BottomBar(
                    navigationIcon = {
                        val currentRoute = navController.currentDestination?.route

                        IconButton(
                            onClick = { navController.navigate("calender") },
                            modifier = Modifier
                                .size(70.dp)
//                                .padding(start = 14.dp, top = 8.dp, end = 14.dp, bottom = 8.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.calender),
                                    contentDescription = "내 기록 아이콘 (캘린더)",
                                    modifier = Modifier
                                        .size(30.dp),
                                    tint = if (currentRoute == "calender") NeonBlue else Gray200
                                )
                                Text(
                                    text = "내기록",
                                    color = if (currentRoute == "calender") NeonBlue else Gray500,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    actionIcon1 = {
                        val currentRoute = navController.currentDestination?.route

                        IconButton(
                            onClick = { navController.navigate("input") },
                            modifier = Modifier
                                .size(70.dp)
//                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.info),
                                    contentDescription = "영양정보 아이콘",
                                    modifier = Modifier.size(30.dp),
                                    tint = if (currentRoute == "input") NeonBlue else Gray200
                                )
                                Text(
                                    text = "영양정보",
                                    color = if (currentRoute == "input") NeonBlue else Gray500,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    actionIcon2 = {
                        val currentRoute = navController.currentDestination?.route

                        IconButton(
                            onClick = { /* doSomething() */ },
                            modifier = Modifier
                                .size(70.dp)
//                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.consult),
                                    contentDescription = "상담 아이콘",
                                    modifier = Modifier.size(30.dp),
//                                    tint = if (currentRoute == "") NeonRed else Gray200
                                    tint = Gray200
                                )
                                Text(
                                    text = "상담",
//                                    color = if (currentRoute == "calender") NeonRed else Gray500,
                                    color = Gray500,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    actionIcon3 = {
                        val currentRoute = navController.currentDestination?.route

                        IconButton(
                            onClick = { navController.navigate("state") },
                            modifier = Modifier
                                .size(70.dp)
//                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.state),
                                    contentDescription = "내 상태 아이콘",
                                    modifier = Modifier.size(30.dp),
                                    tint = if (currentRoute == "state") NeonBlue else Gray200
                                )
                                Text(
                                    text = "내상태",
                                    color = if (currentRoute == "state") NeonBlue else Gray500,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Gray50)
            ) {
                Calender(calendarUiModel, dataSource)
            }
        }
    )
}

@Composable
private fun Calender(
    calendarUiModel: CalendarUiModel,
    dataSource: CalendarDataSource
) {
    var calendarUiModel1 = calendarUiModel
    Header(data = calendarUiModel1,
        onPrevClickListener = { startDate ->
            // refresh the CalendarUiModel with new data
            // by get data with new Start Date (which is the startDate-1 from the visibleDates)
            val finalStartDate = startDate.minusDays(1)
            calendarUiModel1 = dataSource.getData(
                startDate = finalStartDate,
                lastSelectedDate = calendarUiModel1.selectedDate.date
            )
        }, onNextClickListener = { endDate ->
            // refresh the CalendarUiModel with new data
            // by get data with new Start Date (which is the endDate+2 from the visibleDates)
            val finalStartDate = endDate.plusDays(2)
            calendarUiModel1 = dataSource.getData(
                startDate = finalStartDate,
                lastSelectedDate = calendarUiModel1.selectedDate.date
            )
        }
    )
    Content(
        data = calendarUiModel1,
        onDateClickListener = { date ->
            calendarUiModel1 = calendarUiModel1.copy(
                selectedDate = date,
                visibleDates = calendarUiModel1.visibleDates.map {
                    it.copy(
                        isSelected = it.date.isEqual(date.date)
                    )
                }
            )
        }
    )
}

@Composable
fun Header(
    data: CalendarUiModel,
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit,
) {
    Row {
        Text(
            // show "Today" if user selects today's date
            // else, show the full format of the date
            text =
            if (data.selectedDate.isToday) {
                "Today"
            } else {
                data.selectedDate.date.format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                )
            }, modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(
            onClick = { onPrevClickListener(data.startDate.date) }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        IconButton(
            onClick = { onNextClickListener(data.endDate.date) }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Next"
            )
        }
    }
}

@Composable
fun Content(
    data: CalendarUiModel,
    onDateClickListener: (CalendarUiModel.Date) -> Unit,
) {
    LazyRow {
        // pass the visibleDates to the UI
        items(
            items = data.visibleDates) { date ->
            ContentItem(
                date = date, onDateClickListener
            )
        }
    }
}

@Composable
fun ContentItem(
    date: CalendarUiModel.Date,
    onClickListener: (CalendarUiModel.Date) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(
                vertical = 4.dp,
                horizontal = 4.dp
            )
            .clickable { onClickListener(date) },
        colors = CardDefaults.cardColors(
            // background colors of the selected date
            // and the non-selected date are different
            containerColor =
            if (date.isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondary
            }
        ),
    ) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(48.dp)
                .padding(4.dp)
        ) {
            Text(
                text = date.day, // day "Mon", "Tue"
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = date.date.dayOfMonth.toString(), // date "15", "16"
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}