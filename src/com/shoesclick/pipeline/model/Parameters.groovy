package com.shoesclick.pipeline.model;

class Parameters{

    def sonarServerUrl
    def sonarScannerPath
    def sonarToken
    def projectName
    def workspaceJob
    def awsAccountId
    def awsEksRegion
    def awsEcrRepository
    def tagHash

    Parameters(sonarServerUrl, sonarScannerPath, sonarToken,projectName,workspaceJob,awsAccountId,awsEksRegion,awsEcrRepository,tagHash){
        this.sonarServerUrl = sonarServerUrl;
        this.sonarScannerPath = sonarScannerPath;
        this.sonarToken = sonarToken;
        this.projectName = projectName;
        this.workspaceJob = workspaceJob;
        this.awsAccountId = awsAccountId;
        this.awsEksRegion = awsEksRegion;
        this.awsEcrRepository = awsEcrRepository;
        this.projectName = projectName;
        this.tagHash = tagHash;
    }

}



