package com.vishal2376.scrollblock.presentation.common

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.vishal2376.scrollblock.R
import com.vishal2376.scrollblock.ui.theme.black500
import com.vishal2376.scrollblock.ui.theme.gray


val fontMontserrat = FontFamily(
	Font(R.font.montserrat_bold, FontWeight.Bold),
	Font(R.font.montserrat_regular, FontWeight.Normal)
)
val fontRoboto = FontFamily(Font(R.font.roboto))
val fontPoppins = FontFamily(Font(R.font.poppins))

val titleStyle = TextStyle(
	fontSize = 30.sp, fontFamily = fontMontserrat, fontWeight = FontWeight.Bold
)

val h1style = TextStyle(
	fontSize = 24.sp, fontFamily = fontMontserrat, fontWeight = FontWeight.Bold
)

val headlineStyle = TextStyle(
	fontSize = 22.sp, fontFamily = fontMontserrat, fontWeight = FontWeight.Bold
)

val h2style = TextStyle(
	fontSize = 20.sp, fontFamily = fontMontserrat
)

val h3style = TextStyle(
	fontSize = 18.sp, fontFamily = fontMontserrat
)

val h4style = TextStyle(
	fontSize = 16.sp, fontFamily = fontPoppins
)

val descriptionStyle = TextStyle(
	fontSize = 16.sp, fontFamily = fontRoboto, color = gray
)

val smallDescriptionStyle = TextStyle(
	fontSize = 14.sp, fontFamily = fontRoboto, color = gray
)

val summaryTitleStyle = TextStyle(
	fontSize = 18.sp,
	fontFamily = fontMontserrat,
	fontWeight = FontWeight.Normal,
	color = black500,
)

val summaryInfoStyle = TextStyle(
	fontSize = 30.sp,
	fontFamily = fontMontserrat,
	fontWeight = FontWeight.Bold,
	color = black500,
)