package ke.don.ma3routes.core.domain.util

fun String.getInitials(): String {
    return this.split(" ")
        .filter { it.isNotBlank() }
        .mapNotNull { it.firstOrNull()?.uppercaseChar() }
        .joinToString("")
}
