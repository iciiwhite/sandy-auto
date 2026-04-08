# iOS Setup – Sandy Auto

This guide covers building and running Sandy Auto on iPhone and iPad devices, as well as the iOS Simulator. Because iOS has strict sandboxing and does not expose system‑wide accessibility APIs to third‑party apps, Sandy Auto’s automation capabilities on iOS differ from other platforms. The app leverages **on‑device screen analysis** and **voice‑over / shortcut integrations** where possible, but full background automation is limited to what iOS permits.

---

## Prerequisites

- **macOS Ventura 13.0 or later**
- **Xcode 15.0 or later** (available from the Mac App Store)
- **CocoaPods** (installed via `sudo gem install cocoapods`)
- An **Apple Developer account** (free or paid) for signing and device deployment
- An iPhone or iPad running **iOS 16.0 or later** (physical device recommended for testing automation)

---

## Step 1: Clone and Prepare the Project

```bash
git clone https://github.com/iciiwhite/sandy-auto.git
cd sandy-auto
```

1.1 Install CocoaPods Dependencies

The iOS application uses CocoaPods to integrate the shared Kotlin Multiplatform framework.

```bash
cd iosApp
pod install
```

After installation, always open iosApp.xcworkspace (not the .xcodeproj file) in Xcode.

---

Step 2: Build the Shared Kotlin Framework

The shared code is compiled into a .framework that Xcode consumes. Build it using Gradle:

```bash
cd ..
./gradlew :shared:embedAndSignAppleFrameworkForXcode
```

This will generate the shared.framework inside shared/build/xcode-frameworks/.

Note: Xcode may automatically trigger this build step if you have the Kotlin Multiplatform plugin installed. You can also run the Gradle task manually whenever you modify shared code.

---

Step 3: Open and Configure Xcode Project

```bash
open iosApp/iosApp.xcworkspace
```

3.1 Set Development Team

1. In Xcode, select the iosApp project in the navigator.
2. Under Signing & Capabilities, choose your Team from the dropdown.
3. Ensure Bundle Identifier is unique (e.g., com.yourname.sandyauto.ios).

3.2 Adjust Minimum Deployment Target

The project is configured for iOS 14.0 by default. For full automation features, iOS 16.0 is recommended.

---

Step 4: Run on Simulator or Device

· Select a simulator (e.g., iPhone 15 Pro) from the scheme toolbar.
· Press Cmd + R to build and run.

To run on a physical device:

1. Connect your iPhone/iPad via USB.
2. Select it as the run destination.
3. Trust the developer certificate on the device (Settings → General → VPN & Device Management).

---

Step 5: Grant Required Permissions

iOS does not provide a public API for simulating touch events or reading other apps' screens. Instead, Sandy Auto uses screen recording (ReplayKit) to capture what is happening and can direct you to perform actions manually, or integrate with Shortcuts for limited automation.

5.1 Screen Recording Permission

The app uses RPScreenRecorder to capture the screen content for AI analysis. You must grant permission when prompted.

1. Launch Sandy Auto.
2. Navigate to the Dashboard.
3. Tap Start Capture – iOS will show a system dialog.
4. Select Record Screen and confirm.

Important: Screen recording on iOS captures everything, including sensitive information. Ensure you trust the AI provider you are using.

5.2 Shortcuts Integration (Optional)

For actual automation, you can create iOS Shortcuts that Sandy Auto can suggest and trigger.

1. Open the Shortcuts app on your device.
2. Create a new shortcut containing actions you want Sandy Auto to perform (e.g., send a message, open an app).
3. In Sandy Auto's Settings, link the shortcut by name.

Sandy Auto will then be able to run that shortcut as part of an automation workflow (requires user confirmation per execution).

5.3 Accessibility Permissions (Limited)

Sandy Auto cannot act as a system accessibility service on iOS. However, you can enable Voice Control or Switch Control manually via Settings → Accessibility. The app can provide voice commands that you can speak to control the device, but it cannot inject those commands programmatically.

---

Step 6: Configure AI Provider

1. Inside Sandy Auto, go to Settings.
2. Select your preferred AI provider (Gemini, Mistral, Grok, or Claude).
3. Enter your API key.
4. Optionally, set the automation advisor mode (where the app suggests actions but does not execute them).

API keys are stored in the iOS Keychain via the shared SecureStorage implementation.

---

Step 7: Testing Automation on iOS

Given the platform restrictions, Sandy Auto on iOS operates in two modes:

· Advisor Mode – The AI analyses your screen and provides step‑by‑step instructions that you follow manually.
· Shortcut Mode – The AI triggers pre‑defined Shortcuts that perform specific tasks (requires user confirmation).

To test:

1. Create a project in the Projects tab.
2. Add a bot and assign a task description.
3. From the Dashboard, tap Start Advisor.
4. Switch to another app – Sandy Auto will display a persistent notification (iOS banner) with the next suggested action.

For Shortcut mode, ensure your shortcuts are configured and linked. The bot will ask for permission before running each shortcut.

---

Limitations on iOS

Feature Supported Notes
Programmatic clicks/typing ❌ Not allowed by iOS sandbox
Screen reading (OCR) ✅ Via ReplayKit screen recording
Background automation ⚠️ Only advisor mode works in background; shortcuts require foreground confirmation
Cross‑app interaction ❌ Apps cannot control other apps
Accessibility service ❌ No public API equivalent to Android

---

Troubleshooting

Issue Solution
Build fails with "Framework not found" Run ./gradlew :shared:embedAndSignAppleFrameworkForXcode manually
Screen recording dialog does not appear Check that ReplayKit is available (simulator does not support screen recording – use a physical device)
API key not saving Ensure Keychain Sharing is enabled in Xcode capabilities (should be configured by default)
Shortcuts not appearing Verify shortcut names exactly match (case‑sensitive) and that Shortcuts app is installed
App crashes on launch Check that shared.framework is correctly embedded; clean build folder and rebuild

---

Building a Release Archive

To create an .ipa for TestFlight or App Store distribution:

1. In Xcode, select Product → Archive.
2. Once archiving completes, use the Organizer window to validate and distribute.

Remember to increment the build number and version in Info.plist before submission.

---

Next Steps

· Read **[ANDROID SETUP](ANDROID_SETUP.md)** for full automation on Android.
· Read **[JVM SETUP](JVM_SETUP.md)** for desktop automation.

For feature requests, bug reports, or contributions, please open an issue on GitHub.
