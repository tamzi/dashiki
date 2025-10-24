
package com.tamzi.dds.atoms.type

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.tamzi.dds.R


/**
 * Dashiki typography:
 * Consists of 2 files that work together:
 *  - DType(located ion the components package) and
 *  - DFont located in the atoms directory
 * Font:
 * Defines the font family types only here
 * We use val for the purpose of making it available in the entire application
 * You first add the fonts to the res folder under fonts
 * Then just reference them here.
 * Font-  List all fonts that will be used in the application
 * Defines the font family types
 */

public val MontserratRegular = FontFamily(Font(R.font.montserrat_regular))
public val MontserratBold = FontFamily(Font(R.font.montserrat_bold))
public val MontserratSemiBold = FontFamily(Font(R.font.montserrat_semi_bold))
public val MontserratLight = FontFamily(Font(R.font.montserrat_light))
public val MontserratMedium = FontFamily(Font(R.font.montserrat_medium))

public val MontserratExtraLight = FontFamily(Font(R.font.montserrat_extra_light))

public val MontserratThin = FontFamily(Font(R.font.montserrat_thin))


public val GraphikFont = FontFamily(Font(R.font.graphik_light, FontWeight.Light))
public val SantanaFont = FontFamily(Font(R.font.santana_black, FontWeight.Normal))
