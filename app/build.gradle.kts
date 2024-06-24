import java.util.Properties

plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	id("com.google.devtools.ksp")
	kotlin("kapt")
	id("com.google.dagger.hilt.android")
}

android {
	namespace = "com.vishal2376.scrollblock"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.vishal2376.scrollblock"
		minSdk = 26
		targetSdk = 34
		versionCode = 2
		versionName = "1.0-beta02"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}

		val keystoreFile = project.rootProject.file("local.properties")
		val properties = Properties()
		properties.load(keystoreFile.inputStream())

		val instagramId = properties.getProperty("INSTAGRAM_ID") ?: ""
		val youtubeId = properties.getProperty("YOUTUBE_ID") ?: ""
		val linkedinId = properties.getProperty("LINKEDIN_ID") ?: ""
		val snapchatId = properties.getProperty("SNAPCHAT_ID") ?: ""

		buildConfigField("String", "INSTAGRAM_ID", instagramId)
		buildConfigField("String", "YOUTUBE_ID", youtubeId)
		buildConfigField("String", "LINKEDIN_ID", linkedinId)
		buildConfigField("String", "SNAPCHAT_ID", snapchatId)
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
			signingConfig = signingConfigs.getByName("debug")
		}

		debug {
			applicationIdSuffix = ".debug"
			versionNameSuffix = "-debug"
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		compose = true
		buildConfig = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.1"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
	ksp {
		arg(
			"room.schemaLocation",
			"$projectDir/schemas"
		)
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)

	//navigation
	implementation(libs.androidx.navigation.compose)

	//material icons extended
	implementation(libs.androidx.material.icons.extended)

	//room
	implementation(libs.androidx.room.runtime)
	annotationProcessor(libs.androidx.room.compiler)
	implementation(libs.androidx.room.ktx)
	ksp(libs.androidx.room.compiler)

	//hilt
	implementation(libs.hilt.android)
	kapt(libs.hilt.android.compiler)

	//data store
	implementation(libs.androidx.datastore.preferences)

	//acra - crash reports
	implementation(libs.acra.mail)
	implementation(libs.acra.dialog)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}