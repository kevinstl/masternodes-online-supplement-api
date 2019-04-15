#!/bin/bash

branch=$1

if [[ ! -z ${KUBE_ENV} ]]
then
    git config remote.origin.url https://github.com/kevinstl/masternodes-online-supplement-api.git
    git config --global credential.helper store
    jx step git credentials

    ls -al

#    git checkout $branch

    git pull origin $branch

fi


