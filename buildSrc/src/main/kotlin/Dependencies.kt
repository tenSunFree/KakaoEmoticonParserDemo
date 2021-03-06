import org.gradle.api.JavaVersion

object Application {
    const val minSdk = 23
    const val targetSdk = 30
    const val compileSdk = 30
    const val versionCode = 20
    const val jvmTarget = "1.8"
    const val versionName = "aaa"

    val targetCompat = JavaVersion.VERSION_1_8
    val sourceCompat = JavaVersion.VERSION_1_8
}

object Versions {
    object Essential {
        const val Kotlin = "1.4.31"
        const val Gradle = "7.0.0-alpha09"
        const val CoreKtx = "1.3.2"
    }

    object Di {
        const val Dagger = "2.28"
    }

    object Ui {
        const val Lottie = "3.6.1"
        const val LottieCompose = "1.0.0-alpha07-SNAPSHOT"
        const val Glide = "0.6.2"
        const val ConstraintLayout = "1.0.0-alpha04"
    }

    object Util {
        const val AndroidUtil = "5.1.5"
        const val CrashReporter = "1.1.0"
        const val JsonConverter = "2.9.0"
    }

    object Network {
        const val OkHttp = "4.9.1"
        const val Retrofit = "2.9.0"
    }

    object Rx {
        const val RxKotlin = "3.0.1"
        const val RxAndroid = "3.0.0"
        const val RxRetrofit = "2.9.0"
    }

    object Jetpack {
        const val Room = "2.3.0-beta03"
    }

    object Compose {
        const val Version = "1.0.0-beta02"
        const val Navigation = "1.0.0-alpha09"
        const val Activity = "1.3.0-alpha04"
    }
}

object Dependencies {
    val network = listOf(
        "com.squareup.okhttp3:okhttp:${Versions.Network.OkHttp}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OkHttp}",
        "com.squareup.retrofit2:retrofit:${Versions.Network.Retrofit}"
    )

    val rx = listOf(
        "io.reactivex.rxjava3:rxkotlin:${Versions.Rx.RxKotlin}",
        "io.reactivex.rxjava3:rxandroid:${Versions.Rx.RxAndroid}",
        "com.squareup.retrofit2:adapter-rxjava3:${Versions.Rx.RxRetrofit}"
    )

    val essential = listOf(
        "androidx.core:core-ktx:${Versions.Essential.CoreKtx}",
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Essential.Kotlin}"
    )

    val di = listOf(
        "com.google.dagger:dagger:${Versions.Di.Dagger}"
    )

    val ui = listOf(
        "com.github.skydoves:landscapist-coil:1.1.7",
        "com.airbnb.android:lottie:${Versions.Ui.Lottie}",
        "com.google.accompanist:accompanist-glide:${Versions.Ui.Glide}",
        "com.airbnb.android:lottie-compose:${Versions.Ui.LottieCompose}"
        // "androidx.constraintlayout:constraintlayout-compose:${Versions.Ui.ConstraintLayout}"
    )

    val util = listOf(
        "com.github.sungbin5304:AndroidUtils:${Versions.Util.AndroidUtil}",
        "com.balsikandar.android:crashreporter:${Versions.Util.CrashReporter}",
        "com.squareup.retrofit2:converter-gson:${Versions.Util.JsonConverter}"
    )

    var room = listOf(
        "androidx.room:room-runtime:${Versions.Jetpack.Room}",
        "androidx.room:room-ktx:${Versions.Jetpack.Room}"
    )

    var compose = listOf(
        "androidx.navigation:navigation-compose:${Versions.Compose.Navigation}",
        "androidx.activity:activity-compose:${Versions.Compose.Activity}",
        "androidx.compose.ui:ui:${Versions.Compose.Version}",
        "androidx.compose.material:material:${Versions.Compose.Version}",
        "androidx.compose.material:material-icons-extended:${Versions.Compose.Version}",
        "androidx.compose.ui:ui-tooling:${Versions.Compose.Version}"
    )

    val compiler = listOf(
        "com.google.dagger:dagger-compiler:${Versions.Di.Dagger}",
        "androidx.room:room-compiler:${Versions.Jetpack.Room}"
    )
}
