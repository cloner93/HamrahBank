package com.pmb.ballon.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class CustomColors(
    onForegroundNeutralDefault: Color,
    onForegroundNeutralDisabled: Color,
    onForegroundNeutralCTA: Color,
    strokeInverted1: Color,
    strokeInverted2: Color,
    foregroundNeutralDefault: Color,
    foregroundNeutralRest: Color,
    foregroundNeutralDisabled: Color,
    foregroundPrimaryDefault: Color,
    foregroundSuccessDefault: Color,
    strokeNeutral2Default: Color,
    strokeNeutral2Active: Color,
    strokeNeutral2Error: Color,
    strokeNeutral1Default: Color,
    strokeNeutral3Rest: Color,
    onBackgroundTintPrimaryDefault: Color,
    onBackgroundTintPrimaryDisabled: Color,
    onBackgroundPrimaryActive: Color,
    onBackgroundPrimaryDisabled: Color,
    onBackgroundPrimaryCTA: Color,
    onBackgroundPrimaryDefault: Color,
    onBackgroundPrimarySubdued: Color,
    onBackgroundErrorDefault: Color,
    onBackgroundErrorDisabled: Color,
    onBackgroundErrorSubdued: Color,
    onBackgroundWarningDefault: Color,
    onBackgroundWarningDisabled: Color,
    onBackgroundWarningSubdued: Color,
    backgroundTintNeutralDefault: Color,
    backgroundTintSuccessDefault: Color,
    backgroundTintWarningDefault: Color,
    backgroundTintErrorDefault: Color,
    onBackgroundTintWarningDefault: Color,
    onBackgroundTintErrorRest: Color,
    onBackgroundTintNeutralDefault: Color,
    onBackgroundTintSuccessDefault: Color,
    backgroundTintPrimaryDefault: Color,
    stateLayerPrimaryHovered: Color,
    stateLayerPrimaryPressed: Color,
    stateLayerNeutralHovered: Color,
    stateLayerNeutralPressed: Color,
    stateLayerNeutralModalBackground: Color,
    stateLayerInvertedHovered: Color,
    stateLayerInvertedPressed: Color,
    background1Neutral: Color,
    background2Neutral: Color,
    background2Primary: Color,
    background3Neutral: Color,
    onBackgroundSuccessSubdued: Color,
    onBackgroundSuccessDisabled: Color,
    onBackgroundSuccessDefault: Color,
    onBackgroundNeutralDefault: Color,
    onBackgroundNeutralSubdued: Color,
    onBackgroundNeutralActive: Color,
    onBackgroundNeutralCTA: Color,
    onBackgroundNeutralDisabled: Color,
    foregroundErrorDefault: Color,
    foregroundWarningDefault: Color,
    iconColor: Color,
    iconBackgroundColor: Color,
    isLight: Boolean,
) {

    var onForegroundNeutralDefault by mutableStateOf(onForegroundNeutralDefault)
        private set
    var onForegroundNeutralDisabled by mutableStateOf(onForegroundNeutralDisabled)
        private set
    var onForegroundNeutralCTA by mutableStateOf(onForegroundNeutralCTA)
        private set
    var strokeInverted1 by mutableStateOf(strokeInverted1)
        private set
    var strokeInverted2 by mutableStateOf(strokeInverted2)
        private set
    var foregroundNeutralDefault by mutableStateOf(foregroundNeutralDefault)
        private set
    var foregroundNeutralDisabled by mutableStateOf(foregroundNeutralDisabled)
        private set
    var foregroundPrimaryDefault by mutableStateOf(foregroundPrimaryDefault)
        private set
    var foregroundNeutralRest by mutableStateOf(foregroundNeutralRest)
        private set
    var foregroundSuccessDefault by mutableStateOf(foregroundSuccessDefault)
        private set
    var strokeNeutral2Default by mutableStateOf(strokeNeutral2Default)
        private set
    var strokeNeutral2Active by mutableStateOf(strokeNeutral2Active)
        private set
    var strokeNeutral2Error by mutableStateOf(strokeNeutral2Error)
        private set
    var strokeNeutral1Default by mutableStateOf(strokeNeutral1Default)
        private set
    var strokeNeutral3Rest by mutableStateOf(strokeNeutral3Rest)
        private set
    var onBackgroundTintPrimaryDefault by mutableStateOf(onBackgroundTintPrimaryDefault)
        private set
    var onBackgroundTintPrimaryDisabled by mutableStateOf(onBackgroundTintPrimaryDisabled)
        private set
    var onBackgroundPrimaryActive by mutableStateOf(onBackgroundPrimaryActive)
        private set
    var onBackgroundPrimaryDisabled by mutableStateOf(onBackgroundPrimaryDisabled)
        private set
    var onBackgroundPrimaryCTA by mutableStateOf(onBackgroundPrimaryCTA)
        private set
    var onBackgroundPrimaryDefault by mutableStateOf(onBackgroundPrimaryDefault)
        private set
    var onBackgroundPrimarySubdued by mutableStateOf(onBackgroundPrimarySubdued)
        private set
    var onBackgroundErrorDefault by mutableStateOf(onBackgroundErrorDefault)
        private set
    var onBackgroundErrorDisabled by mutableStateOf(onBackgroundErrorDisabled)
        private set
    var onBackgroundErrorSubdued by mutableStateOf(onBackgroundErrorSubdued)
        private set
    var onBackgroundWarningDefault by mutableStateOf(onBackgroundWarningDefault)
        private set
    var onBackgroundWarningDisabled by mutableStateOf(onBackgroundWarningDisabled)
        private set
    var onBackgroundWarningSubdued by mutableStateOf(onBackgroundWarningSubdued)
        private set
    var backgroundTintNeutralDefault by mutableStateOf(backgroundTintNeutralDefault)
        private set
    var backgroundTintSuccessDefault by mutableStateOf(backgroundTintSuccessDefault)
        private set
    var backgroundTintWarningDefault by mutableStateOf(backgroundTintWarningDefault)
        private set
    var backgroundTintErrorDefault by mutableStateOf(backgroundTintErrorDefault)
        private set
    var onBackgroundTintWarningDefault by mutableStateOf(onBackgroundTintWarningDefault)
        private set
    var onBackgroundTintErrorRest by mutableStateOf(onBackgroundTintErrorRest)
        private set
    var onBackgroundTintNeutralDefault by mutableStateOf(onBackgroundTintNeutralDefault)
        private set
    var onBackgroundTintSuccessDefault by mutableStateOf(onBackgroundTintSuccessDefault)
        private set
    var stateLayerPrimaryPressed by mutableStateOf(stateLayerPrimaryPressed)
        private set
    var backgroundTintPrimaryDefault by mutableStateOf(backgroundTintPrimaryDefault)
        private set
    var stateLayerPrimaryHovered by mutableStateOf(stateLayerPrimaryHovered)
        private set
    var stateLayerNeutralHovered by mutableStateOf(stateLayerNeutralHovered)
        private set
    var stateLayerNeutralPressed by mutableStateOf(stateLayerNeutralPressed)
        private set
    var stateLayerNeutralModalBackground by mutableStateOf(stateLayerNeutralModalBackground)
        private set
    var stateLayerInvertedHovered by mutableStateOf(stateLayerInvertedHovered)
        private set
    var stateLayerInvertedPressed by mutableStateOf(stateLayerInvertedPressed)
        private set
    var background1Neutral by mutableStateOf(background1Neutral)
        private set
    var background2Neutral by mutableStateOf(background2Neutral)
        private set
    var background2Primary by mutableStateOf(background2Primary)
        private set
    var background3Neutral by mutableStateOf(background3Neutral)
        private set
    var onBackgroundSuccessSubdued by mutableStateOf(onBackgroundSuccessSubdued)
        private set
    var onBackgroundSuccessDisabled by mutableStateOf(onBackgroundSuccessDisabled)
        private set
    var onBackgroundSuccessDefault by mutableStateOf(onBackgroundSuccessDefault)
        private set
    var onBackgroundNeutralDefault by mutableStateOf(onBackgroundNeutralDefault)
        private set
    var onBackgroundNeutralSubdued by mutableStateOf(onBackgroundNeutralSubdued)
        private set
    var onBackgroundNeutralActive by mutableStateOf(onBackgroundNeutralActive)
        private set
    var onBackgroundNeutralCTA by mutableStateOf(onBackgroundNeutralCTA)
        private set
    var onBackgroundNeutralDisabled by mutableStateOf(onBackgroundNeutralDisabled)
        private set
    var foregroundErrorDefault by mutableStateOf(foregroundErrorDefault)
        private set
    var foregroundWarningDefault by mutableStateOf(foregroundWarningDefault)
        private set
    var iconColor by mutableStateOf(iconColor)
        private set
    var iconBackgroundColor by mutableStateOf(iconBackgroundColor)
        private set

    var isLight by mutableStateOf(isLight)
        private set

    fun copy(
        onForegroundNeutralDefault: Color = this.onForegroundNeutralDefault,
        onForegroundNeutralDisabled: Color = this.onForegroundNeutralDisabled,
        onForegroundNeutralCTA: Color = this.onForegroundNeutralCTA,
        strokeInverted1: Color = this.strokeInverted1,
        strokeInverted2: Color = this.strokeInverted2,
        foregroundNeutralDefault: Color = this.foregroundNeutralDefault,
        foregroundNeutralRest: Color = this.foregroundNeutralRest,
        foregroundNeutralDisabled: Color = this.foregroundNeutralDisabled,
        foregroundPrimaryDefault: Color = this.foregroundPrimaryDefault,
        foregroundSuccessDefault: Color = this.foregroundSuccessDefault,
        strokeNeutral2Default: Color = this.strokeNeutral2Default,
        strokeNeutral2Active: Color = this.strokeNeutral2Active,
        strokeNeutral2Error: Color = this.strokeNeutral2Error,
        strokeNeutral1Default: Color = this.strokeNeutral1Default,
        strokeNeutral3Rest: Color = this.strokeNeutral3Rest,
        onBackgroundTintPrimaryDefault: Color = this.onBackgroundTintPrimaryDefault,
        onBackgroundTintPrimaryDisabled: Color = this.onBackgroundTintPrimaryDisabled,
        onBackgroundPrimaryActive: Color = this.onBackgroundPrimaryActive,
        onBackgroundPrimaryDisabled: Color = this.onBackgroundPrimaryDisabled,
        onBackgroundPrimaryCTA: Color = this.onBackgroundPrimaryCTA,
        onBackgroundPrimaryDefault: Color = this.onBackgroundPrimaryDefault,
        onBackgroundPrimarySubdued: Color = this.onBackgroundPrimarySubdued,
        onBackgroundErrorDefault: Color = this.onBackgroundErrorDefault,
        onBackgroundErrorDisabled: Color = this.onBackgroundErrorDisabled,
        onBackgroundErrorSubdued: Color = this.onBackgroundErrorSubdued,
        onBackgroundWarningDefault: Color = this.onBackgroundWarningDefault,
        onBackgroundWarningDisabled: Color = this.onBackgroundWarningDisabled,
        onBackgroundWarningSubdued: Color = this.onBackgroundWarningSubdued,
        backgroundTintNeutralDefault: Color = this.backgroundTintNeutralDefault,
        backgroundTintSuccessDefault: Color = this.backgroundTintSuccessDefault,
        backgroundTintWarningDefault: Color = this.backgroundTintWarningDefault,
        backgroundTintErrorDefault: Color = this.backgroundTintErrorDefault,
        onBackgroundTintWarningDefault: Color = this.onBackgroundTintWarningDefault,
        onBackgroundTintErrorRest: Color = this.onBackgroundTintErrorRest,
        onBackgroundTintNeutralDefault: Color = this.onBackgroundTintNeutralDefault,
        onBackgroundTintSuccessDefault: Color = this.onBackgroundTintSuccessDefault,
        backgroundTintPrimaryDefault: Color = this.backgroundTintPrimaryDefault,
        stateLayerPrimaryHovered: Color = this.stateLayerPrimaryHovered,
        stateLayerPrimaryPressed: Color = this.stateLayerPrimaryPressed,
        stateLayerNeutralHovered: Color = this.stateLayerNeutralHovered,
        stateLayerNeutralPressed: Color = this.stateLayerNeutralPressed,
        stateLayerNeutralModalBackground: Color = this.stateLayerNeutralModalBackground,
        stateLayerInvertedHovered: Color = this.stateLayerInvertedHovered,
        stateLayerInvertedPressed: Color = this.stateLayerInvertedPressed,
        background1Neutral: Color = this.background1Neutral,
        background2Neutral: Color = this.background2Neutral,
        background2Primary: Color = this.background2Primary,
        background3Neutral: Color = this.background3Neutral,
        onBackgroundSuccessSubdued: Color = this.onBackgroundSuccessSubdued,
        onBackgroundSuccessDisabled: Color = this.onBackgroundSuccessDisabled,
        onBackgroundSuccessDefault: Color = this.onBackgroundSuccessDefault,
        onBackgroundNeutralDefault: Color = this.onBackgroundNeutralDefault,
        onBackgroundNeutralSubdued: Color = this.onBackgroundNeutralSubdued,
        onBackgroundNeutralActive: Color = this.onBackgroundNeutralActive,
        onBackgroundNeutralCTA: Color = this.onBackgroundNeutralCTA,
        onBackgroundNeutralDisabled: Color = this.onBackgroundNeutralDisabled,
        foregroundErrorDefault: Color = this.foregroundErrorDefault,
        foregroundWarningDefault: Color = this.foregroundWarningDefault,
        iconColor: Color = this.iconColor,
        iconBackgroundColor: Color = this.iconBackgroundColor,
        isLight: Boolean = this.isLight,
    ) = CustomColors(
        onForegroundNeutralDefault = onForegroundNeutralDefault,
        onForegroundNeutralDisabled = onForegroundNeutralDisabled,
        onForegroundNeutralCTA = onForegroundNeutralCTA,
        strokeInverted1 = strokeInverted1,
        strokeInverted2 = strokeInverted2,
        foregroundNeutralDefault = foregroundNeutralDefault,
        foregroundNeutralRest= foregroundNeutralRest,
        foregroundNeutralDisabled = foregroundNeutralDisabled,
        foregroundPrimaryDefault = foregroundPrimaryDefault,
        foregroundSuccessDefault = foregroundSuccessDefault,
        strokeNeutral2Default = strokeNeutral2Default,
        strokeNeutral2Active = strokeNeutral2Active,
        strokeNeutral2Error = strokeNeutral2Error,
        strokeNeutral1Default = strokeNeutral1Default,
        strokeNeutral3Rest = strokeNeutral3Rest,
        onBackgroundTintPrimaryDefault = onBackgroundTintPrimaryDefault,
        onBackgroundTintPrimaryDisabled = onBackgroundTintPrimaryDisabled,
        onBackgroundPrimaryActive = onBackgroundPrimaryActive,
        onBackgroundPrimaryDisabled = onBackgroundPrimaryDisabled,
        onBackgroundPrimaryCTA = onBackgroundPrimaryCTA,
        onBackgroundPrimaryDefault = onBackgroundPrimaryDefault,
        onBackgroundPrimarySubdued = onBackgroundPrimarySubdued,
        onBackgroundErrorDefault = onBackgroundErrorDefault,
        onBackgroundErrorDisabled = onBackgroundErrorDisabled,
        onBackgroundErrorSubdued = onBackgroundErrorSubdued,
        onBackgroundWarningDefault = onBackgroundWarningDefault,
        onBackgroundWarningDisabled = onBackgroundWarningDisabled,
        onBackgroundWarningSubdued = onBackgroundWarningSubdued,
        backgroundTintNeutralDefault = backgroundTintNeutralDefault,
        backgroundTintSuccessDefault = backgroundTintSuccessDefault,
        backgroundTintWarningDefault = backgroundTintWarningDefault,
        backgroundTintErrorDefault = backgroundTintErrorDefault,
        onBackgroundTintWarningDefault = onBackgroundTintWarningDefault,
        onBackgroundTintErrorRest = onBackgroundTintErrorRest,
        onBackgroundTintNeutralDefault = onBackgroundTintNeutralDefault,
        onBackgroundTintSuccessDefault = onBackgroundTintSuccessDefault,
        backgroundTintPrimaryDefault = backgroundTintPrimaryDefault,
        stateLayerPrimaryHovered = stateLayerPrimaryHovered,
        stateLayerPrimaryPressed = stateLayerPrimaryPressed,
        stateLayerNeutralHovered = stateLayerNeutralHovered,
        stateLayerNeutralPressed = stateLayerNeutralPressed,
        stateLayerNeutralModalBackground = stateLayerNeutralModalBackground,
        stateLayerInvertedHovered = stateLayerInvertedHovered,
        stateLayerInvertedPressed = stateLayerInvertedPressed,
        background1Neutral = background1Neutral,
        background2Neutral = background2Neutral,
        background2Primary = background2Primary,
        background3Neutral = background3Neutral,
        onBackgroundSuccessSubdued = onBackgroundSuccessSubdued,
        onBackgroundSuccessDisabled = onBackgroundSuccessDisabled,
        onBackgroundSuccessDefault = onBackgroundSuccessDefault,
        onBackgroundNeutralDefault = onBackgroundNeutralDefault,
        onBackgroundNeutralSubdued = onBackgroundNeutralSubdued,
        onBackgroundNeutralActive = onBackgroundNeutralActive,
        onBackgroundNeutralCTA = onBackgroundNeutralCTA,
        onBackgroundNeutralDisabled = onBackgroundNeutralDisabled,
        foregroundErrorDefault = foregroundErrorDefault,
        foregroundWarningDefault = foregroundWarningDefault,
        iconColor = iconColor,
        iconBackgroundColor = iconBackgroundColor,
        isLight = isLight,
    )

    // Will be explain later
    fun updateColorsFrom(other: CustomColors) {
        onForegroundNeutralDefault = other.onForegroundNeutralDefault
        onForegroundNeutralDisabled = other.onForegroundNeutralDisabled
        onForegroundNeutralCTA = other.onForegroundNeutralCTA
        strokeInverted1 = other.strokeInverted1
        strokeInverted2 = other.strokeInverted2
        foregroundNeutralDefault = other.foregroundNeutralDefault
        foregroundNeutralRest = other.foregroundNeutralRest
        foregroundNeutralDisabled = other.foregroundNeutralDisabled
        foregroundPrimaryDefault = other.foregroundPrimaryDefault
        foregroundSuccessDefault = other.foregroundSuccessDefault
        strokeNeutral2Default = other.strokeNeutral2Default
        strokeNeutral2Active = other.strokeNeutral2Active
        strokeNeutral2Error = other.strokeNeutral2Error
        strokeNeutral1Default = other.strokeNeutral1Default
        strokeNeutral3Rest = other.strokeNeutral3Rest
        onBackgroundTintPrimaryDefault = other.onBackgroundTintPrimaryDefault
        onBackgroundTintPrimaryDisabled = other.onBackgroundTintPrimaryDisabled
        onBackgroundPrimaryActive = other.onBackgroundPrimaryActive
        onBackgroundPrimaryDisabled = other.onBackgroundPrimaryDisabled
        onBackgroundPrimaryCTA = other.onBackgroundPrimaryCTA
        onBackgroundPrimaryDefault = other.onBackgroundPrimaryDefault
        onBackgroundPrimarySubdued = other.onBackgroundPrimarySubdued
        onBackgroundErrorDefault = other.onBackgroundErrorDefault
        onBackgroundErrorDisabled = other.onBackgroundErrorDisabled
        onBackgroundErrorSubdued = other.onBackgroundErrorSubdued
        onBackgroundWarningDefault = other.onBackgroundWarningDefault
        onBackgroundWarningDisabled = other.onBackgroundWarningDisabled
        onBackgroundWarningSubdued = other.onBackgroundWarningSubdued
        backgroundTintNeutralDefault = other.backgroundTintNeutralDefault
        backgroundTintSuccessDefault = other.backgroundTintSuccessDefault
        backgroundTintWarningDefault = other.backgroundTintWarningDefault
        backgroundTintErrorDefault = other.backgroundTintErrorDefault
        onBackgroundTintWarningDefault = other.onBackgroundTintWarningDefault
        onBackgroundTintErrorRest = other.onBackgroundTintErrorRest
        onBackgroundTintNeutralDefault = other.onBackgroundTintNeutralDefault
        onBackgroundTintSuccessDefault = other.onBackgroundTintSuccessDefault
        backgroundTintPrimaryDefault = other.backgroundTintPrimaryDefault
        stateLayerPrimaryHovered = other.stateLayerPrimaryHovered
        stateLayerPrimaryPressed = other.stateLayerPrimaryPressed
        stateLayerNeutralHovered = other.stateLayerNeutralHovered
        stateLayerNeutralPressed = other.stateLayerNeutralPressed
        stateLayerNeutralModalBackground = other.stateLayerNeutralModalBackground
        stateLayerInvertedHovered = other.stateLayerInvertedHovered
        stateLayerInvertedPressed = other.stateLayerInvertedPressed
        background1Neutral = other.background1Neutral
        background2Neutral = other.background2Neutral
        background2Primary = other.background2Primary
        background3Neutral = other.background3Neutral
        onBackgroundSuccessSubdued = other.onBackgroundSuccessSubdued
        onBackgroundSuccessDisabled = other.onBackgroundSuccessDisabled
        onBackgroundSuccessDefault = other.onBackgroundSuccessDefault
        onBackgroundNeutralDefault = other.onBackgroundNeutralDefault
        onBackgroundNeutralSubdued = other.onBackgroundNeutralSubdued
        onBackgroundNeutralActive = other.onBackgroundNeutralActive
        onBackgroundNeutralCTA = other.onBackgroundNeutralCTA
        onBackgroundNeutralDisabled = other.onBackgroundNeutralDisabled
        foregroundErrorDefault = other.foregroundErrorDefault
        foregroundWarningDefault = other.foregroundWarningDefault
        iconColor = other.iconColor
        isLight = other.isLight
    }
}


fun lightColors() = CustomColors(
    onForegroundNeutralDefault = Color(0xFFFFFFFF),
    onForegroundNeutralDisabled = Color(0xFF9B9BA1),
    onForegroundNeutralCTA = Color(0xFF90CAF9),
    strokeInverted1 = Color(0xFFFFFFFF),
    strokeInverted2 = Color(0xFFFFFFFF),
    foregroundNeutralDefault = Color(0xFF161618),
    foregroundNeutralRest = Color(0xFFB8B8BC),
    foregroundNeutralDisabled = Color(0xFFE2E2E4),
    foregroundPrimaryDefault = Color(0xFFC11332),
    foregroundSuccessDefault = Color(0xFF388E3C),
    strokeNeutral2Default = Color(0xFFE2E2E4),
    strokeNeutral2Active = Color(0xFF161618),
    strokeNeutral2Error = Color(0xFFB71C1C),
    strokeNeutral1Default = Color(0xFFD4D4D7),
    strokeNeutral3Rest = Color(0x1F000000),
    onBackgroundTintPrimaryDefault = Color(0xFF810D21),
    onBackgroundTintPrimaryDisabled = Color(0xFFE35B73),
    onBackgroundPrimaryActive = Color(0xFF161618),
    onBackgroundPrimaryDisabled = Color(0xFFC6C6C9),
    onBackgroundPrimaryCTA = Color(0xFFC11332),
    onBackgroundPrimaryDefault = Color(0xFF161618),
    onBackgroundPrimarySubdued = Color(0xFF5A5A61),
    onBackgroundErrorDefault = Color(0xFF971111),
    onBackgroundErrorDisabled = Color(0xFFEF9A9A),
    onBackgroundErrorSubdued = Color(0xFFD32F2F),
    onBackgroundWarningDefault = Color(0xFF2A2001),
    onBackgroundWarningDisabled = Color(0xFFFFE083),
    onBackgroundWarningSubdued = Color(0xFFAA8004),
    backgroundTintNeutralDefault = Color(0xFFF0F0F1),
    backgroundTintSuccessDefault = Color(0xFFA5D6A7),
    backgroundTintWarningDefault = Color(0xFFFFE083),
    backgroundTintErrorDefault = Color(0xFFFFCDD2),
    onBackgroundTintWarningDefault = Color(0xFF7F6003),
    onBackgroundTintErrorRest = Color(0xFF971111),
    onBackgroundTintNeutralDefault = Color(0xFF0B0B0C),
    onBackgroundTintSuccessDefault = Color(0xFF1B5E20),
    backgroundTintPrimaryDefault = Color(0xFFF7D0D7),
    stateLayerPrimaryHovered = Color(0xC1112E0F),
    stateLayerPrimaryPressed = Color(0xC1112E1A),
    stateLayerNeutralHovered = Color(0x0D0D0D0A),
    stateLayerNeutralPressed = Color(0x0D0D0D14),
    stateLayerNeutralModalBackground = Color(0x0D0D0D8F),
    stateLayerInvertedHovered = Color(0xFFFFFF14),
    stateLayerInvertedPressed = Color(0xFFFFFF1F),
    background1Neutral = Color(0xFFFFFFFF),
    background2Neutral = Color(0xFFF7F7F8),
    background2Primary = Color(0xFFFBE7EB),
    background3Neutral = Color(0xFFF0F0F1),
    onBackgroundSuccessSubdued = Color(0xFF388E3C),
    onBackgroundSuccessDisabled = Color(0xFFA5D6A7),
    onBackgroundSuccessDefault = Color(0xFF164D1A),
    onBackgroundNeutralDefault = Color(0xFF161618),
    onBackgroundNeutralSubdued = Color(0xFF5A5A61),
    onBackgroundNeutralActive = Color(0xFF161618),
    onBackgroundNeutralCTA = Color(0xFFC11332),
    onBackgroundNeutralDisabled = Color(0xFFC6C6C9),
    foregroundErrorDefault = Color(0xFFD32F2F),
    foregroundWarningDefault = Color(0xFFAA8004),

    iconColor = Color(0xFF00629D),
    iconBackgroundColor = Color(0x4D00629D),
    isLight = true,
)

fun darkColors() = CustomColors(
    onForegroundNeutralDefault = Color(0xFF2D2D30),
    onForegroundNeutralDisabled = Color(0xFF2D2D30),
    onForegroundNeutralCTA = Color(0xFFFFFFFF),
    strokeInverted1 = Color(0xFF2D2D30),
    strokeInverted2 = Color(0xFF2D2D30),
    foregroundNeutralDefault = Color(0xFFB8B8BC),
    foregroundNeutralRest = Color(0xFFB8B8BC),
    foregroundNeutralDisabled = Color(0xFF434349),
    foregroundPrimaryDefault = Color(0xFF550816),
    foregroundSuccessDefault = Color(0xFF144111),
    strokeNeutral2Default = Color(0xFF434349),
    strokeNeutral2Active = Color(0xFF2D2D30),
    strokeNeutral2Error = Color(0xFFE53935),
    strokeNeutral1Default = Color(0xFFCCCCCC),
    strokeNeutral3Rest = Color(0xFF434349),
    onBackgroundTintPrimaryDefault = Color(0xFF550816),
    onBackgroundTintPrimaryDisabled = Color(0xFFFFFFFF),
    onBackgroundPrimaryActive = Color(0xFF550816),
    onBackgroundPrimaryDisabled = Color(0xFF2D2D30),
    onBackgroundPrimaryCTA = Color(0xFF550816),
    onBackgroundPrimaryDefault = Color(0xFFFFFFFF),
    onBackgroundPrimarySubdued = Color(0xFFFFFFFF),
    onBackgroundErrorDefault = Color(0xFFFFFFFF),
    onBackgroundErrorDisabled = Color(0xFFFFFFFF),
    onBackgroundErrorSubdued = Color(0xFFFFFFFF),
    onBackgroundWarningDefault = Color(0xFFFFFFFF),
    onBackgroundWarningDisabled = Color(0xFFFFFFFF),
    onBackgroundWarningSubdued = Color(0xFFFFFFFF),
    backgroundTintNeutralDefault = Color(0xFF2D2D30),
    backgroundTintSuccessDefault = Color(0xFF388E3C),
    backgroundTintWarningDefault = Color(0xFFA13900),
    backgroundTintErrorDefault = Color(0xFF7B0102),
    onBackgroundTintWarningDefault = Color(0xFF732900),
    onBackgroundTintErrorRest = Color(0xFF7B0102),
    onBackgroundTintNeutralDefault = Color(0xFF434349),
    onBackgroundTintSuccessDefault = Color(0xFF388E3C),
    backgroundTintPrimaryDefault = Color(0xFF5A5A61),
    stateLayerPrimaryHovered = Color(0xFFFFFFFF),
    stateLayerPrimaryPressed = Color(0xFFFFFFFF),
    stateLayerNeutralHovered = Color(0xFFFFFFFF),
    stateLayerNeutralPressed = Color(0xFFFFFFFF),
    stateLayerNeutralModalBackground = Color(0xFFFFFFFF),
    stateLayerInvertedHovered = Color(0xFFFFFFFF),
    stateLayerInvertedPressed = Color(0xFFFFFFFF),
    background1Neutral = Color(0xFFFFFFFF),
    background2Neutral = Color(0xFFFFFFFF),
    background2Primary = Color(0xFFFFFFFF),
    background3Neutral = Color(0xFFFFFFFF),
    onBackgroundSuccessSubdued = Color(0xFFFFFFFF),
    onBackgroundSuccessDisabled = Color(0xFFFFFFFF),
    onBackgroundSuccessDefault = Color(0xFFFFFFFF),
    onBackgroundNeutralDefault = Color(0xFFFFFFFF),
    onBackgroundNeutralSubdued = Color(0xFFFFFFFF),
    onBackgroundNeutralActive = Color(0xFF550816),
    onBackgroundNeutralCTA = Color(0xFF550816),
    onBackgroundNeutralDisabled = Color(0xFF2D2D30),
    foregroundErrorDefault = Color(0xFF550816),
    foregroundWarningDefault = Color(0xFF550816),

    iconColor = Color(0xFF00629D),
    iconBackgroundColor = Color(0x4D00629D),
    isLight = false,
)