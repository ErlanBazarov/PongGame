E.B.T

## Getting Started
The project is made using VScode IDE. To recreate same environment: 

- Install javafx sdk from https://gluonhq.com/products/javafx/
- Reference libraries from lib folder of downloaded sdk (using vscode)
- Add configuration file in vscode. `Run > Add Configuration`, then press `Java`
- After line 17 in `.vscode/launch.json` add `"vmArgs": "--module-path [path-to-your-lib-folder] --add-modules javafx.controls,javafx.fxml",`
  - For example `"vmArgs": "--module-path C:/Users/erlan.bazarov_2023/Desktop/CS/OOP/openjfx-11.0.2_windows-x64_bin-sdk/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml",`

