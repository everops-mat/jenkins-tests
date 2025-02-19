FROM jenkins/jenkins:lts

USER root

# Install dependencies (Git and Job DSL Plugin)
RUN apt-get update && apt-get install -y git \
    && rm -rf /var/lib/apt/lists/*

# Switch back to Jenkins user
USER jenkins

# Install Job DSL Plugin
RUN jenkins-plugin-cli --plugins job-dsl git configuration-as-code \
workflow-job workflow-cps

# Copy JCasC configuration files
COPY casc.yaml /var/jenkins_home/casc.yaml

# Ensure Jenkins starts with JCasC
ENV CASC_JENKINS_CONFIG=/var/jenkins_home/casc.yaml

# Set up a seed job
# COPY files/seed.groovy /usr/share/jenkins/ref/init.groovy.d/seed-job.groovy

# ENTRYPOINT ["/sbin/tini", "--", "/usr/local/bin/jenkins.sh"]
