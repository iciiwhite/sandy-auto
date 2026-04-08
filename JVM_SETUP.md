# JVM Desktop Setup – Sandy Auto

This guide explains how to build and run Sandy Auto on desktop operating systems: **Windows**, **macOS**, and **Linux**. The JVM desktop application uses `java.awt.Robot` for mouse and keyboard simulation, giving the AI direct control over your system. Because desktop automation requires low‑level input permissions, some platforms need additional configuration.

---

## Prerequisites

- **Java Development Kit (JDK) 17 or higher**  
  Verify with: `java -version` and `javac -version`
- **Gradle** (the project includes the Gradle Wrapper, so no manual installation is required)
- **Rust and Cargo** (only if you intend to modify the native Rust library; pre‑compiled binaries are included)

---

## Step 1: Clone and Enter the Project

```bash
git clone https://github.com/iciiwhite/sandy-auto.git
cd sandy-auto
```

---

Step 2: Build the Desktop Application

Use the Gradle wrapper to build the distributable application:

```bash
./gradlew :desktopApp:package
```

This will create platform‑specific distributions inside:

```
desktopApp/build/compose/binaries/main/
```

You will find subdirectories like deb, rpm, msi, dmg, or an executable JAR depending on the target OS.

To run the application directly without packaging:

```bash
./gradlew :desktopApp:run
```

---

Step 3: Platform‑Specific Permissions

Sandy Auto needs permission to simulate mouse movements, clicks, and keyboard input. How to grant this permission varies by OS.

3.1 Windows

No extra configuration is usually required. When you first run Sandy Auto, Windows may show a UAC prompt asking for permission to allow input simulation. Accept it.

If automation does not work, ensure the application is not running as Administrator – Robot can sometimes fail when elevated. Run as a normal user.

3.2 macOS

macOS requires explicit Accessibility permission for any app that controls the mouse and keyboard.

1. Open System Settings → Privacy & Security → Accessibility.
2. Click the + button and add the Sandy Auto application (the .app bundle from the dmg or the JAR launcher).
3. Ensure the checkbox next to Sandy Auto is enabled.

Note: If you are running from the terminal using ./gradlew :desktopApp:run, you must grant accessibility permission to your terminal emulator (e.g., Terminal.app, iTerm2) instead. For packaged applications, grant it to the .app bundle.

3.3 Linux

On Linux, java.awt.Robot requires access to /dev/uinput or the X11/Wayland input subsystem.

Option A: Run with sudo (Quick Test)

```bash
sudo ./gradlew :desktopApp:run
```

This grants full access but is not recommended for regular use.

Option B: Configure uinput (Recommended)

1. Ensure the uinput kernel module is loaded:
   ```bash
   sudo modprobe uinput
   ```
2. Add your user to the input group:
   ```bash
   sudo usermod -a -G input $USER
   ```
3. Log out and back in, or start a new session with newgrp input.
4. Create a udev rule to give the input group write access to /dev/uinput:
   ```bash
   echo 'KERNEL=="uinput", MODE="0660", GROUP="input"' | sudo tee /etc/udev/rules.d/99-uinput.rules
   sudo udevadm control --reload-rules
   sudo udevadm trigger
   ```

After these steps, you can run Sandy Auto without sudo.

Note for Wayland users: java.awt.Robot may have limited functionality under Wayland. For full automation, consider switching to an X11 session or using a compatibility layer like xdotool via external scripts.

---

Step 4: Configure AI Provider

1. Launch Sandy Auto (either via Gradle run task or the installed application).
2. Navigate to Settings.
3. Choose your AI provider (Gemini, Mistral, Grok, or Claude).
4. Enter your API key.
5. Adjust any other preferences (e.g., screenshot interval, bot verbosity).

API keys are stored securely using the operating system's credential manager (Windows Credential Manager, macOS Keychain, or libsecret on Linux).

---

Step 5: Test Automation

1. Create a project on the Projects tab.
2. Add a bot and assign an AI model.
3. Return to the Dashboard and click Start Automation.
4. Sandy Auto will begin analysing the screen and performing actions. A small overlay window may appear to indicate that automation is active.

You can pause or stop the automation at any time from the dashboard.

---

Troubleshooting

Symptom Likely Cause Solution
Mouse doesn't move / clicks don't work Missing accessibility permission Follow Step 3 for your OS
Application launches but screen capture is blank Wayland compositor (Linux) Switch to X11 or use a virtual display
High CPU usage Screenshot interval too short Increase the interval in Settings
"Could not initialize class java.awt.Robot" Running headless (no display) Ensure a graphical session is available
API key not saved Credential manager not available Install libsecret on Linux, or use environment variables (see below)

Environment Variable Fallback

If the OS credential manager fails, you can set API keys via environment variables:

```bash
export SANDY_GEMINI_KEY="your-key"
export SANDY_MISTRAL_KEY="your-key"
export SANDY_GROK_KEY="your-key"
export SANDY_CLAUDE_KEY="your-key"
./gradlew :desktopApp:run
```

---

Building a Native Installer

To create a .msi (Windows), .dmg (macOS), or .deb/.rpm (Linux) package:

```bash
./gradlew :desktopApp:packageReleaseDistributionForCurrentOS
```

The output will be in desktopApp/build/compose/binaries/main-release/.

---

Next Steps

· Read **[ANDROID SETUP](ANDROID_SETUP.md)** to deploy on Android.
· Read **[IOS SETUP](iOS_SETUP.md)** for iOS configuration.

For issues or contributions, visit the GitHub repository.
