## Subject Line:   
- **Verbs:** This section should begin with a verb in the imperative mood. Here are some common options:
    - **Add:** Use this to indicate adding **new features or functionalities**.   
    - **Fix:** Use this for **bug fixes** and **resolving issues**.
    - **Update:** Use this for changes that **modify existing functionality or code**.
    - **Refactor:** Use this for code restructuring or improvements **without changing functionality**.
    - **Remove:** Use this to indicate the **removal of features, code, or files**.
    - **Chore:** Use this for **non-code related changes** like documentation updates, dependency updates, etc.
- **Description:** Briefly describe the change you made. This should be clear and concise, focusing on the what rather than the how.
    - Examples:
        - "Fix: login form validation error"
        - "Add: support for dark mode"
        - "Chore: dependency to version X.Y.Z"
## Body (Optional):   
- The body section allows you to elaborate on the changes you made in the commit.
- Provide more details to explain the context and purpose of the changes.
    - Examples:
        - "Fixed a bug that caused the login form to fail when using a specific character in the password field."
        - "Added a new configuration option to enable dark mode for the user interface."
        - "Updated the dependency from version 2.1.0 to 2.2.1 to address a security vulnerability."
- **Use this section to explain the "why" behind the changes**.
    - This helps reviewers understand the rationale and potential impact.
## Footer (Optional):
- The footer section is **used for specific references and additional information**.
- Include references to related issues using keywords like "Fixes" or "Closes" followed by the issue number (e.g., "Fixes #123").
- Mention any breaking changes introduced in the commit. Breaking changes are changes that could potentially cause issues in existing code.
- Briefly describe the breaking change and any potential mitigation steps.
## Examples:
```
Add: support for dark mode

Added a new configuration option (`enableDarkMode`) to allow users to switch the application interface to dark mode.
This improves accessibility and user experience for users who prefer a darker theme.
```
```
Fix: login form validation error

Fixed a bug that caused the login form to fail validation when a special character was used in the password field.
This issue prevented successful user logins.

Closes #101 (Login form validation issue)
```
```
Update: improve login process security

Implemented secure password hashing using bcrypt to enhance login security.
This replaces the previous less secure storage method.
Added rate limiting for login attempts to prevent brute-force attacks.
```
```
Refactor: improve code readability in user model

Refactored the `User` model class to improve code readability and maintainability.
Extracted logic into separate helper functions and renamed variables for better clarity.
This change does not affect existing functionality.
```
```
Remove: deprecated search functionality

Removed the deprecated search functionality due to low usage and potential performance overhead.
This simplifies the codebase and improves application performance.
```
```
Chore: update lodash dependency to v4.17.21

Updated the lodash dependency from version 4.17.15 to version 4.17.21 to address a security vulnerability (CVE-2024-12345).
This update ensures a more secure application environment.

```
   
