package co.edu.unbosque.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("themebean")   // así lo invocas en el XHTML con #{themeBean}
@SessionScoped
public class ThemeBean implements Serializable {

    private boolean darkMode = false;

    public boolean isDarkMode() {
        return darkMode;
    }

    public void toggle() {
        this.darkMode = !this.darkMode;
    }

    public String getToggleLabel() {
        return darkMode ? "Modo Claro" : "Modo Oscuro";
    }

    public String getBodyClass() {
        return darkMode ? "dark-mode" : "";
    }
}
