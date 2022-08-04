package fr.bsm.location.infrastructure.config;

public final class SpringProfileConstants {

  public static final String LOCAL = "local";

  public static final String MOCK_GEOCODING = "mock-geocoding";

  public static final String UNMOCK_GEOCODING = "!" + MOCK_GEOCODING;

  private SpringProfileConstants() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }
}
