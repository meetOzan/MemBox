package com.mertozan.membox

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.theme.ui.ButtonBlue
import com.mertozan.membox.presentation.theme.ui.IndicatorGray
import com.mertozan.membox.presentation.theme.ui.OnboardingBlack
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import kotlinx.coroutines.launch

import com.mertozan.membox.presentation.R.drawable as presentationDraw
import com.mertozan.membox.localization.R.string as localizationR

@ExperimentalFoundationApi
@Composable
fun OnboardingScreen(
    onHomeScreenNavigate : () -> Unit
) {

    val pagerState = rememberPagerState(pageCount = {
        3
    })

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DotsIndicator(
            dotCount = 3,
            type = ShiftIndicatorType(dotsGraphic = DotGraphic(color = IndicatorGray)),
            pagerState = pagerState,
            modifier = Modifier.padding(16.dp).height(4.dp)
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            when (it) {
                0 -> {
                    OnboardingItem(
                        image = presentationDraw.onboarding_woman,
                        title = localizationR.onboarding_1_title,
                        description = localizationR.onboarding_1_description,
                        onNextClicked = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                    )
                }

                1 -> {
                    OnboardingItem(
                        image = presentationDraw.onboarding_star,
                        title = localizationR.onboarding_2_title,
                        description = localizationR.onboarding_2_description,
                        onNextClicked = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(2)
                            }
                        }
                    )
                }

                2 -> {
                    OnboardingItem(
                        image = presentationDraw.onboarding_family,
                        title = localizationR.onboarding_3_title,
                        description = localizationR.onboarding_3_description,
                        isLastScreen = true,
                        onContinueClicked = {
                            onHomeScreenNavigate.invoke()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingItem(
    image: Int,
    title: Int,
    description: Int,
    isLastScreen: Boolean = false,
    onNextClicked: () -> Unit = {},
    onContinueClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.48f)
                .weight(2.3f)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            CustomText(
                text = stringResource(id = title),
                fontWeight = FontWeight.Bold,
                fontSize = 28,
                color = OnboardingBlack
            )
            CustomText(
                text = stringResource(id = description),
                fontWeight = FontWeight.Normal,
                fontSize = 14,
                color = OnboardingBlack,
                modifier = Modifier.padding(top = 8.dp, bottom = 28.dp)
            )
            if (isLastScreen) {
                ElevatedButton(
                    onClick = {
                        onContinueClicked.invoke()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = ButtonBlue
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    CustomText(
                        text = stringResource(id = localizationR.start_to_save_memories),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16,
                        color = Color.White
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    CustomText(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable {
                                onNextClicked.invoke()
                            },
                        text = stringResource(id = localizationR.next),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14,
                        color = IndicatorGray
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewOfOnBoarding() {
    OnboardingScreen(
        onHomeScreenNavigate = {}
    )
}
