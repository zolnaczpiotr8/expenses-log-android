package zolnaczpiotr8.com.github.expenses.log.app.ui

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import java.time.Duration
import java.time.Instant

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setUpSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ExpensesLogUi()
        }
    }

    private fun setUpSplashScreen() {
        installSplashScreen()
        setUpOnExitAnimation()
    }

    private fun setUpOnExitAnimation() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            return
        }
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.height.toFloat(),
            )
            slideUp.interpolator = AnticipateInterpolator()
            val animationDuration = splashScreenView.iconAnimationDuration
            val animationStart = splashScreenView.iconAnimationStart
            slideUp.duration = if (animationDuration != null && animationStart != null) {
                (animationDuration - Duration.between(animationStart, Instant.now()))
                    .toMillis()
                    .coerceAtLeast(0L)
            } else {
                0L
            }
            slideUp.doOnEnd {
                splashScreenView.remove()
            }
            slideUp.start()
        }
    }
}
