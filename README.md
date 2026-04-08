# Sandy Auto

![sandy](https://raw.githubusercontent.com/iciiwhite/sandy-auto/main/sandy.webp)

Sandy Auto is a cross-platform automation framework that embeds AI agents directly into your devices. It gives Gemini, Mistral, Grok, or Claude the ability to see your screen, interact with applications, and carry out complex tasks on your behalf—without writing a single line of script. The bots run with their own persistent context, manage linked accounts, and operate autonomously while you focus on what matters.

---

## Why Sandy Auto?

Modern AI assistants are powerful, but they are locked inside chat interfaces. Sandy Auto bridges that gap by granting write access to your device environment. Bots can click, type, swipe, read screen content, and make decisions based on real-time context. Whether you need to manage social media, handle repetitive office workflows, or orchestrate multi‑step processes across different apps, Sandy Auto gives your AI agents a life of their own inside your phone, tablet, or desktop.

---

## Key Features

- **Truly Cross‑Platform**  
  Runs natively on Android, iOS, Windows, macOS, and Linux. One codebase, one consistent experience.

- **Multi‑AI Support**  
  Choose from Gemini, Mistral, Grok, or Claude as the decision engine. Switch providers without changing your automation logic.

- **Autonomous Bots with Persistent Memory**  
  Each bot maintains its own state and can evolve behaviour over time. Bots live on the device, not in the cloud.

- **Account & Project Management**  
  Securely link external service accounts (email, social, cloud storage) and organise automation tasks into projects.

- **Full Device Control**  
  Accessibility services on Android, system‑level automation on desktop, and appropriate permissions on iOS allow bots to simulate real user interactions.

- **Rust‑Powered Performance**  
  Core image processing and template matching are offloaded to a Rust library for speed and low resource usage.

- **Secure by Design**  
  Credentials and API keys are encrypted at rest using platform‑specific secure storage (Android Keystore, iOS Keychain, OS‑native credential managers).

---

## Supported AI Providers

| Provider | Model Used | Notes |
|----------|------------|-------|
| Gemini   | gemini‑pro (text) + vision capabilities | Requires Google AI Studio API key |
| Mistral  | mistral‑small / mistral‑medium | Requires Mistral API key |
| Grok     | grok‑1 | Requires xAI API key |
| Claude   | claude‑3‑sonnet / claude‑3‑opus | Requires Anthropic API key |

All providers are abstracted behind a single `AIService` interface, making it trivial to add new models in the future.

---

## Prerequisites

- **Java Development Kit** (JDK) 17 or higher
- **Android Studio** (for Android builds) or Xcode 15+ (for iOS)
- **Rust** and Cargo (only if modifying the native library)
- **API Keys** from at least one supported AI provider

---

## Installation & Setup

The project uses Kotlin Multiplatform with Jetpack Compose for the UI and Gradle as the build system. Platform‑specific setup instructions are provided in separate guides:

- **[ANDROID_SETUP.md](ANDROID_SETUP.md)** – Building, running, and enabling accessibility services on Android.
- **[IOS_SETUP.md](IOS_SETUP.md)** – Configuring the Xcode project, signing, and iOS permissions.
- **[JVM_SETUP.md](JVM_SETUP.md)** – Running on Windows, macOS, and Linux desktops.

For a quick start:

```bash
git clone https://github.com/iciiwhite/sandy-auto.git
cd sandy-auto

# Build and run the desktop version
./gradlew :desktopApp:run

# Build the Android APK
./gradlew :androidApp:assembleDebug
```

After launching the app, navigate to Settings to enter your AI provider API keys and adjust automation preferences.

---

How It Works

1. Capture Context – Sandy Auto grabs a screenshot and metadata about the foreground application.
2. AI Decision – The chosen AI model analyses the screen content and decides the next action (click, type, swipe, wait).
3. Execute – The appropriate platform‑specific automation service performs the action.
4. Loop – Steps repeat continuously, allowing the bot to handle long‑running tasks without intervention.

All actions are queued and can be paused, resumed, or stopped from the main dashboard.

---

Project Structure

```
sandy-auto/
├── composeApp/        # Shared Compose UI across all platforms
├── shared/            # Core business logic, AI clients, repositories
├── androidApp/        # Android application module
├── iosApp/            # Xcode project for iOS
├── desktopApp/        # JVM desktop launcher
├── rust_lib/          # Native Rust library for image processing
└── gradle/            # Wrapper and configuration
```

---

Configuration

Sensitive values (API keys, account tokens) are never hardcoded. They are stored using the SecureStorage abstraction, which leverages:

· Android: EncryptedSharedPreferences
· iOS: Keychain Services
· Desktop: OS credential manager via platform APIs

To set your AI provider key, use the in‑app settings screen or, for advanced users, set an environment variable:

```bash
export SANDY_GEMINI_KEY="your-google-ai-key"
```

---

Contributing

We welcome contributions! Please see CONTRIBUTING.md for guidelines on submitting issues and pull requests. Before working on major changes, open a discussion to align with the project's roadmap.

---

License

This project is licensed under the Apache-2.0 License – see the **[LICENSE](LICENSE)** file for details.

---

Acknowledgements

Built with:

· Kotlin Multiplatform
· Jetpack Compose
· Ktor
· Koin

---

Sandy Auto – Let AI live in your devices.
