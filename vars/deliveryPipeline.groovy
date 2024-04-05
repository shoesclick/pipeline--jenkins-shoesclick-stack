import com.shoesclick.pipeline.languages.LanguageTools
import com.shoesclick.pipeline.languages.NpmTools
import com.shoesclick.pipeline.languages.MavenTools
import com.shoesclick.pipeline.languages.PythonTools
import com.shoesclick.pipeline.languages.GoTools
import com.shoesclick.pipeline.steps.GitHubTools
import com.shoesclick.pipeline.steps.DockerTools
import com.shoesclick.pipeline.steps.SonarTools
import com.shoesclick.pipeline.steps.KubernatesTools
import com.shoesclick.pipeline.model.Parameters
import com.shoesclick.pipeline.strategy.WinCmd

def call(body) {

    def params = [:]

    def sysCmd = new WinCmd(this)

    def gitHubTools = new GitHubTools(sysCmd)
    def dockerTools = new DockerTools(sysCmd)
    def sonarTools = new SonarTools(sysCmd)
    def kubernatesTools = new KubernatesTools(sysCmd)

    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = params
    body()

    if (!params.package_manager) {
        currentBuild.result = 'FAILURE'
        error('deliveryPipeline => Missing required param: package_manager\n')
    }

    node {

        gitHubTools.checkoutSCM()

        def parameterModel = new Parameters(
                env.SONAR_SERVER_URL,
                env.SONAR_SCANNER_PATH,
                'sqp_5a70f9ae87771a7fe959d1df084fb8e45a8b4b68',
                env.JOB_NAME,
                env.WORKSPACE,
                env.DCK_ACCOUNT_ID,
                env.DCK_REPOSITORY,
                gitHubTools.getRevision()
        )

        println("BRANCH: ${this.scm.branches}")
        println("PARAMETERS: ${parameterModel.dckAccountId} , ${parameterModel.dckRepository}, ${parameterModel.projectName} , ${parameterModel.workspaceJob}, ${parameterModel.tagHash}")
        println("GITHASH: ${parameterModel.tagHash}")

        switch (params.package_manager){
            case "npm":
                def npmTools = new NpmTools(sysCmd)
                npmTools.install()
                npmTools.test()
                break

            case "maven":
                def mavenTools = new MavenTools(sysCmd)
                mavenTools.install()
                mavenTools.test()
                break

            case "pip":
                def pythonTools = new PythonTools(sysCmd)
                pythonTools.install()
                pythonTools.test()
                break

            case "go":
                def goTools = new GoTools(sysCmd)
                goTools.install()
                goTools.test()
                break

            default:
                currentBuild.result = 'FAILURE'
                error('deliveryPipeline => Invalid value for param: package_manager\nValue: ' + params.package_manager)

        }

       // sonarTools.scanProject(parameterModel)
        dockerTools.build(parameterModel)
        kubernatesTools.deployEKS(parameterModel)
        dockerTools.removeImagesDocker(parameterModel)

    }


}
