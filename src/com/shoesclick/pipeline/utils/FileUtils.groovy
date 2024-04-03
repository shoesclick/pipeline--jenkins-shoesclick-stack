package com.shoesclick.pipeline.utils;

import com.shoesclick.pipeline.model.Parameters;

class FileUtils{

    def file

    FileUtils(String filePath){
        this.file = new File(filePath);
    }

    def replaceDeploymentYaml(Parameters model){
        def readContent = this.file.text
                .replaceAll('\\$dck-accountid',"${model.dckAccountId}")
                .replaceAll('\\$dck-repository',"${model.dckRepository}")
                .replaceAll('\\$githash',"${model.tagHash}");

        this.file.text = readContent;
    }

}
