package com.example.schoolapp.studybase.onBoardingIntro

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import com.example.schoolapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        // HorizontalPager for onboarding screens
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> OnboardingPage(
                    imageRes = R.drawable.onboard_1,
                    title = "Learn Anything Outside the Class",
                    description = "Let the learning process flow to develop their cognitive development skills",
                    onNextClicked = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(page + 1)
                        }
                    }
                )
                1 -> OnboardingPage(
                    imageRes = R.drawable.onboard_2,
                    title = "Thousands of Course Activities",
                    description = "High-quality learning materials for every subject that will help do self-learning",
                    onNextClicked = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(page + 1)
                        }
                    }
                )
                2 -> OnboardingPage(
                    imageRes = R.drawable.onboard_3,
                    title = "Online Session with Teacher",
                    description = "Join live sessions with a teacher or mentor and get notified for their upcoming sessions",
                    showStartButton = true,
                    onNextClicked = { /* Navigate to the main screen */ }
                )
            }
        }

        // Skip button at the top right corner
        TextButton(
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(2)
                }
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text(text = "Skip", color = Color.White)
        }
    }
}

@Composable
fun OnboardingPage(
    imageRes: Int,
    title: String,
    description: String,
    showStartButton: Boolean = false,
    onNextClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A73E8))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)  // Reduced size for better performance
                .padding(bottom = 16.dp)
        )

        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = description,
            fontSize = 16.sp,
            color = Color.White.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 32.dp)
        )

        Button(
            onClick = onNextClicked,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(0.8f)
                .height(50.dp)
        ) {
            Text(text = if (showStartButton) "LET'S BEGIN" else "NEXT")
        }
    }
}
