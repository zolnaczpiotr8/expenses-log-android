# Expenses Log

A **fast, privacy-first, offline expense tracker for Android**, built with a strong focus on
**architecture, performance, accessibility, and long-term maintainability**.

<p align="center">
  <a href="https://play.google.com/store/apps/details?id=zolnaczpiotr8.com.github.expenses.log">
    <img alt="Get it on Google Play"
         src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png"
         height="80">
  </a>
</p>

![License](https://img.shields.io/badge/license-MIT-green)
![Platform](https://img.shields.io/badge/platform-Android-brightgreen)
![Privacy](https://img.shields.io/badge/privacy-100%25%20offline-blue)

---

## ✨ Overview

**Expenses Log** is a lightweight Android application for tracking personal expenses — designed to work
**fully offline**, with **no ads**, **no analytics**, and **no data collection**.

While intentionally simple for users, the app is carefully engineered to demonstrate
**modern, production-ready Android development practices**.

- **APK size:** ~1.5 MiB
- **Translations:** 80+ of the most widely spoken languages
- **Performance:** Optimized for low-end and older devices
- **Accessibility:** Screen reader, large text, contrast, keyboard support
- **Privacy:** All data stored locally on the device

---

## Architecture

The application architecture follows the official **Google Android Architecture Guidelines**
and is built around **unidirectional data flow (UDF)**.

- Single source of truth for application state
- Clear separation of UI, state, and business logic
- Predictable state transitions driven by explicit events
- No hidden side effects in UI components

This approach improves testability, simplifies reasoning about state, and enables safe,
long-term evolution of the codebase.

Reference: https://developer.android.com/topic/architecture

---

## UI & Design System

The user interface is implemented in accordance with **Material Design 3** guidelines:

- Consistent typography, color, and spacing system
- Clear visual hierarchy and interaction patterns
- Dynamic theming support
- UI behavior aligned with platform expectations

Reference: https://m3.material.io/

---

## ♿ Accessibility

Accessibility is treated as a **first-class requirement** and the app is designed in accordance
with **WCAG (Web Content Accessibility Guidelines)** principles.

- Full TalkBack support
- Semantic UI components
- Keyboard and non-touch navigation
- High-contrast compatibility
- Layouts resilient to large font and display scaling

Reference: https://www.w3.org/WAI/standards-guidelines/wcag/

---

## ⚡ Performance & Optimization

- Baseline Profiles for faster cold startup
- Macrobenchmark tests to detect performance regressions
- Optimized rendering paths and minimal allocations
- Smooth performance even on very limited hardware

---

## Testing Strategy

The project implements a **comprehensive testing pyramid**:

- **Unit tests** — business logic and data layers
- **UI tests** — screen behavior and user interactions
- **End-to-end tests** — real-world usage scenarios
- **Screenshot tests** — UI consistency across devices and locales
- **Benchmark tests** — performance characteristics

All tests are deterministic, reproducible, and CI-ready.

---

## Internationalization

- Translated into **80+ languages**
- RTL layout support
- Locale-aware formatting
- Layouts tested against extreme text expansion

---

## Privacy Policy

Expenses Log is a privacy-first application:
- No data collection
- No network access
- No ads or trackers

The full privacy policy is available here:  
https://zolnaczpiotr8.github.io/expenses-log-privacy-policy/

---

## Tech Stack

- Kotlin
- Android SDK
- Jetpack libraries
- Material Design 3
- AndroidX Test
- Macrobenchmark & Baseline Profiles

---

## Availability

Available on **Google Play**:  
https://play.google.com/store/apps/details?id=zolnaczpiotr8.com.github.expenses.log

---

## Purpose of This Repository

This repository serves both as:
1. A useful, privacy-respecting Android application
2. A public showcase of **professional Android engineering practices**

It reflects my approach to:
- architecture aligned with platform guidelines
- unidirectional data flow
- performance-focused development
- comprehensive testing
- accessibility and privacy by default

---

## License

This project is licensed under the **MIT License**.  
See the [LICENSE](LICENSE) file for details.
