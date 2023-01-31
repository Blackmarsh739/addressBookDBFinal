import org.jetbrains.exposed.sql.Database

data class AppContext(
    val db: Database
) {
}