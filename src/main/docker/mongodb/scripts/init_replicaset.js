var status = rs.status();
if (status.errmsg === 'no replset config has been received') {
    rs.initiate();
}
for (var i = 1; i <= param; i++) {
    if (i!==1)
<<<<<<< HEAD
        rs.add(folder+"_jxjhipstermicroservicetemplate-mongodb-node_" + i + ":27017");
}
cfg = rs.conf();
cfg.members[0].host = folder+"_jxjhipstermicroservicetemplate-mongodb-node_1:27017";
=======
        rs.add(folder+"_MasternodesOnlineSupplementApi-mongodb-node_" + i + ":27017");
}
cfg = rs.conf();
cfg.members[0].host = folder+"_MasternodesOnlineSupplementApi-mongodb-node_1:27017";
>>>>>>> 0284cc9f6f3785d76bb0b3ca255ac2b96b28461d
rs.reconfig(cfg);
