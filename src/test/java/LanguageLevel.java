public enum LanguageLevel {
    BEGINNER("beginner"),
    INTERMEDIATE("intermediate"),
    ADVANCED("advanced");

    private final String value;

    LanguageLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}