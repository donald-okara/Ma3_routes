## Brand & Style

This design system is built for the rapid, high-stakes environment of Nairobi’s informal transit network. The brand personality is **Reliable, Efficient, and Locally Grounded**. It moves away from the chaotic visual noise often associated with public transport, providing a calm, hyper-legible interface that functions perfectly under low-light conditions and night-time navigation.

The design style is **Minimalist with High-Contrast Boldness**, optimized for a **Dark Mode** environment. It prioritizes utility and speed of recognition. By using a restricted but vibrant palette against deep surfaces, the interface ensures that commuters can identify their route and stage at a glance while walking through high-traffic areas. The aesthetic is "utility-first," stripping away decorative elements in favor of structural clarity and high-performance interactions.

## Colors

The palette is optimized for outdoor legibility and immediate recognition, specifically tailored for a dark interface.

- **Primary (Matatu Yellow):** A vibrant amber used for key actions, route numbers, and active states. It cuts through the dark background with maximum luminance.
- **Secondary (Deep Charcoal):** Used for primary UI scaffolding and background layers. It provides the necessary weight to ground the vibrant primary color.
- **Tertiary (Transit Blue):** Reserved specifically for walking paths, GPS location markers, and connectivity indicators to differentiate "human" movement from "vehicle" movement.
- **Surface & Backgrounds:** Deep grays and blacks reduce eye strain and maintain a professional, modern feel.

Success, Warning, and Error states should use standard semantic colors (Green/Red) but must maintain the high-contrast ratio established by the primary palette.

## Typography

This design system utilizes **Inter** for its exceptional legibility and systematic feel. The type hierarchy is intentionally "top-heavy" to emphasize route numbers and destination names.

- **Display Route:** Used for the large, iconic route numbers (e.g., "111", "46"). It uses the heaviest weight and negative letter spacing for maximum impact.
- **Headlines:** Used for stage names and primary navigation headers.
- **Body:** Standardized for distances, fare estimates, and instructional text.
- **Labels:** Small, all-caps styling for metadata like "NEXT STAGE" or "EST. TIME," ensuring they are distinct from primary information.

Inter is stored in `:core:resources` and exposed through `ke.don.ma3routes.core.resources.Resources`. UI code should consume those IDs instead of owning font files directly; app launcher assets remain in the app resource set.

## Layout & Spacing

The layout follows a **Fluid Grid** model designed primarily for one-handed mobile use. 

- **Edge Margins:** A consistent 16px margin on the left and right of the screen ensures content doesn't bleed into the physical edges of the device.
- **Vertical Rhythm:** A 4px baseline grid is used to maintain tight, organized stacks of information. 
- **Touch Targets:** All interactive elements (buttons, list items, search bars) must maintain a minimum height of 48px to accommodate fast-paced interactions.
- **Information Density:** High density is permitted for "Stage Lists," while "Navigation View" uses increased whitespace (24px+ stacks) to minimize cognitive load during movement.

## Elevation & Depth

In this dark mode environment, depth is communicated through **Tonal Layering**.

- **Level 0 (Background):** Deepest surface (#121212).
- **Level 1 (Cards/Containers):** Slightly lighter surface tones with a subtle 1px border.
- **Level 2 (Active/Floating):** Use a primary color "glow" (very low opacity primary color shadow) or a crisp 2px stroke in the primary color to indicate focus.
- **Sheet Overlays:** Bottom sheets used for route details use a 40% black scrim on the background to focus attention on the drawer.

## Shapes

The shape language is **Rounded (0.5rem / 8px)**. This provides a modern, clean look that feels systematic and friendly, balancing the starkness of the dark mode palette.

- **Standard Buttons & Inputs:** 8px radius.
- **Route Badges:** 8px radius, creating a distinct "tag" like appearance.
- **Bottom Sheets:** Only the top-left and top-right corners are rounded (16px) to suggest they are anchored to the bottom of the device.

## Components

### Search & Input
- **Search Bar:** Large, 56px height, using a dark surface fill with a prominent search icon. Text should use `body-lg`.
- **Input Fields:** Clear labels using `label-bold` placed above the field.

### Route Cards
- **Structure:** Large Route Number on the left in a Matatu Yellow box. Destination and via-points on the right.
- **Visuals:** Use high-contrast light text for destinations. Use `label-bold` for "VIA" points to save space.

### Stage List Items
- **Walking Indicators:** A vertical dashed line (Transit Blue) connecting stages to indicate walking paths.
- **Distance Tags:** Small, secondary-colored pills with white text for "5 min walk" or "200m."

### Buttons
- **Primary:** Matatu Yellow background with Black text for maximum visibility.
- **Secondary:** Transparent background with a 2px Matatu Yellow or White border.

### Focused Navigation View
- **Current Instruction:** Top-aligned card with high-contrast `headline-lg`.
- **Progress Bar:** A vertical timeline style view showing the current position relative to the destination.

```agsl
name: Matatu Route Helper
colors:
  surface: '#111415'
  surface-dim: '#111415'
  surface-bright: '#37393b'
  surface-container-lowest: '#0c0e10'
  surface-container-low: '#1a1c1d'
  surface-container: '#1e2021'
  surface-container-high: '#282a2c'
  surface-container-highest: '#333537'
  on-surface: '#e2e2e4'
  on-surface-variant: '#d4c5ab'
  inverse-surface: '#e2e2e4'
  inverse-on-surface: '#2f3132'
  outline: '#9c8f78'
  outline-variant: '#504532'
  surface-tint: '#fbbc00'
  primary: '#ffe2ab'
  on-primary: '#402d00'
  primary-container: '#ffbf00'
  on-primary-container: '#6d5000'
  inverse-primary: '#795900'
  secondary: '#c8c6c5'
  on-secondary: '#313030'
  secondary-container: '#4a4949'
  on-secondary-container: '#bab8b7'
  tertiary: '#dce5ff'
  on-tertiary: '#002e69'
  tertiary-container: '#b1c9ff'
  on-tertiary-container: '#0051ad'
  error: '#ffb4ab'
  on-error: '#690005'
  error-container: '#93000a'
  on-error-container: '#ffdad6'
  primary-fixed: '#ffdfa0'
  primary-fixed-dim: '#fbbc00'
  on-primary-fixed: '#261a00'
  on-primary-fixed-variant: '#5c4300'
  secondary-fixed: '#e5e2e1'
  secondary-fixed-dim: '#c8c6c5'
  on-secondary-fixed: '#1c1b1b'
  on-secondary-fixed-variant: '#474646'
  tertiary-fixed: '#d8e2ff'
  tertiary-fixed-dim: '#adc6ff'
  on-tertiary-fixed: '#001a41'
  on-tertiary-fixed-variant: '#004493'
  background: '#111415'
  on-background: '#e2e2e4'
  surface-variant: '#333537'
typography:
  display-route:
    fontFamily: Inter
    fontSize: 48px
    fontWeight: '800'
    lineHeight: 48px
    letterSpacing: -0.04em
  headline-lg:
    fontFamily: Inter
    fontSize: 24px
    fontWeight: '700'
    lineHeight: 32px
    letterSpacing: -0.02em
  headline-lg-mobile:
    fontFamily: Inter
    fontSize: 20px
    fontWeight: '700'
    lineHeight: 28px
  headline-md:
    fontFamily: Inter
    fontSize: 18px
    fontWeight: '600'
    lineHeight: 24px
  body-lg:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  label-bold:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '700'
    lineHeight: 16px
    letterSpacing: 0.05em
  label-sm:
    fontFamily: Inter
    fontSize: 11px
    fontWeight: '500'
    lineHeight: 14px
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  unit: 4px
  edge-margin: 16px
  gutter: 12px
  stack-sm: 8px
  stack-md: 16px
  stack-lg: 24px
```
