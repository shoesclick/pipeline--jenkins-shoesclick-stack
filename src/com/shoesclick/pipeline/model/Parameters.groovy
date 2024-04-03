package com.shoesclick.pipeline.model;

class Parameters{

    def sonarServerUrl
    def sonarScannerPath
    def sonarToken
    def projectName
    def workspaceJob
    def dckAccountId
    def dckRepository
    def tagHash

    Parameters(sonarServerUrl, sonarScannerPath, sonarToken, projectName, workspaceJob, dckAccountId, dckRepository, tagHash){
        this.sonarServerUrl = sonarServerUrl;
        this.sonarScannerPath = sonarScannerPath;
        this.sonarToken = sonarToken;
        this.projectName = projectName;
        this.workspaceJob = workspaceJob;
        this.dckAccountId = dckAccountId;
        this.dckRepository = dckRepository;
        this.projectName = projectName;
        this.tagHash = tagHash;
    }

}



