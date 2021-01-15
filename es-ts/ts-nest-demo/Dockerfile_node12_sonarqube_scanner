FROM node:12.16.1-alpine

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories

ENV SONAR_SCANNER_VERSION=4.3.0.2102
ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk/jre
ENV PATH $PATH:/sonar-scanner-${SONAR_SCANNER_VERSION}-linux/bin:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin

RUN set -x \
    && apk add --no-cache unzip openjdk8-jre \
    && wget -O sonar-scanner-cli.zip https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-${SONAR_SCANNER_VERSION}-linux.zip \
    && unzip sonar-scanner-cli.zip \
    && rm sonar-scanner-cli.zip \
    && sed -i 's/use_embedded_jre=true/use_embedded_jre=false/g' /sonar-scanner-${SONAR_SCANNER_VERSION}-linux/bin/sonar-scanner
