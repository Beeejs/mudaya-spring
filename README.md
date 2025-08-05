# 🚚 MudaYa

**MudaYa** es una aplicación de escritorio para empresas de logística.  
Permite gestionar de forma centralizada:

- 📝 **Cotizaciones de clientes**
- 👥 **Clientes**
- 🚛 **Mudanzas**
- 🚐 **Vehículos**
- 👷 **Transportistas (conductores)**

---

## ⚙️ Tecnologías utilizadas

- **Java**
- **JavaFX**
- **PostgreSQL** (base de datos)

---

## 🔑 Consideraciones importantes

- ✅ El **sistema crea por defecto un usuario administrador** (las credenciales se ponen en el archivo `.env` → email y contraseña).
---



## 🛠 Requisitos para correr el proyecto

- **Java 17 o superior**
- **JavaFX** (tenés que descargar JavaFX y configurarlo en tu IDE):
  - Cuando corras la app, agregá en los parámetros de VM algo como:
    ```
    --module-path /ruta/a/javafx/lib --add-modules javafx.controls,javafx.fxml
    ```
    (reemplazá `/ruta/a/javafx/lib` por la ruta real donde tengas las libs de JavaFX).

- **PostgreSQL**:
  - La base de datos debe estar creada.
  - Configurá correctamente el archivo `.env` con los datos de conexión a la base.
---
