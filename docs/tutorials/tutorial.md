# **Tutorial: Android Assignment - Irfan Ullah**

Welcome to the tutorial for setting up and exploring the **Android Assignment** project. This project demonstrates the use of **Retrofit** for REST API integration and **Kotlin Jetpack components** to build a scalable and maintainable Android application.

---

## **Prerequisites**

Before you start, ensure the following are installed on your system:

- **Android Studio** (latest version)
- **Java Development Kit (JDK)**
- A Git client for version control

---

## **Step 1: Clone the Repository**

Clone the repository from GitHub to your local system:
```bash
git clone https://github.com/Irfan-Ullah-cs/Android-Jetpack-Retrofit.git
````
Navigate to the project directory:

```bash
cd Android-Jetpack-Retrofit
```
## Steps to Run and Explore the Project

### Step 2: Open the Project in Android Studio
1. Open Android Studio.
2. Click **File > Open** and navigate to the cloned project folder.
3. Select the folder and click **OK**.

### Step 3: Sync Gradle
Gradle sync may start automatically. If it doesn’t:
1. Go to **File > Sync Project with Gradle Files**.
2. Ensure that all dependencies resolve successfully.

### Step 4: Run the Application
1. Connect an Android device or start an emulator in Android Studio.
2. Click on the **Run** button (green triangle) in the toolbar or press `Shift + F10`.
3. The app will compile and launch on your device/emulator.

---

## Project Features

### AUTOMAcorp.kt Functionality
The **AUTOMAcorp.kt** file is a key part of the application with the following features:

#### Room Input and Navigation
- Includes an **input field** where users can enter the name of a room.
- After clicking **Open**, the app navigates to the **Room Activity**.
- The Room Activity displays:
    - **Room Name**
    - **Current Temperature**
    - **Set Temperature**, which the user can adjust dynamically.

#### Top Bar Buttons
The top bar in the Room Activity includes the following buttons:
1. **Back Button**: Navigates back to the previous screen.
2. **Show All Rooms**: Displays a list of all available rooms.
3. **Mail Button**: Opens an email interface, allowing users to directly contact the developer.
4. **GitHub Profile Button**: Links to the developer's GitHub profile for further exploration.

## Project Features

### Data Fetching
- The application uses **Retrofit** to fetch real-time data from a REST API and display it dynamically in the UI.

### Architecture
- **Kotlin Jetpack Components**: The app is designed using `ViewModel` and `LiveData` for lifecycle-aware and responsive UI operations.
- **Scalable Design**: The modular architecture ensures the app can be expanded easily in the future.

### Project Structure
- `Services/`: Contains API configuration, data models, and Retrofit setup.
- `ui/`: Manages UI components and ViewModels.
- `AUTOMAcorp.kt`: Acts as the entry point for the application.

---

## Contributing to the Project

### Fork and Clone the Repository
1. Fork the repository by clicking the **Fork** button on GitHub.
2. Clone your forked repository:
   ```bash
   git clone https://github.com/yourusername/your-forked-repository.git
   cd your-forked-repository
    ```
### Make Changes and Open a Pull Request
### Make the necessary changes in the code.
Commit your changes:
```bash
git add .
git commit -m "Describe your changes"
```
### Push to your forked repository:
```bash
git push origin main
```

## License
This project is licensed under the MIT License. Feel free to use, modify, and distribute the code as per the license terms.