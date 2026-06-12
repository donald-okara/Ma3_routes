# Contributing

Thanks for helping improve Ma3 Routes. This project uses small, focused changes and keeps architecture decisions visible in docs.

## Development Flow

1. Pick an issue or create one before starting larger work.
2. Create a branch from the current mainline.
3. Keep changes scoped to one feature, fix, or refactor.
4. Update docs when module ownership, architecture, data contracts, or design-system behavior changes.
5. Open a pull request using the repository template.

## Local Checks

Run these before opening a pull request:

```bash
./gradlew spotlessCheck :app:assembleDebug
```

Use this to apply formatting:

```bash
./gradlew spotlessApply
```

## Architecture Notes

- Feature modules own feature UI and presentation state.
- `:core:ui` owns reusable Compose components and theme implementation.
- `:core:resources` owns cross-module Android resources such as fonts and notification assets.
- Datasource modules own persistence, remote access, orchestration, and sync behavior.
- Hilt setup is centralized in the `ma3.hilt` convention plugin.

## Pull Request Expectations

- Keep PRs readable and reviewable.
- Include tests or explain why the change does not need them.
- Include screenshots for UI changes when practical.
- Do not commit generated build output.
