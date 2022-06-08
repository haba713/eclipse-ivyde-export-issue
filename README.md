# Eclipse IvyDE managed libraries not exported

## Issue description

IvyDE managed libraries are not exported to runnable JAR file.

## Environment

- Eclipse 2022-06 M2 (4.24.0 M2)
- IvyDE 2.2.0.final-201311091524-RELEASE
- OpenJDK 17.0.3+7-Debian-1deb11u1
- Debian GNU/Linux 11 (Bullseye)

## Steps for reproducing the issue

1. Clone [the project](https://github.com/haba713/eclipse-ivyde-export-issue):
    ```
    git clone git@github.com:haba713/eclipse-ivyde-export-issue.git
    ```
 3. Import the cloned project into Eclipse workspace.
 4. Right-click the class `src/com.example/Main.java` in the project tree and
   choose Run as → Java Application. See the output: `{"key":"value"}`.
 5. Right-click the project and choose Export → Java → Runnable JAR file.
 6. Choose the launch configuration for the class `com.example.Main` and
   Library handling: [x] Extract required libraries into generated JAR. Click
   Finish.
 7. Try to run the generated JAR file:
    ```
    $ java -jar eclipse-ivyde-export-issue.jar
    Exception in thread "main" java.lang.NoClassDefFoundError: org/json/JSONObject
      at com.example.Main.main(Main.java:8)
    Caused by: java.lang.ClassNotFoundException: org.json.JSONObject
      at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
      at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188)
      at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:520)
      ... 1 more
    ```

The class `org.json.JSONObject` and other JSON classes are not included in the
JAR file even though the library is in the [Ivy dependencies](ivy.xml).
