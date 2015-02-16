SUMMARY = "MagOS image for development"

require magos-image-prod.bb

IMAGE_FEATURES += "dev-pkgs tools-debug tools-profile tools-testapps debug-tweaks"

IMAGE_INSTALL += "kernel-dev packagegroup-magos-buildtools"

