mongo mongodb://minikube-easy:30017

mongoexport --db MasternodesOnlineSupplementApi --collection masternodes_online_supplement --out export-4-23-2017.json


mongoimport -h minikube-easy:30017 -d MasternodesOnlineSupplementApi -c MasternodesOnlineSupplement -u ${MONGO_PROD_TEST_USER} -p ${MONGO_PROD_TEST_PASS} --file jhi_user.json



mongoimport -h minikube-easy:30017 -d masternodes-online-supplement -c masternodes_online_supplement --file export-4-23-2017.json
