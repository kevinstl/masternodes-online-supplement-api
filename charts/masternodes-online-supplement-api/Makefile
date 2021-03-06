CHART_REPO := http://jenkins-x-chartmuseum:8080
CURRENT=$(pwd)
NAME := masternodes-online-supplement-api
OS := $(shell uname)
RELEASE_VERSION := $(shell cat ../../VERSION)

build: clean
	rm -rf requirements.lock
	helm dependency build
	helm lint

install: clean build
	helm install . --name ${NAME}

upgrade: clean build
	helm upgrade ${NAME} .

delete:
	helm delete --purge ${NAME}

clean:
	rm -rf charts
	rm -rf ${NAME}*.tgz

release: clean
	helm dependency build
	helm lint
	helm init --client-only
	helm package .
	curl --fail -u $(CHARTMUSEUM_CREDS_USR):$(CHARTMUSEUM_CREDS_PSW) --data-binary "@$(NAME)-$(shell sed -n 's/^version: //p' Chart.yaml).tgz" $(CHART_REPO)/api/charts
	rm -rf ${NAME}*.tgz%

tag:
echo debug1
ifeq ($(OS),Darwin)
    echo debug2
	sed -i "" -e "s/version:.*/version: $(RELEASE_VERSION)/" Chart.yaml
	sed -i "" -e "s/tag:.*/tag: $(RELEASE_VERSION)/" values.yaml
else ifeq ($(OS),Linux)
    echo debug3
	sed -i -e "s/version:.*/version: $(RELEASE_VERSION)/" Chart.yaml
	sed -i -e "s|repository:.*|repository: $(DOCKER_REGISTRY)\/kevinstl\/masternodes-online-supplement-api|" values.yaml
	sed -i -e "s/tag:.*/tag: $(RELEASE_VERSION)/" values.yaml
else
    echo debug4
	echo "platfrom $(OS) not supported to release from"
	exit -1
endif
    echo debug5
	git add --all
	git commit -m "release $(RELEASE_VERSION)" --allow-empty # if first release then no verion update is performed
	git tag -fa v$(RELEASE_VERSION) -m "Release version $(RELEASE_VERSION)"
	git push origin v$(RELEASE_VERSION)
