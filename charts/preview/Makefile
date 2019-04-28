OS := $(shell uname)

preview: 
ifeq ($(OS),Darwin)
	sed -i "" -e "s/version:.*/version: $(PREVIEW_VERSION)/" Chart.yaml
	sed -i "" -e "s/version:.*/version: $(PREVIEW_VERSION)/" ../*/Chart.yaml
	sed -i "" -e "s/tag:.*/tag: $(PREVIEW_VERSION)/" values.yaml
else ifeq ($(OS),Linux)
	sed -i -e "s/version:.*/version: $(PREVIEW_VERSION)/" Chart.yaml
	sed -i -e "s/version:.*/version: $(PREVIEW_VERSION)/" ../*/Chart.yaml
<<<<<<< HEAD
	sed -i -e "s|repository:.*|repository: $(DOCKER_REGISTRY)\/kevinstl\/jx-jhipster-microservice-template|" values.yaml
=======
	sed -i -e "s|repository:.*|repository: $(DOCKER_REGISTRY)\/kevinstl\/masternodes-online-supplement-api|" values.yaml
>>>>>>> 0284cc9f6f3785d76bb0b3ca255ac2b96b28461d
	sed -i -e "s/tag:.*/tag: $(PREVIEW_VERSION)/" values.yaml
else
	echo "platfrom $(OS) not supported to release from"
	exit -1
endif
	echo "  version: $(PREVIEW_VERSION)" >> requirements.yaml
	jx step helm build
