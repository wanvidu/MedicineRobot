 
FROM gitpod/workspace-full-vnc

USER root

RUN add-apt-repository ppa:maarten-fonville/android-studio && \
    apt-get update && \
    apt-get install libc6 libncurses5 libstdc++6 lib32z1 libbz2-1.0 \
        android-studio --no-install-recommends --yes \
        && apt-get clean \
        && rm -rf /var/lib/apt/lists/*
