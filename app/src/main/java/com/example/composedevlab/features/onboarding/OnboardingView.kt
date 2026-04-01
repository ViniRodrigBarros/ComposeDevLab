package com.example.composedevlab.features.onboarding

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.example.composedevlab.core.data.models.OnboardingPageModel
import com.example.composedevlab.features.NavigationEvent
import com.example.composedevlab.ui.theme.ComposeDevLabTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun OnboardingRoute(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onNavigate: (NavigationEvent) -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.navigationEvent.collectLatest { event ->
            onNavigate(event)
        }
    }

    OnboardingScreen(
        state = state,
        onFinish = { viewModel.onFinishOnboarding() }
    )
}

@Composable
fun OnboardingScreen(
    state: OnboardingState,
    onFinish: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { state.pages.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) { pageIndex ->
            OnboardingPageItem(page = state.pages[pageIndex])
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                repeat(state.pages.size) { index ->
                    PagerIndicator(isSelected = pagerState.currentPage == index)
                }
            }

            Button(
                onClick = {
                    if (pagerState.currentPage < state.pages.size - 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onFinish()
                    }
                },
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = if (pagerState.currentPage < state.pages.size - 1) "Próximo" else "Começar",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun OnboardingPageItem(page: OnboardingPageModel) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(page.lottieRes))
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .size(300.dp)
                .weight(1f)
        )
        
        Text(
            text = page.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = page.description,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun PagerIndicator(isSelected: Boolean) {
    val width by animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp)
    
    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(10.dp)
            .width(width)
            .clip(CircleShape)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary 
                else MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            )
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    ComposeDevLabTheme {
        OnboardingScreen(
            state = OnboardingState(),
            onFinish = {}
        )
    }
}
