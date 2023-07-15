/*
 * Compiled by Tamzi
 *
 * You may use this in you compose android projectsThey are arraged in sets with already defined color codes.
 * inspiored by from uiehues.com: https://www.instagram.com/uihues/
 */
package com.tamzi.dds.atoms.color

import androidx.compose.ui.graphics.Color
/**

Set transparency with hex value:
From below lookUp table, if you want to set % alpha/opacity to your colours you replace first
 FF values with the below look up.

the way colours work is:
val creamy = Color(0xFFF6EDE2)
is first two letters are:
 FF - opacity/Alpha value

 then:
 F6EDE2 -  HEX colour code

so in Android FF is set for a 100% opacity. to make it less opaque or more transparent we add a
different value other than FF.
To set our `indigo1` value to be less opaque to a value of say 65% opacity we will add A6 and
it will be: A6F6EDE2

val creamy = Color(0xA6F6EDE2)


this is especially of you are going for a different variant of the colours.

IMPORTANT, this is different for web colour where, web uses the transpoarency at the end instaead of the start.
i will not delve into that here. :)

- **100% — FF**
- 99% — FC
- 98% — FA
- 97% — F7
- 96% — F5
- 95% — F2
- 94% — F0
- 93% — ED
- 92% — EB
- 91% — E8
- **90% — E6**
- 89% — E3
- 88% — E0
- 87% — DE
- 86% — DB
- 85% — D9
- 84% — D6
- 83% — D4
- 82% — D1
- 81% — CF
- **80% — CC**
- 79% — C9
- 78% — C7
- 77% — C4
- 76% — C2
- **75% — BF**
- 74% — BD
- 73% — BA
- 72% — B8
- 71% — B5
- **70% — B3**
- 69% — B0
- 68% — AD
- 67% — AB
- 66% — A8
- 65% — A6
- 64% — A3
- 63% — A1
- 62% — 9E
- 61% — 9C
- **60% — 99**
- 59% — 96
- 58% — 94
- 57% — 91
- 56% — 8F
- 55% — 8C
- 54% — 8A
- 53% — 87
- 52% — 85
- 51% — 82
- **50% — 80**
- 49% — 7D
- 48% — 7A
- 47% — 78
- 46% — 75
- 45% — 73
- 44% — 70
- 43% — 6E
- 42% — 6B
- 41% — 69
- **40% — 66**
- 39% — 63
- 38% — 61
- 37% — 5E
- 36% — 5C
- 35% — 59
- 34% — 57
- 33% — 54
- 32% — 52
- 31% — 4F
- **30% — 4D**
- 29% — 4A
- 28% — 47
- 27% — 45
- 26% — 42
- **25% — 40**
- 24% — 3D
- 23% — 3B
- 22% — 38
- 21% — 36
- **20% — 33**
- 19% — 30
- 18% — 2E
- 17% — 2B
- 16% — 29
- 15% — 26
- 14% — 24
- 13% — 21
- 12% — 1F
- 11% — 1C
- **10% — 1A**
- 9% — 17
- 8% — 14
- 7% — 12
- 6% — 0F
- 5% — 0D
- 4% — 0A
- 3% — 08
- 2% — 05
- 1% — 03
- **0% — 00**
**/


/*Default from compose*/
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)


/*
 * Set one, from theme
 *
 */
val white = Color(0xFFFFFFFF)
val bluish = Color(0xFF5944BE)
val darkBluish = Color(0xFF312651)
val black = Color(0xFF131313)
val reddish = Color(0xFFFF4343)
val warmRed = Color(0xFFFF7754)
val limeGreenish = Color(0xFF4ABB00)
val gray = Color(0xFFEFEFEF)
val deepGray = Color(0xFFE6E4E6)
val darkGray = Color( 0xFF83829A)
val lightGray = Color(0xFFF6F6F6)
val buttonRed = Color(0xFF7777CC)


/*
 * uiHues
 *
 */
val amber = Color(0xFFFCC775)
val lighterBluish = Color(0xFF11D5E7)
val lightBluish = Color(0xFF1EAAE5)
val blue = Color(0xFF2A2F8F)


/*
 * #Hue000
 *
 */
val indigo = Color(0xFF452344)
val reddisher = Color(0xFFE96B54)
val amberish = Color(0xFFFEC253)
val warmishGreen = Color(0xFF47B39C)

/*
 * #Hue001
 */
val skybluishDark = Color(0xFF1661BF)
val purplish = Color(0xFF56158B)
val pinkish = Color(0xFFC7206E)
val amberish1 = Color(0xFFECA309)

/*
 * #Hue002
 */
val blacker = Color(0xFF151B20)
val raddish = Color(0xFFEE6B52)
val amberish2 = Color(0xFFFEA44A)
val yellow = Color(0xFFEFCE5B)
val yellowThin = Color(0xFFEFCE5B)

/*
 * #Hue003
 */
val indigo1 = Color(0xFF472C61)
val pinkysh = Color(0xFFA2334E)
val reddishDarker = Color(0xFFFF7F57)
val skyLightBlue = Color(0xFF5EDAF5)

/*
 * #Hue004
 */
val indigo2 = Color(0xFF654D67)
val darkPinkysh = Color(0xFF9D5875)
val lightReddish = Color(0xFFE5ABB2)
val cream = Color(0xFFF6EDE2)
val creamy = Color(0xC9F6EDE2)

/*

Set transparency with hex value:
From below lookUp table, if you want to set % alpha/opacity to your colours you replace first
 FF values with the below look up.

the way colours work is:
val creamy = Color(0xFFF6EDE2)
is first two letters are:
 FF - opacity/Alpha value

 then:
 F6EDE2 -  HEX colour code

so in Android FF is set for a 100% opacity. to make it less opaque or more transparent we add a
different value other than FF.
To set our `indigo1` value to be less opaque to a value of say 65% opacity we will add A6 and
it will be: A6F6EDE2

val creamy = Color(0xA6F6EDE2)


this is especially of you are going for a different variant of the colours.

IMPORTANT, this is different for web colour where, web uses the transpoarency at the end instaead of the start.
i will not delve into that here. :)

- **100% — FF**
- 99% — FC
- 98% — FA
- 97% — F7
- 96% — F5
- 95% — F2
- 94% — F0
- 93% — ED
- 92% — EB
- 91% — E8
- **90% — E6**
- 89% — E3
- 88% — E0
- 87% — DE
- 86% — DB
- 85% — D9
- 84% — D6
- 83% — D4
- 82% — D1
- 81% — CF
- **80% — CC**
- 79% — C9
- 78% — C7
- 77% — C4
- 76% — C2
- **75% — BF**
- 74% — BD
- 73% — BA
- 72% — B8
- 71% — B5
- **70% — B3**
- 69% — B0
- 68% — AD
- 67% — AB
- 66% — A8
- 65% — A6
- 64% — A3
- 63% — A1
- 62% — 9E
- 61% — 9C
- **60% — 99**
- 59% — 96
- 58% — 94
- 57% — 91
- 56% — 8F
- 55% — 8C
- 54% — 8A
- 53% — 87
- 52% — 85
- 51% — 82
- **50% — 80**
- 49% — 7D
- 48% — 7A
- 47% — 78
- 46% — 75
- 45% — 73
- 44% — 70
- 43% — 6E
- 42% — 6B
- 41% — 69
- **40% — 66**
- 39% — 63
- 38% — 61
- 37% — 5E
- 36% — 5C
- 35% — 59
- 34% — 57
- 33% — 54
- 32% — 52
- 31% — 4F
- **30% — 4D**
- 29% — 4A
- 28% — 47
- 27% — 45
- 26% — 42
- **25% — 40**
- 24% — 3D
- 23% — 3B
- 22% — 38
- 21% — 36
- **20% — 33**
- 19% — 30
- 18% — 2E
- 17% — 2B
- 16% — 29
- 15% — 26
- 14% — 24
- 13% — 21
- 12% — 1F
- 11% — 1C
- **10% — 1A**
- 9% — 17
- 8% — 14
- 7% — 12
- 6% — 0F
- 5% — 0D
- 4% — 0A
- 3% — 08
- 2% — 05
- 1% — 03
- **0% — 00**
*/

