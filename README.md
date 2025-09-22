# SMB Maps Launcher

An Android launcher application similar to Apple CarPlay/Android Auto designed for head display units.

## Features

- **Split-screen interface**: 
  - Left panel: Picture-in-Picture (PIP) display for selected applications
  - Right panel: Grid of available applications for selection
- **CarPlay/Android Auto inspired design**: Dark theme with rounded corners and modern UI elements
- **Full-screen launcher**: Designed to replace the default Android launcher for head units
- **Application management**: Automatically detects and displays installed applications

## Architecture

### Main Components

1. **MainActivity.kt**: The main launcher activity that handles the split-screen interface
2. **AppGridAdapter.kt**: RecyclerView adapter for displaying applications in a grid
3. **AppInfo.kt**: Data model for application information
4. **Layout files**: 
   - `activity_main.xml`: Split-screen layout with PIP and apps grid
   - `item_app.xml`: Individual app item layout for the grid

### Key Features Implementation

- **Application Detection**: Uses PackageManager to query all launchable applications
- **PIP Simulation**: Displays selected app icon and name with launch options
- **Full-screen UI**: Hides system navigation and status bars for immersive experience
- **Landscape Orientation**: Optimized for head display units in landscape mode

## Installation

1. Clone this repository
2. Open in Android Studio
3. Build and install on your Android head display unit
4. Set as default launcher in Android settings

## Usage

1. Launch the app (it will become your default launcher)
2. Browse available applications in the right panel
3. Tap an app to select it for PIP display in the left panel
4. Use "Launch" to open the app normally or "PIP Mode" for background display

## Technical Notes

- Requires Android API 24+ (Android 7.0)
- Designed for landscape orientation
- Uses Material Design components with custom CarPlay-inspired styling
- Implements proper launcher intent filters to replace system launcher

## Future Enhancements

- True Picture-in-Picture mode implementation (requires system-level permissions)
- Widget support in PIP area
- Voice command integration
- Custom app shortcuts and favorites
- Integration with car systems (OBD-II, GPS, etc.)

## Screenshots

The launcher features a modern, dark interface similar to automotive infotainment systems, with smooth transitions and touch-friendly controls optimized for use while driving.

## Project Structure

```
app/
├── src/main/
│   ├── java/com/smbmaps/launcher/
│   │   ├── MainActivity.kt              # Main launcher activity
│   │   ├── adapters/
│   │   │   └── AppGridAdapter.kt        # App grid RecyclerView adapter
│   │   └── models/
│   │       └── AppInfo.kt               # App data model
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml        # Split-screen main layout
│   │   │   └── item_app.xml             # App grid item layout
│   │   ├── values/
│   │   │   ├── colors.xml               # CarPlay-inspired color scheme
│   │   │   ├── strings.xml              # App strings
│   │   │   └── themes.xml               # Material Design themes
│   │   └── drawable/
│   │       ├── panel_background.xml     # Panel styling
│   │       └── app_icon_background.xml  # App icon styling
│   └── AndroidManifest.xml              # Launcher configuration
├── build.gradle                         # App dependencies
└── proguard-rules.pro                   # ProGuard configuration
```