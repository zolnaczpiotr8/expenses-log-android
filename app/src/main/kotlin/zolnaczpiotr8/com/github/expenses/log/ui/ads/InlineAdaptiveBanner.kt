package zolnaczpiotr8.com.github.expenses.log.ui.ads

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

@Composable
fun InlineAdaptiveBanner(
    modifier: Modifier = Modifier,
) {
  val context = LocalContext.current
  val adView = remember { AdView(context) }
  val adVisibilityState = remember { mutableStateOf(false) }
  DisposableEffect(Unit) {
    adView.adUnitId =
        "ca-app-pub-3940256099942544/9214589741" // TODO: change it to real id before releasing the
    // app
    adView.setAdSize(
        AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(
            context,
            AdSize.FULL_WIDTH,
        ),
    )
    val request = AdRequest.Builder().build()
    adView.adListener =
        object : AdListener() {
          override fun onAdFailedToLoad(error: LoadAdError) {
            adVisibilityState.value = false
          }

          override fun onAdLoaded() {
            adVisibilityState.value = true
          }
        }
    adView.loadAd(request)

    onDispose { adView.destroy() }
  }
  LifecycleResumeEffect(Unit) {
    adView.resume()
    onPauseOrDispose { adView.pause() }
  }
  AnimatedVisibility(
      visible = adVisibilityState.value,
      modifier = modifier,
  ) {
    AndroidView(
        factory = { adView },
    )
  }
}
