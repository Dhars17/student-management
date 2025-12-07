Student Management (simple JDBC console app)

Overview
- Small Java console app to manage students (add, list, update, delete) using MySQL.
- Source files live in `src/`. Compiled classes go to `bin/` in the examples below.

Preconditions
- MySQL server is running and accessible.
- Database `student_db` exists (or run `db/schema.sql`).
- The MySQL JDBC driver (Connector/J) is present. This repo already contains `lib/mysql-connector-j-9.5.0.jar`.

How to create the DB/table (if needed)
- Open your MySQL client and run `db/schema.sql`:
  - Example (on machine with mysql client):
    USE mysql; -- or connect as your root/user
    SOURCE db/schema.sql;

Compile
- From PowerShell (run from repository root):
```powershell
mkdir -Force "c:\Users\Dharshini Senthil\OneDrive\Desktop\student-management\bin"
javac -d "c:\Users\Dharshini Senthil\OneDrive\Desktop\student-management\bin" "c:\Users\Dharshini Senthil\OneDrive\Desktop\student-management\src\model\Student.java" "c:\Users\Dharshini Senthil\OneDrive\Desktop\student-management\src\dao\StudentDAO.java" "c:\Users\Dharshini Senthil\OneDrive\Desktop\student-management\src\db\DBConnection.java" "c:\Users\Dharshini Senthil\OneDrive\Desktop\student-management\src\Main.java"
```

Run (with bundled connector jar)
```powershell
java -cp "c:\Users\Dharshini Senthil\OneDrive\Desktop\student-management\bin;c:\Users\Dharshini Senthil\OneDrive\Desktop\student-management\lib\mysql-connector-j-9.5.0.jar" Main
```

If you use a different connector jar path, replace the path after the semicolon.

Notes
- DB credentials are in `src/db/DBConnection.java` (user `root` and the password that was in the file). Change to environment variables or a config file for production.
- If you prefer a build tool, I can convert this to Maven/Gradle to manage dependencies automatically.

Next steps I can do for you
- Convert to Maven and add Connector/J as a dependency.
- Add simple JUnit tests and a CI workflow.
- Add input validation and nicer CLI formatting.

