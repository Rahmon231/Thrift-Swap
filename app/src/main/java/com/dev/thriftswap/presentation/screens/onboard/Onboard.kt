package com.dev.thriftswap.presentation.screens.onboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.thriftswap.R
import com.dev.thriftswap.presentation.components.AppLogo
import com.dev.thriftswap.presentation.navigation.ThriftScreens

@Composable
fun OnboardingScreen(navController: NavController) {
    OnboardingContent(
        onGoogleClick = { /* Handle Google button click */ },
        onAppleClick = { /* Handle Apple button click */ },
        onEmailClick = { navController.navigate(ThriftScreens.RegisterScreen.name) },
        onSkip = { navController.navigate(ThriftScreens.HomeScreen.name) }
    )
}

@Preview
@Composable
fun OnboardingContent(
    onGoogleClick: () -> Unit = {},
    onAppleClick: () -> Unit = {},
    onEmailClick: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFDFCFB), // off-white top
                        Color(0xFFF5F3F0), // cream middle
                        Color(0xFFECE7E1)  // soft beige bottom
                    )
                )
            )
            .padding(24.dp)
    ) {
        // Skip button
        Text(
            text = "Skip >>",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { onSkip() },
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.roboto)),

            )
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
               AppLogo()
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Thrift Swap",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.beau_rivage_regular))
                    )
                )
            }

            Spacer(modifier = Modifier.height(102.dp))

            // Google button
            Button(
                onClick = onGoogleClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Continue with Google", 
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    ))
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Apple button
            OutlinedButton(
                onClick = onAppleClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_apple),
                    contentDescription = "Apple",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Continue with Apple", color = Color.Black,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    ))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "or",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.roboto))
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Email option
            Text(
                text = "Continue with Email",
                modifier = Modifier.clickable { onEmailClick() },
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = colorResource(R.color.green),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.roboto))
                )
            )
        }
    }
}
