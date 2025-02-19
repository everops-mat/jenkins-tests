default:
	@echo "make <target>"
	@echo "  where target is one of:"
	@echo "    build-jenkins-contorller"
	@echo "    run-jenkins-contorller"

build-jenkins-controller:
	@docker build -t jenkins:test -f Dockerfile .

run-jenkins-controller:
	@docker run -d --name jenkins -p 8081:8080 -p 50000:50000 jenkins:test

stop-jenkins-controller:
	@docker stop jenkins

clean:
	@docker stop jenkins
	@docker rm jenkins

