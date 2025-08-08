# 🤝 Contributing to MediMate

Thank you for your interest in contributing to **MediMate**!  
We welcome contributions from everyone — whether you’re fixing a bug, adding a new feature, improving documentation, or enhancing tests.

---

## 📌 How to Contribute

### 1. Fork the Repository
Click the **Fork** button at the top right of the repository page to create your own copy.

### 2. Clone Your Fork
```bash
git clone https://github.com/<your-username>/HMS_PRJCT.git
cd HMS_PRJCT
```

### 3. Create a New Branch
Use a descriptive branch name:
```bash
git checkout -b feature/your-feature-name
```

Examples:
- `feature/appointment-reminders`
- `bugfix/fix-billing-calculation`
- `docs/update-readme`

### 4. Make Your Changes
Follow the project structure and naming conventions. Keep your commits small and focused.

### 5. Test Your Changes
Before committing, ensure your changes work and do not break existing functionality.

### 6. Commit and Push
```bash
git add .
git commit -m "Brief description of changes"
git push origin feature/your-feature-name
```

### 7. Submit a Pull Request (PR)
- Go to your fork on GitHub.
- Click **Compare & pull request**.
- Provide a clear title and description of your changes.
- Reference related issues using `#issue-number`.

---

## 🛠 Code Guidelines

- **Language & Framework**: Java 8+, Spring Boot
- Follow existing package/module structure.
- Keep functions and classes focused on a single responsibility.
- Use meaningful commit messages.
- Write comments where necessary, but keep the code self-explanatory.

---

## 📂 Project Structure Reference

```
HMS_PRJCT
├── src/main/java/com/Major/Project
│   ├── Appointment       # Appointment booking and scheduling
│   ├── Billing           # Billing module
│   ├── Configuration     # App configs and security
│   ├── Doctor            # Doctor management
│   ├── Inventory         # Medical stock
│   ├── Laboratory        # Lab test logic
│   ├── Patient           # Patient records
│   ├── Pharmacy          # Medicine management
│   ├── Reporting         # Reports and dashboards
│   ├── Security          # JWT & Spring Security
│   └── Staff             # HR management
└── resources
```

---

## 🧪 Testing

- Use **JUnit** for unit tests.
- Ensure tests pass before submitting a PR.
- Add tests for any new functionality.

---

## 📜 License

By contributing, you agree that your contributions will be licensed under the [**MIT License**](LICENSE).

---

## 🙏 Acknowledgements

We appreciate all contributions, whether big or small.  
Your help makes **MediMate** better for everyone! 🚀
