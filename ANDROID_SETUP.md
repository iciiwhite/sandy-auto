# Android Setup – Sandy Auto

This guide covers everything needed to build, install, and configure Sandy Auto on an Android device or emulator. Because Sandy Auto requires system‑level automation capabilities, you must manually enable the Accessibility Service and grant overlay permission after installation.

---

## Prerequisites

- **Android Studio** (Hedgehog | 2023.1.1 or newer) with:
  - Android SDK Platform 34
  - NDK (if modifying Rust code)
  - JDK 17 configured in Android Studio
- An Android device running **Android 7.0 (API 24) or higher** (physical device recommended for testing automation)
- **USB debugging** enabled on the device (Settings → Developer options → USB debugging)

---

## Step 1: Clone and Open the Project

```bash
git clone https://github.com/iciiwhite/sandy-auto.git
cd sandy-auto
```

Launch Android Studio, select Open an Existing Project, and choose the sandy-auto root folder. Gradle sync will start automatically.

---

Step 2: Build the APK

You can build the debug APK directly from the terminal:

```bash
./gradlew :androidApp:assembleDebug
```

The output APK will be located at:

```
androidApp/build/outputs/apk/debug/androidApp-debug.apk
```

To build a release APK, use assembleRelease and sign it with your keystore (see Android Studio signing configuration).

---

Step 3: Install on Device

With USB debugging enabled, install via ADB:

```bash
adb install -r androidApp/build/outputs/apk/debug/androidApp-debug.apk
```

Alternatively, drag and drop the APK onto the device if using an emulator.

---

Step 4: Grant Required Permissions

Sandy Auto needs two special permissions to interact with the UI of other apps. These must be granted manually by the user (cannot be granted programmatically).

4.1 Overlay Permission (SYSTEM_ALERT_WINDOW)

This allows Sandy Auto to draw a small control overlay (e.g., a floating button) so you can start/stop automation without leaving the current app.

1. Open Settings → Apps → Sandy Auto.
2. Tap Advanced or Special app access.
3. Select Display over other apps.
4. Toggle Allow display over other apps to ON.

Note: On some devices this is found under "App permissions" → "Other permissions".

4.2 Accessibility Service

The Accessibility Service is the core of Sandy Auto’s automation. It gives the app the ability to read screen content, perform clicks, and type text.

1. Open Settings → Accessibility (or Accessibility → Installed services).
2. Find Sandy Auto in the list and tap it.
3. Toggle the switch to ON.
4. Confirm the security dialog that appears (this warns that the app will be able to view and control your screen).

Important: The service must remain enabled at all times for automation to work. Some devices may automatically disable accessibility services after system updates – check this setting if automation suddenly stops.

---

Step 5: Configure AI Provider (Inside the App)

1. Launch Sandy Auto from the app drawer.
2. Navigate to the Settings tab.
3. Select your preferred AI Provider (Gemini, Mistral, Grok, or Claude).
4. Enter your API key in the corresponding field.
5. Optionally adjust the automation interval and bot personality settings.

API keys are stored encrypted using Android's EncryptedSharedPreferences.

---

Step 6: Test Automation

1. On the Projects tab, create a new project (e.g., "Daily Report").
2. Add a bot to the project and assign it an AI model.
3. Return to the Dashboard and tap Start Automation.
4. The floating overlay button should appear. You can now minimise Sandy Auto and let the bot run in the background.

To see what the bot is doing, expand the notification shade – Sandy Auto displays a persistent notification while automation is active (this is required by Android to keep the accessibility service alive).

---

Troubleshooting

Issue Likely Cause Solution
"Accessibility Service not enabled" message Service was disabled or not granted Re‑enable it in Settings → Accessibility
Floating button not showing Overlay permission missing Enable "Display over other apps" (Step 4.1)
Bot doesn't click in the right place Screen density or orientation changed Restart the automation session; the bot recalibrates on each start
App crashes on startup Missing Google Play Services (for Gemini SDK) Install Google Play Services on emulator, or use a different AI provider
API key rejected Key format incorrect or expired Double‑check the key from the provider console; ensure no extra spaces

---

Building from Source (Advanced)

If you modify the shared Kotlin code or the Rust library, you can rebuild the project with:

```bash
./gradlew :shared:build
./gradlew :androidApp:assembleDebug
```

The Rust library is precompiled for common architectures (arm64-v8a, armeabi-v7a, x86_64). To rebuild the native library yourself, install Rust and run:

```bash
cd rust_lib
cargo build --release
```

The Gradle build automatically picks up the compiled .so files.

---

Next Steps

· Read **[JVM SETUP](JVM_SETUP.md)** to run Sandy Auto on Windows, macOS, or Linux.
· Read **[IOS SETUP](iOS_SETUP.md)** for iOS configuration.

For feature requests or bug reports, please open an issue on GitHub.
