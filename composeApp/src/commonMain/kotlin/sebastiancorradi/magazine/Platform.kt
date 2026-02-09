package sebastiancorradi.magazine

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform